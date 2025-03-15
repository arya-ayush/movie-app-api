package com.om.movieapp.service;

import com.om.movieapp.model.tmdb.MovieDetail;
import com.om.movieapp.model.tmdb.MovieSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.net.SocketException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import java.net.URI;

@Service
public class TmdbService {
    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String TMDB_TOKEN = "ghisvf7iu6tbe2ejvnx4trejh2vb"; // Replace with your actual TMDB token
    private static final String API_TMDB = "your_api_url_here"; // Replace with the actual API URL
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    @Qualifier("tmDbRestTemplate")
    private RestTemplate tmDbRestTemplate;

    public MovieSearchResponse discover(final String name) {
        String url = UriComponentsBuilder.fromHttpUrl(String.format("%s/search/movie", BASE_URL))
                .queryParam("query", name)
                .queryParam("include_adult", true)
                .queryParam("language", "en-US")
                .queryParam("page", 1)
                .toUriString();
        return tmDbRestTemplate.getForObject(url, MovieSearchResponse.class);
    }

    public List<Object> fetchDiscover() throws InterruptedException {
//        if (type == null || type.isEmpty()) {
//            type = "results";
//        }
        LOG.info("CALLED");
        String url = "https://api.themoviedb.org/3/discover/movie?language=en&sort_by=popular&with_genres=16";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TMDB_TOKEN);
        headers.set("Accept", "application/json");

        int maxRetries = 3;
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    LOG.info("Response received successfully from TMDB API.");

                    return (List<Object>) response.getBody();
                } else {
                    LOG.info("ERROR");
                    throw new RuntimeException("Failed to load data");
                }

            } catch (HttpClientErrorException e) {

                retryCount++;
                System.out.println("Retrying... Attempt: " + retryCount);
                TimeUnit.SECONDS.sleep((long) Math.pow(2, retryCount)); // Exponential backoff
            }
        }
        throw new RuntimeException("Failed to fetch data after retries");
    }

    public MovieDetail getMovieDetail(int movieId) {
        String url = String.format("%s/movie/%d?language=en-US", BASE_URL, movieId);
        LOG.info("API  "+ url);
        return tmDbRestTemplate.getForObject(url, MovieDetail.class);
    }

}
