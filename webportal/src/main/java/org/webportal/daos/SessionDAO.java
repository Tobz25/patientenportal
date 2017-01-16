package org.webportal.daos;

import org.webportal.model.User;
import org.webportal.model.WebSession;

public interface SessionDAO extends GenericDAO<WebSession, Long>{
	
	
	/*
	 * Erstellt eine neue Session
	 */
	public WebSession newSession(User user);

	public User findSession(String token);
	
}
