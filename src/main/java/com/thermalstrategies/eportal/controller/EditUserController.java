/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.controller.UserCommandBean.CustomerSelect;
import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.utils.CustomStringUtils;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import com.thermalstrategies.eportal.utils.ErrorModelAndView;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Stuart
 */
public class EditUserController extends SimpleFormController {

    private ApplicationManager applicationManager;

    EditUserController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;

//        setSessionForm(true);
//        setValidateOnBinding(false);
//		setFormView("project/cedUser");
//        setCacheSeconds(1);
        setCommandClass(UserCommandBean.class);
//        setValidator(new EditUserControllerValidator());
    }

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, org.springframework.validation.BindException bindException) throws Exception {
        Integer id = CustomWebUtils.getRequestIntegerParameter(request, "id");

        request.getSession().setAttribute("user_form_submitted", false);
        ModelAndView mv = new ModelAndView("user/user");
        UserCommandBean ucb;
        if (id != null) {
            ucb = applicationManager.getUserManager().getUser(id);
        } else {
            ucb = new UserCommandBean();
            ucb.setIsenabled(true);
        }

        String[] customerIds = CustomStringUtils.commaDelimetedStringToArray(ucb.getCustomers());
        ucb.setCustomerSelectList(createCustomerSelectList(ucb, customerIds));

        mv.addObject("command", ucb);

        CustomWebUtils.setRoleInsideModel(mv);
        return mv;
    }

    private List<CustomerSelect> createCustomerSelectList(UserCommandBean ucb, String[] customerIds) {
        List<CustomerSelect> customerSelectList = new ArrayList<CustomerSelect>();
        for (Customer c : applicationManager.getCustomerManager().getCustomerList()) {
            UserCommandBean.CustomerSelect cs = ucb.new CustomerSelect();
            String iid = c.getId().toString();
            String name = c.getCustomerName();
            cs.setId(iid);
            for (String ids : customerIds) {
                if (iid.equals(ids)) {
                    cs.setChecked(true);
                }
            }

            cs.setName(name);
            customerSelectList.add(cs);
        }
        return customerSelectList;
    }

    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, org.springframework.validation.BindException errors) throws Exception {

        UserCommandBean userCommandBean = (UserCommandBean) command;

        boolean b = (Boolean) request.getSession().getAttribute("user_form_submitted");
        if (b) {
            return new ModelAndView("expired");
        }

        String commaDelimitedString = "";
        for (Customer c : applicationManager.getCustomerManager().getCustomerList()) {
//            System.out.println("custId: " + c.getId());
            String currentId = request.getParameter(c.getId().toString());
            if (currentId != null && !"".equals(currentId)) {
//                System.out.println("currentId: " + currentId);
                commaDelimitedString = commaDelimitedString + c.getId() + ",";
            }
        }

        String commaDelimetedCustIds = CustomStringUtils.commaDelimetedStringToSqlInString(commaDelimitedString);


        if (userCommandBean.getRole_id() == 300 && "".equals(commaDelimetedCustIds)) {
            errors.reject("error.user.selectcustomer");
        }

        if (userCommandBean.getRole_id() == 400 && "".equals(commaDelimetedCustIds)) {
            errors.reject("error.user.selectcustomer2");
        } else if (userCommandBean.getRole_id() == 400) {
            // make sure the user only selects only one.
            if (CustomStringUtils.commaDelimetedStringToArray(commaDelimetedCustIds).length > 1) {
                errors.reject("error.user.selectcustomer2");
            } else {
                // lets assign a customer with this id
                try {
                    userCommandBean.setCustomer_id(Integer.parseInt(commaDelimetedCustIds));
                } catch(Exception e)
                {
                    errors.reject("error.user.retrievingcustomer");
                }

            }
        }


        if (errors.hasErrors()) {
            userCommandBean.setCustomerList(applicationManager.getCustomerManager().getCustomerList());
            String[] customerIds = new String[]{};
            userCommandBean.setCustomerSelectList(createCustomerSelectList(userCommandBean, customerIds));
            ModelAndView mv = new ErrorModelAndView("user/user", errors, command);
            return mv;
        }


        ModelAndView mv = new ModelAndView("success");
        String save = request.getParameter("Save");
        String delete = request.getParameter("Delete");

        if (save != null) {
            System.out.println("Saving...");

            try {
                applicationManager.getUserManager().saveUser(userCommandBean, commaDelimetedCustIds);
                request.getSession().setAttribute("user_form_submitted", true);
            } catch (Exception e) {
                return CustomWebUtils.getErrorModelAndView("Error: Most likely this username already exists: returned from server: " + e.getMessage());
            }

            mv.addObject("message", "User successfully saved... <br> <a href=\"listuser.htm\">Return to user list.</a>");

        }

        if (delete != null) {
            System.out.println("Deleting...");
            try {
                applicationManager.getUserManager().deleteUser(userCommandBean);
            } catch (Exception e) {
                return CustomWebUtils.getErrorModelAndView("Error: unable to delete this user: returned from server: " + e.getMessage());
            }
            mv.addObject("message", "User successfully deleted. <br> <a href=\"listuser.htm\">Return to user list.</a>");

        }

        // They canceled...  Send them back to the list.
        mv = new ModelAndView(new RedirectView("listuser.htm"));


        return mv;

    }
}
