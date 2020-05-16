package com.zhongyuanbbs.demo.Service.impl;

import com.zhongyuanbbs.demo.Mapper.GithubUserMapper;
import com.zhongyuanbbs.demo.Service.GithubUserService;
import com.zhongyuanbbs.demo.domain.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GithubUserServiceImpl implements GithubUserService {

    @Autowired
    private GithubUserMapper githubUserMapper;

    @Override
    public Integer addGithubUser(GitHubUser gitHubUser) {
        return githubUserMapper.addGithubUser(gitHubUser);
    }

    @Override
    public GitHubUser getGitHubUserByToken(String token) {
        return githubUserMapper.getGitHubUserByToken(token);
    }

    @Override
    public GitHubUser getGithunUserById(Integer id) {
        return githubUserMapper.getGithunUserById(id);
    }

    @Override
    public Integer updateGithunUser(GitHubUser gitHubUser) {
        return githubUserMapper.updateGithunUser(gitHubUser);
    }

    @Override
    public GitHubUser getGithunUserByTId(Integer creator) {
        return githubUserMapper.getGithunUserByTId(creator);
    }
}
