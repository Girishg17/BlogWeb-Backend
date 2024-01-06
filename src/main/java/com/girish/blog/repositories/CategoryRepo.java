package com.girish.blog.repositories;

import com.girish.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo  extends JpaRepository<Category,Integer> {


}
