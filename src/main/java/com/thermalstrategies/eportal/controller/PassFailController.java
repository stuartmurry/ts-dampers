/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.model.User;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Stuart
 */
public class PassFailController implements Controller {

    private ApplicationManager applicationManager;

    public PassFailController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("passfail");
        // Mike Hinchcliffe's special report
        User user = EPortalSecurityContext.getUser();
        System.out.println("Mike's User ID=" + user.getId());
        if (user.getId() == 14) { // 14 = Mike's userid 
            mv = new ModelAndView(new RedirectView("hinchcliffe.htm"));
        }

        List<PassFailBean> rsList = applicationManager.getStatisticsManager().getCustomerOverallPassFail();
        // Transpose the resultset into table friendly table.  Something like a pivot table.
        Integer custId = null;
        Integer buildingId = null;
        List<PassFailManagerBean> passFailManagerBeanList = new ArrayList<PassFailManagerBean>();
        PassFailManagerBean bean;
        List<Building> buildingList = null;
        for (PassFailBean b : rsList) {
            // New Customer
            if (custId == null || !b.getCustId().equals(custId)) {
                bean = new PassFailManagerBean();
                custId = b.getCustId();
                bean.setCustId(custId);
                bean.setCustomerName(b.getCustomerName());
                buildingList = new ArrayList<Building>();
                Building building = new Building();
                building.setBuildingId(b.getBuildingId());
                building.setBuildingName(b.getBuildingName());
                building.setPass(b.getPass());
                building.setFail(b.getFail());
                building.setInaccessible(b.getInaccessible());
                building.setFailedRepaired(b.getFailedRepaired());
                building.setInaccessibleRepaired(b.getInaccessibleRepaired());
                building.setNewConstruction(b.getNewConstruction());
                building.setPending(b.getPending());
                building.setFailInaccessible(b.getFailInaccessible());

                buildingList.add(building);
                bean.setBuildingList(buildingList);
                passFailManagerBeanList.add(bean);
            } else {
                Building building = new Building();
                building.setBuildingId(b.getBuildingId());
                building.setBuildingName(b.getBuildingName());
                building.setPass(b.getPass());
                building.setFail(b.getFail());
                building.setInaccessible(b.getInaccessible());
                building.setFailedRepaired(b.getFailedRepaired());
                building.setInaccessibleRepaired(b.getInaccessibleRepaired());
                building.setNewConstruction(b.getNewConstruction());
                building.setPending(b.getPending());
                building.setFailInaccessible(b.getFailInaccessible());
                buildingList.add(building);
            }
        }

        mv.addObject("passFailManagerList", passFailManagerBeanList);

        List<PassFailBean> passFailList = applicationManager.getStatisticsManager().getCustomerPassFail();

        System.out.println("Size: " + passFailList.size());
        mv.addObject("passFailList", passFailList);

        CustomWebUtils.setRoleInsideModel(mv);

        return mv;

    }

    public class PassFailManagerBean {

        private Integer custId;
        private String customerName;
        private List<Building> buildingList;

        /**
         * @return the custId
         */
        public Integer getCustId() {
            return custId;
        }

        /**
         * @param custId the custId to set
         */
        public void setCustId(Integer custId) {
            this.custId = custId;
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
    }

    public class Building {

        private Integer buildingId;
        private String buildingName;
        private Integer pass;
        private Integer fail;
        private Integer inaccessible;
        private Integer failedRepaired;
        private Integer inaccessibleRepaired;
        private Integer newConstruction;
        private Integer pending;
        private Integer failInaccessible;

        /**
         * @return the buildingId
         */
        public Integer getBuildingId() {
            return buildingId;
        }

        /**
         * @param buildingId the buildingId to set
         */
        public void setBuildingId(Integer buildingId) {
            this.buildingId = buildingId;
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
         * @return the pass
         */
        public Integer getPass() {
            return pass;
        }

        /**
         * @param pass the pass to set
         */
        public void setPass(Integer pass) {
            this.pass = pass;
        }

        /**
         * @return the fail
         */
        public Integer getFail() {
            return fail;
        }

        /**
         * @param fail the fail to set
         */
        public void setFail(Integer fail) {
            this.fail = fail;
        }

        /**
         * @return the inaccessible
         */
        public Integer getInaccessible() {
            return inaccessible;
        }

        /**
         * @param inaccessible the inaccessible to set
         */
        public void setInaccessible(Integer inaccessible) {
            this.inaccessible = inaccessible;
        }

        /**
         * @return the failedRepaired
         */
        public Integer getFailedRepaired() {
            return failedRepaired;
        }

        /**
         * @param failedRepaired the failedRepaired to set
         */
        public void setFailedRepaired(Integer failedRepaired) {
            this.failedRepaired = failedRepaired;
        }

        /**
         * @return the inaccessibleRepaired
         */
        public Integer getInaccessibleRepaired() {
            return inaccessibleRepaired;
        }

        /**
         * @param inaccessibleRepaired the inaccessibleRepaired to set
         */
        public void setInaccessibleRepaired(Integer inaccessibleRepaired) {
            this.inaccessibleRepaired = inaccessibleRepaired;
        }

        /**
         * @return the newConstruction
         */
        public Integer getNewConstruction() {
            return newConstruction;
        }

        /**
         * @param newConstruction the newConstruction to set
         */
        public void setNewConstruction(Integer newConstruction) {
            this.newConstruction = newConstruction;
        }

        /**
         * @return the pending
         */
        public Integer getPending() {
            return pending;
        }

        /**
         * @param pending the pending to set
         */
        public void setPending(Integer pending) {
            this.pending = pending;
        }

        /**
         * @return the failInaccessible
         */
        public Integer getFailInaccessible() {
            return failInaccessible;
        }

        /**
         * @param failInaccessible the failInaccessible to set
         */
        public void setFailInaccessible(Integer failInaccessible) {
            this.failInaccessible = failInaccessible;
        }

    }
}
