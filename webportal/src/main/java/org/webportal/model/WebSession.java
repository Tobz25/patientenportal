package org.webportal.model;
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity
public class WebSession extends BaseClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;

	@Column
	private String token;
	
	@OneToOne
	private User user;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public WebSession() {
		
	}

	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
