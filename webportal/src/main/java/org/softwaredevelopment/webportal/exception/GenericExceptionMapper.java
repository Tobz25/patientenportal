package org.softwaredevelopment.webportal.exception;

/*
 * Creates a response with a generic error message, in case no explicit error message can be mapped to a response
 */
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.softwaredevelopment.webportal.model.ErrorMessage;


@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500, "https://wiseb.wiwi.tu-dresden.de/wiki");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	}
}
