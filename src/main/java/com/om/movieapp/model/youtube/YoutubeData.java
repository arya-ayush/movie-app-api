package com.om.movieapp.model.youtube;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class YoutubeData {

  private String videoId;
  private Thumbnail thumbnail;
  private ViewCountText viewCountText;
  private PublishedTimeText publishedTimeText;
  private Title title;
  private DescriptionSnippet descriptionSnippet;
  private LengthText lengthText;

  public String getVideoId() {
    return videoId;
  }

  public void setVideoId(String videoId) {
    this.videoId = videoId;
  }

  public Thumbnail getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(Thumbnail thumbnail) {
    this.thumbnail = thumbnail;
  }

  public ViewCountText getViewCountText() {
    return viewCountText;
  }

  public void setViewCountText(ViewCountText viewCountText) {
    this.viewCountText = viewCountText;
  }

  public PublishedTimeText getPublishedTimeText() {
    return publishedTimeText;
  }

  public void setPublishedTimeText(PublishedTimeText publishedTimeText) {
    this.publishedTimeText = publishedTimeText;
  }

  public Title getTitle() {
    return title;
  }

  public void setTitle(Title title) {
    this.title = title;
  }

  public DescriptionSnippet getDescriptionSnippet() {
    return descriptionSnippet;
  }

  public void setDescriptionSnippet(DescriptionSnippet descriptionSnippet) {
    this.descriptionSnippet = descriptionSnippet;
  }

  public LengthText getLengthText() {
    return lengthText;
  }

  public void setLengthText(LengthText lengthText) {
    this.lengthText = lengthText;
  }
}
