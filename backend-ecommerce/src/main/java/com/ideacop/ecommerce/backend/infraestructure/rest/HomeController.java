package com.ideacop.ecommerce.backend.infraestructure.rest;

import com.ideacop.ecommerce.backend.application.ProductService;
import com.ideacop.ecommerce.backend.domain.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }
}
