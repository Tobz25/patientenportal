package org.softwaredevelopment.webportal.service;

import java.util.ArrayList;
import java.util.List;

import org.softwaredevelopment.webportal.model.User;
import org.softwaredevelopment.webportal.daos.DAOFactory;
import org.softwaredevelopment.webportal.daos.SessionDAO;
import org.softwaredevelopment.webportal.daos.UserDAO;
import org.softwaredevelopment.webportal.daos.WebSessionDAOImpl;
/*
 * Created a session for a given user.
 * 
 * @author Tobi
 */
import org.softwaredevelopment.webportal.model.WebSession;

public class SessionService {
	
	public String createSessionToken(User user){
		WebSessionDAOImpl wsdi = new WebSessionDAOImpl();
		wsdi.addSession(user);
		
		List<WebSession> sessions = wsdi.getWebSessions();
		for (WebSession ws : sessions) {
			if (ws.getUser().equals(user)) return ws.getToken();
		}
		
		return null;
	}

}
