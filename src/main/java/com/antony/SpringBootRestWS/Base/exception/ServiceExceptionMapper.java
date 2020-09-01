package com.antony.SpringBootRestWS.Base.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.http.HttpStatus;

public class ServiceExceptionMapper implements ExceptionMapper<ServiceException>{

	@Override
	public Response toResponse(ServiceException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setMessage("TTTTTT:::"+ex.getMessage());
		error.setStatusCode(ex.getStatusCode());
		return Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).entity(error).type(MediaType.APPLICATION_JSON).build();
	}

}
