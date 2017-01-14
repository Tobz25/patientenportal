package org.softwaredevelopment.webportal.daos;

import org.softwaredevelopment.webportal.model.User;

public interface UserDAO extends GenericDAO<User, Long>{
	
	
	/*
	 * Gibt den Nutzer zur�ck
	 */
	public User findUser(String benutzerkennung);
	
	
	/*
	 * F�gt Nutzer neu hinzu
	 */
	
	public long addUser(User nutzer);
	
	
	/* Versucht den Nutzer anzumelden
	 * Gibt true zur�ck, wenn erfolgreich 
	 * 
	 * */
	public boolean login(String benutzerkennung, String passwort);
}
