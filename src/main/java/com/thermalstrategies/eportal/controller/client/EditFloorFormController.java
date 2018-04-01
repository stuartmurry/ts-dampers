/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller.client;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.model.Building;
import com.thermalstrategies.eportal.model.Buildingfloor;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Stuart Murry
 */
public class EditFloorFormController extends AbstractFormController {

    public static final String LISTBUILDING = "listbuilding";
    public static final String ACTION = "action";
    public static final String SAVE = "Save";
    public static final String DELETE = "create";
    public static final String CANCEL = "cancel";
    public static final String CUSTID = "custId";
    public static final String BUILDID = "buildId";
    public static final String FLOORID = "floorId";
    private ApplicationManager applicationManager;

    public EditFloorFormController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        setCommandClass(FloorCommandBean.class);
    }




    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException arg3) throws Exception {
        Integer buildId = CustomWebUtils.getRequestIntegerParameter(request, "buildId");
        Integer floorId = CustomWebUtils.getRequestIntegerParameter(request, "floorId");
        ModelAndView mv = new ModelAndView("success");
        CustomWebUtils.setRoleInsideModel(mv);
        FloorCommandBean commander = (FloorCommandBean) command;
        String save = request.getParameter("save");
        System.out.println("Save: " + save);
        if (save != null) {
            Buildingfloor floor;
            if (floorId == null) {
                floor = new Buildingfloor();
                Building building = applicationManager.getBuildingManager().getBuilding(buildId);
                floor.setBuilding(building);
            } else {
                floor = applicationManager.getBuildingManager().getFloor(floorId);
            }
            floor.setFloorName(commander.getFloorName());
            floor.setSequenceNum(commander.getSequenceNum());

            applicationManager.getBuildingManager().saveBuildingFloor(floor);
            mv.addObject("message", "Floor Successfully saved..");
        }

        mv.setView(new RedirectView("listclient.htm"));

        // boolean cancel = "".equals(request.getParameter( CANCEL )) ? false : true;
        return mv;

    }



    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException arg2) throws Exception {
        boolean newButton = "new".equals(request.getParameter(ACTION));
        ModelAndView mv = new ModelAndView("client/editfloor");
        CustomWebUtils.setRoleInsideModel(mv);
        if (newButton) {
            String buildIdParamId = request.getParameter(BUILDID);
            int buildingId = Integer.parseInt(buildIdParamId);
            mv.addObject("buildId", buildingId);
            mv.addObject("command", new FloorCommandBean());
            return mv;
        }

        boolean edit = "edit".equals(request.getParameter(ACTION));
        if (edit) {
            String buildIdParamId = request.getParameter(BUILDID);
            int buildingId = Integer.parseInt(buildIdParamId);

            String buildingFloorParamId = request.getParameter(FLOORID);
            int buildingFloorId = Integer.parseInt(buildingFloorParamId);
            Buildingfloor buildingfloor = applicationManager.getBuildingManager().getFloor(buildingFloorId);

            mv.addObject("buildId", buildingId);

            FloorCommandBean bean = new FloorCommandBean();
            BeanUtils.copyProperties(buildingfloor, bean);
            mv.addObject("command", bean);
            return mv;
        }

        boolean delete = "delete".equals(request.getParameter(ACTION));
        if (delete) {
            //String buildIdParamId = request.getParameter( BUILDID );
            //int buildingId = Integer.parseInt( buildIdParamId );

            String buildingFloorParamId = request.getParameter(FLOORID);
            int buildingFloorId = Integer.parseInt(buildingFloorParamId);
            Buildingfloor buildingfloor = applicationManager.getBuildingManager().getFloor(buildingFloorId);
            applicationManager.getBuildingManager().deleteFloor(buildingfloor);

            return new ModelAndView(new RedirectView("listclient.htm"));
        }

        return new ModelAndView("client/editclient");
    }

}







