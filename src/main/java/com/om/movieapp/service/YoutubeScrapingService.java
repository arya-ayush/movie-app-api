package com.om.movieapp.service;

import com.google.gson.Gson;
import com.om.movieapp.model.Youtube;
import com.om.movieapp.model.youtube.Thumbnails;
import com.om.movieapp.model.youtube.YoutubeData;
import com.om.movieapp.utils.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
      // split script tag that contains relevant youtube data
      Elements elements = document.getElementsByTag("script");
//      LOG.debug("**** <{}>", elements.toString());
      String elementString = null;
      for (Element element : elements) {
        elementString = element.toString();
        if (elementString.contains("videoRenderer")) {
          break;
        }
      }
      if (StringUtils.isNotBlank(elementString)) {
        youtubeList = parseYoutubeJson(elementString);
//        LOG.debug("youtubeList *** <{}>", new Gson().toJson(youtubeList));
      }
    } catch (IOException e) {
      LOG.error("fetchResults - Failed to create connection with exception <{}>", ExceptionUtils.getStackTrace(e));
    }
    return youtubeList;
  }

  /**
   * Method to parse data from youtube JSON
   * @param youtubeDataElement element that contains youtube data
   */
  private List<Youtube> parseYoutubeJson(String youtubeDataElement) {
    String[] videosData = youtubeDataElement.split("\"videoRenderer\":");
    List<Youtube> youtubeList = new ArrayList<>();
    for (String videoData : videosData) {
      if (!videoData.contains("<script>") && !videoData.contains("</script>")) {
        String correctedVideoData = videoData.substring(0, videoData.length() - 3);
        try {
          YoutubeData youtubeData = new Gson().fromJson(correctedVideoData, YoutubeData.class);
          if (StringUtils.isNotBlank(youtubeData.getVideoId())) {
            Youtube youtube = new Youtube();
            youtube.setVideoId(youtubeData.getVideoId());
            if (youtubeData.getTitle() != null && !CollectionUtils.isEmpty(youtubeData.getTitle().getRuns())) {
              youtube.setTitle(youtubeData.getTitle().getRuns().get(0).getText());
            }
//            LOG.debug("youtube - **** <{}>", new Gson().toJson(youtube));
//            if (youtubeData.getTitle() != null && !CollectionUtils.isEmpty(youtubeData.getDescriptionSnippet().getRuns())) {
//              youtube.setDescription(youtubeData.getDescriptionSnippet().getRuns().get(0).getText());
//            }
            youtube.setDescription(youtube.getTitle());
            if (youtubeData.getLengthText() != null) {
              youtube.setDuration(youtubeData.getLengthText().getSimpleText());
            }
            if (youtubeData.getPublishedTimeText() != null) {
              youtube.setPublishDate(youtubeData.getPublishedTimeText().getSimpleText());
            }
            if (youtubeData.getViewCountText() != null) {
              youtube.setViewsCount(youtubeData.getViewCountText().getSimpleText());
            }
            if (youtubeData.getThumbnail() != null &&
                !CollectionUtils.isEmpty(youtubeData.getThumbnail().getThumbnails())) {
              Thumbnails thumbnails = youtubeData.getThumbnail().getThumbnails().get(0);
              youtube.setThumbnail(thumbnails);
            }
            youtubeList.add(youtube);
          }
        } catch (Exception e) {
          // TODO check this why these exceptions are occurring
//          LOG.debug("parseYoutubeJson - ****** <{}>", videoData);
        }
      }
    }
    return youtubeList;
  }

  /**
   * Method to parse youtube DOM
   * This method was used initially.
   * @param document
   * @return youtubeList
   */
  private List<Youtube> parseYoutubeDOM(Document document) {
    List<Youtube> youtubeList = new ArrayList<>();
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
    return youtubeList;
  }
}
