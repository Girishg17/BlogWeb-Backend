package com.girish.blog.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer postId;

    private String title;

    private String content;

    private  String imageName;

    private Date addeddate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private User user;


}
