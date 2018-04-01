/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author Stuart
 */
public class RepairHistoryBean
{
    private Integer id;
    private String date;
    private String description;
    private Integer damperId;
    private String aliasId;
    private String status;
    private String comments;
    private String building;
    private String dateTestedTs;
    private Integer custId;
    private Integer buildingId;
    private Integer floorId;

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .append("id",getId())
                .append("date",getDate())
                .append("description",getDescription())
                .append("damperId",getDamperId())
                .append("aliasId",getAliasId())
                .append("status",getStatus())
                .append("comments",getComments())
                .append("building",getBuilding())
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
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
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
     * @return the building
     */
    public String getBuilding() {
        return building;
    }

    /**
     * @param building the building to set
     */
    public void setBuilding(String building) {
        this.building = building;
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
     * @return the custId
     */
    public Integer getCustId() {
        return custId;
    }

    /**
     * @param custId the custId to set
     */
    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    /**
     * @return the buildingId
     */
    public Integer getBuildingId() {
        return buildingId;
    }

    /**
     * @param buildingId the buildingId to set
     */
    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    /**
     * @return the floorId
     */
    public Integer getFloorId() {
        return floorId;
    }

    /**
     * @param floorId the floorId to set
     */
    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }


}
