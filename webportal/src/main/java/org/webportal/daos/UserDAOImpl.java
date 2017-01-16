package org.webportal.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.util.HibernateUtil;
import org.webportal.model.Employee;
import org.webportal.model.User;

public class UserDAOImpl {

	public void addUser(User user){
        Session session = HibernateUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addUser(session, user);        
        tx.commit();
        session.close();
        
    }
    
    private void addUser(Session session, User user){
        User newUser = new User();
        
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmailaddress(user.getEmailaddress());
        newUser.setSalutation(user.getSalutation());
        session.save(newUser);
    }
    
    public List<User> getUsers(){
        Session session = HibernateUtil.getSession();    
        Query query = session.createQuery("from User");
        List<User> users =  query.list();
        session.close();
        return users;
    }
 
    public int deleteUser(int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from User where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updateEmployee(int id, Employee emp){
         /*if(id <=0)  
               return 0;  
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
            return rowCount;
            
            */
    	return 0;
    }
	
    /*
	public User findUser(String username) {
		Criterion crit = Restrictions.eq("username", username);
		List<User> users = usernameExists(username); 
		if (users.size() != 0) return users.get(0);
		else return null;
	}
	
	public List<User> usernameExists(String username) {
		Criterion crit = Restrictions.eq("username", username);
		return findByCriteria(crit);
	}

	public long addUser(User user) {
		persist(user); return 0;
	}
	
	//geh�rt in service logik
	public boolean login(String username, String password) {
		try {
			if (findUser(username).getPassword().equals(password)) return true;
			else return false;
		}
		catch(Exception e) {
			return false;
		}
	}

	
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}
	*/
}
