/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.PictureManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author vmurry
 */
public class PicturesController implements Controller {
    
    private HibernateTemplate hibernateTemplate;
    public PicturesController(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PictureManager pictureManager= new PictureManager(hibernateTemplate);
        Integer id = CustomWebUtils.getRequestIntegerParameter(request, "id");
        OutputStream os = response.getOutputStream();
        pictureManager.picture(os, id);
        return null;
    }


}
