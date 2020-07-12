package com.om.movieapp.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;

import com.om.movieapp.exception.ApplicationException;
import com.om.movieapp.model.App;
import com.om.movieapp.model.Company;
import com.om.movieapp.utils.messages.Messages;

@Path("company")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyResource {

  @GET
  public Response getCompany(@HeaderParam("apiKey") String apiKey) {
    if (StringUtils.isEmpty(apiKey)) {
      throw new ApplicationException(HttpStatus.BAD_REQUEST_400,
          new String(Messages.INCORRECT_PARAMETERS.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
    }
    List<App> otherApps = new ArrayList<>();
    App uChatApp = new App();
    uChatApp.setPackageName("com.omApps.uChat");
    uChatApp.setName("Video Chat-Make Friends, Meet People, Social Media");
    otherApps.add(uChatApp);
    List<String> websites = new ArrayList<>();
    websites.add("http://freefullmovies.in");
    App app = new App();
    app.setVersionCode(45);
    app.setPackageName("com.om.fullmovie");
    app.setVersion("4.0.0");
    Company company = new Company();
    company.setName("Omtech");
    company.setOtherApps(otherApps);
    company.setApp(app);
    company.setWebsites(websites);
    return Response.ok(company).build();
  }
}
