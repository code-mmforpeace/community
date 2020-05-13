package com.zhongyuanbbs.demo.Service;

import com.zhongyuanbbs.demo.domain.GitHubUser;

public interface GithubUserService {

    Integer addGithubUser(GitHubUser gitHubUser);

    GitHubUser getGitHubUserByToken(String token);

    GitHubUser getGithunUserById(Integer id);

    Integer updateGithunUser(GitHubUser gitHubUser);

}
