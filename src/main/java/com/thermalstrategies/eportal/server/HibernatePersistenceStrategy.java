/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.server;

import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Stuart
 */
public class HibernatePersistenceStrategy {

    
    private SessionFactory sessionFactory;

    public HibernatePersistenceStrategy() {
        
        //Lets get the configuration file from 
        Configuration configuration = new Configuration();
        // We don't need to store this string into memory
        configuration.configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
        
    }

    public void commit() throws DatabaseException {
        Session session = sessionFactory.getCurrentSession(); 

        try {
            session.getTransaction().commit();
        } catch (Throwable t) {
            // uh oh ... failed persisting, gotta release
            release();

            // wrap and rethrow so caller knows something bad happened
            throw new DatabaseException(t);
        }
    }

    /**
     * Release database session, rollback any uncommitted changes.
     *
     * IMPORTANT: we don't want to open a transaction and force the use of a
     * jdbc connection just to close the session and do a rollback, so this
     * method must be sensitive about how the release is triggered.
     *
     * In particular we don't want to use our custom getSession() method which
     * automatically begins a transaction.  Instead we get a Session and check
     * if there is already an active transaction that needs to be rolled back.
     * If not then we can close the Session without ever getting a jdbc
     * connection, which is important for scalability.
     */
    public void release() {
        Session session = sessionFactory.getCurrentSession();
        try
        {
            if (session != null && session.isOpen()) {

                try {
                    Transaction tx = session.getTransaction();

                    if (tx != null && tx.isActive()) {
                        tx.rollback();
                    }
                } catch (Throwable t) {
                } finally {
                    if (session.isOpen()) {
                        session.close();
                    }
                }
            }
        } catch (Throwable t)
        {
        }
    }

    public void save(Serializable s) throws DatabaseException 
    {
            Session session = sessionFactory.getCurrentSession();
            session.save( s );
    }

    public void update(Serializable s) throws DatabaseException {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate( s );
    }

    public void delete(Serializable s) throws DatabaseException {
            Session session = sessionFactory.getCurrentSession();    
            session.delete( s );
    }

    
    public Session getSession() {

            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
        
            return session;
    }
    
}
