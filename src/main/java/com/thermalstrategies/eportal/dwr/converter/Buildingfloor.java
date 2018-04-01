package com.thermalstrategies.eportal.dwr.converter;

import java.io.Serializable;


/***
 * A Direct Web Remoting wrapper for the Buildingfloor entity
 * @author Stuart Murry
 */
public class Buildingfloor implements Serializable {

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String floorName;
    
    private Integer buildingId;

    /** default constructor */
    public Buildingfloor() {
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFloorName() {
        return this.floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }
}

