package com.zhongyuanbbs.demo.controller;

import com.zhongyuanbbs.demo.Mapper.QuestionMapper;
import com.zhongyuanbbs.demo.domain.GitHubUser;
import com.zhongyuanbbs.demo.domain.Question;
import com.zhongyuanbbs.demo.domain.User;
import com.zhongyuanbbs.demo.utils.HttpRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(HttpServletRequest request, Model model){
        GitHubUser user = (GitHubUser) request.getSession().getAttribute("user");
        if(user != null && user.getId() != null){
            String title = HttpRequestUtils.getString(request, "title");
            String questionDesc = HttpRequestUtils.getString(request, "question-desc");
            String tag = HttpRequestUtils.getString(request, "tag");
            if(title != null && !title.equals("")) {
                Question question = new Question();
                question.setTitle(title);
                question.setDescription(questionDesc);
                question.setTag(tag);
                model.addAttribute("title",title);
                model.addAttribute("questionDesc",questionDesc);
                model.addAttribute("tag",tag);
                question.setCreator(user.getId());
                question.setQsCreateTime(new Date());
                question.setQsLastEditTime(new Date());
                Integer integer = questionMapper.newQuestion(question);
                if (integer > -1) {
                    model.addAttribute("message","创建成功！");
                    return "index";
                } else {
                    model.addAttribute("message", "创建失败，违规输入");
                    return "publish";
                }
            }else {
                model.addAttribute("message","标题不能为空！");
                return "publish";
            }
        }else {
            model.addAttribute("message","用户未登录！");
            return "publish";
        }
    }
}
