package com.colossus.manager.controller

import com.colossus.manager.entity.Product
import com.colossus.manager.service.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("catalogue/products")
class ProductsController(private val productService: ProductService) {

    @GetMapping("/list")
    fun getProductsList(model: Model): String {
        model.addAttribute("products", productService.getAllProducts())
        return "catalogue/products/list"
    }

    @GetMapping("/create")
    fun getNewProductPage(): String {
        return "catalogue/products/new_product"
    }

    @PostMapping("/create")
    fun createNewProduct(@RequestParam("title") title: String, @RequestParam("details") details: String): String {
        val product: Product = productService.createProduct(title, details)
        return "redirect:/catalogue/products/list"
    }
}