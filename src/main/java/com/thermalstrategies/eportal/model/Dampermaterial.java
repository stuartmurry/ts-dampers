package com.thermalstrategies.eportal.model;
// Generated Oct 7, 2009 7:19:35 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Dampermaterial generated by hbm2java
 */
public class Dampermaterial  implements java.io.Serializable {


     private Integer id;
     private String materialName;
     private String description;
     private boolean isenabled;
     private Set<Dampertest> dampertests = new HashSet<Dampertest>(0);

    public Dampermaterial() {
    }

	
    public Dampermaterial(Integer id, boolean isenabled) {
        this.id = id;
        this.isenabled = isenabled;
    }
    public Dampermaterial(Integer id, String materialName, String description, boolean isenabled, Set<Dampertest> dampertests) {
       this.id = id;
       this.materialName = materialName;
       this.description = description;
       this.isenabled = isenabled;
       this.dampertests = dampertests;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMaterialName() {
        return this.materialName;
    }
    
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isIsenabled() {
        return this.isenabled;
    }
    
    public void setIsenabled(boolean isenabled) {
        this.isenabled = isenabled;
    }
    public Set<Dampertest> getDampertests() {
        return this.dampertests;
    }
    
    public void setDampertests(Set<Dampertest> dampertests) {
        this.dampertests = dampertests;
    }




}

