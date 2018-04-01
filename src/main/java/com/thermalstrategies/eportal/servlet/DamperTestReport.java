/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.servlet;

import com.thermalstrategies.eportal.controller.DamperListBean;
import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Stuart
 */
public class DamperTestReport extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");
        Integer buildingId = CustomWebUtils.getRequestIntegerParameter(request, "buildingId");
        Integer floorId = CustomWebUtils.getRequestIntegerParameter(request, "floorId");
        Integer pageNum = CustomWebUtils.getRequestIntegerParameter(request, "pageNum");
        Integer level = CustomWebUtils.getRequestIntegerParameter(request, "level");
        Integer year = CustomWebUtils.getRequestIntegerParameter(request, "year");
        Integer status = CustomWebUtils.getRequestIntegerParameter(request, "status");
        String occupancy = request.getParameter("occupancy");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            OutputStream os = response.getOutputStream();

            // Get the the applicationManager from the SpringFramework container
            ServletContext servletContext = this.getServletContext();
            WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
            ApplicationManager applicationManager = (ApplicationManager) wac.getBean("applicationManager");

            // Setup response mode...
            response.setContentType("application/vnd.ms-excel");
            WritableWorkbook wb = Workbook.createWorkbook(os);

            List<DamperListBean> beanList = applicationManager.getDamperManager().getDamperTestList(occupancy, status, year, custId, buildingId, floorId, pageNum);

            int sheetIncrementer = 0;
            Map<Integer, WritableSheet> floorMap = new HashMap<Integer, WritableSheet>();
            Map<Integer, Integer> sheetMarkerMap = new HashMap<Integer, Integer>();
            for (DamperListBean dlb : beanList) {

                Integer flrId = dlb.getFloorId();
                if (!floorMap.containsKey(flrId)) {
                    WritableSheet ws = wb.createSheet(dlb.getBuilding() + "-" + dlb.getFloor(), sheetIncrementer++);
                    // Create header columns
                    ws.addCell(new Label(0, 0, "Date Tested")); // Start at zero
                    ws.addCell(new Label(1, 0, "Alias ID"));
                    ws.addCell(new Label(2, 0, "Customer"));
                    ws.addCell(new Label(3, 0, "Building"));
                    ws.addCell(new Label(4, 0, "Floor"));

                    ws.addCell(new Label(5, 0, "Size"));
                    ws.addCell(new Label(6, 0, "System"));

                    ws.addCell(new Label(7, 0, "Status"));
                    ws.addCell(new Label(8, 0, "Location"));
                    ws.addCell(new Label(9, 0, "Sublocation"));
                    ws.addCell(new Label(10, 0, "Comments"));

                    floorMap.put(flrId, ws);
                }

                WritableSheet currentSheet = floorMap.get(flrId);

                Date dateTested = dlb.getDate();
                String aliasId = toAllCaps(dlb.getAliasId());
                String customer = toAllCaps(dlb.getCustomer());
                String building = toAllCaps(dlb.getBuilding());
                String floor = toAllCaps(dlb.getFloor());
                String size = dlb.getDimensions();
                String system = toAllCaps(dlb.getSystem());
                String sts = toAllCaps(dlb.getStatus());
                String location = toAllCaps(dlb.getLocation());
                String sublocation = toAllCaps(dlb.getSublocation());
                String comments = toAllCaps(dlb.getComment());

                if (!sheetMarkerMap.containsKey(flrId)) {
                    sheetMarkerMap.put(flrId, 1); // Start at 1
                }

                Integer row = sheetMarkerMap.get(flrId);

                // Customer
                currentSheet.addCell(new DateTime(0, row, dateTested));
                currentSheet.addCell(new Label(1, row, aliasId));
                currentSheet.addCell(new Label(2, row, customer));
                currentSheet.addCell(new Label(3, row, building));
                currentSheet.addCell(new Label(4, row, floor));

                currentSheet.addCell(new Label(5, row, size));
                currentSheet.addCell(new Label(6, row, system));

                currentSheet.addCell(new Label(7, row, sts));
                currentSheet.addCell(new Label(8, row, location));
                currentSheet.addCell(new Label(9, row, sublocation));
                currentSheet.addCell(new Label(10, row, comments));

                sheetMarkerMap.put(flrId, row + 1);
            }

            // All sheets and cells added. Now write out the workbook
            wb.write();
            wb.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (Exception e) {
            }

        }
    }

    private String toAllCaps(String s) {
        if (s == null) {
            return "";
        }
        return s.toUpperCase();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
