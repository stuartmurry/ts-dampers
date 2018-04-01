/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SELECT da.id, da.comments, da.date_tested_ts, ds.status FROM Dampertestarchive da
        inner join damperstatus ds on ds.id=da.status_id
	where dampertest_id=1810
 * @author Stuart
 */
public class CommentHistoryBean
{
    private Integer id;
    private Integer damperId;
    private String dateTestedTs;
    private String comments;
    private String status;

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .append("id",getId())
                .append("damperId",getDamperId())
                .append("dateTestedTs",getDateTestedTs())
                .append("status",getStatus())
                .append("comments",getComments())
                .toString();
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the dateTestedTs
     */
    public String getDateTestedTs() {
        return dateTestedTs;
    }

    /**
     * @param dateTestedTs the dateTestedTs to set
     */
    public void setDateTestedTs(String dateTestedTs) {
        this.dateTestedTs = dateTestedTs;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the damperId
     */
    public Integer getDamperId() {
        return damperId;
    }

    /**
     * @param damperId the damperId to set
     */
    public void setDamperId(Integer damperId) {
        this.damperId = damperId;
    }

    


    




}
