package com.myblog2.payload;

import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;
    private Long postId;

}
