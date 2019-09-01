package com.example.demo.mapper;

import com.example.demo.model.Question;

public interface QuestionExtMapper {
    int incView(Question question);

    int incCommentCount(Question question);
}
