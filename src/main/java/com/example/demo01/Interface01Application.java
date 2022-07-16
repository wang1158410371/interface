package com.example.demo01;

import com.example.demo01.entity.User;
import com.example.demo01.service.UsersSer;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@MapperScan("com/example/demo01/dao")

public class Interface01Application {




    public static void main(String[] args) {
        SpringApplication.run(Interface01Application.class, args);
    }

}
