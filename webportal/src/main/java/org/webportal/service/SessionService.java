package org.webportal.service;

import java.util.ArrayList;
import java.util.List;

import org.webportal.daos.DAOFactory;
import org.webportal.daos.SessionDAO;
import org.webportal.daos.UserDAO;
import org.webportal.daos.WebSessionDAOImpl;
import org.webportal.model.User;
import org.webportal.model.WebSession;

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
