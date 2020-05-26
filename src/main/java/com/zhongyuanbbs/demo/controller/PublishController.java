package com.zhongyuanbbs.demo.controller;

import com.zhongyuanbbs.demo.Service.QuestionService;
import com.zhongyuanbbs.demo.domain.GitHubUser;
import com.zhongyuanbbs.demo.domain.Question;
import com.zhongyuanbbs.demo.dto.QuestionDto;
import com.zhongyuanbbs.demo.utils.HttpRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @GetMapping("/publish/{questionId}")
    public String publish(@PathVariable("questionId")Integer questionId,Model model){
        QuestionDto questionById = questionService.getQuestionById(questionId);
        model.addAttribute("questionId",questionId);
        model.addAttribute("title",questionById.getTitle());
        model.addAttribute("questionDesc",questionById.getDescription());
        model.addAttribute("tag",questionById.getTag());
        return "/publish";
    }

    @PostMapping("/publish")
    public String doPublish(HttpServletRequest request, Model model){
        GitHubUser user = (GitHubUser) request.getSession().getAttribute("user");
        if(user != null && user.getId() != null){
            String title = HttpRequestUtils.getString(request, "title");
            String questionDesc = HttpRequestUtils.getString(request, "question-desc");
            String tag = HttpRequestUtils.getString(request, "tag");
            int questionId = HttpRequestUtils.getInt(request, "questionId");
            if(title != null && !title.equals("")) {
                Question question = new Question();
                question.setTitle(title);
                question.setDescription(questionDesc);
                question.setTag(tag);
                model.addAttribute("title",title);
                model.addAttribute("questionDesc",questionDesc);
                model.addAttribute("tag",tag);
                question.setCreator(user.getId());
                if(questionId > 0) {
                    QuestionDto questionById = questionService.getQuestionById(questionId);
                    Integer creator = questionById.getCreator();
                    if (creator.equals(user.getId())) {
                        question.setId(questionId);
                        Integer integer = questionService.createOrUpdateQuestion(question);
                        if (integer > -1) {
                            model.addAttribute("message", "修改成功！");
                            return "publish";
                        }
                    }else {
                        model.addAttribute("message", "修改失败，违规输入");
                        return "publish";
                    }
                }else {
                    Integer integer = questionService.createOrUpdateQuestion(question);
                    if (integer > -1) {
                        model.addAttribute("message","创建成功！");
                        return "publish";
                    } else {
                        model.addAttribute("message", "创建失败，违规输入");
                        return "publish";
                    }
                }
            }else {
                model.addAttribute("message","标题不能为空！");
                return "publish";
            }
        }else {
            model.addAttribute("message","用户未登录！");
            return "publish";
        }
        return "publish";
    }
}
