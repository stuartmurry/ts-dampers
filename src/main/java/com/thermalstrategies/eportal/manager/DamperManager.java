/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import com.thermalstrategies.eportal.controller.DamperListBean;
import com.thermalstrategies.eportal.controller.damper.DamperCommandBean;
import com.thermalstrategies.eportal.exception.CustomSecurityException;
import com.thermalstrategies.eportal.model.Building;
import com.thermalstrategies.eportal.model.Buildingfloor;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.model.Dampercomment;
import com.thermalstrategies.eportal.model.Dampermaterial;
import com.thermalstrategies.eportal.model.Damperstatus;
import com.thermalstrategies.eportal.model.Dampertest;
import com.thermalstrategies.eportal.model.Dampertype;
import com.thermalstrategies.eportal.model.Pictures;
import com.thermalstrategies.eportal.model.Repairhistory;
import com.thermalstrategies.eportal.model.User;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import com.thermalstrategies.eportal.utils.CustTimer;
import com.thermalstrategies.eportal.utils.CustomDateUtils;
import com.thermalstrategies.eportal.utils.CustomNumberUtils;
import com.thermalstrategies.eportal.utils.DamperUtils;
import com.thermalstrategies.eportal.utils.DamperUtils.OCCUPANCY;
import com.thermalstrategies.eportal.utils.Page;
import com.thermalstrategies.eportal.utils.SequenceGenerator;
import com.thermalstrategies.eportal.utils.SessionUtils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author smurry
 */
public class DamperManager extends BaseManager {

    private static final boolean ENABLED = true;
//    private final String DAMPERTYPE_ALIASID_ELEMENT = "aliasId";
//    private final String DAMPERTEST_DAMPERNUMBER_ELEMENT = "dampernumber";
//    private final String DAMPERTEST_SERIES_ELEMENT = "series";
//    private final String CUSTOMER_PRIMARY_KEY = "customer.id";
//    private final String BUILDING_PRIMARY_KEY = "building.id";
//    private final String BUILDINGFLOOR_PRIMARY_KEY = "buildingfloor.id";
//    private final String DAMPERTYPE_PRIMARY_KEY = "dampertype.id";
    private static final String[] LETTERS = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private HibernateTemplate tstratDamperTemplate;
    private JdbcTemplate jdbcTemplate;

