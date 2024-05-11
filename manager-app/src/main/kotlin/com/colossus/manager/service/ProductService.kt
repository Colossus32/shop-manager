package com.colossus.manager.service

import com.colossus.manager.entity.Product

interface ProductService {

    fun getAllProducts(): List<Product>
    fun createProduct(title: String, details: String): Product
}