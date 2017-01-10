package org.softwaredevelopment.webportal.service;

import org.softwaredevelopment.webportal.model.Session;

/*
 * This class is for authentication validation. In case of a login, it checks whether username and password match with user data
 * from the database and in case of a secured resource request it checks if there is a session for the session token offered 
 * by the client 
 * @author Tobi
 */
public class AuthenticationService {
	
	//SessionService sessionService = new SessionService();
	
	public boolean authenticateUser(String username, String password) throws Exception{
		
		//TODO: Check username and password with data in the database
		if(username.equals("patient") && password.equals("patient")){
			return true;
		}
		else return false;
	}
	
	public boolean authenticateToken(String token){
		
		//TODO:Check the token string with a session token in the database
		for(Session sessionObject: SessionService.sessions){
			if(sessionObject.getToken().equals(token)){
				return true;
			}
			else return false;
		}
		return false;
	}

}
