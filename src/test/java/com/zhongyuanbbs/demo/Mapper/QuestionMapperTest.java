package com.zhongyuanbbs.demo.Mapper;

import com.zhongyuanbbs.demo.DemoApplicationTests;
import com.zhongyuanbbs.demo.domain.Question;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuestionMapperTest extends DemoApplicationTests {

    @Autowired
    private QuestionMapper questionMapper;

    @Test
    @Ignore
    public void testCount(){
        Integer integer = questionMapper.queryQuestionCount();
        System.out.println(integer);
    }

    @Test
    public void testQuestionList(){
        List<Question> questionLists = questionMapper.getQuestionLists(1,5);
        //System.out.println(Math.ceil(12/5));
        System.out.println(questionLists.size());
    }
}
