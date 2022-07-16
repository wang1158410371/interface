package com.example.demo01.service;

import com.example.demo01.dao.UserMapper;
import com.example.demo01.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by anita on 2022/6/18.
 */
@Service
public class UsersSer{
    @Resource
    public UserMapper userMapper;



    public User getUser(String name){
        return userMapper.getUserName(name);
    }

    public User getUser1(String name ,String sex){
        return userMapper.getUser1(name,sex);
    }

    public User getUserSex(int sex){
        return userMapper.getUserSex(sex);
    }

}
