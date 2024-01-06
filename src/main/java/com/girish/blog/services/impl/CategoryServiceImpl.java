package com.girish.blog.services.impl;

import com.girish.blog.entities.Category;
import com.girish.blog.entities.User;
import com.girish.blog.exceptions.ResourceNotFound;
import com.girish.blog.payloads.CategoryDto;
import com.girish.blog.repositories.CategoryRepo;
import com.girish.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo catrepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categorydto) {
        Category category=this.modelMapper.map(categorydto,Category.class);
        System.out.println(category.getCategoryTitle());
        Category addCat=this.catrepo.save(category);
        return this.modelMapper.map(addCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categorydto, Integer catId) {
        Category catofId =this.catrepo.findById(catId)
                .orElseThrow(()->new ResourceNotFound("Category","Id",catId));
        catofId.setCategoryDescription(categorydto.getCategoryDescription());
        catofId.setCategoryTitle(categorydto.getCategoryTitle());
        Category updated=this.catrepo.save(catofId);
        return this.modelMapper.map(updated ,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer catId) {
        Category catofId =this.catrepo.findById(catId)
                .orElseThrow(()->new ResourceNotFound("Category","Id",catId));
        this.catrepo.delete(catofId);

    }

    @Override
    public CategoryDto getCategory(Integer catId) {
        Category catofId =this.catrepo.findById(catId)
                .orElseThrow(()->new ResourceNotFound("Category","Id",catId));
        return this.modelMapper.map(catofId,CategoryDto.class);


    }


    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category>category=this.catrepo.findAll();
        List<CategoryDto>list=category.stream().map(category1 -> new CategoryDto(category1.getCategoryId(),category1.getCategoryDescription(),category1.getCategoryTitle())).toList();
        return  list;
    }
}
