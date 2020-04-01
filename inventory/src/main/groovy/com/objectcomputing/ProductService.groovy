package com.objectcomputing

import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.services.Service

@CurrentTenant
@Service(Product)
interface ProductService {

    Product findById(Serializable id)

    List<Product> list()

    Product save(Product product)
}
