/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.controller.damper.DamperCommandBean;
import com.thermalstrategies.eportal.manager.CommentHistoryManager;
import com.thermalstrategies.eportal.manager.CustomerManager;
import com.thermalstrategies.eportal.manager.DamperManager;
import com.thermalstrategies.eportal.manager.DamperManager.UNIT;
import com.thermalstrategies.eportal.manager.PictureManager;
import com.thermalstrategies.eportal.manager.RepairHistoryManager;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author smurry
 */
public class EditDamperController extends SimpleFormController {

    private HibernateTemplate template;
    private JdbcTemplate jdbcTemplate;
//    private static final String[] LETTERS = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public EditDamperController(SessionFactory sessionFactory, DataSource dataSource) {
        template = new HibernateTemplate(sessionFactory);
        jdbcTemplate = new JdbcTemplate(dataSource);
        setSessionForm(false); // Enable Session Form Handling
//        setBindOnNewForm(true);
        setFormView("damper");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        DamperManager damperManager = new DamperManager(template, jdbcTemplate);
        PictureManager pictureManager = new PictureManager(template);
        RepairHistoryManager repairHistoryManager = new RepairHistoryManager(template);
        CommentHistoryManager commentHistoryManager = new CommentHistoryManager(template, jdbcTemplate);

        Integer id = CustomWebUtils.getRequestIntegerParameter(request, "id");
        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");
        Integer buildingId = CustomWebUtils.getRequestIntegerParameter(request, "buildingId");
        Integer floorId = CustomWebUtils.getRequestIntegerParameter(request, "floorId");
        Integer damperTypeId = CustomWebUtils.getRequestIntegerParameter(request, "damperTypeId");
        Integer singleUnit = CustomWebUtils.getRequestIntegerParameter(request, "singleUnit");
        Integer addInSeries = CustomWebUtils.getRequestIntegerParameter(request, "addInSeries");

        // Remember what kind of damper.
        request.getSession().setAttribute("damperTypeId", damperTypeId);

