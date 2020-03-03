package com.objectcomputing

import groovy.util.logging.Slf4j
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.security.annotation.Secured

@Slf4j
@Secured("isAuthenticated()")
@Controller("/product")
class ProductController {

    final ProductService productService

    ProductController(ProductService productService) {
        this.productService = productService
    }

    @Get("/{id}")
    Product show(Long id, @Header String tenantId) {
        log.info(tenantId)

        return productService.findById(id) ?: null
    }

    @Get("/")
    List<Product> list(@Header String tenantId) {
        log.info(tenantId)

        return productService.list()
    }


}
