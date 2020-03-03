package com.objectcomputing;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

@Client(id = "inventory", path = "/product")
public interface ProductClient {

    @Get("/{id}")
    ProductDetails show(Long id);

    @Get("/")
    List<ProductDetails> list();
}
