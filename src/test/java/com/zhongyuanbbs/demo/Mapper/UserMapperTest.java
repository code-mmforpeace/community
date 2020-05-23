package com.zhongyuanbbs.demo.Mapper;

import com.zhongyuanbbs.demo.DemoApplicationTests;
import com.zhongyuanbbs.demo.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;


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

    //判断字符串是否为唯一字符串
    @Test
    public void testHashSet(){
        HashSet hashSet = new HashSet();
        String s = "abcccsffggwwsfd";
        for (int i = 0; i < s.length(); i++) {
            hashSet.add(s.charAt(i));
        }

        System.out.println(hashSet.size());
        System.out.println(s.length());
    }

}
