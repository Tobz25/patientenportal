package org.webportal.daos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface GenericDAO<T, ID extends Serializable> {  
		  
	//T findeMitId(ID id, boolean lock);  
	  
	List<T> findAll();  
	
	public List<T> findByCriteria(Criterion... criterion);
	  
	//List<T> findeMitBeispiel(T exampleInstance);  
	  
	T persist(T entity);  
	  
	void delete(T entity);  
}  

