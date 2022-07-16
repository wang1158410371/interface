package com.example.demo01.controller;

import com.example.demo01.entity.User;
import com.example.demo01.service.UsersSer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by anita on 2022/7/3.
 */
@RestController
@RequestMapping("hello1")
public class Usercontroller {
    @Autowired
    private UsersSer usersSer;
    @GetMapping("hello1")
    public String hello1(){
        return "hello1 world";
    }

    @GetMapping("/{sex}")
    public User getUser(@PathVariable("sex") int sex){
        System.out.println(sex);
        return usersSer.getUserSex(sex);
    }

    @GetMapping("/123")
    public User getNameUser(@Param("name") String name){
        System.out.println(name);
        return usersSer.getUser(name);
    }

    @GetMapping("/1")
    public User getUser1(String name,String sex){

        return usersSer.getUser1(name,sex);
    }

  @RequestMapping("/222")
    public String getUser3(String name,String sex){

        return "姓名为"+name+"年龄为"+sex;
    }

}
