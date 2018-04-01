package com.thermalstrategies.eportal.model;
// Generated Nov 28, 2009 10:26:13 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Dampertest generated by hbm2java
 */
public class Dampertest  implements java.io.Serializable {


     private Integer id;
     private Customer customer;
     private Dampertype dampertype;
     private Building building;
     private Dampermaterial dampermaterial;
     private Damperstatus damperstatus;
     private Buildingfloor buildingfloor;
     private User user;
     private String aliasId;
     private Integer sizel;
     private Integer sizew;
     private String system;
     private String systemtype;
     private String location;
     private String sublocation;
     private Integer dampernumber;
     private Integer series;
     private Date dateTestedTs;
     private Date repairDate;
     private String comments;
     private boolean isenabled;
     private Date nextTestDate;
     private String occupancy;
     private String specialProcedures;
     private Set<Repairhistory> repairhistories = new HashSet<Repairhistory>(0);

    public Dampertest() {
    }

	
    public Dampertest(Integer id, boolean isenabled) {
        this.id = id;
        this.isenabled = isenabled;
    }
    public Dampertest(Integer id, Customer customer, Dampertype dampertype, Building building, Dampermaterial dampermaterial, Damperstatus damperstatus, Buildingfloor buildingfloor, User user, String aliasId, Integer sizel, Integer sizew, String system, String systemtype, String location, String sublocation, Integer dampernumber, Integer series, Date dateTestedTs, Date repairDate, String comments, boolean isenabled, Date nextTestDate, String occupancy, Set<Repairhistory> repairhistories, String specialProcedures) {
       this.id = id;
       this.customer = customer;
       this.dampertype = dampertype;
       this.building = building;
       this.dampermaterial = dampermaterial;
       this.damperstatus = damperstatus;
       this.buildingfloor = buildingfloor;
       this.user = user;
       this.aliasId = aliasId;
       this.sizel = sizel;
       this.sizew = sizew;
       this.system = system;
       this.systemtype = systemtype;
       this.location = location;
       this.sublocation = sublocation;
       this.dampernumber = dampernumber;
       this.series = series;
       this.dateTestedTs = dateTestedTs;
       this.repairDate = repairDate;
       this.comments = comments;
       this.isenabled = isenabled;
       this.nextTestDate = nextTestDate;
       this.occupancy = occupancy;
       this.repairhistories = repairhistories;
       this.specialProcedures = specialProcedures;
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
    public Dampertype getDampertype() {
        return this.dampertype;
    }
    
    public void setDampertype(Dampertype dampertype) {
        this.dampertype = dampertype;
    }
    public Building getBuilding() {
        return this.building;
    }
    
    public void setBuilding(Building building) {
        this.building = building;
    }
    public Dampermaterial getDampermaterial() {
        return this.dampermaterial;
    }
    
    public void setDampermaterial(Dampermaterial dampermaterial) {
        this.dampermaterial = dampermaterial;
    }
    public Damperstatus getDamperstatus() {
        return this.damperstatus;
    }
    
    public void setDamperstatus(Damperstatus damperstatus) {
        this.damperstatus = damperstatus;
    }
    public Buildingfloor getBuildingfloor() {
        return this.buildingfloor;
    }
    
    public void setBuildingfloor(Buildingfloor buildingfloor) {
        this.buildingfloor = buildingfloor;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getAliasId() {
        return this.aliasId;
    }
    
    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }
    public Integer getSizel() {
        return this.sizel;
    }
    
    public void setSizel(Integer sizel) {
        this.sizel = sizel;
    }
    public Integer getSizew() {
        return this.sizew;
    }
    
    public void setSizew(Integer sizew) {
        this.sizew = sizew;
    }
    public String getSystem() {
        return this.system;
    }
    
    public void setSystem(String system) {
        this.system = system;
    }
    public String getSystemtype() {
        return this.systemtype;
    }
    
    public void setSystemtype(String systemtype) {
        this.systemtype = systemtype;
    }
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    public String getSublocation() {
        return this.sublocation;
    }
    
    public void setSublocation(String sublocation) {
        this.sublocation = sublocation;
    }
    public Integer getDampernumber() {
        return this.dampernumber;
    }
    
    public void setDampernumber(Integer dampernumber) {
        this.dampernumber = dampernumber;
    }
    public Integer getSeries() {
        return this.series;
    }
    
    public void setSeries(Integer series) {
        this.series = series;
    }
    public Date getDateTestedTs() {
        return this.dateTestedTs;
    }
    
    public void setDateTestedTs(Date dateTestedTs) {
        this.dateTestedTs = dateTestedTs;
    }
    public Date getRepairDate() {
        return this.repairDate;
    }
    
    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }
    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    public boolean isIsenabled() {
        return this.isenabled;
    }
    
    public void setIsenabled(boolean isenabled) {
        this.isenabled = isenabled;
    }
    public Date getNextTestDate() {
        return this.nextTestDate;
    }
    
    public void setNextTestDate(Date nextTestDate) {
        this.nextTestDate = nextTestDate;
    }
    public String getOccupancy() {
        return this.occupancy;
    }
    
    public void setOccupancy(String occupancy) {
        this.occupancy = occupancy;
    }
    public Set<Repairhistory> getRepairhistories() {
        return this.repairhistories;
    }
    
    public void setRepairhistories(Set<Repairhistory> repairhistories) {
        this.repairhistories = repairhistories;
    }

    /**
     * @return the specialProcedures
     */
    public String getSpecialProcedures() {
        return specialProcedures;
    }

    /**
     * @param specialProcedures the specialProcedures to set
     */
    public void setSpecialProcedures(String specialProcedures) {
        this.specialProcedures = specialProcedures;
    }


}


