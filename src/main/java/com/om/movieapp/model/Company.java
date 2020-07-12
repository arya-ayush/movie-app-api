package com.om.movieapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Company {

  private Integer id;
  private String name;
  private List<String> websites;
  private List<App> otherApps;
  private App app;

  /**
   * @return id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return websites
   */
  public List<String> getWebsites() {
    return websites;
  }

  /**
   * @param websites the websites to set
   */
  public void setWebsites(List<String> websites) {
    this.websites = websites;
  }

  /**
   * @return otherApps
   */
  public List<App> getOtherApps() {
    return otherApps;
  }

  /**
   * @param otherApps the otherApps to set
   */
  public void setOtherApps(List<App> otherApps) {
    this.otherApps = otherApps;
  }

  public App getApp() {
    return app;
  }

  public void setApp(App app) {
    this.app = app;
  }
}
