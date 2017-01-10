package org.softwaredevelopment.webportal.model;
/*
 * A session is created when a client is loggin in with correct username and id. The session contains a token, which is 
 * stored in the DB and returned to the client. The client uses this token to access secured resourced. When the client
 * logs off, the session with the token is deleted.
 * 
 * @author Tobi
 */
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class Session {
	private String token;
	private int userID;
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Session( int user){
		this.userID = user;
		Random random = new SecureRandom();
		this.token = new BigInteger(130, random).toString(32);
	}
	

}
