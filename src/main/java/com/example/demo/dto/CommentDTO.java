package com.example.demo.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;

    private Long parentId;

    private Integer type;

    private String comment;

    private Long commentator;

    private Long likeCount;

    private Long gmtCreate;

    private Long gmtModified;
}
