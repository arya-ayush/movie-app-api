package com.om.movieapp.service;

import com.om.movieapp.model.tmdb.MovieDetail;
import com.om.movieapp.model.tmdb.MovieSearchResponse;
import com.om.movieapp.utils.JsonUtil;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);
    private static final String BOLLYWOOD_MOVIE_URL = "http://103.145.232.246/Data/movies/Bollywood/%s/";
    private static final String HOLLYWOOD_MOVIE_URL = "http://103.145.232.246/Data/movies/Hollywood/%s/";

    @Autowired
    private TmdbService tmdbService;

    public Map<String, String> fetchBollywoodMovies(final String year) {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        final Map<String, String> movieMp4Map = new HashMap<>();
        try {
            Document doc = Jsoup.connect(String.format(BOLLYWOOD_MOVIE_URL, year)).get();
            Elements links = doc.select("a"); // Get all movie links

            for (Element link : links) {
                String movieName = link.text().replace("/", "").trim(); // Clean movie name
                movieName = movieName.replaceAll("\\(\\d{4}\\)", "").trim(); // Remove year

                String moviePageUrl = link.absUrl("href"); // Movie folder link

                // Skip invalid links
                if (movieName.equals("..") || movieName.isEmpty() || moviePageUrl.isEmpty()) {
                    continue;
                }
                // Now visit each movie page to find the .mp4 file link
                String mp4Url = getMp4Link(moviePageUrl);
                if (mp4Url != null) {
                    movieMp4Map.put(movieName, mp4Url);
                }
                String finalMovieName = movieName;
                CompletableFuture.runAsync(() -> {
                    final MovieSearchResponse movieSearchResponse = tmdbService.discover(finalMovieName);
                    if (!CollectionUtils.isEmpty(movieSearchResponse.getResults())) {
                        final MovieDetail movieDetail = tmdbService.getMovieDetail(movieSearchResponse.getResults().get(0).getId());
                        saveMovieDetail(movieDetail, mp4Url);
                    }
                }, executorService);
            }
        } catch (Exception e) {
            LOG.error("Failed to process error {}", ExceptionUtils.getStackTrace(e));
        }
        return movieMp4Map;
    }

    private void saveMovieDetail(MovieDetail movieDetail, String mp4Url) {
        LOG.info("***** {} and URL {}", JsonUtil.toJson(movieDetail), mp4Url);
    }

    public Map<String, String> fetchHollywoodMovies(final String year) {
        final Map<String, String> movieMp4Map = new HashMap<>();
        try {
            Document doc = Jsoup.connect(String.format(HOLLYWOOD_MOVIE_URL, year)).get();
            Elements links = doc.select("a"); // Get all movie links

            for (Element link : links) {
                String movieName = link.text().replace("/", "").trim(); // Clean movie name
                movieName = movieName.replaceAll("\\(\\d{4}\\)", "").trim(); // Remove year

                String moviePageUrl = link.absUrl("href"); // Movie folder link

                // Skip invalid links
                if (movieName.equals("..") || movieName.isEmpty() || moviePageUrl.isEmpty()) {
                    continue;
                }
                // Now visit each movie page to find the .mp4 file link
                String mp4Url = getMp4Link(moviePageUrl);
                if (mp4Url != null) {
                    movieMp4Map.put(movieName, mp4Url);
                }
            }
        } catch (Exception e) {
            LOG.error("Failed to process error {}", ExceptionUtils.getStackTrace(e));
        }
        return movieMp4Map;
    }

    /**
     * Fetches the .mp4 file link from a given movie page.
     */
    private String getMp4Link(String moviePageUrl) {
        try {
            Document moviePage = Jsoup.connect(moviePageUrl).get();
            Elements mp4Links = moviePage.select("a[href$=.mp4]"); // Select links ending with .mp4

            if (!mp4Links.isEmpty()) {
                return mp4Links.first().absUrl("href"); // Return the first .mp4 URL
            }
        } catch (IOException e) {
            LOG.error("Failed to process error {}", ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

}
