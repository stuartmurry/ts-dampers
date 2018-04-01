package com.thermalstrategies.eportal;

import java.util.List;

public class BuildingUnderTestBean {

    private Integer testCycleId;
    private Integer customerId;
    private String customer;
    private Integer buildingId;
    private String building;
    private String startDate;
    private Integer percentComplete;
    private List<FloorUnderTestBean> floorUnderTestList;

    /**
     * @return the customerId
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(String customer) {
        this.customer = customer;
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
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the percentComplete
     */
    public Integer getPercentComplete() {
        return percentComplete;
    }

    /**
     * @param percentComplete the percentComplete to set
     */
    public void setPercentComplete(Integer percentComplete) {
        this.percentComplete = percentComplete;
    }

    /**
     * @return the floorUnderTestList
     */
    public List<FloorUnderTestBean> getFloorUnderTestList() {
        return floorUnderTestList;
    }

    /**
     * @param floorUnderTestList the floorUnderTestList to set
     */
    public void setFloorUnderTestList(List<FloorUnderTestBean> floorUnderTestList) {
        this.floorUnderTestList = floorUnderTestList;
    }

    /**
     * @return the testCycleId
     */
    public Integer getTestCycleId() {
        return testCycleId;
    }

    /**
     * @param testCycleId the testCycleId to set
     */
    public void setTestCycleId(Integer testCycleId) {
        this.testCycleId = testCycleId;
    }

    

}
