package com.objectcomputing

import groovy.util.logging.Slf4j
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.security.annotation.Secured

@Slf4j
@Secured("isAuthenticated()")
@Controller("/product")
class ProductController /* implements ProductApi */ {

    final ProductService productService

    ProductController(ProductService productService) {
        this.productService = productService
    }

    @Get("/{id}")
    ProductDetails show(Long id) {
        return productService.findById(id) as ProductDetails ?: null
    }

    @Get("/")
    List<ProductDetails> list(@Header tenantId) {
        log.info("tenantId: ${tenantId}")
        return productService.list().collect { it as ProductDetails }
    }


}
