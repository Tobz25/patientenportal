package org.softwaredevelopment.webportal.service;

import org.softwaredevelopment.webportal.model.User;

import java.util.List;

import org.softwaredevelopment.webportal.daos.DAOFactory;
import org.softwaredevelopment.webportal.daos.SessionDAO;
import org.softwaredevelopment.webportal.daos.UserDAO;
import org.softwaredevelopment.webportal.daos.UserDAOImpl;
import org.softwaredevelopment.webportal.daos.WebSessionDAOImpl;
import org.softwaredevelopment.webportal.model.WebSession;

/*
 * This class is for authentication validation. In case of a login, it checks whether username and password match with user data
 * from the database and in case of a secured resource request it checks if there is a session for the session token offered 
 * by the client 
 * @author Tobi
 */
public class AuthenticationService {
	
	//SessionService sessionService = new SessionService();
	//public static DAOFactory daoFabrik = DAOFactory.instance(DAOFactory.HIBERNATE);
	//public UserDAO ndao = daoFabrik.getUserDAO();
	//public SessionDAO sdao;
	

	public User authenticateUser(String username, String password) throws Exception{
		UserDAOImpl udao = new UserDAOImpl();
		List<User> users = udao.getUsers();
		for (User u : users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}
	
	public User getAuthenticatedUser(String username) {
		UserDAOImpl udao = new UserDAOImpl();
		List<User> users = udao.getUsers();
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}
	
	public boolean authenticateToken(String token){
		WebSessionDAOImpl wsdi = new WebSessionDAOImpl();
		List<WebSession> sessions = wsdi.getWebSessions();
		for (WebSession ws : sessions) {
			if (ws.getToken().equals(token)) return true;
		}
		return false;
	}

}
