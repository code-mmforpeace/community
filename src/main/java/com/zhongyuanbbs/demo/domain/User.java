package com.zhongyuanbbs.demo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;
    private String zkUsername;
    private String zkPassword;
    private Date createTime;
    private Date lastEditTime;

}
