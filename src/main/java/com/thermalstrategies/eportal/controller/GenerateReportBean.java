/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.model.Building;
import com.thermalstrategies.eportal.model.Buildingfloor;
import com.thermalstrategies.eportal.model.Customer;
import java.util.List;

/**
 *
 * @author Stuart
 */
public class GenerateReportBean {

    private List<Customer> customerList;
    private List<Building> buildingList;
    private List<Buildingfloor> floorList;
    private Integer customer_id;
    private Integer building_id;
    private Integer floor_id;
    private Integer damperstatus_id;

    /**
     * @return the customerList
     */
    public List<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * @param customerList the customerList to set
     */
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    /**
     * @return the damperstatus_id
     */
    public Integer getDamperstatus_id() {
        return damperstatus_id;
    }

    /**
     * @param damperstatus_id the damperstatus_id to set
     */
    public void setDamperstatus_id(Integer damperstatus_id) {
        this.damperstatus_id = damperstatus_id;
    }

    /**
     * @return the customer_id
     */
    public Integer getCustomer_id() {
        return customer_id;
    }

    /**
     * @return the building_id
     */
    public Integer getBuilding_id() {
        return building_id;
    }

    /**
     * @return the floor_id
     */
    public Integer getFloor_id() {
        return floor_id;
    }

    /**
     * @return the buildingList
     */
    public List<Building> getBuildingList() {
        return buildingList;
    }

    /**
     * @param buildingList the buildingList to set
     */
    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    /**
     * @return the floorList
     */
    public List<Buildingfloor> getFloorList() {
        return floorList;
    }

    /**
     * @param floorList the floorList to set
     */
    public void setFloorList(List<Buildingfloor> floorList) {
        this.floorList = floorList;
    }

}
