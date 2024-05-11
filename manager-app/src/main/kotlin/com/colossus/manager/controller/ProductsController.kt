package com.colossus.manager.controller

import com.colossus.manager.entity.Product
import com.colossus.manager.entity.payload.NewProductPayload
import com.colossus.manager.service.ProductService
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

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
    fun createNewProduct(@Valid newProductPayload: NewProductPayload,
                         bindingResult: BindingResult,
                         model: Model): String {

        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", newProductPayload)
            model.addAttribute("errors", bindingResult.allErrors.stream()
                .map { ObjectError:: getDefaultMessage}
                .toList())
            return "catalogue/products/new_product"
        }

        val product: Product = productService.createProduct(newProductPayload.title, newProductPayload.details)
        return "redirect:/catalogue/products/%d".format(product.id)
    }

}