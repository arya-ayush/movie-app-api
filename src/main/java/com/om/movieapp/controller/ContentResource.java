package com.om.movieapp.controller;

import com.om.movieapp.exception.ApplicationException;
import com.om.movieapp.model.Highlight;
import com.om.movieapp.service.ContentService;
import com.om.movieapp.utils.messages.Messages;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Path("content")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContentResource {

    @Autowired
    private ContentService contentService;

    @GET
    @Path("highlights")
    public Response getHighlights(@HeaderParam("apiKey") String apiKey) {
        if (StringUtils.isEmpty(apiKey)) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST_400,
                    new String(Messages.INCORRECT_PARAMETERS.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }
        List<Highlight> highlightList = contentService.getHighlights();
        return Response.ok(highlightList).build();
    }

}
