package com.om.movieapp.model.youtube;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LengthText {

  private String simpleText;

  public String getSimpleText() {
    return simpleText;
  }

  public void setSimpleText(String simpleText) {
    this.simpleText = simpleText;
  }
}
