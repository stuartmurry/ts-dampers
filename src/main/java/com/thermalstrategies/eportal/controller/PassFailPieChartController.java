/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.awt.Color;
import java.io.OutputStream;
import java.text.AttributedString;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Stuart
 */
public class PassFailPieChartController implements Controller, PieSectionLabelGenerator {

    private ApplicationManager applicationManager;

    public PassFailPieChartController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer pass = CustomWebUtils.getRequestIntegerParameter(request, "pass");
        Integer fail = CustomWebUtils.getRequestIntegerParameter(request, "fail");
        Integer inaccessible = CustomWebUtils.getRequestIntegerParameter(request, "inaccessible");
        String customer = request.getParameter("customer");

        response.setContentType("image/png");
        OutputStream out = response.getOutputStream();
        try {
            // create a dataset...
            DefaultPieDataset dataset = new DefaultPieDataset();
            Integer total = pass + fail + inaccessible;

            Double passedPercent = pass.doubleValue()/total.doubleValue() * 100;
            Double faliedPercent = fail.doubleValue()/total.doubleValue() * 100;
            Double inaccessiblePercent = inaccessible.doubleValue()/total.doubleValue() * 100;

            dataset.setValue("PASS", passedPercent.intValue());
            dataset.setValue("FAIL", faliedPercent.intValue());
            dataset.setValue("INACCESSIBLE", inaccessiblePercent.intValue());

            // create a chart...
            JFreeChart chart = ChartFactory.createPieChart(
                    customer,
                    dataset,
                    true, // legend?
                    true, // tooltips?
                    false // URLs?
                    );
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setExplodePercent(1, 0.30);
            plot.setSectionPaint(0, Color.GREEN);
            plot.setSectionPaint(1, Color.RED);
            plot.setSectionPaint(2, Color.BLUE);

//            plot.setLegendLabelGenerator(this);
            plot.setLabelGenerator(this);
            plot.setLegendLabelGenerator(this);

            ChartUtilities.writeChartAsPNG(out, chart, 400, 300);
        } catch(Exception e)
        {
            e.printStackTrace();
        }

        finally {
            out.close();
        }

        // We are taking over response mode
        return null;
    }

    public String generateSectionLabel(PieDataset dataSet, Comparable c) {

        Double i = null;
        try {
//            System.out.println("Comparable value here: " + c);
            i = (Double)dataSet.getValue(c);
            return c + ": " + i.intValue() + "%";
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        return "0%";

    }

    public AttributedString generateAttributedSectionLabel(PieDataset arg0, Comparable arg1) {
        return new AttributedString("sdfadsfsd");
    }
}
