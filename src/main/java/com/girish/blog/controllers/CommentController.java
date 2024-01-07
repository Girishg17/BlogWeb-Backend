package com.girish.blog.controllers;

import com.girish.blog.payloads.ApiResponse;
import com.girish.blog.payloads.CommenDto;
import com.girish.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/post/{postid}/comments")
    public ResponseEntity<CommenDto>createCOM(@RequestBody CommenDto commenDto, @PathVariable(name="postid")Integer postid)
    {
        CommenDto create=this.commentService.createComment(commenDto,postid);
        return new ResponseEntity<CommenDto>(create, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/delete/{commentid}")
    public ResponseEntity<ApiResponse>deletecom(@PathVariable(name="commentid")Integer comid)
    {
        this.commentService.deleteComment(comid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully",true), HttpStatus.CREATED);
    }

}
