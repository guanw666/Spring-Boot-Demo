package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.model.Comment;
import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Resource
    private CommentMapper commentMapper;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {

        //User user = (User) request.getSession().getAttribute("user");

        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setParentId(commentDTO.getParentId());
        comment.setType(1);
        comment.setComment(commentDTO.getComment());
        //comment.setCommentor(user.getId());
        comment.setCommentator(1L);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());

        commentMapper.insertSelective(comment);

        return null;
    }
}
