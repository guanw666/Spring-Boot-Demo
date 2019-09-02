package com.example.demo.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {

    private Long parentId;

    private Integer type;

    private String comment;
}
