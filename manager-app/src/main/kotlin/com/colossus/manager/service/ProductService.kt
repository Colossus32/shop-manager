package com.colossus.manager.service

import com.colossus.manager.entity.Product
import java.util.Optional

interface ProductService {

    fun getAllProducts(): List<Product>
    fun createProduct(title: String, details: String): Product
    fun getProductById(productId: Long): Optional<Product>

    fun updateProduct(productId: Long, title: String, details: String): Product

    fun deleteProductById(productId: Long)
}