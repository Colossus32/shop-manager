package com.colossus.manager.repository

import com.colossus.manager.entity.Product
import java.util.*

interface ProductRepository {

    fun getAllProducts(): List<Product>
    fun save(product: Product): Product
    fun findById(productId: Long): Optional<Product>

    fun deleteById(productId: Long)
}