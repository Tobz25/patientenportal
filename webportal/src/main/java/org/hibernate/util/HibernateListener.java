package org.hibernate.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.getInstance();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		 HibernateUtil.getSession().close();
	}

}
