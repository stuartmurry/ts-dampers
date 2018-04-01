/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller.damper;

/**
 *
 * @author Stuart
 */
public class DamperTypeCommandBean
{
    
        private Integer id;
        private Integer custId;
        private Integer buildingId;
        private Integer floorId;
        private Integer dampertype_id;
        private Integer singleUnit;

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
     * @return the dampertype_id
     */
    public Integer getDampertype_id() {
        return dampertype_id;
    }

    /**
     * @param dampertype_id the dampertype_id to set
     */
    public void setDampertype_id(Integer dampertype_id) {
        this.dampertype_id = dampertype_id;
    }

    /**
     * @return the singleUnit
     */
    public Integer getSingleUnit() {
        return singleUnit;
    }

    /**
     * @param singleUnit the singleUnit to set
     */
    public void setSingleUnit(Integer singleUnit) {
        this.singleUnit = singleUnit;
    }
}
