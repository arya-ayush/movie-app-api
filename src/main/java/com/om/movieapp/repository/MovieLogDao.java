package com.om.movieapp.repository;


import com.om.movieapp.model.omdb.MovieCategory;
import com.om.movieapp.model.omdb.MovieDetail;
import com.om.movieapp.model.omdb.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    public List<MovieDetail> fetchFeaturedMovies() {
        String sql = "SELECT * FROM movies";

        return jdbcTemplate.query(sql, new RowMapper<MovieDetail>() {
            @Override
            public MovieDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                MovieDetail movie = new MovieDetail();
                movie.setName(rs.getString("name"));
                movie.setDescription(rs.getString("description"));
                movie.setTrailerUrl(rs.getString("trailer_url"));
                movie.setMovieUrl(rs.getString("movie_url"));
                movie.setPosterUrl(rs.getString("poster_url"));
                movie.setBackdropUrl(rs.getString("backdrop_url"));
                movie.setGenre(rs.getString("genre"));
                movie.setRuntime(rs.getString("runtime"));
                movie.setReleaseDate(rs.getString("release_date"));
                movie.setTmdbId(rs.getString("tmdb_id"));
                movie.setDeleteFlag(rs.getBoolean("delete_flag"));
                movie.setTagline(rs.getString("tagline"));
                movie.setStatus(rs.getString("status"));
                movie.setCategoryId(Integer.valueOf(rs.getString("category_id")));

                 movie.setTypeId(Integer.valueOf(rs.getString("type_id")));
                movie.setFeaturedFlag(rs.getBoolean("featured_flag"));
                return movie;
            }
        });
    }
    public List<MovieType> fetchMovieTypes() {
        String sql = "SELECT * FROM movie_types";

        return jdbcTemplate.query(sql, new RowMapper<MovieType>() {
            @Override
            public MovieType mapRow(ResultSet rs, int rowNum) throws SQLException {
                MovieType type = new MovieType();
                type.setId(rs.getInt("id"));
                type.setName(rs.getString("name"));
                return type;
            }
        });
    }
    public List<MovieCategory> fetchMovieCategories() {
        String sql = "SELECT * FROM movie_categories";

        return jdbcTemplate.query(sql, new RowMapper<MovieCategory>() {
            @Override
            public MovieCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
                MovieCategory category = new MovieCategory();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                return category;
            }
        });
    }
}