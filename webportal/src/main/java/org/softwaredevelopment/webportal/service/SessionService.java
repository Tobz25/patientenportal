package org.softwaredevelopment.webportal.service;

import java.util.ArrayList;
import java.util.List;

/*
 * Created a session for a given user.
 * 
 * @author Tobi
 */
import org.softwaredevelopment.webportal.model.Session;

public class SessionService {
	
	UserService userService = new UserService();
	
	//List of Mok objects
	public static List<Session> sessions = new ArrayList<Session>();
	
	public String createSessionToken(String username){
		int userID = userService.getUserIDByUsername(username);
		Session session = new Session(userID);
		sessions.add(session);
		return session.getToken();	
	}

}
