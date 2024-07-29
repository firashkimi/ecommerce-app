package com.ideacop.ecommerce.backend.infraestructure.rest;

import com.ideacop.ecommerce.backend.application.ProductService;
import com.ideacop.ecommerce.backend.domain.model.Product;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/admin/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Product> save(@RequestPart("product") Product product,
                                        @Parameter()
                                        @RequestPart(value = "image", required = false) MultipartFile image) {
        Product productoGuardado= null;

        try {
            productoGuardado = productService.save(product, image);
            return new ResponseEntity<>(productoGuardado, HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
