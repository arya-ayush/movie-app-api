package com.om.movieapp.controller;

import com.om.movieapp.exception.ApplicationException;
import com.om.movieapp.model.User;
import com.om.movieapp.service.UserService;
import com.om.movieapp.utils.messages.Messages;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Autowired
    private UserService userService;

    @POST
    public Response addUser(@HeaderParam("apiKey") String apiKey, @RequestBody User user) {
        if (StringUtils.isEmpty(apiKey)) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST_400,
                    new String(Messages.INCORRECT_PARAMETERS.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }
        if (StringUtils.isBlank(user.getUuid())) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST_400,
                    new String(Messages.INVALID_UUID.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }
        int count = userService.insertUser(user);
        if (count == 0) {
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR_500,
                    new String(Messages.INTERNAL_SERVER_ERROR.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }
        return Response.status(HttpStatus.OK_200, "Successful").build();
    }


    @GET
    @Path("{uuid}")
    public Response getUser(@HeaderParam("apiKey") String apiKey, @PathParam("uuid") String uuid) {
        if (StringUtils.isEmpty(apiKey)) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST_400,
                    new String(Messages.INCORRECT_PARAMETERS.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }
        return Response.ok(userService.getUser(uuid)).build();
    }

}
