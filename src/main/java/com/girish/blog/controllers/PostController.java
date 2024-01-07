package com.girish.blog.controllers;

import com.girish.blog.entities.PostEntity;
import com.girish.blog.payloads.ApiResponse;
import com.girish.blog.payloads.PostDto;
import com.girish.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/user/{userId}/category/{catid}/posts")
    public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto, @PathVariable(name = "userId") Integer userId,@PathVariable(name = "catid") Integer catid)
    {
        PostDto createPost=this.postService.createPost(postDto,userId,catid);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}/postsuser")
    public ResponseEntity<List<PostDto>>getPostByUser(@PathVariable(name = "userId")Integer uid)

    {   List<PostDto>ans=this.postService.getPostByUser(uid);
        return new  ResponseEntity<>(ans,HttpStatus.OK);
    }

    @GetMapping("/user/{catid}/postscat")
    public ResponseEntity<List<PostDto>>getPostByCat(@PathVariable(name = "catid")Integer uid)

    {   List<PostDto>ans=this.postService.getPostByCategory(uid);
        return new  ResponseEntity<>(ans,HttpStatus.OK);
    }
    @GetMapping("/posts")
    public  ResponseEntity<List<PostDto>>getAllpost(){
        List<PostDto>allpost=this.postService.getAllPost();
        return new ResponseEntity<List<PostDto>>(allpost,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public  ResponseEntity<PostDto>getpostById(@PathVariable (name="postId") Integer id){
        PostDto allpost=this.postService.getPostById(id);
        return new ResponseEntity<PostDto>(allpost,HttpStatus.OK);
    }

    @DeleteMapping("/post/delete/{postId}")
    public ApiResponse deletePost(@PathVariable (name="postId") Integer pid)
    {
        this.postService.deletePost(pid);
        return new ApiResponse("Post is deleted sucessfully",true);
    }

    @PutMapping("/post/update/{postId}")
    public ResponseEntity<PostDto> update(@RequestBody PostDto postDto,@PathVariable (name="postId") Integer pid)
    {
        PostDto update=this.postService.updatePost(postDto,pid);
        return new ResponseEntity<PostDto>(update,HttpStatus.OK);
    }


}