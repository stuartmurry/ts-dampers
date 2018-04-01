/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.controller.damper.DamperTypeCommandBean;
import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.model.Dampertype;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Stuart
 */
public class SelectDamperTypeController extends SimpleFormController{

    private ApplicationManager applicationManager;
    public SelectDamperTypeController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        
        setFormView("dampertype");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new DamperTypeCommandBean();
    }

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) throws Exception {
        ModelAndView mv = super.showForm(request, response, bindException);
        Integer id = CustomWebUtils.getRequestIntegerParameterWithSessionChecking(request, "id");
        Integer custId = CustomWebUtils.getRequestIntegerParameterWithSessionChecking(request, "custId");
        Integer buildingId = CustomWebUtils.getRequestIntegerParameterWithSessionChecking(request, "buildingId");
        Integer floorId = CustomWebUtils.getRequestIntegerParameterWithSessionChecking(request, "floorId");
        Integer singleUnit = CustomWebUtils.getRequestIntegerParameterWithSessionChecking(request, "singleUnit");

        List<Dampertype> dampertypelist = applicationManager.getDamperManager().getDamperTypeList();
        mv.addObject("dampertypelist", dampertypelist);
        // We are going to pass all this information to editDamper.htm?custId=&1buildingId=3&floorId=40
        mv.addObject("id", id);
        mv.addObject("custId", custId);
        mv.addObject("buildingId", buildingId);
        mv.addObject("floorId", floorId);
        mv.addObject("singleUnit", singleUnit);

        CustomWebUtils.setRoleInsideModel(mv);

        return mv;
    }

    private void processErrors(DamperTypeCommandBean command, ModelAndView mv, BindException errors) {
        mv.setViewName("dampertype");
        mv.addAllObjects(errors.getModel());
        List<CustomerBuildingFloorBean> customerBuildingFloorBeanList = applicationManager.getCustomerManager().getCustomerBuildingFloorList();
        mv.addObject("customerBuildingFloorBeanList", customerBuildingFloorBeanList);
        CustomWebUtils.setRoleInsideModel(mv);
        List<Dampertype> dampertypelist = applicationManager.getDamperManager().getDamperTypeList();
        mv.addObject("dampertypelist", dampertypelist);
        mv.addObject("command", command);
    }

    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object o, BindException errors) throws Exception {

        DamperTypeCommandBean command = (DamperTypeCommandBean)o;

        Integer custId = CustomWebUtils.getRequestIntegerParameterWithSessionChecking(request, "custId");
        Integer buildingId = CustomWebUtils.getRequestIntegerParameterWithSessionChecking(request, "buildingId");
        Integer floorId = CustomWebUtils.getRequestIntegerParameterWithSessionChecking(request, "floorId");
        Integer singleUnit = CustomWebUtils.getRequestIntegerParameterWithSessionChecking(request, "singleUnit");
//        Integer damperTypeId = CustomWebUtils.getRequestIntegerParameterWithSessionChecking(request, "damperTypeId");
        
        ModelAndView mv = new ModelAndView(new RedirectView("editDamper.htm?custId=" + custId + "&buildingId=" + buildingId + "&floorId=" + floorId + "&singleUnit=" + singleUnit + "&damperTypeId=" + command.getDampertype_id()));

        if (errors.hasErrors()) {
            System.out.println("Has Errors");
            // If this is an existing damper with a repair history then retrieve it
            processErrors(command, mv, errors);
//            mv.setViewName("damper");
        }
        
        return mv;
    }

}
