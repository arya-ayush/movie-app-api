package com.om.movieapp.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.om.movieapp.controller.PingResource;
import com.om.movieapp.controller.YoutubeScrapingResource;

@Component
@ApplicationPath("v1")
public class JerseyConfiguration extends ResourceConfig {

  public JerseyConfiguration() {
    register(PingResource.class);
    register(YoutubeScrapingResource.class);
  }
}
