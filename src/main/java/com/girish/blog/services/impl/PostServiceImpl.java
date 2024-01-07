package com.girish.blog.services.impl;

import com.girish.blog.entities.Category;
import com.girish.blog.entities.PostEntity;
import com.girish.blog.entities.User;
import com.girish.blog.exceptions.ResourceNotFound;
import com.girish.blog.payloads.PostDto;
import com.girish.blog.payloads.PostResponse;
import com.girish.blog.repositories.CategoryRepo;
import com.girish.blog.repositories.PostRepo;
import com.girish.blog.repositories.Userrepo;
import com.girish.blog.services.PostService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Userrepo userrepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postdto,Integer userId,Integer catid) {
        User user=this.userrepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFound("User","user id",userId));

        Category category=this.categoryRepo.findById(catid)
                .orElseThrow(()-> new ResourceNotFound("category","category id",catid));
        PostEntity post=this.modelMapper.map(postdto,PostEntity.class);
        post.setImageName("default.png");
        post.setAddeddate(new Date());
        post.setUser(user);
        post.setCategory(category);
        PostEntity saved=this.postRepo.save(post);
        return this.modelMapper.map(saved,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postdto, Integer postId) {
        PostEntity post=this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFound("post","post id",postId));
        post.setTitle(postdto.getTitle());
        post.setContent(postdto.getContent());
        post.setImageName(postdto.getImageName());
        PostEntity updated=this.postRepo.save(post);
        return this.modelMapper.map(post,PostDto.class);

    }

    @Override
    public void deletePost(Integer postId) {
        PostEntity post=this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFound("post","post id",postId));
        this.postRepo.delete(post);


    }

    @Override
    public PostResponse getAllPost(Integer pagNumber, Integer pagesize,String sortby,String dir) {
        Sort sort=null;
        if(dir.equalsIgnoreCase("asc"))
        {
            sort=Sort.by(sortby).ascending();
        }
        else{
            sort=Sort.by(sortby).descending();
        }
        Pageable p= PageRequest.of(pagNumber,pagesize, sort);
        Page<PostEntity> pagePost=this.postRepo.findAll(p);
        List<PostEntity>content=pagePost.getContent();
        List<PostDto>all=content.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(all);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return  postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        PostEntity post=this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFound("post","post id",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId)

                .orElseThrow(()->new ResourceNotFound("category","of id",categoryId));
        List<PostEntity>posts=this.postRepo.findByCategory(cat);
        List<PostDto>postDtoList=posts.stream().map((post)->this
                .modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtoList;

    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user=this.userrepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFound("User","user id",userId));
        List<PostEntity>postUser=this.postRepo.findByUser(user);
         List<PostDto>postDtoList=postUser.stream().map(post->this
                .modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
         return  postDtoList;


    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
