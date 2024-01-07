package com.girish.blog.controllers;

import com.girish.blog.config.AppContants;
import com.girish.blog.entities.PostEntity;
import com.girish.blog.payloads.ApiResponse;
import com.girish.blog.payloads.PostDto;
import com.girish.blog.payloads.PostResponse;
import com.girish.blog.services.FileService;
import com.girish.blog.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

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
    public  ResponseEntity<PostResponse>getAllpost(@RequestParam(value = "pageNumber",defaultValue = AppContants.PAGE_NUMBER,required = false)Integer pageNum, @RequestParam(value = "pageSize",defaultValue = AppContants.PAGE_SIZE,required = false)Integer pageSize, @RequestParam(value = "sortBy",defaultValue = AppContants.SORT_BY,required = false)String sortBy, @RequestParam(value = "sortDir",defaultValue = AppContants.SORT_DIR,required = false) String sortDir){
        PostResponse ans =this.postService.getAllPost(pageNum,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(ans,HttpStatus.OK);
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
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>>searchByTitle(@PathVariable(value = "keyword") String keyword)
    {
        List<PostDto>posts=this.postService.searchPosts(keyword);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto>uploasPostImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable(name="postId")Integer postId
            ) throws IOException {
              PostDto postDto=  this.postService.getPostById(postId);
              String fileName=this.fileService.uploadImage(path,image);

              postDto.setImageName(fileName);
              PostDto updatedpost=this.postService.updatePost(postDto,postId);
              return new ResponseEntity<PostDto>(updatedpost,HttpStatus.OK);

    }
    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable(name="imageName")String imageName,
            HttpServletResponse response
    )throws IOException{
        InputStream resource=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }


}
