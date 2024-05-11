package com.colossus.manager.service.impl

import com.colossus.manager.entity.Product
import com.colossus.manager.repository.ProductRepository
import com.colossus.manager.service.ProductService
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class DefaultProductService(private val productRepository: ProductRepository): ProductService {
    override fun getAllProducts(): List<Product> {
        return productRepository.getAllProducts()
    }

    override fun createProduct(title: String, details: String): Product {
        return productRepository.save(Product(null, title, details))
    }

    override fun getProductById(productId: Long): Optional<Product> {
        return productRepository.findById(productId)
    }

    override fun updateProduct(productId: Long, title: String, details: String): Product {
        val product = productRepository.findById(productId).orElseThrow { RuntimeException("Product not found") }
        val updatedProduct = product.copy(title = title, details = details)
        productRepository.deleteById(productId)
        productRepository.save(updatedProduct)
        return updatedProduct
    }

    override fun deleteProductById(productId: Long) {
        productRepository.deleteById(productId)
    }

}