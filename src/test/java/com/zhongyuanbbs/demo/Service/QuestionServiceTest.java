package com.zhongyuanbbs.demo.Service;

import com.zhongyuanbbs.demo.DemoApplicationTests;
import com.zhongyuanbbs.demo.domain.Question;
import com.zhongyuanbbs.demo.dto.PageDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuestionServiceTest extends DemoApplicationTests {

    @Autowired
    private QuestionService questionService;

    @Test
    public void getQuestionList(){
        PageDto pageDto = questionService.getQuestionLists(1,5);
        System.out.println(pageDto.getQuestions().size());
        for (Question question:pageDto.getQuestions()) {
            System.out.println(question);
        }
    }

}
