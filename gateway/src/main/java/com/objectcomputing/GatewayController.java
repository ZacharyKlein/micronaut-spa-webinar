package com.objectcomputing;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;

import java.util.List;

@Secured("isAuthenticated()")
@Controller("/")
public class GatewayController {

    private final ProductClient productClient;

    GatewayController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Get("/products")
    List<ProductDetails> list() {
        return productClient.list();
    }

    @Get("/products/{id}")
    ProductDetails getProduct(Long id) {
        return productClient.show(id);
    }

}
