package com.example.demo.controller;

import com.example.demo.dto.NotificationDTO;
import com.example.demo.enums.NotificationTypeEnum;
import com.example.demo.model.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

    @Resource
    private NotificationService notificationService;

    @Resource
    private CommentService commentService;

    @GetMapping("/notification/{id}")
    public String question(HttpServletRequest request,
                           @PathVariable(name = "id") Long id) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, user.getId());
        if (NotificationTypeEnum.REPLY_QUESTION.getType().equals(notificationDTO.getType())
                || NotificationTypeEnum.REPLY_COMMENT.getType().equals(notificationDTO.getType())) {
            return "redirect:/question/" + notificationDTO.getOuterId();
        } else {
            return "redirect:/";
        }
    }
}
