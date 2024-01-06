package com.girish.blog.controllers;

import com.girish.blog.payloads.CategoryDto;
import com.girish.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService catservice;
    @PostMapping("/createCat")
    public ResponseEntity<CategoryDto>createCategory(@Valid@RequestBody CategoryDto catdto)
    {
        CategoryDto created=this.catservice.createCategory(catdto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @PostMapping("/updateCat/{catId}")
    public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto catdto, @PathVariable("catId") Integer catId){
        CategoryDto updated=this.catservice.updateCategory(catdto,catId);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }
    @DeleteMapping("/deleteCat/{catId}")
    public  ResponseEntity<?>deleteCategory(@PathVariable ("catId")Integer catId)
    {
        this.catservice.deleteCategory(catId);
        return new ResponseEntity<>(Map.of("message", "Category deleted succesfully"), HttpStatus.OK);
    }
    @PostMapping("/getCatById/{catId}")
    public ResponseEntity<CategoryDto>getCategoryById(@PathVariable ("catId")Integer catId)
    {
        CategoryDto cat=this.catservice.getCategory(catId);
        return new ResponseEntity<>(cat,HttpStatus.OK);

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>>getAllCat(){
        return ResponseEntity.ok(this.catservice.getAllCategories());
    }
}
