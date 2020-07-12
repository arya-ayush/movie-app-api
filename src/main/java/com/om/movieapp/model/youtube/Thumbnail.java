package com.om.movieapp.model.youtube;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Thumbnail {

  private List<Thumbnails> thumbnails;

  public List<Thumbnails> getThumbnails() {
    return thumbnails;
  }

  public void setThumbnails(List<Thumbnails> thumbnails) {
    this.thumbnails = thumbnails;
  }
}
