package com.zhongyuanbbs.demo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Date qsCreateTime;
    private Date qsLastEditTime;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private GitHubUser gitHubUser;
}
