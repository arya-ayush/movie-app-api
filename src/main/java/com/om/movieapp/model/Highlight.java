package com.om.movieapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.om.movieapp.model.youtube.Thumbnails;
import com.om.movieapp.utils.JsonUtil;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Highlight {

    private int id;
    private String videoId;
    private String title;
    private String description;
    private String url;
    private List<Thumbnails> posters;
    private String posterJson;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Thumbnails> getPosters() {
        return posters;
    }

    public void setPosters(List<Thumbnails> posters) {
        this.posters = posters;
        setPosterJson(JsonUtil.toJson(posters));
    }

    public String getPosterJson() {
        return posterJson;
    }

    public void setPosterJson(String posterJson) {
        this.posterJson = posterJson;
    }
}
