/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import com.thermalstrategies.eportal.model.Building;
import com.thermalstrategies.eportal.model.Testcycle;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author Stuart
 */
public class UtilitiesManager extends BaseManager {

    private HibernateTemplate tstratDamperTemplate;
    private JdbcTemplate jdbcTemplate;

    public UtilitiesManager(HibernateTemplate tstratDamperTemplate, JdbcTemplate jdbcTemplate) {
        super(tstratDamperTemplate);
        this.tstratDamperTemplate = tstratDamperTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void recalulateNextTestDate() {
        StringBuffer sql = new StringBuffer();
        sql.append(" update dampertest d ");
        sql.append(" set next_test_date= ");
        sql.append(" case occupancy ");
        sql.append(" when 'BUSINESS' then Date_ADD(date_tested_ts, INTERVAL 4 YEAR) ");
        sql.append(" when 'NEW_CONSTRUCTION' then Date_ADD(date_tested_ts, INTERVAL 1 YEAR) ");
        sql.append(" else ");
        sql.append(" Date_ADD(date_tested_ts, INTERVAL 6 YEAR) END ");

        jdbcTemplate.execute(sql.toString());
    }

    public void startNewTestCycle(final Integer buildingId) {

        System.out.println("Starting new test cycle...");

        List<Testcycle> tcList = tstratDamperTemplate.loadAll(Testcycle.class);
        boolean alreadyExists = false;
        for (Testcycle tc : tcList) {
            Integer bldId = tc.getBuilding().getId();
            
            if (bldId.equals(buildingId)) {
                if (!tc.getComplete()) {
                    alreadyExists = true;
                }
            }
        }

        if (!alreadyExists) {

            Integer testCycleId = (Integer) tstratDamperTemplate.execute(new HibernateCallback() {

                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    Testcycle tc = new Testcycle();
//                    BeanUtils.copyProperties(ucb, user);
//                    Integer custId = ucb.getCustomer_id();
//                    Integer roleId = ucb.getRole_id();
//                    if (custId != null && custId > 0) {
//                        Customer customer = (Customer) session.load(Customer.class, custId);
//                        user.setCustomer(customer);
//                    }
//                    if (roleId != null && roleId > 0) {
//                        Role role = (Role) session.load(Role.class, roleId);
//                        user.setRole(role);
//                    }
//                    user.setCustomers(commaDelimetedCustIds);
                    Building building = (Building) session.load(Building.class, buildingId);
                    tc.setBuilding(building);
                    tc.setCustomer(building.getCustomer());
                    tc.setDescription(building.getBuildingName() + " started on " + (new Date()).toString());
                    tc.setStartdate(new Date());
                    tc.setComplete(false);
                    session.saveOrUpdate(tc);
                    return tc.getId();
                }
            });
            
            StringBuffer sql = new StringBuffer();
            sql = new StringBuffer();
            sql.append(" INSERT INTO dampertestarchive ");
            sql.append(" (testcycle_id,dampertest_id,alias_id,building_id,type_id,sizel,sizew,system,systemtype,material_id,floor_id, ");
            sql.append(" status_id,location,sublocation,dampernumber,series, ");
            sql.append(" date_tested_ts,repair_date,comments,tester,isenabled,customer_id,next_test_date,occupancy,special_procedures,xposition,yposition) ");
            sql.append(" select ?, id,alias_id,building_id,type_id,sizel,sizew,system,systemtype,material_id, ");
            sql.append(" floor_id,status_id,location,sublocation,dampernumber,series,date_tested_ts,repair_date, ");
            sql.append(" comments,tester,isenabled,customer_id,next_test_date,occupancy,special_procedures,xposition,yposition ");
            sql.append(" from dampertest ");
            sql.append(" where dampertest.building_id=? ");
            jdbcTemplate.update(sql.toString(), new Object[]{testCycleId, buildingId});
            
//            sql.append(" INSERT INTO commenthistory (date_tested_ts, dampertest_id, comments, status_id) ");
//            sql.append(" SELECT dt.date_tested_ts, dt.id, dt.comments, ds.id ");
//            sql.append(" FROM dampertest dt ");
//            sql.append("  inner join building b on b.id=dt.building_id ");
//            sql.append("  inner join damperstatus ds on ds.id=dt.status_id ");
//            sql.append(" where b.id=? ");
//            jdbcTemplate.update(sql.toString(), new Object[]{buildingId});

            sql = new StringBuffer();
            sql.append(" update dampertest dt ");
            sql.append(" inner join building b on b.id=dt.building_id ");
            sql.append(" set comments='', date_tested_ts=null, dt.status_id=null, dt.tester=null ");
            sql.append(" where b.id=? ");
            jdbcTemplate.update(sql.toString(), new Object[]{buildingId});

//            sql = new StringBuffer();
//            sql.append(" INSERT INTO testcycle (customer_id, building_id, startdate, description, complete) ");
//            sql.append(" SELECT c.id, b.id, curdate(), concat(b.building_name, ' test started on ', curdate()), false ");
//            sql.append(" FROM building b ");
//            sql.append(" inner join customer c on c.id=b.customer_id ");
//            sql.append(" where b.id=? ");
//            jdbcTemplate.update(sql.toString(), new Object[]{buildingId});
            

        }
    }

    public List<Testcycle> getTestCycleList() {
        return tstratDamperTemplate.loadAll(Testcycle.class);
    }
}
