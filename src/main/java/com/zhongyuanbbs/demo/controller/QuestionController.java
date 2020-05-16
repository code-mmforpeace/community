package com.zhongyuanbbs.demo.controller;

import com.zhongyuanbbs.demo.Service.QuestionService;
import com.zhongyuanbbs.demo.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{questionId}")
    public String question(@PathVariable(name = "questionId")Integer questionId, Model model){
        Question questionById = questionService.getQuestionById(questionId);
        model.addAttribute("question",questionById);
        return "question";
    }

}
