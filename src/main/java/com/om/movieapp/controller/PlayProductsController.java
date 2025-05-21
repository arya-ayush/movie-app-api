package com.om.movieapp.controller;

import com.om.movieapp.model.PlayProducts;
import com.om.movieapp.repository.PlayProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/products")
public class PlayProductsController {

    @Autowired
    private PlayProductsRepository productRepository;

    @GET
    @Path("/get")
    public List<PlayProducts> getAllProducts() {
        return productRepository.getAllProducts();
    }
}