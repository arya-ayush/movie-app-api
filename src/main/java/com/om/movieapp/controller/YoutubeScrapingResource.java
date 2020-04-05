package com.om.movieapp.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.om.movieapp.model.Youtube;
import com.om.movieapp.service.YoutubeScrapingService;

@Path("youtube")
@Produces(MediaType.APPLICATION_JSON)
public class YoutubeScrapingResource {

  private static final Logger LOG = LoggerFactory.getLogger(YoutubeScrapingResource.class);

  @Autowired
  private YoutubeScrapingService youtubeScrapingService;

  @GET
  public Response fetchData() {
    List<Youtube> youtubeList = youtubeScrapingService.fetchResults("Latest hollywood full movie");
    return Response.ok(youtubeList).build();
  }
}
