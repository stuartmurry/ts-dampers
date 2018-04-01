package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

public class NewTestCycleController implements Controller {
    private ApplicationManager applicationManager;

    public NewTestCycleController(ApplicationManager applicationManager)
    {
        this.applicationManager = applicationManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView(new RedirectView("utilities.htm"));

        Integer buildingId = CustomWebUtils.getRequestIntegerParameter(request, "buildingId");

        applicationManager.getUtilitiesManager().startNewTestCycle(buildingId);

        return mv;
    }
}
