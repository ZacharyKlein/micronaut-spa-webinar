package com.objectcomputing.gateway;

import com.objectcomputing.ProductApi;
import com.objectcomputing.clients.ProductClient;
import com.objectcomputing.ProductDetails;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;

import java.util.List;

@Secured("isAuthenticated()")
@Controller("/products")
public class ProductGateway implements ProductApi {

    private final ProductClient productClient;

    ProductGateway(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Get("/")
    public List<ProductDetails> list() {
        return productClient.list();
    }

    @Get("/{identifier}")
    public ProductDetails show(Long identifier) {
        return productClient.show(identifier);
    }

}
