package com.om.movieapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private String name;
    private String dob;
    private String phone_number;
    private String Country_code;
    private String password;
}
