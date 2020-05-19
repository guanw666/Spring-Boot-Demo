package com.example.demo.dto;

import lombok.Data;

@Data
public class QuestionQueryDTO {
    private Integer pageSize;
    private Integer offset;
    private String search;
}
