package org.webportal.exception;

/*
 * Exception, which is thrown if a resource requested by a client cannot be found.
 * Thus, it should be used in the service classes requesting data from the database.
 * @author Tobi
 */
public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7607296716457706636L;

	public DataNotFoundException(String message) {
		super(message);
	}

}
