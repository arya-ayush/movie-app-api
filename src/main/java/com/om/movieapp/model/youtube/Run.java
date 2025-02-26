package com.om.movieapp.model.youtube;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Run {

  private String text;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}
