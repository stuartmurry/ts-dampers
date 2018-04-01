/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.utils;


import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Stuart
 */
public class ErrorModelAndView extends ModelAndView {

    public ErrorModelAndView(String view, BindException errors, Object command) {
        super(view);
        addAllObjects(errors.getModel());
        addObject("command", command);
        CustomWebUtils.setRoleInsideModel(this);
    }
}
