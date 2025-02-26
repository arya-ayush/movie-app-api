package com.om.movieapp.model.youtube;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Title {

  private List<Run> runs;

  public List<Run> getRuns() {
    return runs;
  }

  public void setRuns(List<Run> runs) {
    this.runs = runs;
  }
}
