package org.softwaredevelopment.webportal.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.softwaredevelopment.webportal.model.ErrorMessage;


@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "https://wiseb.wiwi.tu-dresden.de/wiki");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
