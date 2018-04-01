package com.thermalstrategies.eportal.model;
// Generated Oct 7, 2009 7:19:35 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Building generated by hbm2java
 */
public class Building  implements java.io.Serializable {


     private Integer id;
     private Customer customer;
     private Site site;
     private String aliasId;
     private String buildingName;
     private String address1;
     private String address2;
     private String city;
     private String state;
     private String zip;
     private String poc;
     private String pocPhone;
     private Integer ho;
     private Integer bo;
     private Date lastDateTestedTs;
     private boolean isenabled;
     private Set<Userlog> userlogs = new HashSet<Userlog>(0);
     private Set<Buildingfloor> buildingfloors = new HashSet<Buildingfloor>(0);
     private Set<Dampertest> dampertests = new HashSet<Dampertest>(0);
     private String occupancy;

    public Building() {
    }

	
    public Building(Integer id, String buildingName, boolean isenabled) {
        this.id = id;
        this.buildingName = buildingName;
        this.isenabled = isenabled;
    }
    public Building(Integer id, Customer customer, Site site, String aliasId, String buildingName, String address1, String address2, String city, String state, String zip, String poc, String pocPhone, Integer ho, Integer bo, Date lastDateTestedTs, boolean isenabled, Set<Userlog> userlogs, Set<Buildingfloor> buildingfloors, Set<Dampertest> dampertests) {
       this.id = id;
       this.customer = customer;
       this.aliasId = aliasId;
       this.buildingName = buildingName;
       this.address1 = address1;
       this.address2 = address2;
       this.city = city;
       this.state = state;
       this.zip = zip;
       this.poc = poc;
       this.pocPhone = pocPhone;
       this.ho = ho;
       this.bo = bo;
       this.lastDateTestedTs = lastDateTestedTs;
       this.isenabled = isenabled;
       this.userlogs = userlogs;
       this.buildingfloors = buildingfloors;
       this.dampertests = dampertests;
       this.site = site;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Customer getCustomer() {
        return this.customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public String getAliasId() {
        return this.aliasId;
    }
    
    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }
    public String getBuildingName() {
        return this.buildingName;
    }
    
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    public String getAddress1() {
        return this.address1;
    }
    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getAddress2() {
        return this.address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public String getZip() {
        return this.zip;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String getPoc() {
        return this.poc;
    }
    
    public void setPoc(String poc) {
        this.poc = poc;
    }
    public String getPocPhone() {
        return this.pocPhone;
    }
    
    public void setPocPhone(String pocPhone) {
        this.pocPhone = pocPhone;
    }
    public Integer getHo() {
        return this.ho;
    }
    
    public void setHo(Integer ho) {
        this.ho = ho;
    }
    public Integer getBo() {
        return this.bo;
    }
    
    public void setBo(Integer bo) {
        this.bo = bo;
    }
    public Date getLastDateTestedTs() {
        return this.lastDateTestedTs;
    }
    
    public void setLastDateTestedTs(Date lastDateTestedTs) {
        this.lastDateTestedTs = lastDateTestedTs;
    }
    public boolean isIsenabled() {
        return this.isenabled;
    }
    
    public void setIsenabled(boolean isenabled) {
        this.isenabled = isenabled;
    }
    public Set<Userlog> getUserlogs() {
        return this.userlogs;
    }
    
    public void setUserlogs(Set<Userlog> userlogs) {
        this.userlogs = userlogs;
    }
    public Set<Buildingfloor> getBuildingfloors() {
        return this.buildingfloors;
    }
    
    public void setBuildingfloors(Set<Buildingfloor> buildingfloors) {
        this.buildingfloors = buildingfloors;
    }
    public Set<Dampertest> getDampertests() {
        return this.dampertests;
    }
    
    public void setDampertests(Set<Dampertest> dampertests) {
        this.dampertests = dampertests;
    }

    /**
     * @return the site
     */
    public Site getSite() {
        return site;
    }

    /**
     * @param site the site to set
     */
    public void setSite(Site site) {
        this.site = site;
    }

    /**
     * @return the occupancy
     */
    public String getOccupancy() {
        return occupancy;
    }

    /**
     * @param occupancy the occupancy to set
     */
    public void setOccupancy(String occupancy) {
        this.occupancy = occupancy;
    }




}


