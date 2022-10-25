package com.ad.blogpost.services;

import com.ad.blogpost.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    // Create
    CategoryDto createCategory(CategoryDto categoryDto);

    // UPDATE
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    // DELETE
    void deleteCategory(Long categoryId);

    //GET
    CategoryDto getCategory(Long categoryId);

    List<CategoryDto> getAllCategory();

    CategoryDto getCategoryByName(String categoryName);

}
