package com.girish.blog.services;

import com.girish.blog.entities.PostEntity;
import com.girish.blog.payloads.PostDto;
import com.girish.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postdto,Integer userId,Integer catId);

    PostDto updatePost(PostDto postdto,Integer postid);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageno, Integer pagesize);

    PostDto getPostById(Integer postId);

    List<PostDto>getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);

}
