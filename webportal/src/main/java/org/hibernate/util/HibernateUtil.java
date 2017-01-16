package org.hibernate.util;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.webportal.model.User;
import org.webportal.model.WebSession;

public class HibernateUtil {
 
    private static HibernateUtil instance = new HibernateUtil();
	
	private SessionFactory sessionFactory;
 
	public static HibernateUtil getInstance() {
		return instance;
	}
	
    private HibernateUtil() {
       // neue SessionFactory aus hibernate.cfg.xml anlegen
       Configuration cfg = new Configuration();
            	
       cfg.configure();
       sessionFactory = cfg.buildSessionFactory();
    }
 
        
    public static Session getSession() {
    	 Session session =  getInstance().sessionFactory.openSession();
         return session;
    }
}