package com.om.movieapp.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class ResponseFilter implements ContainerResponseFilter {
  @Override
  public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext response) {
    response.getHeaders().add("Access-Control-Allow-Headers",
        "Origin, X-Requested-With, Content-Type, Accept, apiKey, If-Modified-Since, signed-request");
    response.getHeaders().add("Access-Control-Allow-Origin", "*");
    response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.getHeaders().add("Access-Control-Expose-Headers", "Content-Disposition");
  }
}
