package com.zhongyuanbbs.demo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class GitHubUser {

    private Integer id;
    private String zkGithubUsername;
    private Long zkGithubAccountId;
    private String zkGithubToken;
    private String zkGithubBio;
    private Date createTime;
    private Date lastEditTime;
    private String imageUrl;

}
