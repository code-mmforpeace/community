package com.zhongyuanbbs.demo.controller;

import com.zhongyuanbbs.demo.Service.GithubUserService;
import com.zhongyuanbbs.demo.Service.NotifyService;
import com.zhongyuanbbs.demo.Service.QuestionService;
import com.zhongyuanbbs.demo.domain.GitHubUser;
import com.zhongyuanbbs.demo.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private GithubUserService githubUserService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotifyService notifyService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          Model model, HttpServletRequest request,
                          @RequestParam(name="pageIndex",defaultValue = "1")int pageIndex,
                          @RequestParam(name = "pageSize",defaultValue = "5") int pageSize
    ){

        GitHubUser user = (GitHubUser)request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        if(action.equals("questions")){
            model.addAttribute("section",action);
            model.addAttribute("sectionName","我的帖子 - 仲园bbs");
            PageDto pageDto = questionService.getQuestionListsById(user.getId(), pageIndex, pageSize);
            model.addAttribute("pageDto",pageDto);
        }else if(action.equals("replies")) {
            model.addAttribute("section",action);
            model.addAttribute("sectionName","最新回复 - 仲园bbs");
        }else if(action.equals("like")){
            model.addAttribute("section",action);
            model.addAttribute("sectionName","我关注的 - 仲园bbs");
        }
        return "profile";
    }

}
