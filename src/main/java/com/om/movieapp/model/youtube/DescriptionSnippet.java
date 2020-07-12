package com.om.movieapp.model.youtube;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DescriptionSnippet {

  private List<Run> runs = null;

  public List<Run> getRuns() {
    return runs;
  }

  public void setRuns(List<Run> runs) {
    this.runs = runs;
  }

}
