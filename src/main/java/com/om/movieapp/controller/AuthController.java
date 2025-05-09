package com.om.movieapp.controller;

import com.om.movieapp.dto.MvResponse;
import com.om.movieapp.dto.Otp;
import com.om.movieapp.dto.User;
import com.om.movieapp.model.DeviceInfo;
import com.om.movieapp.service.OtpUserService;
import com.om.movieapp.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {
    @Autowired
    private PhoneService service;

    @Autowired
    private OtpUserService otpUserService;


    @GET
    @Path("send-otp")
    public String sendOtp(@QueryParam("country_code") String countryCode, @QueryParam("mobile_no") String mobileNo) {

        return service.sendOtp(countryCode + mobileNo);
    }

    @POST
    @Path("verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Otp otp){


        MvResponse mv_response=new MvResponse();
        if(service.verifyOtp(otp.getNumber(),otp.getOtp())){
            mv_response.setMessage("verification done");
            return ResponseEntity.status(200).body(mv_response);
        }
        mv_response.setMessage("Wront otp or otp has expired");
        return ResponseEntity.status(300).body(mv_response);
    }

    @POST
    @Path("sign-up")
    public ResponseEntity<?>  sign_up(@RequestBody User user){
        MvResponse mv_response=new MvResponse();
        if(otpUserService.save_user(user)){
            mv_response.setMessage("signup successfull");
            return ResponseEntity.status(200).body(mv_response);
        }
        mv_response.setMessage("Server error");
        return ResponseEntity.status(504).body(mv_response);
    }
}
