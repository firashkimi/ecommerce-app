package com.ideacop.ecommerce.backend.infraestructure.rest;

import com.ideacop.ecommerce.backend.application.CategoryService;
import com.ideacop.ecommerce.backend.domain.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