    public DamperManager(HibernateTemplate tstratDamperTemplate, JdbcTemplate jdbcTemplate) {
        super(tstratDamperTemplate);
        this.tstratDamperTemplate = tstratDamperTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 
     * @param dampertest
     *
     * @deprecated Required a setting in the *.hbm file lazy initialization
     */
    @Deprecated
    public void deleteDamper(final Dampertest dampertest) {
        tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                if ("Customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
                    throw new SQLException("You don't have permissions to delete this damper.  Contact your administrator.");
                }
                for (Repairhistory rh : dampertest.getRepairhistories()) {
                    session.delete(rh);
                }
                session.delete(dampertest);
                return null;
            }
        });

    }

    public void deleteDamper(final Integer id) {
        tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                if ("Customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
                    throw new SQLException("You don't have permissions to delete this damper.  Contact your administrator.");
                }

                Dampertest dampertest = (Dampertest) session.load(Dampertest.class, id);

                if ("employee".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
                    User user = dampertest.getUser();
                    if (!EPortalSecurityContext.getUser().getUserName().equals(user.getUserName())) {
                        throw new SQLException("You don't have permissions to delete this damper.  You are not the original author. Contact your supervisor to make a change to this damper.");
                    }
                }

                for (Repairhistory rh : dampertest.getRepairhistories()) {
                    session.delete(rh);
                }
                session.delete(dampertest);
                return null;
            }
        });
    }

    public void decommissionDamper(final Integer id) {


        tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                if ("Customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
                    throw new SQLException("You don't have permissions to decommission this damper.  Contact your administrator.");
                }

                Dampertest dampertest = (Dampertest) session.load(Dampertest.class, id);

                if ("employee".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
                    User user = dampertest.getUser();
                    if (!EPortalSecurityContext.getUser().getUserName().equals(user.getUserName())) {
                        throw new SQLException("You don't have the proper permissions to decommission this damper.  You are not the original author. Contact your supervisor to make a change to this damper.");
                    }
                }

                // Decommissioned
                Damperstatus damperstatus = (Damperstatus) session.load(Damperstatus.class, 6);

//                dampertest.setDampernumber(0);
//                dampertest.setSeries(0);
                dampertest.setDamperstatus(damperstatus);
                session.save(dampertest);

                return null;
            }
        });

    }

    public Dampertest getDamper(Integer id) {
        return (Dampertest) tstratDamperTemplate.load(Dampertest.class, id);
    }

    public enum UNIT {

        SINGLE, MULTIPLE
    }

    public DamperCommandBean getDamperCommandBean(final Integer id) {
        return (DamperCommandBean) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                StringBuffer sql = new StringBuffer();

                sql.append(" SELECT ");
                sql.append(" d.id, ");
                sql.append(" d.dateTestedTs, ");
                sql.append(" d.aliasId, ");
                sql.append(" c.id, ");
                sql.append(" c.customerName, ");
                sql.append(" b.id, ");
                sql.append(" b.buildingName, ");
                sql.append(" bf.id, ");
                sql.append(" bf.floorName, ");
                sql.append(" d.comments, ");
                sql.append(" dt.id, ");
                sql.append(" dt.typeName, ");
                sql.append(" d.sizel, ");
                sql.append(" d.sizew, ");
                sql.append(" d.system, ");
                sql.append(" dm.id, ");
//                sql.append(" dm.material_name, ");
                sql.append(" ds.id, ");
                sql.append(" d.location,");
                sql.append(" d.sublocation,");
                sql.append(" d.dampernumber,");
                sql.append(" d.series, ");
                sql.append(" d.nextTestDate, \n");

                // Calculate Highest in series
//                sql.append(" ( select max(dd.dampernumber) from Dampertest dd \n ");
//                sql.append("          inner join dd.dampertype as ddt \n");
//                sql.append("          inner join dd.customer as cc \n");
//                sql.append("          inner join dd.building as bb \n");
//                sql.append("          inner join dd.buildingfloor as bbf \n");
//                sql.append("          where ddt.aliasId = \n");
//                sql.append("          ( ");
//                sql.append("            select dddt.aliasId from Dampertype dddt where dddt.id=dt.id ");
//                sql.append("          ) \n");
//                sql.append("          and bb.id=b.id and cc.id=c.id and bbf.id=bf.id \n ");
////                sql.append("       ) ");
//                sql.append(" ),\n");
                sql.append(" b.aliasId, \n");
                sql.append(" dt.aliasId, \n");
                sql.append(" d.occupancy, \n");
                sql.append(" d.specialProcedures \n");


                sql.append(" FROM Dampertest d \n");
                sql.append("   inner join d.customer as c \n");
                sql.append("   inner join d.building as b \n");
                sql.append("   inner join d.buildingfloor as bf \n");
                sql.append("   inner join d.dampertype as dt \n");
                sql.append("   inner join d.dampermaterial as dm \n");
                sql.append("   left join d.damperstatus as ds \n");

                sql.append(" where d.id=" + id);

                Query query = session.createQuery(sql.toString());
                DamperCommandBean damperCommandBean = null;
                Object[] row = (Object[]) query.uniqueResult();
                if (row != null) {
                    damperCommandBean = new DamperCommandBean();
                    damperCommandBean.setId(CustomNumberUtils.parseInt(row[0]));
                    damperCommandBean.setDatetested(
                            CustomDateUtils.convertJasonMoonsDateToString(
                            CustomDateUtils.convertSQLDate(row[1])));
                    damperCommandBean.setAliasId(StringUtils.capitalize("" + row[2]));
                    damperCommandBean.setCustomer_id(CustomNumberUtils.parseInt(row[3]));
                    damperCommandBean.setCustomer(StringUtils.capitalize("" + row[4]));
                    damperCommandBean.setBuilding_id(CustomNumberUtils.parseInt(row[5]));
                    damperCommandBean.setBuilding(StringUtils.capitalize("" + row[6]));
                    damperCommandBean.setBuildingfloor_id(CustomNumberUtils.parseInt(row[7]));
                    damperCommandBean.setBuildingfloor(StringUtils.capitalize("" + row[8]));
                    damperCommandBean.setComments(StringUtils.capitalize("" + row[9]));
                    damperCommandBean.setDampertype_id(CustomNumberUtils.parseInt(row[10]));
                    damperCommandBean.setDampertype(StringUtils.capitalize("" + row[11]));
                    damperCommandBean.setSizel("" + row[12]);
                    damperCommandBean.setSizew("" + row[13]);
                    damperCommandBean.setSystem(StringUtils.capitalize("" + row[14]));
                    damperCommandBean.setDampermaterial_id(CustomNumberUtils.parseInt(row[15]));
                    // We don't need dampermaterial name.  The collection is preloaded separately
                    damperCommandBean.setDamperstatus_id(CustomNumberUtils.parseIntNullAsZero(row[16]));
                    damperCommandBean.setLocation(StringUtils.capitalize("" + row[17]));
                    damperCommandBean.setSublocation(StringUtils.capitalize("" + row[18]));
                    damperCommandBean.setDampernumber(CustomNumberUtils.parseIntNullAsZero(row[19]));
                    damperCommandBean.setSeries(CustomNumberUtils.parseIntNullAsZero(row[20]));
                    damperCommandBean.setNextTestDate(
                            CustomDateUtils.convertJasonMoonsDateToString(
                            CustomDateUtils.convertSQLDate(row[21])));
//                    damperCommandBean.setHighestDamperNumber(CustomNumberUtils.parseInt(row[22]));
                    damperCommandBean.setBuildingAliasId(StringUtils.capitalize("" + row[22]));
                    damperCommandBean.setDampertypeAliasId(StringUtils.capitalize("" + row[23]));
                    damperCommandBean.setOccupancy(StringUtils.capitalize("" + row[24]));

                    damperCommandBean.setSpecialProcedures(StringUtils.capitalize("" + row[25]));

                }

                return damperCommandBean;

            }
        });

    }

    public void saveDamper(DamperCommandBean command) throws CustomSecurityException {
        saveDamper(command, false);
    }

    public void saveDamper(final DamperCommandBean command, final boolean clone) throws CustomSecurityException {
        tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                // Make sure customers only see their stuff and not other clients

                System.out.println("Saving Damper...");
                if ("customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
                    throw new SQLException("You don't have permissions to save this damper.  Contact your administrator.");
                }

                int id = command.getId() == null ? 0 : command.getId();
                Dampertest dampertest;
                if (id > 0 && !clone) {

                    dampertest = (Dampertest) session.load(Dampertest.class, id);

                    // We don't have to pull up custId, buildingId, floorId, or damperTypeId
                    // because hibernate already has them connected when loading dampertest record

                    // Employees can only change their own records.
                    if ("employee".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
                        User user = dampertest.getUser();
                        if (user != null) {
                            if (!EPortalSecurityContext.getUser().getUserName().equals(user.getUserName())) {
                                throw new SQLException("You don't have permissions to save this damper.  You are not the original author. Contact your supervisor to make a change to this damper.");
                            }
                        }

                    }

                } else {

                    dampertest = new Dampertest();
                    Customer customer = (Customer) session.load(Customer.class, command.getCustomer_id());
                    Building building = (Building) session.load(Building.class, command.getBuilding_id());
                    Buildingfloor buildingFloor = (Buildingfloor) session.load(Buildingfloor.class, command.getBuildingfloor_id());
                    Dampertype dampertype = (Dampertype) session.load(Dampertype.class, command.getDampertype_id());

                    dampertest.setCustomer(customer);
                    dampertest.setBuilding(building);
                    dampertest.setBuildingfloor(buildingFloor);
                    dampertest.setDampertype(dampertype);
                    dampertest.setUser(EPortalSecurityContext.getUser());

                }

                populateDamperTest(id, dampertest, session, command);

                session.saveOrUpdate(dampertest);



                // save the image if it exists
                System.out.println("Saving Multi-Part File");
                try {
                    MultipartFile file = command.getPicture();

                    if (!file.isEmpty()) {
                        Pictures pictures = new Pictures();
                        pictures.setName(file.getOriginalFilename());
                        pictures.setDampertest(dampertest);
                        Blob blob = Hibernate.createBlob(file.getInputStream());
                        pictures.setImage(blob);
                        session.saveOrUpdate(pictures);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        });
    }

    private Dampertest populateDamperTest(Integer id, Dampertest dampertest, Session session, DamperCommandBean command) {
        System.out.println("Populating Dampertest");
        Damperstatus damperstatus = (Damperstatus) session.load(Damperstatus.class, command.getDamperstatus_id());
        Dampermaterial dampermaterial = (Dampermaterial) session.load(Dampermaterial.class, command.getDampermaterial_id());

        dampertest.setDamperstatus(damperstatus);
        dampertest.setDampermaterial(dampermaterial);

        String aliasId = command.getAliasId();
        dampertest.setAliasId(aliasId);
        dampertest.setComments(StringUtils.capitalize(command.getComments()));

//        dampertest.setDampernumber(command.getDampernumber());
//

        Integer damperNumber = DamperUtils.getDamperNumberFromAliasId(aliasId);
        dampertest.setDampernumber(damperNumber);
        String letter = DamperUtils.getDamperLetterFromAliasId(aliasId);
        Integer series = DamperUtils.letterToNumber(letter);
        dampertest.setSeries(series);
//        System.out.println("** AliasID->" + aliasId + " DamperNumber->" + damperNumber + " Letter->" + letter + " Series->" + series );

        dampertest.setDateTestedTs(CustomDateUtils.convertJasonMoonsStringToDate(command.getDatetested()));
        dampertest.setLocation(StringUtils.capitalize(command.getLocation()));
        if (id > 0) {
            // The user must save the damper before he he can alter the datetested.
            dampertest.setNextTestDate(CustomDateUtils.convertJasonMoonsStringToDate(command.getNextTestDate()));
        } else {
            //New damper so lets calculate the next test date

            dampertest.setNextTestDate(
                    DamperUtils.predictNextTestDate(CustomDateUtils.convertJasonMoonsStringToDate(command.getNextTestDate()), OCCUPANCY.valueOf(command.getOccupancy())));
        }

        dampertest.setOccupancy(command.getOccupancy());

        dampertest.setSizew(CustomNumberUtils.parseInt(command.getSizew()));
        dampertest.setSizel(CustomNumberUtils.parseInt(command.getSizel()));
        dampertest.setSublocation(StringUtils.capitalize(command.getSublocation()));
        dampertest.setSystem(StringUtils.capitalize(command.getSystem()));

        dampertest.setSpecialProcedures(StringUtils.capitalize(command.getSpecialProcedures()));

        String repairHistory = command.getRepairHistory();
        if (repairHistory != null && !"".equals(repairHistory.trim())) {
            Repairhistory history = new Repairhistory();
            history.setDescription(command.getRepairHistory());
            history.setRepairDate(new Date());
            history.setDampertest(dampertest);

            session.save(history);
        }

        return dampertest;
    }

    public List<Dampertype> getDamperTypeList() {
        return tstratDamperTemplate.loadAll(Dampertype.class);
    }

    public Page getDampertestList(final String occupancy, final Integer status, final Integer year, final Integer custId, final Integer buildingId, final Integer floorId, final Integer pageNum) throws DataAccessException {
        return (Page) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                CustTimer ct = new CustTimer("getDampertestList()", System.currentTimeMillis());

                StringBuffer sql = new StringBuffer();
                sql.append(" select dt.id, dt.aliasId, b.id, b.buildingName, dt.comments, c.id, c.customerName, dt.dateTestedTs, ");
                sql.append("     bf.id, bf.floorName, dt.location, dt.dampernumber, dt.series, ds.substatus, dt.sublocation, ");
                sql.append("     dt.system, t.typeName, dt.sizel, dt.sizew, dt.occupancy, (select count(p.id) from Pictures p where p.dampertest.id=dt.id) ");
                sql.append(" from Dampertest dt ");
                sql.append("     inner join dt.building as b ");
                sql.append("     inner join dt.customer as c ");
                sql.append("     inner join dt.buildingfloor as bf ");
                sql.append("     left join dt.damperstatus as ds ");
                sql.append("     inner join dt.dampertype as t ");

                StringBuffer countSql = new StringBuffer();
                countSql.append(" select count(*) ");
                countSql.append(" from Dampertest dt ");
                countSql.append("     inner join dt.building as b ");
                countSql.append("     inner join dt.customer as c ");
                countSql.append("     inner join dt.buildingfloor as bf ");
                countSql.append("     left join dt.damperstatus as ds ");
                countSql.append("     inner join dt.dampertype as t ");

                StringBuffer repairSql = new StringBuffer();
                repairSql.append(" select dt.id, rh.description  ");
                repairSql.append(" from Repairhistory rh ");
                repairSql.append("     inner join rh.dampertest as dt ");
                repairSql.append("     inner join dt.building as b ");
                repairSql.append("     inner join dt.customer as c ");
                repairSql.append("     inner join dt.buildingfloor as bf ");
                repairSql.append("     left join dt.damperstatus as ds ");
                repairSql.append("     inner join dt.dampertype as t ");

                SessionUtils.addRestriction(occupancy, status, year, custId, buildingId, floorId, sql);
                SessionUtils.addRestriction(occupancy, status, year, custId, buildingId, floorId, countSql);
                SessionUtils.addRestriction(occupancy, status, year, custId, buildingId, floorId, repairSql);

                sql.append(" order by c.id, b.id, bf.sequenceNum asc, t.aliasId, dt.dampernumber, dt.series");
//                countSql.append(" order by c.id, b.id, bf.id, t.aliasId, dt.dampernumber, dt.series");
                repairSql.append(" order by c.id, b.id, bf.sequenceNum, t.aliasId, dt.dampernumber, dt.series");

//                System.out.println(sql.toString());
//                System.out.println(countSql.toString());
//                System.out.println(repairSql.toString());

                Query query = session.createQuery(sql.toString());
                Query countQuery = session.createQuery(countSql.toString());
                Query repairQuery = session.createQuery(repairSql.toString());

                int totalRecords = 0;
                for (Iterator it = countQuery.iterate(); it.hasNext();) {
                    Long row = (Long) it.next();
                    totalRecords = row.intValue();
                }

                List<DamperListBean> results = new ArrayList<DamperListBean>();
                if (pageNum == null) {
                    query.setFirstResult(1 * 40).setMaxResults(40);
                } else {
                    query.setFirstResult(pageNum * 40).setMaxResults(40);
                }

                for (Iterator it = query.iterate(); it.hasNext();) {

                    Object[] row = (Object[]) it.next();
                    DamperListBean damperListBean = new DamperListBean();

                    damperListBean.setId(Integer.parseInt("" + row[0]));
                    damperListBean.setAliasId("" + row[1]);
                    damperListBean.setBuildingId(Integer.parseInt("" + row[2]));
                    damperListBean.setBuilding(StringUtils.capitalize("" + row[3]));
//                    damperListBean.setComment(DamperTestUtils.bytesToString((byte[]) row[4]));
                    damperListBean.setComment(StringUtils.capitalize("" + row[4]));
                    damperListBean.setCustomerId(Integer.parseInt("" + row[5]));
                    damperListBean.setCustomer(StringUtils.capitalize("" + row[6]));
                    damperListBean.setDate(CustomDateUtils.convertSQLDate("" + row[7]));
                    damperListBean.setFloorId(Integer.parseInt("" + row[8]));
                    damperListBean.setFloor("" + row[9]);
                    damperListBean.setLocation("" + row[10]);
                    damperListBean.setNumber(CustomNumberUtils.parseIntNullAsZero(row[11]));
                    damperListBean.setSeries(CustomNumberUtils.parseIntNullAsZero(row[12]));
                    damperListBean.setStatus("" + row[13]);
                    damperListBean.setSublocation("" + row[14]);
                    damperListBean.setSystem("" + row[15]);
                    damperListBean.setType("" + row[16]);
                    damperListBean.setDimensions("" + row[18] + "x" + row[17]);
                    damperListBean.setOccupancy("" + row[19]);

                    damperListBean.setPicture(Integer.parseInt("" + row[20]) > 0);

                    results.add(damperListBean);

                }

//                Collections.sort(results, new DampertestSorter());

                for (Iterator it = repairQuery.iterate(); it.hasNext();) {
                    Object[] row = (Object[]) it.next();
                    for (DamperListBean db : results) {
                        Integer id = Integer.parseInt("" + row[0]);
                        if (db.getId().equals(id)) {
                            db.setRepaired(true);
                        }
                    }
                }

                Page page;
                try {
                    if (pageNum != null) {
                        page = new Page(results, pageNum, 40, totalRecords);
                    } else {
                        page = new Page(results, 1, 40, totalRecords);
                    }
                } catch (Exception e) {
                    throw new DataAccessException(e.toString()) {
                    };
                }

                System.out.println(ct.calculateTotalProcessTime());

                return page;
            }
        });

    }

    public List<DamperListBean> getDamperTestList(final String occupancy, final Integer status, final Integer year, final Integer custId, final Integer buildingId, final Integer floorId, final Integer pageNum) throws DataAccessException {
        return (List<DamperListBean>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                CustTimer ct = new CustTimer("getDamperTestList()", System.currentTimeMillis());

                StringBuffer sql = new StringBuffer();
                sql.append(" select dt.id, dt.aliasId, b.id, b.buildingName, dt.comments, c.id, c.customerName, dt.dateTestedTs, ");
                sql.append("     bf.id, bf.floorName, dt.location, dt.dampernumber, dt.series, ds.status, dt.sublocation, ");
                sql.append("     dt.system, t.typeName,dt.sizel, dt.sizew, (select count(p.id) from Pictures p where p.dampertest.id=dt.id) ");
                sql.append(" from Dampertest dt ");
                sql.append("     inner join dt.building as b ");
                sql.append("     inner join dt.customer as c ");
                sql.append("     inner join dt.buildingfloor as bf ");
                sql.append("     left join dt.damperstatus as ds ");
                sql.append("     inner join dt.dampertype as t ");

                StringBuffer repairSql = new StringBuffer();
                repairSql.append(" select dt.id, rh.description  ");
                repairSql.append(" from Repairhistory rh ");
                repairSql.append("     inner join rh.dampertest as dt ");
                repairSql.append("     inner join dt.building as b ");
                repairSql.append("     inner join dt.customer as c ");
                repairSql.append("     inner join dt.buildingfloor as bf ");
                repairSql.append("     left join dt.damperstatus as ds ");
                repairSql.append("     inner join dt.dampertype as t ");

                SessionUtils.addRestriction(occupancy, status, year, custId, buildingId, floorId, sql);
                SessionUtils.addRestriction(occupancy, status, year, custId, buildingId, floorId, repairSql);

                sql.append(" order by c.id, b.id, bf.sequenceNum asc, t.aliasId, dt.dampernumber, dt.series");
                repairSql.append(" order by c.id, b.id, bf.sequenceNum, t.aliasId, dt.dampernumber, dt.series");
//
//                System.out.println(sql.toString());
//                System.out.println(repairSql.toString());

                Query query = session.createQuery(sql.toString());
                Query repairQuery = session.createQuery(repairSql.toString());

                List<DamperListBean> results = new ArrayList<DamperListBean>();

                for (Iterator it = query.iterate(); it.hasNext();) {
                    Object[] row = (Object[]) it.next();
                    DamperListBean damperListBean = new DamperListBean();

                    damperListBean.setId(Integer.parseInt("" + row[0]));
                    damperListBean.setAliasId("" + row[1]);
                    damperListBean.setBuildingId(Integer.parseInt("" + row[2]));
                    damperListBean.setBuilding(StringUtils.capitalize("" + row[3]));
//                    damperListBean.setComment(DamperTestUtils.bytesToString((byte[]) row[4]));
                    damperListBean.setComment(StringUtils.capitalize("" + row[4]));
                    damperListBean.setCustomerId(Integer.parseInt("" + row[5]));
                    damperListBean.setCustomer(StringUtils.capitalize("" + row[6]));
                    damperListBean.setDate(CustomDateUtils.convertSQLDate("" + row[7]));
                    damperListBean.setFloorId(Integer.parseInt("" + row[8]));
                    damperListBean.setFloor("" + row[9]);
                    damperListBean.setLocation("" + row[10]);
                    damperListBean.setNumber(CustomNumberUtils.parseIntNullAsZero(row[11]));
                    damperListBean.setSeries(CustomNumberUtils.parseIntNullAsZero(row[12]));
                    damperListBean.setStatus("" + row[13]);
                    damperListBean.setSublocation("" + row[14]);
                    damperListBean.setSystem("" + row[15]);
                    damperListBean.setType("" + row[16]);
                    damperListBean.setDimensions("" + row[17] + "x" + row[18]);

                    System.out.println("Picture Count ->" + Integer.parseInt("" + row[19]));

                    damperListBean.setPicture(Integer.parseInt("" + row[19]) > 0 ? true : false);

                    results.add(damperListBean);

                }

                for (Iterator it = repairQuery.iterate(); it.hasNext();) {
                    Object[] row = (Object[]) it.next();
                    for (DamperListBean db : results) {
                        Integer id = Integer.parseInt("" + row[0]);
                        if (db.getId().equals(id)) {
                            db.setRepaired(true);
                        }
                    }
                }

                System.out.println(ct.calculateTotalProcessTime());

                return results;
            }
        });

    }

    /**
     *
     * @param custId
     * @param buildingId
     * @param floorId
     * @return
     * @throws DataAccessException
     */
    public List<DamperListBean> getDamperListBeanList(final Integer custId, final Integer buildingId, final Integer floorId) throws DataAccessException {
        return (List<DamperListBean>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                CustTimer ct = new CustTimer("getDampertestList()", System.currentTimeMillis());

                StringBuffer sql = new StringBuffer();
                sql.append(" select dt.id, dt.aliasId, b.id, b.buildingName, dt.comments, c.id, c.customerName, dt.dateTestedTs, ");
                sql.append("     bf.id, bf.floorName, dt.location, dt.dampernumber, dt.series, ds.substatus, dt.sublocation, ");
                sql.append("     dt.system, t.typeName, (select count(p.id) from Pictures p where p.dampertest.id=dt.id) ");
                sql.append(" from Dampertest dt ");
                sql.append("     inner join dt.building as b ");
                sql.append("     inner join dt.customer as c ");
                sql.append("     inner join dt.buildingfloor as bf ");
                sql.append("     left join dt.damperstatus as ds ");
                sql.append("     inner join dt.dampertype as t ");

                StringBuffer repairSql = new StringBuffer();
                repairSql.append(" select dt.id, rh.description  ");
                repairSql.append(" from Repairhistory rh ");
                repairSql.append("     inner join rh.dampertest as dt ");
                repairSql.append("     inner join dt.building as b ");
                repairSql.append("     inner join dt.customer as c ");
                repairSql.append("     inner join dt.buildingfloor as bf ");
                repairSql.append("     left join dt.damperstatus as ds ");
                repairSql.append("     inner join dt.dampertype as t ");

                SessionUtils.addRestriction(custId, buildingId, floorId, sql);
                SessionUtils.addRestriction(custId, buildingId, floorId, repairSql);

                sql.append(" order by c.id, b.id, bf.sequenceNum asc, t.aliasId, dt.dampernumber, dt.series");
//                countSql.append(" order by c.id, b.id, bf.id, t.aliasId, dt.dampernumber, dt.series");
                repairSql.append(" order by c.id, b.id, bf.sequenceNum, t.aliasId, dt.dampernumber, dt.series");

                System.out.println(sql.toString());
                System.out.println(repairSql.toString());

                Query query = session.createQuery(sql.toString());
                Query repairQuery = session.createQuery(repairSql.toString());


                List<DamperListBean> results = new ArrayList<DamperListBean>();

                for (Iterator it = query.iterate(); it.hasNext();) {
                    Object[] row = (Object[]) it.next();
                    DamperListBean damperListBean = new DamperListBean();

                    damperListBean.setId(Integer.parseInt("" + row[0]));
                    damperListBean.setAliasId("" + row[1]);
                    damperListBean.setBuildingId(Integer.parseInt("" + row[2]));
                    damperListBean.setBuilding(StringUtils.capitalize("" + row[3]));
//                    damperListBean.setComment(DamperTestUtils.bytesToString((byte[]) row[4]));
                    damperListBean.setComment(StringUtils.capitalize("" + row[4]));
                    damperListBean.setCustomerId(Integer.parseInt("" + row[5]));
                    damperListBean.setCustomer(StringUtils.capitalize("" + row[6]));
                    damperListBean.setDate(CustomDateUtils.convertSQLDate("" + row[7]));
                    damperListBean.setFloorId(Integer.parseInt("" + row[8]));
                    damperListBean.setFloor("" + row[9]);
                    damperListBean.setLocation("" + row[10]);
                    damperListBean.setNumber(CustomNumberUtils.parseIntNullAsZero(row[11]));
                    damperListBean.setSeries(CustomNumberUtils.parseIntNullAsZero(row[12]));
                    damperListBean.setStatus("" + row[13]);
                    damperListBean.setSublocation("" + row[14]);
                    damperListBean.setSystem("" + row[15]);
                    damperListBean.setType("" + row[16]);

                    damperListBean.setPicture(Integer.parseInt("" + row[17]) > 0);

                    results.add(damperListBean);

                }

//                Collections.sort(results, new DampertestSorter());

                for (Iterator it = repairQuery.iterate(); it.hasNext();) {
                    Object[] row = (Object[]) it.next();
                    for (DamperListBean db : results) {
                        Integer id = Integer.parseInt("" + row[0]);
                        if (db.getId().equals(id)) {
                            db.setRepaired(true);
                        }
                    }
                }

                System.out.println(ct.calculateTotalProcessTime());

                return results;
            }
        });

    }

    public List<String> getAliasIdList(final Integer custId, final Integer buildingId, final Integer floorId) {


        return (List<String>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                CustTimer ct = new CustTimer("getAliasIdList()", System.currentTimeMillis());
                StringBuffer sql = new StringBuffer();
                sql.append("select \n d.aliasId, ds.status ");
                sql.append("\n from Dampertest as d ");
                sql.append("\n   inner join d.customer as c ");
                sql.append("\n   inner join d.building as b ");
                sql.append("\n   inner join d.buildingfloor as bf ");
                sql.append("\n   left join d.damperstatus as ds ");


                sql.append("\n   where c.id=" + custId + " and b.id=" + buildingId + " and bf.id=" + floorId); // 6=Decommissioned

                Query query = session.createQuery(sql.toString());

                List<String> aliasIdList = new ArrayList<String>();
                for (Iterator it = query.iterate(); it.hasNext();) {
                    Object[] objects = (Object[]) it.next();

                    if (!("" + objects[1]).equals("DECOMMISSIONED")) {
                        aliasIdList.add("" + objects[0]);
                    } else {
//                        System.out.println("DECOMMISSIONED -> " + objects[0]);
                    }

                }
                return aliasIdList;
            }
        });


    }

    public DamperCommandBean createDamperCommandBean(Integer damperId) {
        CustTimer ct = new CustTimer("createDamperCommandBean()", System.currentTimeMillis());
        Dampertest dt = (Dampertest) tstratDamperTemplate.get(Dampertest.class, damperId);

        DamperCommandBean bean = getDamperCommandBean(damperId);

        Integer custId = bean.getCustomer_id();
        Integer buildingId = bean.getBuilding_id();
        Integer floorId = bean.getBuildingfloor_id();
        String floorName = bean.getBuildingfloor();
        String buildingAliasId = bean.getBuildingAliasId();
        String typeAliasId = bean.getDampertypeAliasId();
        List<String> aliasIdList = getAliasIdList(custId, buildingId, floorId);

        SequenceGenerator sq = new SequenceGenerator(aliasIdList);

        String damperAliasId = bean.getAliasId();
        Integer damperNumber = DamperUtils.getDamperNumberFromAliasId(damperAliasId);

        String aliasId = sq.getNewMultiUnitDamperNumber(typeAliasId, buildingAliasId, floorName, damperNumber);
        bean.setAliasId(StringUtils.capitalize(aliasId));
        bean.setDatetested(CustomDateUtils.convertJasonMoonsDateToString(new Date()));
        Date nextTestDate = DamperUtils.predictNextTestDate(new Date(), OCCUPANCY.HEALTH_CARE);
        bean.setNextTestDate(CustomDateUtils.convertJasonMoonsDateToString(nextTestDate));

        System.out.println(bean.toString());

        System.out.println(ct.calculateTotalProcessTime());
        return bean;

    }

    public DamperCommandBean createDamperCommandBean(Integer custId, Integer buildingId, Integer floorId, Integer damperTypeId, UNIT unit) {

        CustTimer ct = new CustTimer("createDamperCommandBean()", System.currentTimeMillis());

        List<String> aliasIdList = getAliasIdList(custId, buildingId, floorId);

        DamperCommandBean bean = new DamperCommandBean();

        StringBuffer hql = new StringBuffer("select c.id, c.customerName from Customer c where c.id=" + custId);
        Object[] os = (Object[]) tstratDamperTemplate.find(hql.toString()).get(0);
        bean.setCustomer_id(Integer.parseInt(os[0].toString()));
        bean.setCustomer(os[1].toString());

        hql = new StringBuffer("select b.id, b.buildingName, b.aliasId, b.occupancy from Building b where b.id=" + buildingId);
        os = (Object[]) tstratDamperTemplate.find(hql.toString()).get(0);
        bean.setBuilding_id(Integer.parseInt(os[0].toString()));
        bean.setBuilding(os[1].toString());
        String buildingAliasId = os[2].toString();
        bean.setBuildingAliasId(buildingAliasId);
        bean.setOccupancy(os[3] == null ? "HEALTH_CARE" : os[3].toString());

        hql = new StringBuffer("select bf.id, bf.floorName from Buildingfloor bf where bf.id=" + floorId);
        os = (Object[]) tstratDamperTemplate.find(hql.toString()).get(0);
        bean.setBuildingfloor_id(Integer.parseInt(os[0].toString()));
        String floorName = os[1].toString();
        bean.setBuildingfloor(floorName);

        hql = new StringBuffer("select dt.id, dt.aliasId, dt.typeName from Dampertype dt where dt.id=" + damperTypeId);
        os = (Object[]) tstratDamperTemplate.find(hql.toString()).get(0);
        bean.setDampertype_id(Integer.parseInt(os[0].toString()));
        String typeAliasId = os[1].toString();
        bean.setDampertypeAliasId(typeAliasId);
        bean.setDampertype(os[2].toString());


        SequenceGenerator sq = new SequenceGenerator(aliasIdList);
        String aliasId;
        if (unit == unit.SINGLE) {
            aliasId = sq.getNewSingleUnitDamperNumber(typeAliasId, buildingAliasId, floorName);
        } else {
            aliasId = sq.getNewMultiUnitDamperNumber(typeAliasId, buildingAliasId, floorName);
        }

        bean.setAliasId(StringUtils.capitalize(aliasId));
        bean.setDatetested(CustomDateUtils.convertJasonMoonsDateToString(new Date()));
        Date nextTestDate = DamperUtils.predictNextTestDate(new Date(), OCCUPANCY.HEALTH_CARE);
        bean.setNextTestDate(CustomDateUtils.convertJasonMoonsDateToString(nextTestDate));

        System.out.println(bean.toString());

        System.out.println(ct.calculateTotalProcessTime());
        return bean;


    }

