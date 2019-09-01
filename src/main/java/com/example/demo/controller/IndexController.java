package com.example.demo.controller;

import com.example.demo.Service.QuestionService;
import com.example.demo.dto.PaginationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    private QuestionService questionService;

    @GetMapping("/index")
    public String hello(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                        @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
                        Model model) {
        // 查询问题列表
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
