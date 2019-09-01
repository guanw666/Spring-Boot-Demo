package com.example.demo.controller;

import com.example.demo.service.QuestionService;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private QuestionService questionService;

    @GetMapping("/profile/{section}")
    public String profile(@PathVariable(name = "section") String section,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          Model model) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("section", section);
        if ("questions".equals(section)) {
            model.addAttribute("sectionName", "我的提问");
            // 查询问题列表
            PaginationDTO pagination = questionService.list(user.getId(), page, size);
            model.addAttribute("pagination", pagination);
        } else if ("replies".equals(section)) {
            model.addAttribute("sectionName", "最新回复");
        }
        return "profile";
    }
}
