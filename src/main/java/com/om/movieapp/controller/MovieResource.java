package com.om.movieapp.controller;

import com.om.movieapp.exception.ApplicationException;
import com.om.movieapp.service.MovieService;
import com.om.movieapp.utils.messages.Messages;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @POST
    @Path("/scarp/bollywood")
    public Response getBollywoodMovies(@HeaderParam("apiKey") String apiKey, @QueryParam("year") String year) {
        if (StringUtils.isEmpty(apiKey)) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST_400,
                    new String(Messages.INCORRECT_PARAMETERS.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }
        return Response.ok(movieService.fetchBollywoodMovies(year)).build();
    }

    @POST
    @Path("/scarp/hollywood")
    public Response getHollywoodMovies(@HeaderParam("apiKey") String apiKey, @QueryParam("year") String year) {
        if (StringUtils.isEmpty(apiKey)) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST_400,
                    new String(Messages.INCORRECT_PARAMETERS.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }
        return Response.ok(movieService.fetchHollywoodMovies(year)).build();
    }

    @POST
    @Path("/scrap/featured")
    public Response getFeaturedMovies(@HeaderParam("apiKey") String apiKey,
                                      @QueryParam("user_id") String userId) {
        if (StringUtils.isEmpty(apiKey) || StringUtils.isEmpty(userId)) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST_400,
                    new String(Messages.INCORRECT_PARAMETERS.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }

        return Response.ok(movieService.fetchFeaturedMovies(userId)).build();
    }

}
