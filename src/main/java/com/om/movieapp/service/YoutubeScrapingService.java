package com.om.movieapp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.om.movieapp.model.Youtube;
import com.om.movieapp.utils.constant.Constants;

@Service
public class YoutubeScrapingService {

  @Autowired
  private YoutubeCrawlerService youtubeCrawlerService;

  private static final Logger LOG = LoggerFactory.getLogger(YoutubeScrapingService.class);

  public List<Youtube> fetchResults(String searchText) {
    List<Youtube> youtubeList = new ArrayList<>();
    try {
      // Jsoup connection closes automatically
      Document document = Jsoup.connect(Constants.YOUTUBE_SEARCH_URL)
          .data("search_query", searchText)
          .userAgent("Mozilla/5.0")
          .get();
      for (Element element : document.children()) {
        Elements elements = element.getElementsByClass("yt-lockup yt-lockup-tile yt-lockup-video vve-check clearfix");
        for (Element childElement : elements) {
          Element metaElement = childElement.getElementsByClass("yt-lockup-content").get(0);
          Element thumbnailElement = childElement.getElementsByClass("yt-thumb-simple").get(0);
          Youtube youtube = new Youtube();
          youtube.setVideoId(youtubeCrawlerService.getVideoId(metaElement));
          youtube.setTitle(youtubeCrawlerService.getTitle(metaElement));
          youtube.setDescription(youtubeCrawlerService.getDescription(metaElement));
          youtube.setPublishDate(youtubeCrawlerService.getPublishDate(metaElement));
          youtube.setThumbnail(youtubeCrawlerService.getThumbnail(thumbnailElement));
          youtube.setDuration(youtubeCrawlerService.getDuration(metaElement));
          youtube.setViewsCount(youtubeCrawlerService.getViewsCount(element));
          youtubeList.add(youtube);
        }
      }
    } catch (IOException e) {
      LOG.error("fetchResults - Failed to create connection with exception <{}>", ExceptionUtils.getStackTrace(e));
    }
    return youtubeList;
  }

}
