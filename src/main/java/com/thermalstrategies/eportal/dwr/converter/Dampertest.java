package com.thermalstrategies.eportal.dwr.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Stuart Murry
 */
public class Dampertest 
{
    
    private Integer id; 
    private Date dateTestedTs;
    private String aliasId;
    private String location;
    private String sublocation;
    private String system;
    private String status;
    private Date repairDate;
    private String comments;
    private Integer series;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateTestedTs() 
    {
        DateFormat df = new SimpleDateFormat("M/d/yyyy");
        return df.format(dateTestedTs);
    }

    public void setDateTestedTs(Date dateTestedTs) {
        this.dateTestedTs = dateTestedTs;
    }

    public String getAliasId() {
        return aliasId;
    }

    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSublocation() {
        return sublocation;
    }

    public void setSublocation(String sublocation) {
        this.sublocation = sublocation;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    
}
