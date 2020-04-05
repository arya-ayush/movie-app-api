package com.om.movieapp.model;

public class Thumbnail {

  private String url;
  private Integer width;
  private Integer height;

  /**
   * @return url
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url the url to set
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return width
   */
  public Integer getWidth() {
    return width;
  }

  /**
   * @param width the width to set
   */
  public void setWidth(Integer width) {
    this.width = width;
  }

  /**
   * @return height
   */
  public Integer getHeight() {
    return height;
  }

  /**
   * @param height the height to set
   */
  public void setHeight(Integer height) {
    this.height = height;
  }
}
