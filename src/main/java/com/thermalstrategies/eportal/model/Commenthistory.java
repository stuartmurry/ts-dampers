package com.thermalstrategies.eportal.model;
// Generated Nov 28, 2009 10:26:13 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Repairhistory generated by hbm2java
 */
public class Commenthistory  implements java.io.Serializable {


     private Integer id;
     private Dampertest dampertest;
     private Damperstatus damperstatus;
     private Date dateTestedTs;
     private Date cycleDate;
     private String comments;

    public Commenthistory() {
    }

    public Commenthistory(Dampertest dampertest, Date dateTestedTs, String comments, Date cycleDate, Damperstatus damperstatus) {
       this.dampertest = dampertest;
       this.dateTestedTs = dateTestedTs;
       this.comments = comments;
       this.cycleDate = cycleDate;
       this.damperstatus = damperstatus;
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
     * @return the dampertest
     */
    public Dampertest getDampertest() {
        return dampertest;
    }

    /**
     * @param dampertest the dampertest to set
     */
    public void setDampertest(Dampertest dampertest) {
        this.dampertest = dampertest;
    }

    /**
     * @return the dateTestedTs
     */
    public Date getDateTestedTs() {
        return dateTestedTs;
    }

    /**
     * @param dateTestedTs the dateTestedTs to set
     */
    public void setDateTestedTs(Date dateTestedTs) {
        this.dateTestedTs = dateTestedTs;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the cycleDate
     */
    public Date getCycleDate() {
        return cycleDate;
    }

    /**
     * @param cycleDate the cycleDate to set
     */
    public void setCycleDate(Date cycleDate) {
        this.cycleDate = cycleDate;
    }

    /**
     * @return the damperstatus
     */
    public Damperstatus getDamperstatus() {
        return damperstatus;
    }

    /**
     * @param damperstatus the damperstatus to set
     */
    public void setDamperstatus(Damperstatus damperstatus) {
        this.damperstatus = damperstatus;
    }
   
    


}


