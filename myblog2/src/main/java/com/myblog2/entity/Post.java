package com.myblog2.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name="posts",uniqueConstraints = {@UniqueConstraint(columnNames={"title"})} )
@Data
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="title",nullable=false)
    private String title;
    @Column(name="description",nullable=false)
    private String description;
    @Column(name="content",nullable=false)
    private String content;
    @OneToMany(mappedBy="post",cascade=CascadeType.ALL)
     List<Comment> comments =new ArrayList<>();


}