        DamperCommandBean bean = null;
        try {
            if (id == null) {
                // We need to create a new damper
                UNIT unit;
                if (singleUnit == 2) {
                    unit = UNIT.MULTIPLE;
                } else {
                    unit = UNIT.SINGLE;
                }
                bean = damperManager.createDamperCommandBean(custId, buildingId, floorId, damperTypeId, unit);
                bean.setShowDecommissionedButton(false);
            } else {
                if (addInSeries != null) {
                    bean = damperManager.createDamperCommandBean(id);
                    bean.setShowDecommissionedButton(false);
                } else {
                    bean = damperManager.getDamperCommandBean(id);
                    bean.setShowDecommissionedButton(true);
                }

                List<RepairHistoryBean> rhbList = repairHistoryManager.getRepairHistoryList(null, id, null);

                bean.setRepairHistoryList(rhbList);
                bean.setRepairHistoryCount(rhbList.size());

                List<CommentHistoryBean> chbList = commentHistoryManager.getCommentHistoryListByArchive(id);
                bean.setCommentHistoryBeanList(chbList);
                bean.setCommentHistoryCount(chbList.size());

                bean.setPictureIdList(pictureManager.getPictureIdList(id));

            }

            bean.setDamperMaterialList(damperManager.getDamperMaterial());
            bean.setDamperCommentList(damperManager.getDamperCommentList());

            // We will need this later
        } catch (Exception e) {
            request.getSession().setAttribute("error_damper", true);
            e.printStackTrace();
            return new DamperCommandBean();
        }
        if (bean == null) {
            request.getSession().setAttribute("error_damper", true);
            return new DamperCommandBean();
        } else {
            request.getSession().setAttribute("error_damper", false);
            return bean;
        }

    }

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) throws Exception {

        Integer id = CustomWebUtils.getRequestIntegerParameter(request, "id");
        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");

        boolean b = (Boolean) request.getSession().getAttribute("error_damper");
        if (b) {
            return new ModelAndView("expired");
        }

        request.getSession().setAttribute("damper_form_submitted", false);

        // Let the form take its natural course.
        ModelAndView mv = super.showForm(request, response, bindException);
        prepModel(mv, custId);
        return mv;

    }

    private void processErrors(DamperCommandBean command, ModelAndView mv, BindException errors, Integer custId) {
        mv.setViewName("damper");
        mv.addAllObjects(errors.getModel());
        prepModel(mv, custId);
        if (command.getId() != null) {
            RepairHistoryManager repairHistoryManager = new RepairHistoryManager(template);
            List<RepairHistoryBean> rhbList = repairHistoryManager.getRepairHistoryList(null, command.getId(), null);
            command.setRepairHistoryList(rhbList);
        }
        mv.addObject("command", command);
    }

    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object o, BindException errors) throws Exception {
        //<a href="listDampers.htm?custId=${cust.id}&buildingId=${building.id}&floorId=${floor.id}" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${floor.floorName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>

        String save = request.getParameter("Save");
        String clone = request.getParameter("Clone");
        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");
        Integer addInSeries = CustomWebUtils.getRequestIntegerParameter(request, "addInSeries");

        DamperCommandBean command = (DamperCommandBean) o;

        // Check for resubmit
        try {
            boolean b = (Boolean) request.getSession().getAttribute("damper_form_submitted");
            if (b) {
                return new ModelAndView("expired");
            }
        } catch (Exception e) {
            return new ModelAndView("expired");
        }

        ModelAndView mv = new ModelAndView();
        if (errors.hasErrors()) {
            // If this is an existing damper with a repair history then retrieve it

            processErrors(command, mv, errors, custId);
//            mv.setViewName("damper");
        } else if (save != null) {
            // Save the damper...
            try {
                if ("customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
                    errors.reject("error.damper.save.exception");
                } else {
                    if (new Integer(2).equals(addInSeries)) {
                        // Add in Series
                        command.setId(null);
                    }
                    DamperManager damperManager = new DamperManager(template, jdbcTemplate);
                    damperManager.saveDamper(command);
                    mv.setView(new RedirectView("success.htm?custId=" + command.getCustomer_id() + "&buildingId=" + command.getBuilding_id() + "&floorId=" + command.getBuildingfloor_id() + "&message=damper%20saved%20successfully"));
                    // mv.addObject("message", "Damper saved successfully.");
                    // Added here because of back button failed
                    request.getSession().setAttribute("damper_form_submitted", true);
                }

                request.getSession().setAttribute("user_form_submitted", true);

            } catch (Exception e) {
                e.printStackTrace();
                return CustomWebUtils.getErrorModelAndView("Error: Most likely you are trying to change a database record created by another tester: returned from server: " + e.getMessage());
            }

        } else if (clone != null) {
            // Clone the damper...
            try {
                if ("customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
                    errors.reject("error.damper.save.exception");
                } else {
                    if (new Integer(2).equals(addInSeries)) {
                        // Add in Series
                        command.setId(null);
                    }
                    DamperManager damperManager = new DamperManager(template, jdbcTemplate);
                    damperManager.saveDamper(command, true);
                    mv.setView(new RedirectView("success.htm?custId=" + command.getCustomer_id() + "&buildingId=" + command.getBuilding_id() + "&floorId=" + command.getBuildingfloor_id() + "&message=damper%20saved%20successfully"));
                    // mv.addObject("message", "Damper saved successfully.");
                    // Added here because of back button failed
                    request.getSession().setAttribute("damper_form_submitted", true);
                }

                request.getSession().setAttribute("user_form_submitted", true);

            } catch (Exception e) {
                e.printStackTrace();
                return CustomWebUtils.getErrorModelAndView("Error: Most likely you are trying to change a database record created by another tester: returned from server: " + e.getMessage());
            }
        } else {
            // Wierd Submit, treat it like an error
            processErrors(command, mv, errors, custId);
        }

        return mv;

    }

    private void prepModel(ModelAndView mv, Integer custId) {
        CustomerManager customerManager = new CustomerManager(template);
        List<CustomerBuildingFloorBean> customerBuildingFloorBeanList = customerManager.getCustomerBuildingFloorList(custId);
        mv.addObject("customerBuildingFloorBeanList", customerBuildingFloorBeanList);
        CustomWebUtils.setRoleInsideModel(mv);
    }
}
