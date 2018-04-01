/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import com.thermalstrategies.eportal.controller.CommentHistoryBean;
import com.thermalstrategies.eportal.controller.DamperListBean;
import com.thermalstrategies.eportal.model.Commenthistory;
import com.thermalstrategies.eportal.model.Dampertest;
import com.thermalstrategies.eportal.utils.CustTimer;
import com.thermalstrategies.eportal.utils.CustomDateUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Access the DampertestArchive tables instead of comment history.
 *
 * @author smurry
 */
public class CommentHistoryManager {

    private HibernateTemplate tstratDamperTemplate;
    private JdbcTemplate jdbcTemplate;

    public CommentHistoryManager(HibernateTemplate tstratDamperTemplate, JdbcTemplate jdbcTemplate) throws DataAccessException {
        this.tstratDamperTemplate = tstratDamperTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * *
     * @deprecated Not being used
     * @param id
     */
    public void deleteCommentHistory(Integer id) {
        Commenthistory h = (Commenthistory) tstratDamperTemplate.load(Commenthistory.class, id);
        tstratDamperTemplate.delete(h);
    }

    /**
     * *
     * @deprecated Not being used.
     * @param bean
     */
    public void saveCommentHistory(CommentHistoryBean bean) {
        System.out.println(bean.toString());
        Commenthistory h;
        Integer id = bean.getId();
        if (id != null && id > 0) {
            System.out.println("CommenthistoryId is not null");
            h = (Commenthistory) tstratDamperTemplate.load(Commenthistory.class, id);
        } else {
            System.out.println("CommenthistoryId is null");
            h = new Commenthistory();
            Dampertest dampertest = (Dampertest) tstratDamperTemplate.load(Dampertest.class, bean.getDamperId());
            h.setDampertest(dampertest);
        }

        h.setComments(bean.getComments());

        Date date = CustomDateUtils.convertJasonMoonsStringToDate(bean.getDateTestedTs());
        h.setDateTestedTs(date);

        tstratDamperTemplate.saveOrUpdate(h);

    }

    /**
     * *
     * @deprecated Use getCommentHistoryFromArchive(id)
     * @param id
     * @return
     */
    public CommentHistoryBean getCommentHistory(Integer id) {
        try {
            Commenthistory h = (Commenthistory) tstratDamperTemplate.load(Commenthistory.class, id);
            if (h != null) {
                CommentHistoryBean commentHistoryBean = new CommentHistoryBean();
                //<fmt:formatDate pattern="dd-MMM-yyyy" type="date" value="${command.date}" />
                commentHistoryBean.setDateTestedTs(CustomDateUtils.convertJasonMoonsDateToString(h.getDateTestedTs()));
                commentHistoryBean.setComments(h.getComments());
                commentHistoryBean.setId(h.getId());
                commentHistoryBean.setDamperId(h.getDampertest().getId());

                System.out.println("Getting comment history -> " + commentHistoryBean.toString());
                return commentHistoryBean;
            } else {
                return null;
            }
        } catch (DataAccessException e) {
            return null;
        }

    }

    public List<CommentHistoryBean> getCommentHistoryListByArchive(final Integer damperId) throws DataAccessException {

        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT da.id, da.date_tested_ts, da.comments,ds.substatus,dampertest_id FROM Dampertestarchive da ");
        sql.append(" inner join damperstatus ds on ds.id=da.status_id ");
        sql.append(" where da.dampertest_id=" + damperId);
        return this.jdbcTemplate.query(
                sql.toString(),
                new RowMapper() {

                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

                        CommentHistoryBean comment = new CommentHistoryBean();
                        comment.setId(rs.getInt("id"));
//                        rs.setDate(CustomDateUtils.convertJasonMoonsDateToString((Date) row[1]));
                        if ( rs.getDate("date_tested_ts") != null) {
                            comment.setDateTestedTs(CustomDateUtils.repairHistoryDateformatter(rs.getDate("date_tested_ts")));
                        }

                        comment.setComments(rs.getString("comments"));

                        String status = rs.getString("substatus");
                        System.out.println("Comment Status: " + status);
                        String nStatus ="&lt;Undefined&gt;";

                        if (StringUtils.contains(status, "FAIL"))
                        {
                            nStatus = "<span style=\"color:red\">" + status + "</span>";
                        } else if (StringUtils.contains(status, "INACCESS"))
                        {
                            nStatus = "<span style=\"color:blue\">" + status + "</span>";
                        } else if (StringUtils.contains(status, "PASS"))
                        {
                            nStatus = "<span style=\"color:green\">" + status + "</span>";
                        }
                        comment.setStatus(nStatus);
                        comment.setDamperId(rs.getInt("dampertest_id"));

                        return comment;
              
                    }
                });
//        return (List<CommentHistoryBean>) tstratDamperTemplate.execute(new HibernateCallback() {
//
//            public Object doInHibernate(Session session) throws HibernateException, SQLException {
//
//                CustTimer ct = new CustTimer("getCommentHistoryByArchive()", System.currentTimeMillis());
//
//                List<CommentHistoryBean> list = new ArrayList<CommentHistoryBean>();
//                StringBuffer sql = new StringBuffer();
//                sql.append("SELECT da.id, da.dateTestedTs, da.comments,ds.substatus,dampertestId FROM Dampertestarchive da ");
//                sql.append("inner join da.Damperstatus as ds\n ");
//                sql.append("where da.dampertestId=" + damperId);
//
//                try {
//                    Query query = session.createQuery(sql.toString());
//                    System.out.println(sql.toString());
//                   
//                    for (Iterator it = query.iterate(); it.hasNext();) {
//                        Object[] row = (Object[]) it.next();
//                        CommentHistoryBean rs = new CommentHistoryBean();
//                        rs.setId(Integer.parseInt("" + row[0]));
////                        rs.setDate(CustomDateUtils.convertJasonMoonsDateToString((Date) row[1]));
//                        if ( row[1] != null) {
//                            rs.setDateTestedTs(CustomDateUtils.repairHistoryDateformatter((Date) row[1]));
//                        }
//
//                        rs.setComments("" + row[2]);
//
//                        String status = (String) row[3];
//                        System.out.println("Comment Status: " + status);
//                        String nStatus ="&lt;Undefined&gt;";
//
//                        if (StringUtils.contains(status, "FAIL"))
//                        {
//                            nStatus = "<span style=\"color:red\">" + status + "</span>";
//                        } else if (StringUtils.contains(status, "INACCESS"))
//                        {
//                            nStatus = "<span style=\"color:blue\">" + status + "</span>";
//                        } else if (StringUtils.contains(status, "PASS"))
//                        {
//                            nStatus = "<span style=\"color:green\">" + status + "</span>";
//                        }
//                        rs.setStatus(nStatus);
//                        rs.setDamperId(Integer.parseInt("" + row[4]));
//
//                        System.out.println(rs.toString());
//                        list.add(rs);
//                    }
//                } catch(Exception e)
//                {
//                    // We really don't need to see why this happens
//                    e.printStackTrace();
//                }
//                System.out.println(ct.calculateTotalProcessTime());
//
//                return list;
//            }
//        });
    }

