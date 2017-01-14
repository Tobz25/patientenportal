package org.softwaredevelopment.webportal.daos;

import org.softwaredevelopment.webportal.model.User;
import org.softwaredevelopment.webportal.model.WebSession;

public interface SessionDAO extends GenericDAO<WebSession, Long>{
	
	
	/*
	 * Erstellt eine neue Session
	 */
	public WebSession newSession(User user);

	public User findSession(String token);
	
}
