package com.om.movieapp.controller;

import com.om.movieapp.dto.Device;
import com.om.movieapp.model.DeviceInfo;
import com.om.movieapp.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("device-info")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeviceInfoResource {
    @Autowired
    private DeviceService service;

    @POST
    @Path("add-device")
    public String addDecive(@RequestBody DeviceInfo device){
        boolean x=service.add_device(device);
        if(x==false)return "test fail";
        return "saved";
    }


    @GET
    @Path("device-token")
    public Response getDeviceByToken(@QueryParam("token") String token) {
        DeviceInfo device=service.get_device_by_token(token);

        if (device != null) {
            Device res_body=Device.builder()
                    .info(device.getInfo())
                    .device_token(device.getDeviceToken())
                    .last_ip(device.getLastIp())
                    .last_session_time(device.getLastSessionTime())
                    .build();
            return Response.status(Response.Status.OK).entity(res_body).build();
        } else {
            return Response.status(303).build();
        }
    }
}
