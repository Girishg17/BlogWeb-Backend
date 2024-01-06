package com.girish.blog.services;

import com.girish.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categorydto);
    CategoryDto updateCategory(CategoryDto categorydto,Integer catId);
    void deleteCategory(Integer catId);
    CategoryDto getCategory(Integer catId);
    List<CategoryDto> getAllCategories();
}
