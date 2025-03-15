package com.om.movieapp.service;

import com.om.movieapp.model.omdb.SearchResponse;
import com.om.movieapp.model.tmdb.MovieDetail;
import com.om.movieapp.model.tmdb.MovieSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class OmdbService {
    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);

    private static final String BASE_URL = "http://www.omdbapi.com/?apikey=4f6d6f43";
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    @Qualifier("tmDbRestTemplate")
    private RestTemplate omDbRestTemplate;

    public SearchResponse discover(final String name) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("t", name)  // Corrected query parameter syntax
                .toUriString();

        LOG.info("Fetching movie details from URL: {}", url);

        SearchResponse response = omDbRestTemplate.getForObject(url, SearchResponse.class);

        // Log the full response
        if (response != null) {
            LOG.info("Received response from OMDB: {}", response);
        } else {
            LOG.warn("No response received from OMDB for movie: {}", name);
        }

        return response;
    }




}
