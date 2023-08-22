package com.om.movieapp.properties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties("twilio")
public class TwilioProperties {
    private String accountSid;

    private String authToken;

    private String trailNumber;
}
