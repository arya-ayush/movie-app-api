package com.om.movieapp.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.om.movieapp.model.ApiData;

@Path("ping")
@Produces(MediaType.APPLICATION_JSON)
public class PingResource {

  /**
   * Ping Api
   * @return API info
   */
  @GET
  public Response ping() {
    ApiData apiData = new ApiData();
    return Response.ok(apiData).build();
  }
}
