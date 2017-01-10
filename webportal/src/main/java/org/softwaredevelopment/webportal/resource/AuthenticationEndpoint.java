package org.softwaredevelopment.webportal.resource;
/*
 * This is the service endpoint used for Login. The client has to post a username and a password. The service validates these
 * information and returns a session token.
 * @author Tobi
 */
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.softwaredevelopment.webportal.service.AuthenticationService;
import org.softwaredevelopment.webportal.service.SessionService;

@Path("/authentication")
public class AuthenticationEndpoint {
	
	AuthenticationService authService = new AuthenticationService();
	SessionService sessionService = new SessionService();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Response authenticateUser(@FormParam("username") String username, 
                                     @FormParam("password") String password) {

        try {

            // Authenticate the user using the credentials provided
            authenticate(username, password);

            // Issue a token for the user
            String token = issueToken(username);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }      
    }

    private void authenticate(String username, String password) throws Exception {
    	boolean authenticated = authService.authenticateUser(username, password);
    	if (authenticated == false){
    		throw new Exception();
    	}
    }

    private String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
    	String token = sessionService.createSessionToken(username);
    	return token;
    }
}
