package com.om.movieapp.service;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.om.movieapp.model.youtube.Thumbnails;

@Service
public class YoutubeCrawlerService {

  private static final Logger LOG = LoggerFactory.getLogger(YoutubeCrawlerService.class);

  public String getDescription(Element element) {
    Elements elements = element.getElementsByClass("yt-lockup-description");
    if (CollectionUtils.isEmpty(elements)) {
      return null;
    }
    return elements.get(0).html().replace("&nbsp;", "");
  }

  public String getTitle(Element element) {
    Elements elements = element.select(".yt-lockup-title > a[title]");
    if (CollectionUtils.isEmpty(elements)) {
      return null;
    }
    return elements.get(0).attr("title");
  }

  public String getDuration(Element element) {
    Elements elements = element.getElementsByClass("accessible-description");
    if (CollectionUtils.isEmpty(elements)) {
      return null;
    }
    return elements.get(0).text().replace("- Duration: ", "").replace(".", "");
  }

  public String getViewsCount(Element element) {
    Elements elements = element.getElementsByClass("yt-lockup-meta-info");
    if (CollectionUtils.isEmpty(elements)) {
      return null;
    }
    Elements tags = elements.get(0).getElementsByTag("li");
    if (CollectionUtils.isEmpty(tags)) {
      return null;
    }
    if (tags.size() > 1) {
      return tags.get(1).html();
    }
    return null;
  }

  public String getVideoId(Element element) {
    Elements elements = element.select(".yt-lockup-title > a[title]");
    if (CollectionUtils.isEmpty(elements)) {
      return null;
    }
    String videoUrl = elements.get(0).attr("href");
    if (StringUtils.isEmpty(videoUrl)) {
      return null;
    }
    return videoUrl.split("=")[1];
  }

  public String getPublishDate(Element element) {
    Elements elements = element.getElementsByClass("yt-lockup-meta-info");
    if (CollectionUtils.isEmpty(elements)) {
      return null;
    }
    Elements tags = elements.get(0).getElementsByTag("li");
    return tags.get(0).html();
  }

  public Thumbnails getThumbnail(Element element) {
    Elements elements = element.getElementsByTag("img");
    if (CollectionUtils.isEmpty(elements)) {
      return null;
    }
    element = elements.get(0);
    Thumbnails thumbnails = new Thumbnails();
    thumbnails.setHeight(Integer.parseInt(element.attr("height")));
    thumbnails.setWidth(Integer.parseInt(element.attr("width")));
    String url = element.attr("src");
    if (url.contains(".gif")) {
      url = element.attr("data-thumb");
    }
    thumbnails.setUrl(url);
    return thumbnails;
  }
}
