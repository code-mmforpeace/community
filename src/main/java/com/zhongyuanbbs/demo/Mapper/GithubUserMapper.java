package com.zhongyuanbbs.demo.Mapper;

import com.zhongyuanbbs.demo.domain.GitHubUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GithubUserMapper {

    Integer addGithubUser(GitHubUser gitHubUser);

    GitHubUser getGitHubUserByToken(String token);

    GitHubUser getGithunUserById(Integer id);

    Integer updateGithunUser(GitHubUser gitHubUser);

    GitHubUser getGithunUserByTId(Integer id);

    List<GitHubUser> queryUserByUserIds(@Param("userIds") List<Integer> userIds);
}
