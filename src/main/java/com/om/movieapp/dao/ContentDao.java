package com.om.movieapp.dao;

import com.om.movieapp.model.Highlight;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentDao {

    List<Highlight> getHighlights();

}
