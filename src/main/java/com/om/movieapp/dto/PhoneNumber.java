package com.om.movieapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PhoneNumber {
    private String mobile_no;
    private String country_code;
}
