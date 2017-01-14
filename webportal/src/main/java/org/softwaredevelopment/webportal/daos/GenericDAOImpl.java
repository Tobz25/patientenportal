package org.softwaredevelopment.webportal.daos;

import java.io.Serializable;
import java.util.List;


import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.util.HibernateUtil;

public abstract class GenericDAOImpl<T, ID extends Serializable>  
    implements GenericDAO<T, ID> {  

	private Class<T> persistentClass;  
	private Session session;  
	
	public GenericDAOImpl() {  
		 this.persistentClass = (Class<T>) ((ParameterizedType) getClass()  
                 .getGenericSuperclass()).getActualTypeArguments()[0]; 
		 
		 session = HibernateUtil.getSession();
	}
	
	@SuppressWarnings("unchecked")  
	public void setSession(Session s) {  
	    this.session = s;  
	}  
	
	protected Session getSession() {  
	    if (session == null)  
	        throw new IllegalStateException("Session has not been set on DAO before usage");  
	    return HibernateUtil.getSession();  
	}  
	
	public Class<T> getPersistentClass() {  
	    return persistentClass;  
	}  
	
	@SuppressWarnings("unchecked")  
	public T findById(ID id, boolean lock) {  
	    T entity;  
	    if (lock)  
	        entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);  
	    else  
	        entity = (T) getSession().load(getPersistentClass(), id);  
	
	    return entity;  
	}  
	
	@SuppressWarnings("unchecked")  
	public List<T> findAll() {  
	    return findByCriteria();  
	}  
	
	@SuppressWarnings("unchecked")  
	public List<T> findeMitBeispiel(T exampleInstance, String[] excludeProperty) {  
	    Criteria crit = getSession().createCriteria(getPersistentClass());  
	    Example example =  Example.create(exampleInstance);  
	    for (String exclude : excludeProperty) {  
	        example.excludeProperty(exclude);  
	    }  
	    crit.add(example);  
	    return crit.list();  
	}  
	
	@SuppressWarnings("unchecked")  
	public T persist(T entity) {
		Transaction tx = getSession().beginTransaction();
	    getSession().setFlushMode(FlushMode.COMMIT);
		getSession().saveOrUpdate(entity); 
	    
	    //flush();
	    getSession().getTransaction().commit();
	    getSession().close();
	    return entity;  
	}  
	
	@SuppressWarnings("unchecked")  
	public void delete(T entity) { 
		getSession().beginTransaction();
	    getSession().delete(entity);  
	    getSession().getTransaction().commit();
	}  
	
	@SuppressWarnings("unchecked")  
	public void flush() {  
	    getSession().flush();  
	}  
	
	@SuppressWarnings("unchecked")  
	public void clear() {  
	    getSession().clear();  
	}  
	
	@SuppressWarnings("unchecked")
	private List<T> subFindByCriteira(Criterion... criterion){
		Criteria crit = getSession().createCriteria(getPersistentClass());  
	    for (Criterion c : criterion) {  
	        crit.add(c);  	
	    }   
	    return crit.list();
	}
	
	/** 
	 * Use this inside subclasses as a convenience method. 
	 */  
	public List<T> findByCriteria(Criterion... criterion) {
		//if (!getSession().isOpen() setSession(s); ) 
		Transaction tx = getSession().beginTransaction();
		List<T> erg = subFindByCriteira(criterion);
	    if (tx.isActive()) tx.commit();
	    return erg;
	}  

}