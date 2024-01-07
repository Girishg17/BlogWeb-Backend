package com.girish.blog.payloads;

import com.girish.blog.entities.Category;
import com.girish.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
   private String imageName;
   private Date addeddate;
   private CategoryDto category;
   private UserDto user;


}
