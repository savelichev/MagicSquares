package net.savelichev.magicSquare.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Session factory for hibernate.
 * Singleton pattern.
 */
public class HibernateSessionFactory {

    /**
     * Instance of session factory.
     */
    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {
    }


    /**
     * Create session for hibernate.
     *
     * @return session.
     */
    public static synchronized Session getSession() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Failed to create sessionFactory object." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory.openSession();
    }


}
