package com.ad.blogpost.services;

import com.ad.blogpost.entities.Category;
import com.ad.blogpost.exceptions.ResourceNotFoundException;
import com.ad.blogpost.payloads.CategoryDto;
import com.ad.blogpost.repositories.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo catRepo;

    @Autowired
    private ModelMapper modelMapper;

    private CategoryDto categoryToDto(Category category) {
        return this.modelMapper.map(category, CategoryDto.class);
    }

    private Category dtoToCategory(CategoryDto categoryDto) {
        return this.modelMapper.map(categoryDto, Category.class);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return categoryToDto(this.catRepo.save(dtoToCategory(categoryDto)));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {

        Category category = catRepo
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoty", "ID", categoryId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        return categoryToDto(this.catRepo.save(category));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        this.catRepo.deleteById(categoryId);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category = this.catRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));
        return categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = this.catRepo.findAll();
        return categoryList.stream().map(this::categoryToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryByName(String categoryName) {
        Category category = this.catRepo.findByCategoryTitle(categoryName);
        return categoryToDto(category);
    }

}
