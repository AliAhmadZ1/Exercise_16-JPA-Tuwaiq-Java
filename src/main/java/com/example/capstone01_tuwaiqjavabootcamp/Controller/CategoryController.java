package com.example.capstone01_tuwaiqjavabootcamp.Controller;

import com.example.capstone01_tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Category;
import com.example.capstone01_tuwaiqjavabootcamp.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    //7- Create endpoint for getting and adding and deleting updating a Category.

    @GetMapping("/get")
    public ResponseEntity getCategories() {
        if (categoryService.getCategories().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("there are no category"));
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("new category is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id,@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (categoryService.updateCategory(id,category))
            return ResponseEntity.status(200).body(new ApiResponse("category is updated"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        if (categoryService.deleteCategory(id))
            return ResponseEntity.status(200).body(new ApiResponse("category is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }


}
