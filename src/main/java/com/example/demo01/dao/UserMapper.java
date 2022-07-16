package com.example.demo01.dao;

import com.example.demo01.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by anita on 2022/6/18.
 */
@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user01 where name =#{name}")
    User getUserName(String name);

    @Select("select * from user01 where name =#{name}and sex =#{sex}")
    User getUser1(String name,String sex);

    @Select("select * from user01 where sex =#{sex}")
    User getUserSex(int sex);

}
