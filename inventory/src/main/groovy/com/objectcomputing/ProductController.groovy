package com.objectcomputing

import groovy.util.logging.Slf4j
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Slf4j
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/product")
class ProductController implements ProductApi {

    final ProductService productService

    ProductController(ProductService productService) {
        this.productService = productService
    }

    @Get("/{id}")
    ProductDetails show(Long id) {
        return productService.findById(id) as ProductDetails ?: null
    }

    @Get("/")
    List<ProductDetails> list() {
        return productService.list().collect { it as ProductDetails }
    }


}
