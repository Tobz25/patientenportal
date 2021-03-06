package org.webportal.resource;
import java.util.List;

/*
 * Service endpoint für den Login. Empfängt einen Benutzernamen (username)und ein Password (password).
 * Der Service validiert diese und gibt bei positivemErgebnis ein Token zurück.
 * @author Tobi
 */
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.webportal.daos.EmployeeDAO;
import org.webportal.model.Employee;
import org.webportal.model.User;
import org.webportal.service.AuthenticationService;
import org.webportal.service.SessionService;

@Path("/authentication")
public class AuthenticationEndpoint {
	
	
	AuthenticationService authService;
	
	SessionService sessionService;
	
	public AuthenticationEndpoint() {
		authService = new AuthenticationService();
		sessionService = new SessionService();
	}
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Response authenticateUser(@FormParam("username") String username, 
                                     @FormParam("password") String password) {

        try {

            // Authenticate the user using the credentials provided
            //authenticate(username, password);

            // Issue a token for the user
            String token = authenticate(username, password); 

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
        	System.out.println("Fehler: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }      
    }

    private String authenticate(String username, String password) throws Exception {
    	User authenticatedUser = authService.authenticateUser(username, password);
    	if (authenticatedUser == null){
    		throw new Exception();
    	}
    	else return issueToken(authenticatedUser);
    }

    private String issueToken(User user) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
    	String token = sessionService.createSessionToken(user);
    	return token;
    }
    
    @GET
    @Produces("application/json")
    public List<Employee> getEmployee() {
        EmployeeDAO dao = new EmployeeDAO();
        List employees = dao.getEmployees();
        return employees;
    }
 
    
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Response addEmployee(Employee emp){
        emp.setName(emp.getName());
        emp.setAge(emp.getAge());
                
        EmployeeDAO dao = new EmployeeDAO();
        dao.addEmployee(emp);
        
        return Response.ok().build();
    }
    
    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updateEmployee(@PathParam("id") int id, Employee emp){
        EmployeeDAO dao = new EmployeeDAO();
        int count = dao.updateEmployee(id, emp);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deleteEmployee(@PathParam("id") int id){
        EmployeeDAO dao = new EmployeeDAO();
        int count = dao.deleteEmployee(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}
