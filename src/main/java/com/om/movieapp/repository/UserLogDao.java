package com.om.movieapp.repository;


import com.om.movieapp.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserLogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveUser(User user) {
        String sql = "INSERT INTO users (user_id, name, email, photo_url,coins) " +
                "VALUES (?, ?, ?, ?,?)";

        jdbcTemplate.update(sql,
                user.getUser_id(),
                user.getName(),
                user.getEmail(),
                user.getPhoto_url(),
                user.getCoins()
        );
    }
    public boolean userExists(String userId, String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE user_id = ? OR email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, email);
        return count != null && count > 0;  // returns true if found, false if not
    }
    public User getUserByIdOrEmail(String userId, String email) {
        String sql = "SELECT * FROM users WHERE user_id = ? OR email = ? LIMIT 1";

        List<User> users = jdbcTemplate.query(sql, new Object[]{userId, email}, (rs, rowNum) -> {
            User user = new User();
            user.setUser_id(rs.getString("user_id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPhoto_url(rs.getString("photo_url"));
            user.setCoins(rs.getInt("coins"));

            return user;
        });

        if (users.isEmpty()) {
            return null; // or throw custom exception or handle as you want
        }
        return users.get(0);
    }
}