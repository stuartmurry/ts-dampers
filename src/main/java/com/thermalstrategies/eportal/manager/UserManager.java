/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import com.thermalstrategies.eportal.controller.UserCommandBean;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.model.Role;
import com.thermalstrategies.eportal.model.User;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import com.thermalstrategies.eportal.security.EPortalUserDetail;
import com.thermalstrategies.eportal.utils.CustTimer;
import com.thermalstrategies.eportal.utils.CustomNumberUtils;
import java.security.Principal;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


/**
 *
 * @author smurry
 */
public class UserManager {

    private HibernateTemplate tstratDamperTemplate;

    public UserManager(HibernateTemplate tstratDamperTemplate) {
        this.tstratDamperTemplate = tstratDamperTemplate;
    }

    public EPortalUserDetail getUserDetails(String username) throws Exception {

        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
        dc.add(Restrictions.eq("userName", ("" + username).trim()));
        List<User> userList = tstratDamperTemplate.findByCriteria(dc);

        if (userList.isEmpty()) {
            // check email
            dc = DetachedCriteria.forClass(User.class);
            dc.add(Restrictions.eq("email", ("" + username).trim()));
            userList = tstratDamperTemplate.findByCriteria(dc);
            if (userList.isEmpty()) {
                throw new Exception("Username not found.");
            }
        }

        for (User u : userList) {
            return new EPortalUserDetail(u);
        }
        return null;

    }

    public void deleteUser(final UserCommandBean ucb) {
        tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                User user = EPortalSecurityContext.getUser();
                String role = user.getRole().getRole();
                //Make sure customer don't see other members only their own
                if ("customer".equalsIgnoreCase(role)) {
                    throw new SQLException("You don't have the required permissions to delete.  Contact your administrator.");
                } else {
                    User u = (User) session.load(User.class, ucb.getId());
                    session.delete(u);
                }

                return null;
            }
        });

    }

    public void saveUser(final UserCommandBean ucb, final String commaDelimetedCustIds) {
        tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                User user = new User();
                BeanUtils.copyProperties(ucb, user);
                Integer custId = ucb.getCustomer_id();
                Integer roleId = ucb.getRole_id();
                if (custId != null && custId > 0) {
                    Customer customer = (Customer) session.load(Customer.class, custId);
                    user.setCustomer(customer);
                }
                if (roleId != null && roleId > 0) {
                    Role role = (Role) session.load(Role.class, roleId);
                    user.setRole(role);
                }
                user.setCustomers(commaDelimetedCustIds);
                session.saveOrUpdate(user);
                return null;
            }
        });
    }

    public List<UserCommandBean> getUserList() {
        return getUserList(null);
    }

    public List<UserCommandBean> getUserList(final Integer custId) {

        return (List<UserCommandBean>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                CustTimer ct = new CustTimer("getUserList()", System.currentTimeMillis());
                User user = EPortalSecurityContext.getUser();
                String role = user.getRole().getRole();
                List<UserCommandBean> userCommandBeanList = new ArrayList<UserCommandBean>();

                //Make sure that the customer doesn't see other members only their own
                if ("customer".equalsIgnoreCase(role)) {
                    UserCommandBean ucb = new UserCommandBean();
                    ucb.setId(user.getId());
                    ucb.setLastName(user.getLastName());
                    ucb.setFirstName(user.getFirstName());
//                    BeanUtils.copyProperties(user, ucb);
                    userCommandBeanList.add(ucb);
                } else {

                    StringBuffer sql = new StringBuffer();
                    sql.append(" SELECT u.id, u.firstName, u.lastName from User as u ");

                    if (custId != null && custId > 0) {
                        sql.append(" where u.customer.id=" + custId);
                    }


                    Query query = session.createQuery(sql.toString());
                    for (Iterator it = query.iterate(); it.hasNext();) {
                        Object[] row = (Object[]) it.next();
                        UserCommandBean cb = new UserCommandBean();
                        cb.setId(CustomNumberUtils.parseInt(row[0]));
                        cb.setLastName("" + row[2]);
                        cb.setFirstName("" + row[1]);

//                        BeanUtils.copyProperties(u, cb);
                        userCommandBeanList.add(cb);
                    }
                }
                System.out.println(ct.calculateTotalProcessTime());
                return userCommandBeanList;
            }
        });
    }

    public String forgottenPassword(final String email) {
        return (String) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                System.out.println("Emailing password to email: " + email);
                StringBuffer sql = new StringBuffer();
                sql.append("select u.password from User u where u.email=:email");
                Query query = session.createQuery(sql.toString()).setString("email", email);
                String password = "";
                for (Iterator it = query.iterate(); it.hasNext();) {
                    password = (String) it.next();
                }
                return password;
            }
        });
    }

    public void saveUser(User user) throws DataAccessException {
        tstratDamperTemplate.saveOrUpdate(user);
    }

    public UserCommandBean getUser(final Integer id) throws DataAccessException {
        return (UserCommandBean) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                User user = (User) session.load(User.class, id);
                UserCommandBean ucb = new UserCommandBean();
                BeanUtils.copyProperties(user, ucb);
                ucb.setPasswordconfirm(user.getPassword());
                ucb.setCustomer_id(user.getCustomer() == null ? null : user.getCustomer().getId());
                ucb.setRole_id(user.getRole() == null ? null : user.getRole().getId());
                return ucb;
            }
        });
    }

    public Role getRole(Integer id) throws DataAccessException {
        return (Role) tstratDamperTemplate.load(Role.class, id);
    }

    public User getUser(Principal principal) throws DataAccessException {
        AbstractAuthenticationToken token = (AbstractAuthenticationToken) principal;
        UserDetails userDetails = (UserDetails) token.getPrincipal();
        final String userName = userDetails.getUsername();

        return (User) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                StringBuffer sql = new StringBuffer();
                sql.append("from User as u where u.userName='" + userName + "'");
                Query query = session.createQuery(sql.toString());
                User user = (User) query.uniqueResult();
                return user;
            }
        });
    }

    public User getUser() throws DataAccessException {
        UserDetails userDetails = (UserDetails) getAuthentication().getPrincipal();
        final String userName = userDetails.getUsername();
        return (User) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                StringBuffer sql = new StringBuffer();
                sql.append("from User as u where u.userName='" + "'");
                Query query = session.createQuery(sql.toString());
                User user = (User) query.uniqueResult();
                return user;
            }
        });
    }

    private Authentication getAuthentication() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        return ctx.getAuthentication();
    }

    private boolean searchGrantedAuthorities(GrantedAuthority[] gas, String authority) {
        for (GrantedAuthority ga : gas) {
            if (authority.equals(ga.getAuthority())) {
                return true;
            }
        }
        return false;
    }
}
