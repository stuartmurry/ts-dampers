/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author Stuart
 */
public class CustomerBuildingFloorBean {

    private String customerName;
    private List<Building> buildingList;

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).append("customerName", getCustomerName()).toString();
    }
    private Integer id;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomerBuildingFloorBean other = (CustomerBuildingFloorBean) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
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
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public class Building {

        private Integer id;
        //        String aliasId;
        private String buildingName;
        private Integer siteId;
        private String siteName;
        private boolean test;
//        String address1;
//        String address2;
//        String city;
//        String state;
//        String zip;
//        String poc;
//        String pocPhone;
        private List<Buildingfloor> buildingFloorList;

        public String toString() {
            return new ToStringBuilder(this)
                    .append("id", getId())
                    .append("buildingName", getBuildingName())
                    .append("buildingList", getBuildingList())
                    .append("buildingFloorList", getBuildingFloorList())
                    .toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Building other = (Building) obj;
            if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
            return hash;
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
         * @return the buildingName
         */
        public String getBuildingName() {
            return buildingName;
        }

        /**
         * @param buildingName the buildingName to set
         */
        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        /**
         * @return the buildingFloorList
         */
        public List<Buildingfloor> getBuildingFloorList() {
            return buildingFloorList;
        }

        /**
         * @param buildingFloorList the buildingFloorList to set
         */
        public void setBuildingFloorList(List<Buildingfloor> buildingFloorList) {
            this.buildingFloorList = buildingFloorList;
        }

        /**
         * @return the siteName
         */
        public String getSiteName() {
            return siteName;
        }

        /**
         * @param siteName the siteName to set
         */
        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        /**
         * @return the siteId
         */
        public Integer getSiteId() {
            return siteId;
        }

        /**
         * @param siteId the siteId to set
         */
        public void setSiteId(Integer siteId) {
            this.siteId = siteId;
        }

        /**
         * @return the test
         */
        public boolean isTest() {
            return test;
        }

        /**
         * @param test the test to set
         */
        public void setTest(boolean test) {
            this.test = test;
        }
    }

    public class Buildingfloor {

        private Integer id;
        private String floorName;
        private Integer sequenceNum;

        public String toString() {
            return new ToStringBuilder(this)
                    .append("id", getId())
                    .append("floorName", getFloorName())
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
         * @return the floorName
         */
        public String getFloorName() {
            return floorName;
        }

        /**
         * @param floorName the floorName to set
         */
        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }

        /**
         * @return the sequenceNum
         */
        public Integer getSequenceNum() {
            return sequenceNum;
        }

        /**
         * @param sequenceNum the sequenceNum to set
         */
        public void setSequenceNum(Integer sequenceNum) {
            this.sequenceNum = sequenceNum;
        }

        
    }
}
