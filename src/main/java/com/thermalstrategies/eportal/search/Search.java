/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.search;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.model.Dampertest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author Stuart
 */
public class Search {

    public Search(ApplicationManager applicationManager) throws Exception {

        List<Dampertest> damperTestList = applicationManager.getTstratDamperTemplate().find("from Dampertest");
        createSearchIndex(damperTestList, 0);

        List<Customer> customerList = applicationManager.getCustomerManager().getCustomerListWithNoSecurity();
        for (Customer customer : customerList) {
            Integer customerId = customer.getId();
            damperTestList = applicationManager.getTstratDamperTemplate().find("from Dampertest dt where dt.customer.id=" + customerId);
            createSearchIndex(damperTestList, customerId);
        }
    }

    public List<Results> search(String query, int hitsPerPage, Integer customerId, Integer page) throws Exception {
        query = URLDecoder.decode(query);
        List<Results> resultList = new ArrayList<Results>();
        String[] splits = query.split(" ");
        StringBuilder sb = new StringBuilder();
        int size = 0;
        Set<?> stopWordSet = analyzer.STOP_WORDS_SET;

        for (String s : splits) {
            if (!stopWordSet.contains(s.toLowerCase())) {
                try {
                    // Treat numbers with wildcards not fuzzy proximity

                    Integer.parseInt(s);
                    s = s + "*";
                } catch (Exception e) {
                    // Otherwise treat words with fuzzy proximity
                    s = s + "~";
                }
                if (size++ != 0) {
                    sb.append(" AND ");
                }
                sb.append(s.trim());
            }


        }

        System.out.println("quering -> " + sb.toString());
//        Query q = new QueryParser(
//                Version.LUCENE_CURRENT, "title", analyzer).parse(query);

        Query q = new MultiFieldQueryParser(version, fields, analyzer).parse(sb.toString());

        // 3. search
        IndexSearcher searcher = new IndexSearcher(indexMap.get(customerId), true);
        TopScoreDocCollector collector =
                TopScoreDocCollector.create(hitsPerPage * (page+1), true);

        searcher.search(q, collector);
        Integer totalHits = collector.getTotalHits();
        ScoreDoc[] hits = collector.topDocs().scoreDocs;

        // 4. display results
        Double pagesInTotal = Double.valueOf(totalHits + "")/Double.valueOf(hitsPerPage + "");
        System.out.println("pages in total->" + Math.floor(pagesInTotal));
        List<Integer> totalPageList = new ArrayList<Integer>();
        for (int i = 0; i < Math.floor(pagesInTotal) + 1; i++) {
            totalPageList.add(i);
        }

        int offset = page * hitsPerPage;
        int count = Math.min(totalHits - offset, hitsPerPage);
//        System.out.println("hitsPerPage " + hitsPerPage);
//        System.out.println("offset " + offset);
//        System.out.println("count " + count);
//        System.out.println("Found " + count + " hits.");
        for (int i = 0; i < count ; ++i) {
            Results results = new Results();
            int index = offset + i;
            int docId = hits[index].doc;
            float score = hits[index].score;
            Document d = searcher.doc(docId);
            results.setDocId(docId);
            results.setScore(score);
            results.setTitle(d.get("title"));
            results.setDescription(d.get("description"));
            results.setLink(d.get("link"));
            results.setVisibleHits(hits.length);
            results.setTotalHits(totalHits);
            results.setTotalPageList(totalPageList);
            results.setCurrentPage(page);
            results.setPageSize(hitsPerPage);


//            System.out.println((i + 1) + ". " + score + " " + d.get("aliasId") + " : " + d.get("title") + " : " + d.get("description"));

            resultList.add(results);
        }

        // searcher can only be closed when there
        // is no need to access the documents any more.
        searcher.close();
        return resultList;
    }
    public Map<Integer, Directory> indexMap = new HashMap<Integer, Directory>();
    public static final Version version = Version.LUCENE_30;
    public static final String[] fields = {
        "id",
        "aliasId",
        "title",
        "description",
        "damperType",
        "damperTypeName",
        "buildingName",
        "buildingAliasId",
        "floor",
        "location",
        "subLocation",
        "link",
        "customerName",
        "system",
        "comments",
        "status",
        "occupancy"};
//    private DampertestAnalyzer analyzer = new DampertestAnalyzer(version);
    private StandardAnalyzer analyzer = new StandardAnalyzer(version);

