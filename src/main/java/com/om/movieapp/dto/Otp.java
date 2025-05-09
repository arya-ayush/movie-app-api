package com.om.movieapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Otp {
    private String number;
    private String otp;
}