//    public DamperCommandBean createDamperCommandBean(final Integer custId, final Integer buildingId, final Integer floorId, final Integer damperTypeId, final UNIT unit) {
//
//        StringBuffer sql = new StringBuffer();
//        sql.append(" select ");
//        sql.append("    c.id as customer_id, ");
//        sql.append("    c.customer_name as customer_name, ");
//        sql.append("    b.id as building_id, ");
//        sql.append("    b.alias_id as building_alias_id, ");
//        sql.append("    b.building_name as building_name, ");
//        sql.append("    bf.id as floor_id, ");
//        sql.append("    bf.floor_name as floor_name, ");
//        sql.append("    dt.id as type_id, ");
//        sql.append("    dt.alias_id as type_alias_id, ");
//        sql.append("    dt.type_name as type_name ");
////        sql.append("    ( ");
////        sql.append("       select group_concat(dampernumber SEPARATOR ',') ");
////        sql.append("        from dampertest d ");
////        sql.append("           inner join dampertype ddt on ddt.id = d.type_id ");
////        sql.append("           inner join damperstatus dds on dds.id = d.status_id ");
////        sql.append("        where ");
////        sql.append("               d.customer_id=c.id ");
////        sql.append("       and ");
////        sql.append("               d.building_id=b.id ");
////        sql.append("       and ");
////        sql.append("               d.floor_id=bf.id ");
////        sql.append("       and ");
////        sql.append("               ddt.alias_id = ");
////        sql.append("               ( ");
////        sql.append("                 select alias_id ");
////        sql.append("                 from dampertype ");
////        sql.append("                 where id=dt.id ");
////        sql.append("               ) ");
////        sql.append("       and ");
////        sql.append("       dds.status <> 'DECOMMISSIONED' ");
////        sql.append("    ) as maxdampernumber ");
//
//
//        sql.append(" from ");
//        sql.append("    customer c, ");
//        sql.append("    building b, ");
//        sql.append("    buildingfloor bf, ");
//        sql.append("    dampertype dt ");
//
//        sql.append(" where ");
//        sql.append("    c.id=? ");
//        sql.append("    and ");
//        sql.append("    b.id=? ");
//        sql.append("    and ");
//        sql.append("    bf.id=? ");
//        sql.append("    and ");
//        sql.append("    dt.id=? ");
//
//        System.out.println(sql.toString());
//
//        DamperCommandBean damperCommandBean = (DamperCommandBean) jdbcTemplate.queryForObject(
//                sql.toString(),
//                new Object[]{custId, buildingId, floorId, damperTypeId},
//                new DamperCommandRowMapper());
//
//
//        String aliasId = null;
//        int damperNumber = damperCommandBean.getDampernumber();
//        String buildingFloorName = damperCommandBean.getBuildingfloor();
//        String buildingAliasId = damperCommandBean.getBuildingAliasId();
//        String damperTypeAliasId = damperCommandBean.getDampertypeAliasId();
//        if (unit == UNIT.SINGLE) {
//            aliasId = buildingAliasId + "-" + damperTypeAliasId + "-" + String.format("%03d", damperNumber) + "-" + buildingFloorName;
//            damperCommandBean.setSeries(0);
//        } else if (unit == UNIT.MULTIPLE) {
//            aliasId = buildingAliasId + "-" + damperTypeAliasId + "-" + String.format("%03d", damperNumber) + /*LETTERS[highestInSeries + 1]*/ LETTERS[1] + "-" + buildingFloorName;
//            damperCommandBean.setSeries(1);
//        }
//
//        damperCommandBean.setAliasId(aliasId);
//        damperCommandBean.setDatetested(CustomDateUtils.convertJasonMoonsDateToString(new Date()));
//        Date nextTestDate = DamperUtils.predictNextTestDate(new Date(), OCCUPANCY.HEALTH_CARE);
//        damperCommandBean.setNextTestDate(CustomDateUtils.convertJasonMoonsDateToString(nextTestDate));
//        damperCommandBean.setAliasId(StringUtils.capitalize(aliasId));
//
//        return damperCommandBean;
//
//    }
//    class DamperCommandRowMapper implements RowMapper {
//
//        public Object mapRow(ResultSet rs, int i) throws SQLException {
//            DamperCommandBean command = new DamperCommandBean();
//            command.setCustomer_id(rs.getInt("customer_id"));
//            command.setCustomer(rs.getString("customer_name"));
//            command.setBuilding_id(rs.getInt("building_id"));
//            command.setBuilding(rs.getString("building_name"));
//            command.setBuildingAliasId(rs.getString("building_alias_id"));
//            command.setBuildingfloor_id(rs.getInt("floor_id"));
//            command.setBuildingfloor(rs.getString("floor_name"));
//            command.setDampertype_id(rs.getInt("type_id"));
//            command.setDampertypeAliasId(rs.getString("type_alias_id"));
//            command.setDampertype(rs.getString("type_name"));
//
//            Blob maxdampernumber = rs.getBlob("maxdampernumber");
//            int damperNumber = 0;
//            if (maxdampernumber != null) {
//                byte[] bdata = maxdampernumber.getBytes(1, (int) maxdampernumber.length());
//                String text = new String(bdata);
//                damperNumber = DamperUtils.getNextInSequence(text);
//
//            } else {
//                damperNumber = 1;
//            }
//
//            command.setDampernumber(damperNumber);
//
//            return command;
//
//        }
//    }
//    public DamperCommandBean createDamperCommandBeanInSeries(final Integer damperId) {
//
//        StringBuffer sql = new StringBuffer();
//        sql.append(" select ");
//        sql.append(" c.id as customer_id, ");
//        sql.append(" c.customer_name as customer_name, ");
//        sql.append(" b.id as building_id, ");
//        sql.append(" b.alias_id as building_alias_id, ");
//        sql.append(" b.building_name as building_name, ");
//        sql.append(" bf.id as floor_id, ");
//        sql.append(" bf.floor_name as floor_name, ");
//        sql.append(" dt.id as type_id, ");
//        sql.append(" dt.alias_id as type_alias_id, ");
//        sql.append(" dt.type_name as type_name, ");
//        sql.append(" d.dampernumber as damper_number, ");
//        sql.append(" d.location as location, ");
//        sql.append(" d.sublocation as sublocation, ");
//        sql.append(" d.sizel as sizel, ");
//        sql.append(" d.sizew as sizew, ");
//        sql.append(" dm.id as material_id, ");
//        sql.append(" d.system as system, ");
//        sql.append(" d.comments as comments, ");
//        sql.append(" d.special_procedures as special_procedures, ");
//
//        sql.append(" (select group_concat(series SEPARATOR ',') from dampertest dd ");
//        sql.append(" left join damperstatus dds on dds.id=dd.status_id ");
//        sql.append(" where ");
//        sql.append(" dd.dampernumber = d.dampernumber and ");
//        sql.append(" dd.customer_id = c.id and ");
//        sql.append(" dd.building_id = b.id and ");
//        sql.append(" dd.floor_id = bf.id and");
//        sql.append(" dd.type_id = dt.id");
//        sql.append("       and ");
//        sql.append("       dds.status <> 'DECOMMISSIONED' ");
//        sql.append(" ) as highest_series ");
//
//
//        sql.append(" from ");
//        sql.append(" dampertest d ");
//        sql.append("   inner join customer c on c.id = d.customer_id ");
//        sql.append("   inner join building b on b.id = d.building_id ");
//        sql.append("   inner join buildingfloor bf on bf.id = d.floor_id ");
//        sql.append("   inner join dampertype dt on dt.id = d.type_id ");
//        sql.append("   inner join dampermaterial dm on dm.id = d.material_id ");
//
//        sql.append(" where  d.id=? ");
//
//        System.out.println(sql.toString());
////        System.out.println("c.id -> " + custId);
////        System.out.println("b.id -> " + buildingId);
////        System.out.println("bf.id -> " + floorId);
////        System.out.println("dt.id -> " + damperTypeId);
//
//        DamperCommandBean damperCommandBean = (DamperCommandBean) jdbcTemplate.queryForObject(
//                sql.toString(),
//                new Object[]{damperId},
//                new DamperCommandRowMapperSeriesVersion());
//
//
//        String aliasId = null;
//        int damperNumber = damperCommandBean.getDampernumber();
//        String buildingFloorName = damperCommandBean.getBuildingfloor();
//        String buildingAliasId = damperCommandBean.getBuildingAliasId();
//        String damperTypeAliasId = damperCommandBean.getDampertypeAliasId();
//        Integer series = damperCommandBean.getSeries();
//        aliasId = buildingAliasId + "-" + damperTypeAliasId + "-" + String.format("%03d", damperNumber) + /*LETTERS[highestInSeries + 1]*/ LETTERS[series] + "-" + buildingFloorName;
//        damperCommandBean.setAliasId(aliasId);
//        damperCommandBean.setDatetested(CustomDateUtils.convertJasonMoonsDateToString(new Date()));
//        Date nextTestDate = DamperUtils.predictNextTestDate(new Date(), OCCUPANCY.HEALTH_CARE);
//        damperCommandBean.setNextTestDate(CustomDateUtils.convertJasonMoonsDateToString(nextTestDate));
////        System.out.println(damperCommandBean.toString());
//
//        return damperCommandBean;
//
//    }
//    class DamperCommandRowMapperSeriesVersion implements RowMapper {
//
//        public Object mapRow(ResultSet rs, int i) throws SQLException {
//            DamperCommandBean command = new DamperCommandBean();
//            command.setCustomer_id(rs.getInt("customer_id"));
//            command.setCustomer(rs.getString("customer_name"));
//            command.setBuilding_id(rs.getInt("building_id"));
//            command.setBuilding(rs.getString("building_name"));
//            command.setBuildingAliasId(rs.getString("building_alias_id"));
//            command.setBuildingfloor_id(rs.getInt("floor_id"));
//            command.setBuildingfloor(rs.getString("floor_name"));
//            command.setDampertype_id(rs.getInt("type_id"));
//            command.setDampertypeAliasId(rs.getString("type_alias_id"));
//            command.setDampertype(rs.getString("type_name"));
//            command.setDampernumber(rs.getInt("damper_number"));
//            command.setLocation(rs.getString("location"));
//            command.setSublocation(rs.getString("sublocation"));
//            command.setSizel(rs.getString("sizel"));
//            command.setSizew(rs.getString("sizew"));
//            command.setDampermaterial_id(rs.getInt("material_id"));
//            command.setSystem(rs.getString("system"));
//            command.setComments(rs.getString("comments"));
//            command.setSpecialProcedures(rs.getString("special_procedures"));
//
//
//            Blob maxseries = rs.getBlob("highest_series");
//            int series = 0;
//            if (maxseries != null) {
//                byte[] bdata = maxseries.getBytes(1, (int) maxseries.length());
//                String text = new String(bdata);
//                series = DamperUtils.getNextInSequence(text);
//            } else {
//                series = 1;
//            }
//            command.setSeries(series);
//
//            return command;
//
//        }
//    }
    /**
     * @deprecated not being used nor will full text be implemented because server doesn't
     * support changing some stop words needed by our client
     *
     * @param fullText
     * @param pageNum
     * @return
     * @throws DataAccessException
     */
    public Page getDampertestList(final String fullText, final Integer pageNum) throws DataAccessException {

        CustTimer ct = new CustTimer("getDampertestList(fullTextSearch)", System.currentTimeMillis());

        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT ");
        sql.append(" d.date_tested_ts as 'date_tested_ts', ");
        sql.append(" d.id as 'id', ");
        sql.append(" d.alias_id as 'alias_id', ");
        sql.append(" c.id as 'customer_id', ");
        sql.append(" c.customer_name as 'customer_name', ");
        sql.append(" b.id as 'building_id', ");
        sql.append(" b.building_name as 'building_name', ");
        sql.append(" bf.id as 'floor_id', ");
        sql.append(" bf.floor_name as 'floor_name', ");
        sql.append(" d.comments as 'comments', ");
        sql.append(" dt.type_name as 'type_name', ");
        sql.append(" d.sizel as 'sizel', ");
        sql.append(" d.sizew as 'sizew', ");
        sql.append(" d.system as 'system', ");
        sql.append(" dm.id as 'material_id', ");
        sql.append(" dm.material_name as 'material_name', ");
        sql.append(" ds.status as 'status', ");
        sql.append(" d.location as 'location', ");
        sql.append(" d.sublocation as 'sublocation', ");
        sql.append(" d.dampernumber as 'number', ");
        sql.append(" d.series as 'series' ");


        sql.append(" FROM dampertest d ");
        sql.append("   inner join customer c on c.id = d.customer_id ");
        sql.append("   inner join   building b on b.id = d.building_id ");
        sql.append("   inner join buildingfloor bf on bf.id = d.floor_id ");
        sql.append("   inner join dampertype dt on dt.id = d.type_id ");
        sql.append("   inner join dampermaterial dm on dm.id = d.material_id ");
        sql.append("   inner join damperstatus ds on ds.id = d.status_id ");


        StringBuffer repairSql = new StringBuffer();
        repairSql.append(" select dt.id, rh.description  ");
        repairSql.append(" from Repairhistory rh ");
        repairSql.append("     inner join rh.dampertest as dt ");
        repairSql.append("     inner join dt.building as b ");
        repairSql.append("     inner join dt.customer as c ");
        repairSql.append("     inner join dt.buildingfloor as bf ");
        repairSql.append("     inner join dt.damperstatus as ds ");
        repairSql.append("     inner join dt.dampertype as t ");


        List results = this.jdbcTemplate.query(
                sql.toString(),
                new RowMapper() {

                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        DamperListBean damperListBean = new DamperListBean();
                        damperListBean.setId(rs.getInt("id"));
                        damperListBean.setAliasId(rs.getString("alias_id"));
                        damperListBean.setBuildingId(rs.getInt("building_id"));
                        damperListBean.setBuilding(rs.getString("building_name"));
                        damperListBean.setCustomerId(rs.getInt("customer_id"));
                        damperListBean.setCustomer(StringUtils.capitalize(rs.getString("customer_name")));
//                        damperListBean.setComment(DamperTestUtils.bytesToString(rs.getBytes("comments")));
                        damperListBean.setComment(rs.getString("comments"));
                        damperListBean.setType(rs.getString("type_name"));
                        damperListBean.setSystem(rs.getString("system"));
                        damperListBean.setStatus(rs.getString("status"));
                        damperListBean.setDate(rs.getDate("date_tested_ts"));
                        damperListBean.setFloorId(rs.getInt("floor_id"));
                        damperListBean.setFloor(rs.getString("floor_name"));
                        damperListBean.setLocation(rs.getString("location"));
                        damperListBean.setSublocation(rs.getString("sublocation"));
                        damperListBean.setNumber(rs.getInt("number"));
                        damperListBean.setSeries(rs.getInt("series"));

                        return damperListBean;
                    }
                });

        Page page;
        try {
            if (pageNum != null) {
                page = new Page(results, pageNum, 40, 1600);
            } else {
                page = new Page(results, 1, 40, 1600);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException(e.toString()) {
            };
        }

        System.out.println(ct.calculateTotalProcessTime());
        return page;

    }

    public List<Dampertest> getDampertestList(final Integer custId, final Integer buildingId, final Integer floorId) throws DataAccessException {
        return (List<Dampertest>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                StringBuffer sql = new StringBuffer();
                sql.append("from Dampertest as dt where dt.customer.id=" + custId);
                sql.append("and dt.building.id=" + buildingId);
                sql.append("and dt.buildingfloor.id=" + buildingId);

                Query query = session.createQuery(sql.toString());

                return query.list();
            }
        });

    }

    public void deleteDamperComment(Dampercomment dampercomment) throws DataAccessException {
        tstratDamperTemplate.delete(dampercomment);
    }

    public Dampertype getDampertype(Integer id) throws DataAccessException {

        return (Dampertype) tstratDamperTemplate.load(Dampertype.class, id);
    }

    public List<Dampertype> getDamperTypes() throws DataAccessException {
        return tstratDamperTemplate.loadAll(Dampertype.class);
    }

    public Dampermaterial getDamperMaterial(Integer id) throws DataAccessException {
        return (Dampermaterial) tstratDamperTemplate.load(Dampermaterial.class, id);
    }

    public List<Dampermaterial> getDamperMaterial() throws DataAccessException {
        return tstratDamperTemplate.loadAll(Dampermaterial.class);
    }

    public List<Damperstatus> getDamperStatusList() throws DataAccessException {
        return tstratDamperTemplate.loadAll(Damperstatus.class);
    }

    public Damperstatus getDamperStatus(Integer statusId) throws DataAccessException {
        return (Damperstatus) tstratDamperTemplate.load(Damperstatus.class, statusId);
    }

    public Damperstatus getDamperstatus(final String status, final String repairState) throws DataAccessException {
        return (Damperstatus) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                StringBuffer sql = new StringBuffer();
                sql.append("from Damperstatus as dt where dt.status=" + status);
                sql.append(" and dt.substatus=" + repairState);

                return session.createQuery(sql.toString()).list();
            }
        });
    }

    public List<Dampercomment> getDamperCommentList() throws DataAccessException {

        return tstratDamperTemplate.loadAll(Dampercomment.class);
    }

    public Dampercomment getDamperComment(Integer commentId) throws DataAccessException {

        return (Dampercomment) tstratDamperTemplate.load(Dampercomment.class, commentId);
    }

    public void addDamperComment(String newComment) throws DataAccessException {

        Dampercomment comment = new Dampercomment();
        comment.setComment(newComment);
        comment.setIsenabled(ENABLED);
        tstratDamperTemplate.save(comment);

    }

    public void removeDamperComment(Integer commentId) throws DataAccessException {
        tstratDamperTemplate.delete(tstratDamperTemplate.load(Dampercomment.class, commentId));
    }
}