package com.om.movieapp.dao;


import com.om.movieapp.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class PurchaseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean savePurchase(Purchase purchase) {
        // 1. Check current coin balance
        String checkSql = "SELECT coins FROM users WHERE user_id = ?";
        Integer currentCoins = jdbcTemplate.queryForObject(checkSql, Integer.class, purchase.getUserId());

        if (currentCoins == null || currentCoins < purchase.getAmount()) {
            // Not enough coins, return false
            return false;
        }

        // 2. Proceed with purchase
        String insertSql = "INSERT INTO shorts.purchased (user_id, movie_id, amount) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertSql, purchase.getUserId(), purchase.getMovieId(), purchase.getAmount());

        String updateSql = "UPDATE users SET coins = coins - ? WHERE user_id = ?";
        jdbcTemplate.update(updateSql, purchase.getAmount(), purchase.getUserId());

        return true;
    }
    public int getUserCoins(String userId) {
        String sql = "SELECT coins FROM users WHERE user_id = ?";
        Integer coins = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        return coins != null ? coins : 0;
    }

    public void addCoinsToUser(String userId, int amount) {
        String sql = "UPDATE users SET coins = coins + ? WHERE user_id = ?";
        jdbcTemplate.update(sql, amount, userId);
    }
    public Set<Integer> getPurchasedMovieIdsForUser(String userId) {
        String sql = "SELECT movie_id FROM shorts.purchased WHERE user_id = ?";
        List<Integer> movieIds = jdbcTemplate.queryForList(sql, Integer.class, userId);
        return new HashSet<>(movieIds);
    }
}