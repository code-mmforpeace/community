package com.zhongyuanbbs.demo.Mapper;

import com.zhongyuanbbs.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();

    Integer addUser(User user);

}
