package com.example.bookstore.controller;

import com.example.bookstore.dto.CategoryDto;
import com.example.bookstore.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/category")
    public List<CategoryDto> getAllCategory() {
        return service.getAll();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        Optional<CategoryDto> categoryOptional = service.getById(id);
        if (categoryOptional.isPresent()) {
            CategoryDto category = categoryOptional.get();
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            String errorMessage = "Категории не найдена с id " + id;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        try {
            service.deleteCategory(id);
            return new ResponseEntity<>("Категория удалена", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка удаления категории: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/category/new")
    public void addCategory(@RequestBody CategoryDto category) {
        service.addNewCategory(category);
    }

    @PutMapping("/category/edit/{id}")
    public ResponseEntity<String> editCategory(@RequestBody CategoryDto category, @PathVariable Integer id) {
        try {
            service.updateCategory(category, id);
            return new ResponseEntity<>("Категория обновлена", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка обновления категории: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
