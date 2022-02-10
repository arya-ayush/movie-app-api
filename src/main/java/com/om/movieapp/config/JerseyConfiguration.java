package com.om.movieapp.config;

import com.om.movieapp.controller.CompanyResource;
import com.om.movieapp.controller.PingResource;
import com.om.movieapp.controller.YoutubeScrapingResource;
import com.om.movieapp.filter.ResponseFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

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
