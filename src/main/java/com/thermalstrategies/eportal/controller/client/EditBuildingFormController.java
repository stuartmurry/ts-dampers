/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller.client;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.model.Building;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.model.Site;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Stuart Murry
 */
public class EditBuildingFormController extends SimpleFormController {

    public static final String SAVE = "save";
    public static final String ACTION = "action";
    public static final String NEW = "new";
    public static final String BACK = "back";
    public static final String DELETE = "delete";
    public static final String CANCEL = "cancel";
    public static final String CUSTID = "custId";
    public static final String BUILDID = "buildId";
    private ApplicationManager applicationManager;

    public EditBuildingFormController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        setCommandClass(BuildingCommandBean.class);
    }

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors, Map controlModel) throws Exception {
        System.out.println("Show form - Edit Building Form Controller -");

        ModelAndView mv = new ModelAndView("client/editbuilding");
        CustomWebUtils.setRoleInsideModel(mv);
        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");
        Integer buildingId = CustomWebUtils.getRequestIntegerParameter(request, "buildId");
        BuildingCommandBean command = new BuildingCommandBean();
        if (buildingId != null) {
            Building b = applicationManager.getBuildingManager().getBuilding(buildingId);
            BeanUtils.copyProperties(b, command);
        }
        List siteList = applicationManager.getSiteManager().getSiteList(custId);
        command.setSiteList(siteList);

//        request.getSession().setAttribute("command", command);
        mv.addObject("command", command);
        mv.addObject("custId", custId);
        mv.addObject("buildId", buildingId);
        return mv;

    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        BuildingCommandBean c = (BuildingCommandBean) command;
        // Was the update button pressed?
        ModelAndView mv = new ModelAndView(new RedirectView("listclient.htm"));
        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");
        boolean save = request.getParameter(SAVE) != null;
        if (save) {

            applicationManager.getBuildingManager().saveBuilding(c, custId);
        }

        return mv;
    }
}
