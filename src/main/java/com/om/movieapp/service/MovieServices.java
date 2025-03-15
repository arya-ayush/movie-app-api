package com.om.movieapp.service;


import com.om.movieapp.repository.MovieLogDao;
import com.om.movieapp.model.omdb.MovieDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServices {

    @Autowired
    private MovieLogDao movieDao;

    public void saveMovie(MovieDetail movieDetail) {
        movieDao.saveMovie(movieDetail);
    }
}