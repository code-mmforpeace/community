package com.zhongyuanbbs.demo.Service;

import com.zhongyuanbbs.demo.DemoApplicationTests;
import com.zhongyuanbbs.demo.domain.Question;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuestionServiceTest extends DemoApplicationTests {

    @Autowired
    private QuestionService questionService;

    @Test
    public void getQuestionList(){
        List<Question> questionLists = questionService.getQuestionLists(1,5);
        System.out.println(questionLists.size());
        for (Question question:questionLists) {
            System.out.println(question);
        }
    }

}
