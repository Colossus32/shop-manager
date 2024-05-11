package com.colossus.manager.service.impl

import com.colossus.manager.entity.Product
import com.colossus.manager.repository.ProductRepository
import com.colossus.manager.service.ProductService
import org.springframework.stereotype.Service

@Service
class DefaultProductService(private val productRepository: ProductRepository): ProductService {
    override fun getAllProducts(): List<Product> {
        return productRepository.getAllProducts()
    }

    override fun createProduct(title: String, details: String): Product {
        return productRepository.save(Product(null, title, details))
    }

}