package com.om.movieapp.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.om.movieapp.exception.ApplicationException;
import com.om.movieapp.model.Youtube;
import com.om.movieapp.service.YoutubeScrapingService;
import com.om.movieapp.utils.constant.Constants;
import com.om.movieapp.utils.messages.Messages;

@Path("youtube")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class YoutubeScrapingResource {

  private static final Logger LOG = LoggerFactory.getLogger(YoutubeScrapingResource.class);

  @Autowired
  private YoutubeScrapingService youtubeScrapingService;

  @GET
  @Path("search")
  public Response fetchData(@QueryParam("query") String query, @HeaderParam("apiKey") String apiKey) {
    LOG.info("fetchData - Method begins here with query <{}>", query);
    // TODO do in request filter
    if (StringUtils.isEmpty(apiKey)) {
      throw new ApplicationException(HttpStatus.BAD_REQUEST_400,
          new String(Messages.INCORRECT_PARAMETERS.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
    }
    if (!Constants.MASTER_API_KEY.equals(apiKey)) {
      throw new ApplicationException(HttpStatus.UNPROCESSABLE_ENTITY_422,
          new String(Messages.INCORRECT_API_KEY.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
    }
    if (StringUtils.isEmpty(query)) {
      throw new ApplicationException(HttpStatus.BAD_REQUEST_400,
          new String(Messages.INCORRECT_PARAMETERS.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
    }
    List<Youtube> youtubeList = youtubeScrapingService.fetchResults(query);
    return Response.ok(youtubeList).build();
  }
}
