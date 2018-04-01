package com.thermalstrategies.eportal.model;
// Generated Nov 28, 2009 10:26:13 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Repairhistory generated by hbm2java
 */
public class Repairhistory  implements java.io.Serializable {


     private Integer id;
     private Dampertest dampertest;
     private Date repairDate;
     private String description;

    public Repairhistory() {
    }

    public Repairhistory(Dampertest dampertest, Date repairDate, String description) {
       this.dampertest = dampertest;
       this.repairDate = repairDate;
       this.description = description;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Dampertest getDampertest() {
        return this.dampertest;
    }
    
    public void setDampertest(Dampertest dampertest) {
        this.dampertest = dampertest;
    }
    public Date getRepairDate() {
        return this.repairDate;
    }
    
    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


