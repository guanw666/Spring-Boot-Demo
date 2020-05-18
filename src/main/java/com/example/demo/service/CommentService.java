package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.enums.CommentTypeEnum;
import com.example.demo.enums.NotificationStatusEnum;
import com.example.demo.enums.NotificationTypeEnum;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import com.example.demo.mapper.*;
import com.example.demo.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private CommentExtMapper commentExtMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private QuestionExtMapper questionExtMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private NotificationMapper notificationMapper;

    @Transactional
    public void insert(Comment comment, User user) {

        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PATAM_ERROR);
        }

        // 评论问题,同时将问题的评论数加一
        if (CommentTypeEnum.QUESTION.getType().equals(comment.getType())) {
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            // 插入评论
            commentMapper.insertSelective(comment);
            // 问题评论数+1
            question.setCommentCount(1L);
            questionExtMapper.incCommentCount(question);
            // 创建通知
            createNotify(comment.getCommentator(), question.getCreator(), user.getName(), question.getTitle(),
                    question.getId(), NotificationTypeEnum.REPLY_QUESTION);
        }
        // 回复评论,同时将评论数加一
        else {
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            // 插入评论
            commentMapper.insertSelective(comment);
            // 评论的评论数+1
            Comment incComment = new Comment();
            incComment.setId(dbComment.getId());
            incComment.setCommentCount(1L);
            commentExtMapper.incCommentCount(incComment);
            // 创建通知
            createNotify(comment.getCommentator(), dbComment.getCommentator(), user.getName(), question.getTitle(),
                    question.getId(), NotificationTypeEnum.REPLY_COMMENT);
        }
    }

    private void createNotify(Long commentator, Long receiver, String notiferName, String outerTitle, Long outerId,
                              NotificationTypeEnum notificationType) {
        if (receiver.equals(commentator)) {
            return;
        }
        Notification notification = new Notification();
        notification.setNotifer(commentator);
        notification.setNotiferName(notiferName);
        notification.setReceiver(receiver);
        notification.setType(notificationType.getType());
        notification.setOuterId(outerId);
        notification.setOuterTitle(outerTitle);
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum commentTypeEnum) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(commentTypeEnum.getType());
        commentExample.setOrderByClause("gmt_create desc");
        // 问题评论列表
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        if (commentList == null || commentList.size() == 0) {
            return new ArrayList<>(0);
        }
        // 问题评论列表中所有不重复的评论人id
        List<Long> userIds = commentList.stream().map(Comment::getCommentator).distinct().collect(Collectors.toList());
        // userList
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> userList = userMapper.selectByExample(userExample);
        // Map<userId,user>
        Map<Long, User> longUserMap = userList.stream().collect(Collectors.toMap(User::getId, user -> user));
        return commentList.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(longUserMap.get(commentDTO.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
    }
}
