package com.ad.blogpost.controllers;

import com.ad.blogpost.entities.Category;
import com.ad.blogpost.payloads.ApiResponse;
import com.ad.blogpost.payloads.CategoryDto;
import com.ad.blogpost.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    // CREATE
    @PostMapping("")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto cdto = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(cdto, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable String categoryId, @RequestBody CategoryDto categoryDto){
        CategoryDto cdto = this.categoryService.updateCategory(categoryDto,Long.parseLong(categoryId));
        return new ResponseEntity<>(cdto,HttpStatus.ACCEPTED);
    }

    // DELETE
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable String categoryId){
        this.categoryService.deleteCategory(Long.parseLong(categoryId));
        return new ResponseEntity<>(new ApiResponse("Category is Deleted",true), HttpStatus.OK);
    }

    // GET
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable String categoryId){
        CategoryDto categoryDto = this.categoryService.getCategory(Long.parseLong(categoryId));
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

    // GET ALL
    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtoList = this.categoryService.getAllCategory();
        return new ResponseEntity<>(categoryDtoList,HttpStatus.OK);
    }

    @GetMapping("/name/{categoryName}")
    public ResponseEntity<CategoryDto> getCategoryByName(@PathVariable String categoryName){
        CategoryDto categoryDto = this.categoryService.getCategoryByName(categoryName);
        return new ResponseEntity<>(categoryDto,HttpStatus.FOUND);
    }
}
