package com.girish.blog.repositories;

import com.girish.blog.entities.Category;
import com.girish.blog.entities.PostEntity;
import com.girish.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<PostEntity,Integer> {

    List<PostEntity>findByUser(User user);
    List<PostEntity>findByCategory(Category category);
    @Query("select p from PostEntity p where p.title like :key")
    List<PostEntity>searchByTitle(@Param("key") String title);
}
