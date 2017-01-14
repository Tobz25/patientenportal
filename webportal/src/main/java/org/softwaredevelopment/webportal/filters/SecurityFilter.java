package org.softwaredevelopment.webportal.filters;
/*
 * The securityFilter class is a provider to secure resources. It looks for an authorization token in the client request header
 *  and validates the token with the sessions in the DB and gives access to the resource or denies access.
 * @author Tobi
 */
import java.io.IOException;
import javax.ws.rs.Priorities;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.softwaredevelopment.webportal.helper.Secured;
import org.softwaredevelopment.webportal.service.AuthenticationService;

<<<<<<< HEAD
 
=======
@Secured
>>>>>>> 0dd67758bfe3ce579b7aa8cd5f9789576652c27b
@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter{
	
	AuthenticationService authService = new AuthenticationService();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {			
			// Get the HTTP Authorization header from the request
	        String authorizationHeader = 
	            requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
	        
	     // Check if the HTTP Authorization header is present and formatted correctly
	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	        	// Extract the token from the HTTP Authorization header
	        	String token = authorizationHeader.substring("Bearer".length()).trim();
	        	if (validateToken(token) == true){
	        		return;
	        	}
	        }
	        
	        Response unauthorizedStatus = Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("User cannot access the resource.")
					.build();

	        requestContext.abortWith(unauthorizedStatus);
	        
		
	}
	
	private boolean validateToken(String token) {
		boolean authenticated = authService.authenticateToken(token);
		if (authenticated == false){
    		return false;
    	}
		else return true;
	}

}
