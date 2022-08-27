package com.om.movieapp.dao;

import com.om.movieapp.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    int insertUser(User user);

    User getUser(String uuid);

}
