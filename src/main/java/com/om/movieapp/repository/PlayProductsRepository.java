package com.om.movieapp.repository;

import com.om.movieapp.model.PlayProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveProduct(PlayProducts product) {
        String sql = "INSERT INTO shorts.products (coin_label, coin_code, coin_value) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                product.getCoinLabel(),
                product.getCoinCode(),
                product.getCoinValue());
    }

    public List<PlayProducts> getAllProducts() {
        String sql = "SELECT * FROM shorts.products";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PlayProducts product = new PlayProducts();
            product.setID(rs.getInt("id"));
            product.setCoinLabel(rs.getString("coin_label"));
            product.setCoinCode(rs.getString("coin_code"));
            product.setCoinValue(rs.getInt("coin_value"));
            return product;
        });
    }

    public boolean productExists(String coinCode) {
        String sql = "SELECT COUNT(*) FROM shorts.products WHERE coin_code = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, coinCode);
        return count != null && count > 0;
    }
}