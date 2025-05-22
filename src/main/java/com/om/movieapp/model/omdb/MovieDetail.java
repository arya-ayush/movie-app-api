package com.om.movieapp.model.omdb;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "movies")  // Table name
public class MovieDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Id
    @Column(name = "id")
    private int movie_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @Column(name = "movie_url")
    private String movieUrl;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "backdrop_url")
    private String backdropUrl;

    @Column(name = "genre")
    private String genre;

    @Column(name = "runtime")
    private String runtime;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate = LocalDateTime.now();

    @Column(name = "tmdb_id", unique = true)
    private String tmdbId;

    @Column(name = "delete_flag", nullable = false)
    private boolean deleteFlag = false;

    @Column(name = "tagline")
    private String tagline;

    @Column(name = "status")
    private String status;


    @Column(name = "buy_coins")
    private int buy_coins;


    @Column(name = "featured_flag", nullable = false)
    private boolean featuredFlag = false;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "type_id", nullable = false)
    private Integer typeId;

    @PreUpdate
    public void updateTimestamp() {
        this.modifiedDate = LocalDateTime.now();
    }
}