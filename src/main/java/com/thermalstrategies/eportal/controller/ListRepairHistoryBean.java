/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

/**
 *
 * @author Stuart
 */
public class ListRepairHistoryBean {

    private Integer damperId;
    private Integer custId;
    private Integer buildingId;
    private Integer floorId;
    private Integer pageNum;
    private Integer level;

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

    /**
     * @return the pageNum
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * @param pageNum the pageNum to set
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
    // Left intentionally blank.  We want the abstractformcontroller's functinality without
    // actually having to bind to specific properties
}
