package com.om.movieapp.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.om.movieapp.controller.CompanyResource;
import com.om.movieapp.controller.PingResource;
import com.om.movieapp.controller.YoutubeScrapingResource;
import com.om.movieapp.filter.ResponseFilter;

@Component
@ApplicationPath("v1")
public class JerseyConfiguration extends ResourceConfig {

  public JerseyConfiguration() {
    register(PingResource.class);
    register(YoutubeScrapingResource.class);
    register(CompanyResource.class);
    register(ResponseFilter.class);
  }
}
