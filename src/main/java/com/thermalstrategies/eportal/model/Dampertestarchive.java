package com.thermalstrategies.eportal.model;
// Generated Dec 9, 2014 11:31:45 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Dampertestarchive generated by hbm2java
 */
public class Dampertestarchive  implements java.io.Serializable {


     private int id;
     private String aliasId;
     private Integer buildingId;
     private Integer typeId;
     private Integer sizel;
     private Integer sizew;
     private String system;
     private String systemtype;
     private Integer materialId;
     private Integer floorId;
     private Integer statusId;
     private String location;
     private String sublocation;
     private Integer dampernumber;
     private Integer series;
     private Date dateTestedTs;
     private Date repairDate;
     private String comments;
     private Integer tester;
     private boolean isenabled;
     private Integer customerId;
     private Date nextTestDate;
     private String occupancy;
     private String specialProcedures;
     private Integer xposition;
     private Integer yposition;
     private Integer dampertestId;

    public Dampertestarchive() {
    }

	
    public Dampertestarchive(int id, boolean isenabled) {
        this.id = id;
        this.isenabled = isenabled;
    }
    public Dampertestarchive(int id, String aliasId, Integer buildingId, Integer typeId, Integer sizel, Integer sizew, String system, String systemtype, Integer materialId, Integer floorId, Integer statusId, String location, String sublocation, Integer dampernumber, Integer series, Date dateTestedTs, Date repairDate, String comments, Integer tester, boolean isenabled, Integer customerId, Date nextTestDate, String occupancy, String specialProcedures, Integer xposition, Integer yposition, Integer dampertestId) {
       this.id = id;
       this.aliasId = aliasId;
       this.buildingId = buildingId;
       this.typeId = typeId;
       this.sizel = sizel;
       this.sizew = sizew;
       this.system = system;
       this.systemtype = systemtype;
       this.materialId = materialId;
       this.floorId = floorId;
       this.statusId = statusId;
       this.location = location;
       this.sublocation = sublocation;
       this.dampernumber = dampernumber;
       this.series = series;
       this.dateTestedTs = dateTestedTs;
       this.repairDate = repairDate;
       this.comments = comments;
       this.tester = tester;
       this.isenabled = isenabled;
       this.customerId = customerId;
       this.nextTestDate = nextTestDate;
       this.occupancy = occupancy;
       this.specialProcedures = specialProcedures;
       this.xposition = xposition;
       this.yposition = yposition;
       this.dampertestId = dampertestId;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getAliasId() {
        return this.aliasId;
    }
    
    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }
    public Integer getBuildingId() {
        return this.buildingId;
    }
    
    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }
    public Integer getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
    public Integer getMaterialId() {
        return this.materialId;
    }
    
    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }
    public Integer getFloorId() {
        return this.floorId;
    }
    
    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }
    public Integer getStatusId() {
        return this.statusId;
    }
    
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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
    public Integer getTester() {
        return this.tester;
    }
    
    public void setTester(Integer tester) {
        this.tester = tester;
    }
    public boolean isIsenabled() {
        return this.isenabled;
    }
    
    public void setIsenabled(boolean isenabled) {
        this.isenabled = isenabled;
    }
    public Integer getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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
    public String getSpecialProcedures() {
        return this.specialProcedures;
    }
    
    public void setSpecialProcedures(String specialProcedures) {
        this.specialProcedures = specialProcedures;
    }
    public Integer getXposition() {
        return this.xposition;
    }
    
    public void setXposition(Integer xposition) {
        this.xposition = xposition;
    }
    public Integer getYposition() {
        return this.yposition;
    }
    
    public void setYposition(Integer yposition) {
        this.yposition = yposition;
    }
    public Integer getDampertestId() {
        return this.dampertestId;
    }
    
    public void setDampertestId(Integer dampertestId) {
        this.dampertestId = dampertestId;
    }




}

