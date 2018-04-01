/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


/**
 *
 * @author smurry
 */
public class ErrorController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String message = request.getParameter("message");
        ModelAndView mv = new ModelAndView("success");
        mv.addObject("message", message);
        return mv;
    }

    


}
