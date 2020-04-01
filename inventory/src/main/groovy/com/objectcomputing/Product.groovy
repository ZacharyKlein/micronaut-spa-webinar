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

    //Handle type conversion
    Object asType(Class clazz) {
        if (clazz == ProductDetails) {
            def target = new ProductDetails()
            target.name = this.name
            target.company = this.company
            return target
        }
        else {
            super.asType(clazz)
        }
    }
}
