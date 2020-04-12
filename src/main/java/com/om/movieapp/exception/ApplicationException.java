package com.om.movieapp.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.om.movieapp.model.AppResponse;

/**
 * Class for Application Exception
 * Created by manoj on 25/02/17.
 */
public class ApplicationException extends WebApplicationException {

  /**
   * System generated serialVersionUID
   */
  private static final long serialVersionUID = -5507291461726848875L;

  /**
   * Parameterised constructor
   * @param status the response status
   * @param message response message
   */
  public ApplicationException(Integer status, String message) {
    super(Response.status(status).entity(new AppResponse(status, message, true))
        .type(MediaType.APPLICATION_JSON).build());
  }
}
