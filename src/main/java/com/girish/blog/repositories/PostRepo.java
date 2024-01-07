package com.girish.blog.repositories;

import com.girish.blog.entities.Category;
import com.girish.blog.entities.PostEntity;
import com.girish.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<PostEntity,Integer> {

    List<PostEntity>findByUser(User user);
    List<PostEntity>findByCategory(Category category);
}
