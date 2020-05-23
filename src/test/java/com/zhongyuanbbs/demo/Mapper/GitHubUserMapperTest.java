package com.zhongyuanbbs.demo.Mapper;

import com.zhongyuanbbs.demo.DemoApplicationTests;
import com.zhongyuanbbs.demo.domain.GitHubUser;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GitHubUserMapperTest extends DemoApplicationTests {

    @Autowired
    private GithubUserMapper githubUserMapper;

    @Test
    //@Ignore
    public void testGetGitHubUserById(){
        GitHubUser githunUserById = githubUserMapper.getGithunUserById(64819148);
        System.out.println(githunUserById.getZkGithubUsername());
    }

    @Test
    @Ignore
    public void testUpdateGithubUser(){
        GitHubUser gitHubUser = new GitHubUser();
        gitHubUser.setId(1);
        gitHubUser.setZkGithubAccountId(64819148l);
        gitHubUser.setLastEditTime(new Date());
        gitHubUser.setImageUrl("test");
        githubUserMapper.updateGithunUser(gitHubUser);
    }

    @Test
    public void testQueryGithubUsers(){
        List userIds = Arrays.asList(1,2);
        List<GitHubUser> list = githubUserMapper.queryUserByUserIds(userIds);
        for (GitHubUser user : list) {
            System.out.println(user.getZkGithubUsername());
        }
    }
}
