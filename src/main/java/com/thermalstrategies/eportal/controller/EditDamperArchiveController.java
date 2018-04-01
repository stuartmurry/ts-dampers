/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.controller.damper.DamperCommandBean;
import com.thermalstrategies.eportal.manager.CommentHistoryManager;
import com.thermalstrategies.eportal.manager.CustomerManager;
import com.thermalstrategies.eportal.manager.DamperManager;
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
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author smurry
 */
public class EditDamperArchiveController implements Controller {

    private HibernateTemplate template;
    private JdbcTemplate jdbcTemplate;
//    private static final String[] LETTERS = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public EditDamperArchiveController(SessionFactory sessionFactory, DataSource dataSource) {
        template = new HibernateTemplate(sessionFactory);
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse hsr1) throws Exception {
       
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
        
        ModelAndView mv = new ModelAndView("damper-archive");

        // Remember what kind of damper.
        request.getSession().setAttribute("damperTypeId", damperTypeId);

        DamperCommandBean bean = null;
        try {

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

            bean.setDamperMaterialList(damperManager.getDamperMaterial());
            bean.setDamperCommentList(damperManager.getDamperCommentList());
            
            mv.addObject("command", bean);
            CustomWebUtils.setRoleInsideModel(mv);

            // We will need this later
        } catch (Exception e) {
            return new ModelAndView("data-error");
        }
        return mv;
    }
}
