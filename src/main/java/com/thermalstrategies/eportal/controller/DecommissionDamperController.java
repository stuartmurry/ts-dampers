package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public class DecommissionDamperController extends SuccessController {

    public ApplicationManager applicationManager;

    public DecommissionDamperController(ApplicationManager applicationManager) {
        super(applicationManager);
        this.applicationManager = applicationManager;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        

        Integer id = CustomWebUtils.getRequestIntegerParameter(hsr, "id");

        applicationManager.getDamperManager().decommissionDamper(id);

        // This needs to be after the decommissioning
        ModelAndView mv = super.handleRequest(hsr, hsr1);
        return mv;

    }
}
