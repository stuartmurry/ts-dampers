/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import com.thermalstrategies.eportal.model.Building;
import com.thermalstrategies.eportal.model.Buildingfloor;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.model.User;
import com.thermalstrategies.eportal.model.Userlog;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author Stuart
 */
public class LogManager {

    private HibernateTemplate tstratDamperTemplate;

    public LogManager(HibernateTemplate tstratDamperTemplate) {
        this.tstratDamperTemplate = tstratDamperTemplate;
    }

    public void removeUserLog(Integer userLogId) throws DataAccessException {
        Userlog userLog = (Userlog) tstratDamperTemplate.load(Userlog.class, userLogId);
        tstratDamperTemplate.delete(userLog);
    }

    public void deleteUserLog(Userlog userLog) throws DataAccessException {
        tstratDamperTemplate.delete(userLog);
    }

    public List<Userlog> getUserLogList() throws DataAccessException {
        return tstratDamperTemplate.loadAll(Userlog.class);
    }

    public boolean isUserExist(final User user) throws DataAccessException {
        return (Boolean) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(Userlog.class);
                criteria.add(Restrictions.eq("user.id", user.getId()));
                Userlog userlog = (Userlog) criteria.uniqueResult();
                if (userlog != null) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    public int getUserLogStatus(final User user, final Integer projectId, final Integer buildingId, final Integer buildingFloorId) throws DataAccessException {
        return (Integer) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(Userlog.class);
                criteria.add(Restrictions.eq("project.id", projectId));
                criteria.add(Restrictions.eq("building.id", buildingId));
                criteria.add(Restrictions.eq("buildingfloor.id", buildingFloorId));
                int status = 0;
                Userlog userlog = (Userlog) criteria.uniqueResult();
                if (userlog == null) {
                    // No one is using this floor
                    status = 0;
                } else {
                    int registeredUser = userlog.getUser().getId();
                    int currentUser = user.getId();
                    if (registeredUser == currentUser) {
                        // This person is using this floor
                        status = 1;
                    } else {
                        // Another user is using this floor
                        status = 2;
                    }
                }
                return status;
            }
        });

    }

    public Userlog getUserLog(final Integer userId) throws DataAccessException {
        return (Userlog) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(Userlog.class);
                criteria.add(Restrictions.eq("user.id", userId));

                return criteria.uniqueResult();
            }
        });


    }

    public void assignUserToFloor(final String sessionId, final User user, final Customer customer, final Building building, final Buildingfloor buildingfloor) throws DataAccessException {

        tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(Userlog.class);
                criteria.add(Restrictions.eq("customer.id", customer.getId()));
                criteria.add(Restrictions.eq("building.id", building.getId()));
                criteria.add(Restrictions.eq("buildingfloor.id", buildingfloor.getId()));

                Userlog userlog = (Userlog) criteria.uniqueResult();
                if (userlog == null) {
                    // Assign user to floor
                    userlog = new Userlog();
                    userlog.setCustomer(customer);
                    userlog.setBuilding(building);
                    userlog.setBuildingfloor(buildingfloor);
                    userlog.setUser(user);
                    userlog.setSessionId(sessionId);
                    userlog.setLoginTs(new Date());

                    session.save(userlog);
                } else {
                    throw new SQLException("You cannot assign user: " + user.getUserName() + " to the userlog table because someone else is registered using this project-building-floor# ");
                }
                return null;
            }
        });

    }
}
