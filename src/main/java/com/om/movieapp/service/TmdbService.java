package com.om.movieapp.service;

import com.om.movieapp.model.tmdb.MovieDetail;
import com.om.movieapp.model.tmdb.MovieSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TmdbService {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

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

    public MovieDetail getMovieDetail(int movieId) {
        String url = String.format("%s/movie/%d?language=en-US", BASE_URL, movieId);
        return tmDbRestTemplate.getForObject(url, MovieDetail.class);
    }

}
