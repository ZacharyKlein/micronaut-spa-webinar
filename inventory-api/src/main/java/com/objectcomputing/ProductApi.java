package com.objectcomputing;

import io.micronaut.http.annotation.Get;

import java.util.List;

public interface ProductApi {

    @Get("/{identifier")
    ProductDetails show(Long identifier);

    @Get("/")
    List<ProductDetails> list();
}
