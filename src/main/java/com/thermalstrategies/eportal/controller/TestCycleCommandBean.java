/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.model.Testcycle;

/**
 *
 * @author vmurry
 */
public class TestCycleCommandBean extends Testcycle {
    private Integer id;
    private Integer custId;
    private Integer buildingId;
    private String sDate;
    private String fDate;
    private boolean complete;


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
     * @return the sDate
     */
    public String getsDate() {
        return sDate;
    }

    /**
     * @param sDate the sDate to set
     */
    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    /**
     * @return the fDate
     */
    public String getfDate() {
        return fDate;
    }

    /**
     * @param fDate the fDate to set
     */
    public void setfDate(String fDate) {
        this.fDate = fDate;
    }

    /**
     * @return the complete
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * @param complete the complete to set
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    


}
