package com.objectcomputing

import grails.gorm.MultiTenant
import grails.gorm.annotation.Entity

@Entity
class Product implements MultiTenant<Product> {

    Long id
    String name
    String company

    static mapping = {
        tenantId name:'company'
    }
}