    /**
     * Needs changing...
     *
     * @deprecated
     * @param custId
     * @param damperId
     * @param buildingId
     * @return
     * @throws DataAccessException
     */
    public List<CommentHistoryBean> getCommentHistoryList(final Integer damperId) throws DataAccessException {

        return (List<CommentHistoryBean>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                CustTimer ct = new CustTimer("getCommentHistoryList()", System.currentTimeMillis());

                List<CommentHistoryBean> list = new ArrayList<CommentHistoryBean>();
                StringBuffer sql = new StringBuffer();
                sql.append("select r.id, r.dateTestedTs, r.comments, ds.substatus, dt.id  \n ");
                sql.append(" from Commenthistory r \n");
                sql.append(" inner join r.dampertest as dt\n");
                sql.append(" left join r.damperstatus as ds\n");
//                sql.append(" inner join dt.customer as c\n");

                List<String> whereClause = new ArrayList<String>();

                if (damperId != null && damperId > 0) {
                    whereClause.add("dt.id=" + damperId);
                }

                if (whereClause.size() > 0) {
                    int count = 0;
                    for (String c : whereClause) {
                        if (count++ == 0) {
                            sql.append(" where " + c);
                        } else {
                            sql.append(" and " + c);
                        }
                    }
                }

                sql.append(" order by r.dateTestedTs");

//                System.out.println(sql.toString());
                try {
                    Query query = session.createQuery(sql.toString());
                    System.out.println("Printing out Comment History");
                    for (Iterator it = query.iterate(); it.hasNext();) {
                        Object[] row = (Object[]) it.next();
                        CommentHistoryBean rs = new CommentHistoryBean();
                        rs.setId(Integer.parseInt("" + row[0]));
//                        rs.setDate(CustomDateUtils.convertJasonMoonsDateToString((Date) row[1]));
                        if (row[1] != null) {
                            rs.setDateTestedTs(CustomDateUtils.repairHistoryDateformatter((Date) row[1]));
                        }

                        rs.setComments("" + row[2]);

                        String status = (String) row[3];
                        System.out.println("Comment Status: " + status);
                        String nStatus = "&lt;Undefined&gt;";

                        if (StringUtils.contains(status, "FAIL")) {
                            nStatus = "<span style=\"color:red\">" + status + "</span>";
                        } else if (StringUtils.contains(status, "INACCESS")) {
                            nStatus = "<span style=\"color:blue\">" + status + "</span>";
                        } else if (StringUtils.contains(status, "PASS")) {
                            nStatus = "<span style=\"color:green\">" + status + "</span>";
                        }
                        rs.setStatus(nStatus);
                        rs.setDamperId(Integer.parseInt("" + row[4]));

                        System.out.println(rs.toString());
                        list.add(rs);
                    }
                } catch (Exception e) {
                    // We really don't need to see why this happens
//                    e.printStackTrace();
                }
                System.out.println(ct.calculateTotalProcessTime());

                return list;
            }
        });
    }

