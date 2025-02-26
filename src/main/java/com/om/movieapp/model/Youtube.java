package com.om.movieapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.om.movieapp.model.youtube.Thumbnails;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Youtube implements Serializable {

  private static final long serialVersionUID = -728273679655974753L;

  private int id;
  private String title;
  private String description;
  private String duration;
  private String videoId;
  @JsonProperty("youtube_views_count")
  private String viewsCount;
  @JsonProperty("publish_date")
  private String publishDate;
  private Thumbnails thumbnail;
  private int likes;
  private int dislikes;
  @JsonProperty("app_views_count")
  private int appViewsCount;


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
  public Thumbnails getThumbnail() {
    return thumbnail;
  }

  /**
   * @param thumbnail the thumbnail to set
   */
  public void setThumbnail(Thumbnails thumbnail) {
    this.thumbnail = thumbnail;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  public int getDislikes() {
    return dislikes;
  }

  public void setDislikes(int dislikes) {
    this.dislikes = dislikes;
  }

  public int getAppViewsCount() {
    return appViewsCount;
  }

  public void setAppViewsCount(int appViewsCount) {
    this.appViewsCount = appViewsCount;
  }
}
