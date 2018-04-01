/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.controller.damper.DamperCommandBean;
import com.thermalstrategies.eportal.utils.AbstractValidator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;

/**
 *
 * @author Stuart
 */
public class DamperValidator extends AbstractValidator<DamperCommandBean> {

    @Override
    public void doValidate(DamperCommandBean e, Errors errors) {

        String comments = e.getComments();
        comments = comments == null ? "" : comments.trim();

        if (e.getDamperstatus_id() == 0) {
            errors.rejectValue("damperstatus_id", "error.dampertest.damperstatus");
        } else {
            // Check if it failed or inaccessable. Require the user to comment if so.
            Integer status = e.getDamperstatus_id();
            if (status == 2 || status == 3 || status == 4 || status == 5) {
                
                if ("".equals(comments)) {
                    errors.rejectValue("comments", "error.dampertest.comments");
                }
            }
            
            if (status == 4 || status == 5) {
                if (e.getRepairHistoryCount() == 0)
                {
                    // If they typed something  into repair history
                    if ("".equals(e.getRepairHistory().trim()))
                    {
                        errors.rejectValue("repairHistory", "error.dampertest.repairhistory");
                    }
                }
                
            }



        }

//        if (comments.toCharArray().length > 255) {
//            errors.rejectValue("comments", "error.dampertest.comments.toolong");
//        }
        if (e.getDampermaterial_id() == 0) {
            errors.rejectValue("dampermaterial_id", "error.dampertest.dampermaterial");
        }
        if (e.getSizew() == null || "".equals(e.getSizew())) {
            errors.rejectValue("sizew", "error.dampertest.sizew");
        }
        if (e.getSizel() == null || "".equals(e.getSizel())) {
            errors.rejectValue("sizel", "error.dampertest.sizel");
        }
        String system = e.getSystem();
        if ("".trim().equals(system == null ? "" : system.trim())) {
            errors.rejectValue("system", "error.dampertest.dampersystem");
        }
        if (e.getDampertype_id() == 0) {
            errors.rejectValue("dampertype_id", "error.dampertest.dampertype");
        }
        String location = e.getLocation();
        if ("".trim().equals(location == null ? "" : location.trim())) {
            errors.rejectValue("location", "error.dampertest.location");
        }
        String sublocation = e.getSublocation();
        if ("".trim().equals(sublocation == null ? "" : sublocation.trim())) {
            errors.rejectValue("sublocation", "error.dampertest.sublocation");
        }

        System.out.println(e.toString());

    }
//    protected void validateCustomer(int customerId) {
//        if (customerId == 0) {
//            errors.rejectValue("customer_id", "error.dampertest.customer");
//            this.hasErrors = true;
//        }
//    }
//
//    /**
//     *
//     * @param errors
//     * @param buildingId
//     * @param err
//     *
//     */
//    protected void validateBuilding(int buildingId) {
//        if (buildingId == 0) {
//            errors.rejectValue("building_id", "error.dampertest.building");
//            this.hasErrors = true;
//        }
//    }
//
//    /**
//     *
//     * @param errors
//     * @param buildingfloorId
//     * @param err
//     */
//    protected void validateBuildingfloor(int buildingfloorId) {
//        if (buildingfloorId == 0) {
//            errors.rejectValue("buildingfloor_id", "error.dampertest.buildingfloor");
//            this.hasErrors = true;
//        }
//    }
}
