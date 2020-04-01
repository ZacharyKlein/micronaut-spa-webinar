package com.objectcomputing

import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import static grails.gorm.multitenancy.Tenants.*

import javax.inject.Singleton

@Singleton
class ProductBootstrap {

    final ProductService productService

    ProductBootstrap(ProductService productService) {
        this.productService = productService
    }

    @EventListener
    void init(ServerStartupEvent event) {

        ["ACME", "Makers"].each {tenant ->
            withId(tenant) {
                productService.save(new Product(name: "${tenant} Widget A"))
                productService.save(new Product(name: "${tenant} Widget B"))
                productService.save(new Product(name: "${tenant} Widget C"))
            }
        }




    }

}
