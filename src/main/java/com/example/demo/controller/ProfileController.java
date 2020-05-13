package com.example.demo.controller;

import com.example.demo.dto.NotificationDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.model.Notification;
import com.example.demo.service.NotificationService;
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
    private QuestionService questionService;

    @Resource
    private NotificationService notificationService;

    @GetMapping("/profile/{section}")
    public String profile(@PathVariable(name = "section") String section,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(section)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            // 查询问题列表
            PaginationDTO<QuestionDTO> pagination = questionService.list(user.getId(), page, size);
            model.addAttribute("pagination", pagination);
        } else if ("replies".equals(section)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            PaginationDTO<NotificationDTO> pagination = notificationService.list(user.getId(), page, size);
            model.addAttribute("pagination", pagination);
            long unreadCount = notificationService.unreadCount(user.getId());
            model.addAttribute("unreadCount", unreadCount);
        }
        return "profile";
    }
}
