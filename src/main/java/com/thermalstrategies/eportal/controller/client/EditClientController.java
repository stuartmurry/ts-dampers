/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller.client;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
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
public class EditClientController extends SimpleFormController {

    public static final String LISTBUILDING = "listbuilding";
    public static final String ACTION = "action";
    public static final String SAVE = "save";
    public static final String DELETE = "create";
    public static final String CANCEL = "cancel";
    public static final String CUSTID = "custId";
    private ApplicationManager applicationManager;

    public EditClientController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        ClientCommandBean command = new ClientCommandBean();
        return command;
    }

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors, Map controlModel) throws Exception {
        ModelAndView mv = new ModelAndView("client/editclient");
        boolean newButton = "new".equals(request.getParameter(ACTION));
        if (newButton) {
            Customer customer = new Customer();
            mv.addObject("customer", customer);
        }

        boolean edit = "edit".equals(request.getParameter(ACTION));
        if (edit) {
            String customerParamId = request.getParameter(CUSTID);
            int customerId = Integer.parseInt(customerParamId);
            Customer customer = applicationManager.getCustomerManager().getCustomer(customerId);
            mv.addObject("customer", customer);
        }

        CustomWebUtils.setRoleInsideModel(mv);

        return mv;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {

        boolean save = request.getParameter(SAVE) != null;
        if (save) {
            System.out.println("Saving client...");
            Customer customer = new Customer();
            BeanUtils.copyProperties(command, customer);
            applicationManager.getCustomerManager().saveCustomer(customer);

            return new ModelAndView(new RedirectView("listclient.htm"));

        }

        System.out.println("Invalid submit...");
        // boolean cancel = "".equals(request.getParameter( CANCEL )) ? false : true;
        return new ModelAndView(new RedirectView("listclient.htm"));
    }
}
