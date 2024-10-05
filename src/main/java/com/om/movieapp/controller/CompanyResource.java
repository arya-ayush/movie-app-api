package com.om.movieapp.controller;

import com.om.movieapp.exception.ApplicationException;
import com.om.movieapp.model.App;
import com.om.movieapp.model.Company;
import com.om.movieapp.utils.messages.Messages;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
//    otherApps.add(uChatApp);
    App statusSaverApp = new App();
    uChatApp.setPackageName("com.om.statussaver");
    uChatApp.setName("Status Saver - All in One");
//    otherApps.add(statusSaverApp);
    App app = new App();
    app.setVersionCode(78);
    app.setPackageName("com.om.fullmovie");
    app.setVersion("5.0.1");
    app.setLevelPlayEnabled(false);
    Company company = new Company();
    company.setName("Omtech");
    company.setOtherApps(otherApps);
    company.setApp(app);
    return Response.ok(company).build();
  }
}
