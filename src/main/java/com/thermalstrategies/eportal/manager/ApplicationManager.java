/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author smurry
 */
public class ApplicationManager {

    private HibernateTemplate tstratDamperTemplate;
    private JdbcTemplate jdbcTemplate;
    private DamperManager damperManager;
    private CustomerManager customerManager;
    private BuildingManager buildingManager;
    private MailManager mailManager;
    private UserManager userManager;
    private LogManager logManager;
    private RepairHistoryManager repairHistoryManager;
    private NextTestDateManager nextTestDateManager;
    private StatisticsManager statisticsManager;
    private UtilitiesManager utilitiesManager;
    private ChartManager chartManager;
    private SiteManager siteManager;
    private ExcelManager excelManager;
    private CommentHistoryManager commentHistoryMannager;
    private TestCycleManager testCycleManager;
    private PictureManager pictureManager;
    private DrawingManager drawingManager;

    public ApplicationManager(SessionFactory tstratDamperSessionFactory, DataSource dataSource) {

        this.tstratDamperTemplate = new HibernateTemplate(tstratDamperSessionFactory);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.mailManager = new MailManager();
    }

    public DrawingManager getDrawingManager() {
        if (drawingManager == null) {
            drawingManager = new DrawingManager(getTstratDamperTemplate());
        }
        return drawingManager;
    }

    public PictureManager getPictureManager() {
        if (pictureManager == null) {
            pictureManager = new PictureManager(getTstratDamperTemplate());
        }
        return pictureManager;
    }

    public TestCycleManager getTestCycleManager() {
        if (testCycleManager == null) {
            testCycleManager = new TestCycleManager(getTstratDamperTemplate());
        }
        return testCycleManager;
    }

    public SiteManager getSiteManager() {
        if (siteManager == null) {
            siteManager = new SiteManager(getTstratDamperTemplate());
        }
        return siteManager;
    }

    public UtilitiesManager getUtilitiesManager() {
        if (utilitiesManager == null) {
            utilitiesManager = new UtilitiesManager(getTstratDamperTemplate(),jdbcTemplate);
        }
        return utilitiesManager;
    }

    public DamperManager getDamperManager() {
        if (damperManager == null) {
            damperManager = new DamperManager(getTstratDamperTemplate(),jdbcTemplate);
        }
        return damperManager;
    }

    public CustomerManager getCustomerManager() {
        if (customerManager == null) {
            customerManager = new CustomerManager(getTstratDamperTemplate());
        }
        return customerManager;
    }

    public BuildingManager getBuildingManager() {
        if (buildingManager == null) {
            buildingManager = new BuildingManager( getTstratDamperTemplate(), getCustomerManager());
        }
        return buildingManager;
    }

    public MailManager getMailManager() {
        if (mailManager == null) {
            mailManager = new MailManager();
        }
        return mailManager;

    }

    public UserManager getUserManager() {
        if (userManager == null) {
            userManager = new UserManager(getTstratDamperTemplate());
        }
        return userManager;

    }

    public LogManager getLogManager() {
        if (logManager == null) {
            logManager = new LogManager(getTstratDamperTemplate());
        }
        return logManager;

    }

    public RepairHistoryManager getRepairHistoryManager() {
        if (repairHistoryManager == null) {
            repairHistoryManager = new RepairHistoryManager(getTstratDamperTemplate());
        }
        return repairHistoryManager;

    }

    public NextTestDateManager getNextTestDateManager() {
        if (nextTestDateManager == null) {
            nextTestDateManager = new NextTestDateManager(getTstratDamperTemplate());
        }
        return nextTestDateManager;

    }

    public StatisticsManager getStatisticsManager() {
        if (statisticsManager == null) {
            statisticsManager = new StatisticsManager(getTstratDamperTemplate());
        }
        return statisticsManager;

    }

    public ChartManager getChartManager() {
        if (chartManager == null) {
            chartManager = new ChartManager(getTstratDamperTemplate());
        }
        return chartManager;

    }

    public ExcelManager getExcelManager() {
        if (excelManager == null) {
            excelManager = new ExcelManager(getTstratDamperTemplate());
        }
        return excelManager;

    }

    /**
     * @return the commentHistoryMannager
     */
    public CommentHistoryManager getCommentHistoryManager() {
        if (commentHistoryMannager == null) {
            commentHistoryMannager = new CommentHistoryManager(getTstratDamperTemplate(), jdbcTemplate);
        }
        return commentHistoryMannager;
    }

    /**
     * @return the tstratDamperTemplate
     */
    public HibernateTemplate getTstratDamperTemplate() {
        return tstratDamperTemplate;
    }

    /**
     * @param tstratDamperTemplate the tstratDamperTemplate to set
     */
    public void setTstratDamperTemplate(HibernateTemplate tstratDamperTemplate) {
        this.tstratDamperTemplate = tstratDamperTemplate;
    }


}
