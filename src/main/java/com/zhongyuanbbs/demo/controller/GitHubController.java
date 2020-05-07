package com.zhongyuanbbs.demo.controller;

import com.zhongyuanbbs.demo.Mapper.GithubUserMapper;
import com.zhongyuanbbs.demo.domain.GitHubUser;
import com.zhongyuanbbs.demo.dto.GitHubUserDto;
import com.zhongyuanbbs.demo.provider.GitHubProvider;
import com.zhongyuanbbs.demo.dto.AccessTokenDto;
import com.zhongyuanbbs.demo.utils.HttpRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Controller
public class GitHubController {

    @Autowired
    private GitHubProvider gitHubProvider;
    @Autowired
    private GithubUserMapper githubUserMapper;

    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.redirect.url}")
    private String redirect_url;

    @GetMapping("/callback")
    public String getGitHubCode(HttpServletRequest request, HttpServletResponse response){
        //1.先拿到access_token
        //2.通过access_token去获取用户信息
        String code = HttpRequestUtils.getString(request, "code");
        String state = HttpRequestUtils.getString(request, "state");

        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(client_id);
        accessTokenDto.setClient_secret(client_secret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirect_url);
        accessTokenDto.setState(state);
        //System.out.println(accessTokenDto);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDto);
        GitHubUserDto gitHubUserDto = gitHubProvider.getGitHubUser(accessToken);
        //System.out.println(code+"   "+state);
        //System.out.println(gitHubUserDto.getId());
        //空值判断
        if(gitHubUserDto != null && gitHubUserDto.getId() > -1){
            //登陆成功
            GitHubUser gitHubUser = new GitHubUser();
            gitHubUser.setZkGithubAccountId(gitHubUserDto.getId());
            gitHubUser.setZkGithubToken(String.valueOf(UUID.randomUUID()));
            gitHubUser.setZkGithubBio(gitHubUserDto.getBio());
            gitHubUser.setZkGithubUsername(gitHubUserDto.getName());
            gitHubUser.setCreateTime(new Date());
            gitHubUser.setLastEditTime(new Date());
            Integer i = githubUserMapper.addGithubUser(gitHubUser);
            if(i > -1){
                //TODO
                response.addCookie(new Cookie("token",gitHubUser.getZkGithubToken()));
            }else {

            }
            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";
        }
    }

}
