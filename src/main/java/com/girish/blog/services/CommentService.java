package com.girish.blog.services;

import com.girish.blog.payloads.CommenDto;

public interface CommentService {
    public CommenDto createComment(CommenDto commenDto,Integer postId);
    void deleteComment(Integer commentId);
}
