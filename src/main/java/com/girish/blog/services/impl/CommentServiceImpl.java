package com.girish.blog.services.impl;

import com.girish.blog.entities.Comment;
import com.girish.blog.entities.PostEntity;
import com.girish.blog.exceptions.ResourceNotFound;
import com.girish.blog.payloads.CommenDto;

import com.girish.blog.repositories.CommentRepo;
import com.girish.blog.repositories.PostRepo;
import com.girish.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentrepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommenDto createComment(CommenDto commenDto, Integer postId) {
        PostEntity post=this.postRepo.findById(postId) .orElseThrow(()-> new ResourceNotFound("post","post id",postId));

       Comment comment=this.modelMapper.map(commenDto,Comment.class);
       comment.setPost(post);
       Comment saved=this.commentrepo.save(comment);
       return this.modelMapper.map(saved,CommenDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment com=this.commentrepo.findById(commentId).orElseThrow(()-> new ResourceNotFound("comment","comment id",commentId));
        this.commentrepo.delete(com);
    }
}
