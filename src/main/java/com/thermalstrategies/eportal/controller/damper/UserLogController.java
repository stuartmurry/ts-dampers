/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller.damper;


import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.model.Userlog;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Stuart Murry
 */
public class UserLogController implements Controller
{
    public static final String ACTION = "action";
    public static final String ID = "id";
    public static final String PARAM = "param";

    private ApplicationManager dao;
    
    protected UserLogController(ApplicationManager dao)
    {
            this.dao = dao;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        String action = request.getParameter(ACTION);
        String param = request.getParameter(PARAM);
        String actionId = request.getParameter(ID);
        int id = actionId == null ? 0 : Integer.parseInt( actionId );
        
        if ("list".equals(action))
        {
            List<Userlog> userLogList = dao.getLogManager().getUserLogList();
            Map map = new HashMap<String, Object>();
            map.put("userloglist", userLogList);
            return new ModelAndView("damper/userloglist", map);
        }
        
        if ("endsession".equals(action))
        {
            dao.getLogManager().removeUserLog(id);

            List<Userlog> userLogList = dao.getLogManager().getUserLogList();
            Map map = new HashMap<String, Object>();
            map.put("userloglist", userLogList);
            return new ModelAndView("damper/userloglist", map);
        }
                
        return new ModelAndView("damper/userloglist");
    }


}
