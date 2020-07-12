package com.om.movieapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.om.movieapp.model.youtube.Thumbnails;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Youtube {

  private String title;
  private String description;
  private String duration;
  private String videoId;
  private String viewsCount;
  private String publishDate;
  private Thumbnails thumbnails;

  /**
   * @return title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return duration
   */
  public String getDuration() {
    return duration;
  }

  /**
   * @param duration the duration to set
   */
  public void setDuration(String duration) {
    this.duration = duration;
  }

  /**
   * @return videoId
   */
  public String getVideoId() {
    return videoId;
  }

  /**
   * @param videoId the videoId to set
   */
  public void setVideoId(String videoId) {
    this.videoId = videoId;
  }

  /**
   * @return viewsCount
   */
  public String getViewsCount() {
    return viewsCount;
  }

  /**
   * @param viewsCount the viewsCount to set
   */
  public void setViewsCount(String viewsCount) {
    this.viewsCount = viewsCount;
  }

  /**
   * @return publishDate
   */
  public String getPublishDate() {
    return publishDate;
  }

  /**
   * @param publishDate the publishDate to set
   */
  public void setPublishDate(String publishDate) {
    this.publishDate = publishDate;
  }

  /**
   * @return thumbnail
   */
  public Thumbnails getThumbnails() {
    return thumbnails;
  }

  /**
   * @param thumbnails the thumbnail to set
   */
  public void setThumbnails(Thumbnails thumbnails) {
    this.thumbnails = thumbnails;
  }
}
