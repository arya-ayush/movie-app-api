package com.om.movieapp.controller;

import com.om.movieapp.dao.PurchaseDao;
import com.om.movieapp.model.AddCoinRequest;
import com.om.movieapp.model.PlayProducts;
import com.om.movieapp.repository.PlayProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.om.movieapp.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/products")
public class PlayProductsController {

    @Autowired
    private PlayProductsRepository productRepository;
    @Autowired
    private PurchaseDao purchaseDao;
    @GET
    @Path("/get")
    public List<PlayProducts> getAllProducts() {
        return productRepository.getAllProducts();
    }
    @POST
    @Path("/addCoin")
    public Response addCoins(AddCoinRequest request) {
        if (request.getUserId() == null || request.getAmount() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Collections.singletonMap("message", "Invalid parameters"))
                    .build();
        }

        purchaseDao.addCoinsToUser(request.getUserId(), request.getAmount());

        return Response.ok(Collections.singletonMap("message", "Coins added successfully")).build();
    }

        @POST
        @Path("/buy")

        public ResponseEntity<Map<String, Object>> buyMovie(@RequestBody Purchase purchase) {
            int currentCoins = purchaseDao.getUserCoins(purchase.getUserId());
            Map<String, Object> response = new HashMap<>();

            if (currentCoins < purchase.getAmount()) {
                response.put("message", "Not enough coins to purchase");
                response.put("currentCoins", currentCoins);
                return ResponseEntity.ok(response);
            }

            boolean success = purchaseDao.savePurchase(purchase);
            int updatedCoins = purchaseDao.getUserCoins(purchase.getUserId());

            response.put("message", success ? "Purchase recorded successfully" : "Failed to record purchase");
            response.put("currentCoins", updatedCoins);

            return ResponseEntity.ok(response);
        }

}