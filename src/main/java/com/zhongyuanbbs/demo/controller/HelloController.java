package com.zhongyuanbbs.demo.controller;

import com.zhongyuanbbs.demo.Service.QuestionService;
import com.zhongyuanbbs.demo.domain.GitHubUser;
import com.zhongyuanbbs.demo.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name")String name, Model model){
        model.addAttribute("name",name);
        return "index";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request,Model model,@RequestParam(name="pageIndex",defaultValue = "1")int pageIndex,@RequestParam(name = "pageSize",defaultValue = "5") int pageSize){
//        int pageIndex = HttpRequestUtils.getInt(request, "pageIndex");
//        int pageSize = HttpRequestUtils.getInt(request, "pageSize");
        GitHubUser user = (GitHubUser)request.getSession().getAttribute("user");
//        if(user == null){
//            return "redirect:/";
//        }
        PageDto pageDto = questionService.getQuestionLists(pageIndex,pageSize);
        model.addAttribute("pageDto",pageDto);
        return "index";
    }

}
