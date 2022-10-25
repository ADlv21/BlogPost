package com.ad.blogpost.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "Posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "content",length = 5000)
    private String content;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "posted_date")
    private Date addedDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

}
