package com.example.demo.controller;

import com.example.demo.cache.TagCache;
import com.example.demo.service.QuestionService;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PusblishController {

    @Resource
    private QuestionService questionService;

    @GetMapping("/publish")
    public String pusblish(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(name = "title", required = false) String title,
                            @RequestParam(name = "description", required = false) String description,
                            @RequestParam(name = "tag", required = false) String tag,
                            @RequestParam(name = "id", required = false) Long id,
                            HttpServletRequest request,
                            Model model) {
        // 支持校验失败后页面能够回显
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", TagCache.get());
        // 判断是否登录
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            model.addAttribute("error", "用户未登录！");
            return "publish";
        }
        // 字段校验
        if (title == null || "".equals(title)) {
            model.addAttribute("error", "标题为空！");
            return "publish";
        }
        if (description == null || "".equals(description)) {
            model.addAttribute("error", "内容为空！");
            return "publish";
        }
        if (tag == null || "".equals(tag)) {
            model.addAttribute("error", "标签为空！");
            return "publish";
        }
        String invalidTags = TagCache.filterInvalidTag(tag);
        if (StringUtils.isNotBlank(invalidTags)) {
            model.addAttribute("error", "输入非法标签：" + invalidTags);
            return "publish";
        }
        // 保存问题
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.saveOrUpdate(question);
        return "redirect:index";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("id", questionDTO.getId());
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }
}
