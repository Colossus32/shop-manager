package com.colossus.manager.repository.impl

import com.colossus.manager.entity.Product
import com.colossus.manager.repository.ProductRepository
import org.springframework.stereotype.Repository
import java.util.*
import java.util.stream.LongStream

@Repository
class InMemoryProductRepository: ProductRepository {

    val products = Collections.synchronizedList(LinkedList<Product>())

    init {
        LongStream.range(1,4)
            .forEach() { i -> products.add(Product(i, "Product $i", "Product $i details")) }
    }
    override fun getAllProducts(): List<Product> {
        return Collections.unmodifiableList(products)
    }

    override fun save(product: Product): Product {
        product.id = products.stream()
            .map(Product::id)
            .max(Comparator.naturalOrder<Long?>()?.reversed())
            .orElse(products.size.toLong())?.plus(1)

        products.add(product)
        return product
    }
}