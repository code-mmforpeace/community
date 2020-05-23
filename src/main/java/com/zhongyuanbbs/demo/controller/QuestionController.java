package com.zhongyuanbbs.demo.controller;

import com.zhongyuanbbs.demo.Enums.CommentEnum;
import com.zhongyuanbbs.demo.Service.CommentService;
import com.zhongyuanbbs.demo.Service.QuestionService;
import com.zhongyuanbbs.demo.domain.Question;
import com.zhongyuanbbs.demo.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{questionId}")
    public String question(@PathVariable(name = "questionId")Integer questionId, Model model){
        Question questionById = questionService.getQuestionById(questionId);
        List<CommentDto> commentDTOList = commentService.listByQuestionId(questionId, CommentEnum.QUESTION_COMMENT.getCode());
        Integer count = commentService.queryCommentCountByQuestionId(questionId);
        //阅读数的增加
        questionService.insView(questionId);
        model.addAttribute("question",questionById);
        model.addAttribute("commentList",commentDTOList);
        model.addAttribute("count",count);
        return "question";
    }

}
