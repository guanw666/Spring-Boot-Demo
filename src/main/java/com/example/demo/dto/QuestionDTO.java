package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Data;

@Data
public class QuestionDTO {

    private Long id;

    private String title;

    private String description;

    private Long gmtCreate;

    private Long gmtModified;

    private Long creator;

    private Long commentCount;

    private Long viewCount;

    private Long likeCount;

    private String tag;

    private User user;
}
