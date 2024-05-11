package com.colossus.manager.repository

import com.colossus.manager.entity.Product

interface ProductRepository {

    fun getAllProducts(): List<Product>
    fun save(product: Product): Product
}