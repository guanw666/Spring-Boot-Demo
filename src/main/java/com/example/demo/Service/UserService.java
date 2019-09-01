package com.example.demo.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.model.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> userList = userMapper.selectByExample(example);
        if (userList != null && userList.size() > 0) {
            User dbUser = userList.get(0);
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setBio(user.getBio());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            userMapper.updateByPrimaryKeySelective(dbUser);
        } else {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }
    }
}
