package org.webportal.daos;

import org.hibernate.Session;
import org.hibernate.util.HibernateUtil;

class DAOFactoryImpl extends DAOFactory {

	  public UserDAO getUserDAO() {  
	        return (UserDAO)instantiateDAO(UserDAOImpl.class);  
	    }  
	  
	 
	  
	  private GenericDAOImpl instantiateDAO(Class daoClass) {  
	      try {  
	           GenericDAOImpl dao = (GenericDAOImpl)daoClass.newInstance();  
	           dao.setSession(getCurrentSession());  
	           return dao;  
	       } catch (Exception ex) {  
	           throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);  
	       }  
	  }  
	  
	    // You could override this if you don't want HibernateUtil for lookup  
	    protected Session getCurrentSession() {  
	        return HibernateUtil.getSession();  
	    }



		@Override
		public SessionDAO getSessionDAO() {
			// TODO Auto-generated method stub
			return null;
		}  
	  
	    // Inline concrete DAO implementations with no business-related data access methods.  
	    // If we use public static nested classes, we can centralize all of them in one source file.  
	  /*
	    public static class CommentDAOHibernate  
	            extends GenericHibernateDAO<Comment, Long>  
	            implements CommentDAO {}  
	  
	    public static class ShipmentDAOHibernate  
	            extends GenericHibernateDAO<Shipment, Long>  
	            implements ShipmentDAO {}  
	  */
}
