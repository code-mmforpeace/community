package com.zhongyuanbbs.demo.Mapper;

import com.zhongyuanbbs.demo.domain.GitHubUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GithubUserMapper {

    Integer addGithubUser(GitHubUser gitHubUser);

    GitHubUser getGitHubUserByToken(String token);

    GitHubUser getGithunUserById(Integer id);

    Integer updateGithunUser(GitHubUser gitHubUser);

    GitHubUser getGithunUserByTId(Integer id);
}