//    public List<CommentHistoryBean> getCommentHistoryList(final Integer custId, final Integer damperId, final Integer buildingId, final Integer floorId) throws DataAccessException {
//
//        return (List<CommentHistoryBean>) tstratDamperTemplate.execute(new HibernateCallback() {
//
//            public Object doInHibernate(Session session) throws HibernateException, SQLException {
//
//                CustTimer ct = new CustTimer("getCommentHistoryList()", System.currentTimeMillis());
//
//                List<CommentHistoryBean> list = new ArrayList<CommentHistoryBean>();
//                StringBuffer sql = new StringBuffer();
//                sql.append("select r.id, r.dateTestedTs, r.comments, dt.id, ds.substatus, dt.comments, dt.dateTestedTs \n ");
//                sql.append(" from Commenthistory r \n");
//                sql.append(" inner join r.dampertest as dt\n");
//                sql.append(" inner join dt.damperstatus as ds\n");
//
//                List<String> whereClause = new ArrayList<String>();
//
//                if (damperId != null && damperId > 0) {
//                    whereClause.add("dt.id=" + damperId);
//                }
//                if ("customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
//                    whereClause.add("dt.customer.id=" + EPortalSecurityContext.getCustomer().getId());
//                } else {
//                    if (custId != null && custId > 0) {
//                        whereClause.add("dt.customer.id=" + custId);
//                    }
//                }
//
//                if (buildingId != null && buildingId > 0) {
//                    whereClause.add("b.id=" + buildingId);
//                }
//
//                if (floorId != null && floorId > 0) {
//                    whereClause.add("bf.id=" + floorId);
//                }
//
//                if (whereClause.size() > 0) {
//                    int count = 0;
//                    for (String c : whereClause) {
//                        if (count++ == 0) {
//                            sql.append(" where " + c);
//                        } else {
//                            sql.append(" and " + c);
//                        }
//                    }
//                }
//
//                sql.append(" order by r.repairDate,  b.buildingName");
//
//                System.out.println(sql.toString());
//
//                try {
//                    Query query = session.createQuery(sql.toString());
//                    for (Iterator it = query.iterate(); it.hasNext();) {
//                        Object[] row = (Object[]) it.next();
//                        RepairHistoryBean rs = new RepairHistoryBean();
//                        rs.setId(Integer.parseInt("" + row[0]));
////                        rs.setDate(CustomDateUtils.convertJasonMoonsDateToString((Date) row[1]));
//                        if ( row[1] != null) {
//                            rs.setDate(CustomDateUtils.repairHistoryDateformatter((Date) row[1]));
//                        }
//
//                        rs.setDescription("" + row[2]);
//                        rs.setDamperId(Integer.parseInt("" + row[3]));
//                        rs.setAliasId((String) row[4]);
//                        String status = (String) row[5];
//                        String nStatus ="<Undefined>";
//
//                        if (StringUtils.contains(status, "FAIL"))
//                        {
//                            nStatus = "<span style=\"color:red\">" + status + "</span>";
//                        } else if (StringUtils.contains(status, "INACCESS"))
//                        {
//                            nStatus = "<span style=\"color:blue\">" + status + "</span>";
//                        } else if (StringUtils.contains(status, "PASS"))
//                        {
//                            nStatus = "<span style=\"color:green\">" + status + "</span>";
//                        }
//                        rs.setStatus(nStatus);
////                        rs.setComments(DamperUtils.parseComments((byte[]) row[6]));
//                        rs.setComments((String) row[6]);
//                        rs.setBuilding("" + row[7]);
//                        rs.setDateTestedTs(CustomDateUtils.repairHistoryDateformatter((Date) row[8]));
//
//                        list.add(rs);
//                    }
//                } catch(Exception e)
//                {
//                    // We really don't need to see why this happens
//                }
//                System.out.println(ct.calculateTotalProcessTime());
//
//                return list;
//            }
//        });
//    }
}
