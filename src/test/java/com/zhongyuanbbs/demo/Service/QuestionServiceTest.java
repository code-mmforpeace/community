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
        PageDto<Question> pageDto = questionService.getQuestionLists(1,5);
        System.out.println(pageDto.getT().size());
        for (Question question:pageDto.getT()) {
            System.out.println(question);
        }
    }

}
