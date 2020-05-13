package com.zhongyuanbbs.demo.controller;

import com.zhongyuanbbs.demo.Service.GithubUserService;
import com.zhongyuanbbs.demo.Service.QuestionService;
import com.zhongyuanbbs.demo.domain.GitHubUser;
import com.zhongyuanbbs.demo.domain.Question;
import com.zhongyuanbbs.demo.utils.HttpRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private GithubUserService githubUserService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name")String name, Model model){
        model.addAttribute("name",name);
        return "index";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request,Model model){
        int pageIndex = HttpRequestUtils.getInt(request, "pageIndex");
        int pageSize = HttpRequestUtils.getInt(request, "pageSize");
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    GitHubUser user = githubUserService.getGitHubUserByToken(token);
                    //System.out.println(user.getZkGithubUsername());
                    if(user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<Question> questionLists = questionService.getQuestionLists(pageIndex,pageSize);
        model.addAttribute("questions",questionLists);
        return "index";
    }

}
