package com.zhongyuanbbs.demo.Mapper;

import com.zhongyuanbbs.demo.DemoApplicationTests;
import com.zhongyuanbbs.demo.domain.Comment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentMapperTest extends DemoApplicationTests {

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void testQueryCommentByQuestionId(){
        List<Comment> comments = commentMapper.queryCommentByQuestionId(2,1);
        System.out.println(comments.size());
    }

    @Test
    public void testQueryCount(){
        Integer integer = commentMapper.queryCommentCountByQuestionId(1);
        System.out.println(integer);
    }

}