    private void createSearchIndex(List<Dampertest> damperTestList, Integer customerId) throws IOException {

        Directory index = new RAMDirectory();
        indexMap.put(customerId, index);

        // the boolean arg in the IndexWriter ctor means to
        // create a new index, overwriting any existing index
        IndexWriter w = new IndexWriter(index, analyzer, true,
                IndexWriter.MaxFieldLength.UNLIMITED);
        for (Dampertest dt : damperTestList) {
            String id = dt.getId().toString();
            String link = "editDamper.htm?id=" + id;
//            String building = dt.getBuilding() != null ? dt.getBuilding().getBuildingName() : "";
            String typeAliasId = dt.getDampertype() != null ? dt.getDampertype().getAliasId() : "";
            String typeName = dt.getDampertype() != null ? dt.getDampertype().getTypeName() : "";
            String buildingName = dt.getBuilding() != null ? dt.getBuilding().getBuildingName() : "";
            String buildingAliasId = dt.getBuilding() != null ? dt.getBuilding().getAliasId() : "";
            String floor = dt.getBuildingfloor() != null ? dt.getBuildingfloor().getFloorName() : "";
            String location = dt.getLocation();
            String subLocation = dt.getSublocation();
            String customerName = dt.getCustomer() != null ? dt.getCustomer().getCustomerName() : "";
            String system = dt.getSystem();
            String comments = dt.getComments();
            String status = dt.getDamperstatus() != null ? dt.getDamperstatus().getStatus() : "";
            String subStatus = dt.getDamperstatus() != null ? dt.getDamperstatus().getSubstatus() : "";
            if ("PASS".equalsIgnoreCase(status)) {
                subStatus = "<span style=\"color:green\">" + subStatus.toUpperCase() + "</span>";
            } else if ("FAIL".equalsIgnoreCase(status)) {
                subStatus = "<span style=\"color:red\">" + subStatus.toUpperCase() + "</span>";
            } else {
                subStatus = "<span style=\"color:blue\">" + subStatus.toUpperCase() + "</span>";
            }

            String occupancy = dt.getOccupancy();

            String description = "<strong>" + dt.getAliasId() + "<br>" + subStatus + "<br>" + buildingName + "</strong>(" + customerName + ")<strong>FLOOR</strong>(" + floor + ") <strong>System</strong>(" + system + ") <br><strong>Location</strong>:" + location + "<br><strong>Sublocation</strong>:" + subLocation + "<br><strong>Comments</strong>:" + comments;

//            descriptionBuilder.append(id)

            Document doc = new Document();

            doc.add(new Field("id", id, Field.Store.YES, Field.Index.NO));
            doc.add(new Field("link", link, Field.Store.YES, Field.Index.NO));
            doc.add(new Field("aliasId", dt.getAliasId(), Field.Store.NO, Field.Index.ANALYZED));
            doc.add(new Field("status", subStatus, Field.Store.NO, Field.Index.ANALYZED));
            doc.add(new Field("system", system, Field.Store.NO, Field.Index.ANALYZED));
            doc.add(new Field("title", "Damper Database", Field.Store.YES, Field.Index.ANALYZED));
            doc.add(new Field("description", description, Field.Store.YES, Field.Index.NO));
            doc.add(new Field("damperType", typeAliasId, Field.Store.NO, Field.Index.ANALYZED));
            doc.add(new Field("damperTypeName", typeName, Field.Store.NO, Field.Index.ANALYZED));
            doc.add(new Field("buildingName", buildingName, Field.Store.NO, Field.Index.ANALYZED));
            doc.add(new Field("buildingAliasId", buildingAliasId, Field.Store.NO, Field.Index.ANALYZED));
            doc.add(new Field("floor", "floor " + floor, Field.Store.NO, Field.Index.ANALYZED));
            doc.add(new Field("location", location, Field.Store.NO, Field.Index.ANALYZED));
            doc.add(new Field("subLocation", subLocation, Field.Store.NO, Field.Index.ANALYZED));
            doc.add(new Field("customerName", customerName, Field.Store.NO, Field.Index.ANALYZED));
            doc.add(new Field("comments", comments, Field.Store.NO, Field.Index.ANALYZED));
            doc.add(new Field("occupancy", occupancy, Field.Store.NO, Field.Index.ANALYZED));

            w.addDocument(doc);

        }
        w.close();
    }

    public class Results {

        private int docId;
        private float score;
        private String title;
        private String description;
        private String aliasId;
        private String link;
        private Integer visibleHits;
        private Integer totalHits;
        private List<Integer> totalPageList;
        private Integer currentPage;
        private Integer pageSize;

        /**
         * @return the docId
         */
        public int getDocId() {
            return docId;
        }

        /**
         * @param docId the docId to set
         */
        public void setDocId(int docId) {
            this.docId = docId;
        }

        /**
         * @return the score
         */
        public float getScore() {
            return score;
        }

        /**
         * @param score the score to set
         */
        public void setScore(float score) {
            this.score = score;
        }

        /**
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * @param title the title to set
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * @param description the description to set
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * @return the aliasId
         */
        public String getAliasId() {
            return aliasId;
        }

        /**
         * @param aliasId the aliasId to set
         */
        public void setAliasId(String aliasId) {
            this.aliasId = aliasId;
        }

        /**
         * @return the link
         */
        public String getLink() {
            return link;
        }

        /**
         * @param link the link to set
         */
        public void setLink(String link) {
            this.link = link;
        }

        /**
         * @return the totalHits
         */
        public Integer getTotalHits() {
            return totalHits;
        }

        /**
         * @param totalHits the totalHits to set
         */
        public void setTotalHits(Integer totalHits) {
            this.totalHits = totalHits;
        }

        /**
         * @return the visibleHits
         */
        public Integer getVisibleHits() {
            return visibleHits;
        }

        /**
         * @param visibleHits the visibleHits to set
         */
        public void setVisibleHits(Integer visibleHits) {
            this.visibleHits = visibleHits;
        }

        

        /**
         * @return the currentPage
         */
        public Integer getCurrentPage() {
            return currentPage;
        }

        /**
         * @param currentPage the currentPage to set
         */
        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        /**
         * @return the totalPageList
         */
        public List<Integer> getTotalPageList() {
            return totalPageList;
        }

        /**
         * @param totalPageList the totalPageList to set
         */
        public void setTotalPageList(List<Integer> totalPageList) {
            this.totalPageList = totalPageList;
        }

        /**
         * @return the pageSize
         */
        public Integer getPageSize() {
            return pageSize;
        }

        /**
         * @param pageSize the pageSize to set
         */
        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

    }
}
