package com.om.movieapp.service;

import com.om.movieapp.dao.UserDao;
import com.om.movieapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    public User getUser(String uuid) {
        return userDao.getUser(uuid);
    }


}
