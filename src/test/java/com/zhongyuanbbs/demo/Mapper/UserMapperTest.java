package com.zhongyuanbbs.demo.Mapper;

import com.zhongyuanbbs.demo.DemoApplicationTests;
import com.zhongyuanbbs.demo.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


public class UserMapperTest extends DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setZkUsername("root");
        user.setZkPassword("root");
        user.setCreateTime(new Date());
        user.setLastEditTime(new Date());
        Integer integer = userMapper.addUser(user);
        System.out.println(integer);
    }

}
