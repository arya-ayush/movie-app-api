package com.om.movieapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by manoj on 22/02/17.
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AppResponse {

  private String message;
  private Integer code;
  private Boolean error;
  private Object body;

  /**
   * Parametrised Constructor
   * @param status
   * @param message
   */
  public AppResponse(int status, String message) {
    this.code = status;
    this.message = message;
  }

  /**
   * Parametrised Constructor
   * @param status
   * @param message
   * @param error
   */
  public AppResponse(int status, String message, Boolean error) {
    this(status, message);
    this.error = error;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * @return the code
   */
  public Integer getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(Integer code) {
    this.code = code;
  }

  /**
   * @return the error
   */
  public Boolean getError() {
    return error;
  }

  /**
   * @param error the error to set
   */
  public void setError(Boolean error) {
    this.error = error;
  }

  /**
   * @return body
   */
  public Object getBody() {
    return body;
  }

  /**
   * @param body the body to set
   */
  public void setBody(Object body) {
    this.body = body;
  }
}
