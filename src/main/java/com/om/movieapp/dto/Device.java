package com.om.movieapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@Builder
public class Device {

    private String device_token;

    private String info;


    private String last_session_time;


    private String last_ip;
}
