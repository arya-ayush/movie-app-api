package com.om.movieapp.repository;


import com.om.movieapp.model.omdb.MovieDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MovieLogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveMovie(MovieDetail movie) {
        String sql = "INSERT INTO movies (name, description, trailer_url, movie_url, poster_url, backdrop_url, genre, runtime, release_date, tmdb_id, delete_flag, tagline, status, featured_flag, created_date, modified_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

        jdbcTemplate.update(sql,
                movie.getName(),
                movie.getDescription(),
                movie.getTrailerUrl(),
                movie.getMovieUrl(),
                movie.getPosterUrl(),
                movie.getBackdropUrl(),
                movie.getGenre(),
                movie.getRuntime(),
                movie.getReleaseDate(),
                movie.getTmdbId(),
                movie.isDeleteFlag(),
                movie.getTagline(),
                movie.getStatus(),
                movie.isFeaturedFlag()
        );
    }
}