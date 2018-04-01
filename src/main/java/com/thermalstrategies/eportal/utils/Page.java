/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.utils;

import java.util.List;

public class Page {

    private List results;
    private Integer pageSize;
    private int page;
    private int pageCount;
    private int totalRecords;
    private int showingRecords;

    public Page(List results, int page, Integer pageSize, Integer totalRecords) throws Exception {

        this.page = page;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        this.results = results;
        showingRecords = results.size();
        if (totalRecords > 0) {
            // Calculate page sizes
            double d = totalRecords / pageSize.doubleValue();
//            System.out.println("Divisor(Count): " + d);
            pageCount = ((Double)Math.ceil(d)).intValue();
//            System.out.println("Page(Count): " + pageCount);
        } else {
            pageCount = 0;
        }

    }

    public boolean isNextPage() {
        return results.size() > getPageSize();
    }

    public boolean isPreviousPage() {
        return getPage() > 0;
    }

    /**
     * @return the totalRecords
     */
    public int getTotalRecords() {
        return totalRecords;
    }

    /**
     * @return the results
     */
    public List getResults() {
        return isNextPage() ? results.subList(0, getPageSize() - 1) : results;
    }

    /**
     * @return the pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount the pageCount to set
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return the showingRecords
     */
    public int getShowingRecords() {
        return showingRecords;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
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

    /**
     * @return the fromRecords
     */
    public int getFromRecords() {
        return page * pageSize;
    }

    /**
     * @return the toRecords
     */
    public int getToRecords() {
        if ((page + 1) == pageCount)
        {
            return getTotalRecords();
        } else
        {
            return (page + 1) * pageSize;
        }
    }

}

