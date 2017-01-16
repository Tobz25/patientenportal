package org.webportal.daos;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.util.HibernateUtil;
import org.webportal.model.Employee;
import org.webportal.model.User;
import org.webportal.model.WebSession;

public class WebSessionDAOImpl {

	
	public WebSessionDAOImpl() {
	}
	

	
	public void addSession(User user){
        Session session = HibernateUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addSession(session, user);        
        tx.commit();
        session.close();
        
    }
    
    private void addSession(Session session, User user){
    	WebSession newSession = new WebSession();
    	Random random = new SecureRandom();
		newSession.setToken(new BigInteger(130, random).toString(32));
    	newSession.setUser(user);
    	session.save(newSession);
    }
    
    public List<WebSession> getWebSessions(){
        Session session = HibernateUtil.getSession();    
        Query query = session.createQuery("from WebSession");
        List<WebSession> sessions =  query.list();
        session.close();
        return sessions;
    }
 
    public int deleteWebSession(int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from WebSession where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updateWebSession(int id, WebSession webSession){
         /*if(id <=0)       return 0;  
         Session session = HibernateUtil.getSession();
         Transaction tx = session.beginTransaction();
         String hql = "update Employee set name = :name, age=:age where id = :id";
         Query query = session.createQuery(hql);
         query.setInteger("id",id);
         query.setString("name",emp.getName());
         query.setInteger("age",emp.getAge());
         int rowCount = query.executeUpdate();
         System.out.println("Rows affected: " + rowCount);
         tx.commit();
         session.close();
         return rowCount;*/
    	return 0;
    }




}
