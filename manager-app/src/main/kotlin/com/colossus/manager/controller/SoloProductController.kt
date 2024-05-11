package com.colossus.manager.controller

import com.colossus.manager.entity.Product
import com.colossus.manager.service.ProductService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/catalogue/products/{productId}")
class SoloProductController(private val productService: ProductService) {

    @ModelAttribute("product")
    fun product(@PathVariable("productId") productId: Long): Product {
        return productService.getProductById(productId).orElseThrow()
    }

    @GetMapping
    fun getProduct(): String {
        return "catalogue/products/product"
    }

    @GetMapping("/edit")
    fun getEditProductPage(): String {
        return "catalogue/products/edit"
    }

    @PostMapping("/edit")
    fun updateProduct(@ModelAttribute("product") product: Product,
                      @RequestParam("title") title: String,
                      @RequestParam("details") details: String): String {

        product.id?.let { productService.updateProduct(it, title, details) }
        return "redirect:/catalogue/products/%d".format(product.id)
    }

    @PostMapping("/delete")
    fun deleteProduct(@ModelAttribute("product") product: Product): String {
        product.id?.let { productService.deleteProductById(product.id!!) }
        return "redirect:/catalogue/products/list"
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(exception: NoSuchElementException,
                                     model: Model,
                                     response: HttpServletResponse): String {

        response.status = 404
        model.addAttribute("error", exception.message)
        return "errors/404"
    }
}
