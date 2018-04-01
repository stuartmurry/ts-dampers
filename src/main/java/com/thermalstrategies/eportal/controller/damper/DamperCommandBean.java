/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller.damper;


import com.thermalstrategies.eportal.controller.CommentHistoryBean;
import com.thermalstrategies.eportal.controller.RepairHistoryBean;
import com.thermalstrategies.eportal.model.Dampercomment;
import com.thermalstrategies.eportal.model.Dampermaterial;
import com.thermalstrategies.eportal.model.Drawing;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Stuart
 */
public class DamperCommandBean {

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .append("id",getId())
                .append("building",getBuilding())
                .append("building_id",getBuilding_id())
                .append("buildingfloor_id",getBuildingfloor_id())
                .append("customer_id",getCustomer_id())
                .append("dampersystem",getSystem())
                .append("damperstatus_id",getDamperstatus_id())
                .append("dampermaterial_id",getDampermaterial_id())
                .append("dampercomment_id",getDampercomment_id())
                .append("dampertest_id",getDampertest_id())
                .append("aliasId",getAliasId())
                .append("location",getLocation())
                .append("occupancy",getOccupancy())
                .append("sublocation",getSublocation())
                .append("comments",getComments())
                .append("sizel",getSizel())
                .append("sizew",getSizew())
                .append("system",getSystem())
                .append("datetested",getDatetested())
                .append("nextTestDate",getNextTestDate())
                .append("repairHistory",getRepairHistory())
                .append("repair",getRepair())
                .append("series",getSeries())
                .append("dampernumber",getDampernumber())
                .toString();
    }

    // Bean ids
    private Integer id;
    private int dampertype_id;
    private String dampertype;
    private String dampertypeAliasId;
    private int building_id;
    private String building;
    private String buildingAliasId;
    private int buildingfloor_id;
    private String buildingfloor;
    private int customer_id;
    private String customer;
    private String system;
    private int damperstatus_id;
    private int dampermaterial_id;
    private int dampercomment_id;
    private int dampertest_id;
    private int repairHistoryCount;
    private int commentHistoryCount;

    private boolean showDecommissionedButton;

    private Integer dampernumber;
    private Integer series;
    private Integer highestDamperNumber;
   
    private String aliasId;
    private String location;
    private String sublocation;
    private String comments;
    private String sizel;
    private String sizew;
    private String datetested;
    private Boolean repair;
    private String repairHistory;
    private String occupancy;
    private String specialProcedures;
    private String nextTestDate;
    private MultipartFile picture;
    private MultipartFile pictureThumbNail;

    private List<RepairHistoryBean> repairHistoryList = new ArrayList<RepairHistoryBean>();
    private List<Dampermaterial> damperMaterialList = new ArrayList<Dampermaterial>();
    private List<Dampercomment> damperCommentList = new ArrayList<Dampercomment>();
    private List<CommentHistoryBean> commentHistoryBeanList = new ArrayList<CommentHistoryBean>();
    private List<Integer> pictureIdList = new ArrayList<Integer>();

    private List<Drawing> drawingList = new ArrayList<Drawing>();
    private int drawing_id;


    /**
     *
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
     * @return the dampertype_id
     */
    public int getDampertype_id() {
        return dampertype_id;
    }

    /**
     * @param dampertype_id the dampertype_id to set
     */
    public void setDampertype_id(int dampertype_id) {
        this.dampertype_id = dampertype_id;
    }

    /**
     * @return the dampertype
     */
    public String getDampertype() {
        return dampertype;
    }

    /**
     * @param dampertype the dampertype to set
     */
    public void setDampertype(String dampertype) {
        this.dampertype = dampertype;
    }

    /**
     * @return the building_id
     */
    public int getBuilding_id() {
        return building_id;
    }

    /**
     * @param building_id the building_id to set
     */
    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
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
     * @return the buildingfloor_id
     */
    public int getBuildingfloor_id() {
        return buildingfloor_id;
    }

    /**
     * @param buildingfloor_id the buildingfloor_id to set
     */
    public void setBuildingfloor_id(int buildingfloor_id) {
        this.buildingfloor_id = buildingfloor_id;
    }

    /**
     * @return the buildingfloor
     */
    public String getBuildingfloor() {
        return buildingfloor;
    }

    /**
     * @param buildingfloor the buildingfloor to set
     */
    public void setBuildingfloor(String buildingfloor) {
        this.buildingfloor = buildingfloor;
    }

    /**
     * @return the customer_id
     */
    public int getCustomer_id() {
        return customer_id;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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
     * @return the system
     */
    public String getSystem() {
        return system;
    }

    /**
     * @param system the system to set
     */
    public void setSystem(String system) {
        this.system = system;
    }

    /**
     * @return the damperstatus_id
     */
    public int getDamperstatus_id() {
        return damperstatus_id;
    }

    /**
     * @param damperstatus_id the damperstatus_id to set
     */
    public void setDamperstatus_id(int damperstatus_id) {
        this.damperstatus_id = damperstatus_id;
    }

    /**
     * @return the dampermaterial_id
     */
    public int getDampermaterial_id() {
        return dampermaterial_id;
    }

    /**
     * @param dampermaterial_id the dampermaterial_id to set
     */
    public void setDampermaterial_id(int dampermaterial_id) {
        this.dampermaterial_id = dampermaterial_id;
    }

    /**
     * @return the dampercomment_id
     */
    public int getDampercomment_id() {
        return dampercomment_id;
    }

    /**
     * @param dampercomment_id the dampercomment_id to set
     */
    public void setDampercomment_id(int dampercomment_id) {
        this.dampercomment_id = dampercomment_id;
    }

    /**
     * @return the dampertest_id
     */
    public int getDampertest_id() {
        return dampertest_id;
    }

    /**
     * @param dampertest_id the dampertest_id to set
     */
    public void setDampertest_id(int dampertest_id) {
        this.dampertest_id = dampertest_id;
    }

    /**
     * @return the dampernumber
     */
    public Integer getDampernumber() {
        return dampernumber;
    }

    /**
     * @param dampernumber the dampernumber to set
     */
    public void setDampernumber(Integer dampernumber) {
        this.dampernumber = dampernumber;
    }

    /**
     * @return the series
     */
    public Integer getSeries() {
        return series;
    }

    /**
     * @param series the series to set
     */
    public void setSeries(Integer series) {
        this.series = series;
    }

    /**
     * @return the aliasId
     */
    public String getAliasId() {
        return aliasId;
    }

    /**
     * @param aliasId the aliasId to set
     */
    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the sublocation
     */
    public String getSublocation() {
        return sublocation;
    }

    /**
     * @param sublocation the sublocation to set
     */
    public void setSublocation(String sublocation) {
        this.sublocation = sublocation;
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
     * @return the sizel
     */
    public String getSizel() {
        return sizel;
    }

    /**
     * @param sizel the sizel to set
     */
    public void setSizel(String sizel) {
        this.sizel = sizel;
    }

    /**
     * @return the sizew
     */
    public String getSizew() {
        return sizew;
    }

    /**
     * @param sizew the sizew to set
     */
    public void setSizew(String sizew) {
        this.sizew = sizew;
    }

    /**
     * @return the datetested
     */
    public String getDatetested() {
        return datetested;
    }

    /**
     * @param datetested the datetested to set
     */
    public void setDatetested(String datetested) {
        this.datetested = datetested;
    }

    /**
     * @return the repair
     */
    public Boolean getRepair() {
        return repair;
    }

    /**
     * @param repair the repair to set
     */
    public void setRepair(Boolean repair) {
        this.repair = repair;
    }

    /**
     * @return the repairHistory
     */
    public String getRepairHistory() {
        return repairHistory;
    }

    /**
     * @param repairHistory the repairHistory to set
     */
    public void setRepairHistory(String repairHistory) {
        this.repairHistory = repairHistory;
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

    /**
     * @return the nextTestDate
     */
    public String getNextTestDate() {
        return nextTestDate;
    }

    /**
     * @param nextTestDate the nextTestDate to set
     */
    public void setNextTestDate(String nextTestDate) {
        this.nextTestDate = nextTestDate;
    }

    /**
     * @return the picture
     */
    public MultipartFile getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    /**
     * @return the pictureThumbNail
     */
    public MultipartFile getPictureThumbNail() {
        return pictureThumbNail;
    }

    /**
     * @param pictureThumbNail the pictureThumbNail to set
     */
    public void setPictureThumbNail(MultipartFile pictureThumbNail) {
        this.pictureThumbNail = pictureThumbNail;
    }

    /**
     * @return the repairHistoryList
     */
    public List<RepairHistoryBean> getRepairHistoryList() {
        return repairHistoryList;
    }

    /**
     * @param repairHistoryList the repairHistoryList to set
     */
    public void setRepairHistoryList(List<RepairHistoryBean> repairHistoryList) {
        this.repairHistoryList = repairHistoryList;
    }

    /**
     * @return the damperMaterialList
     */
    public List<Dampermaterial> getDamperMaterialList() {
        return damperMaterialList;
    }

    /**
     * @param damperMaterialList the damperMaterialList to set
     */
    public void setDamperMaterialList(List<Dampermaterial> damperMaterialList) {
        this.damperMaterialList = damperMaterialList;
    }

    /**
     * @return the damperCommentList
     */
    public List<Dampercomment> getDamperCommentList() {
        return damperCommentList;
    }

    /**
     * @param damperCommentList the damperCommentList to set
     */
    public void setDamperCommentList(List<Dampercomment> damperCommentList) {
        this.damperCommentList = damperCommentList;
    }

    /**
     * @return the highestDamperNumber
     */
    public Integer getHighestDamperNumber() {
        return highestDamperNumber;
    }

    /**
     * @param highestDamperNumber the highestDamperNumber to set
     */
    public void setHighestDamperNumber(Integer highestDamperNumber) {
        this.highestDamperNumber = highestDamperNumber;
    }

    /**
     * @return the buildingAliasId
     */
    public String getBuildingAliasId() {
        return buildingAliasId;
    }

    /**
     * @param buildingAliasId the buildingAliasId to set
     */
    public void setBuildingAliasId(String buildingAliasId) {
        this.buildingAliasId = buildingAliasId;
    }

    /**
     * @return the dampertypeAliasId
     */
    public String getDampertypeAliasId() {
        return dampertypeAliasId;
    }

    /**
     * @param dampertypeAliasId the dampertypeAliasId to set
     */
    public void setDampertypeAliasId(String dampertypeAliasId) {
        this.dampertypeAliasId = dampertypeAliasId;
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

    /**
     * @return the repairHistoryCount
     */
    public int getRepairHistoryCount() {
        return repairHistoryCount;
    }

    /**
     * @param repairHistoryCount the repairHistoryCount to set
     */
    public void setRepairHistoryCount(int repairHistoryCount) {
        this.repairHistoryCount = repairHistoryCount;
    }

    /**
     * @return the commentHistoryBeanList
     */
    public List<CommentHistoryBean> getCommentHistoryBeanList() {
        return commentHistoryBeanList;
    }

    /**
     * @param commentHistoryBeanList the commentHistoryBeanList to set
     */
    public void setCommentHistoryBeanList(List<CommentHistoryBean> commentHistoryBeanList) {
        this.commentHistoryBeanList = commentHistoryBeanList;
    }

    /**
     * @return the commentHistoryCount
     */
    public int getCommentHistoryCount() {
        return commentHistoryCount;
    }

    /**
     * @param commentHistoryCount the commentHistoryCount to set
     */
    public void setCommentHistoryCount(int commentHistoryCount) {
        this.commentHistoryCount = commentHistoryCount;
    }

    /**
     * @return the showDecommissionedButton
     */
    public boolean isShowDecommissionedButton() {
        return showDecommissionedButton;
    }

    /**
     * @param showDecommissionedButton the showDecommissionedButton to set
     */
    public void setShowDecommissionedButton(boolean showDecommissionedButton) {
        this.showDecommissionedButton = showDecommissionedButton;
    }

    /**
     * @return the pictureIdList
     */
    public List<Integer> getPictureIdList() {
        return pictureIdList;
    }

    /**
     * @param pictureIdList the pictureIdList to set
     */
    public void setPictureIdList(List<Integer> pictureIdList) {
        this.pictureIdList = pictureIdList;
    }

    /**
     * @return the drawingList
     */
    public List<Drawing> getDrawingList() {
        return drawingList;
    }

    /**
     * @param drawingList the drawingList to set
     */
    public void setDrawingList(List<Drawing> drawingList) {
        this.drawingList = drawingList;
    }

    /**
     * @return the drawing_id
     */
    public int getDrawing_id() {
        return drawing_id;
    }

    /**
     * @param drawing_id the drawing_id to set
     */
    public void setDrawing_id(int drawing_id) {
        this.drawing_id = drawing_id;
    }
}