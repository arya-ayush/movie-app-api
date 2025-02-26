package com.om.movieapp.service;

import com.om.movieapp.dao.ContentDao;
import com.om.movieapp.model.Highlight;
import com.om.movieapp.model.youtube.Thumbnails;
import com.om.movieapp.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentDao contentDao;

    public List<Highlight> getHighlights() {
        List<Highlight> highlightList = contentDao.getHighlights();
        highlightList.forEach(highlight -> {
            highlight.setPosters(JsonUtil.listJson(highlight.getPosterJson(), Thumbnails.class));
            highlight.setPosterJson(null);
        });
        return highlightList;
    }



}
