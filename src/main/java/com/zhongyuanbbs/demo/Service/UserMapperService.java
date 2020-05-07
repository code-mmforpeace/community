package com.zhongyuanbbs.demo.Service;


import com.zhongyuanbbs.demo.domain.User;

import java.util.List;

public interface UserMapperService {

    List<User> findAll();

    Integer addUser(User user);

}
