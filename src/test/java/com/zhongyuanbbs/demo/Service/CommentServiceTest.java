package com.zhongyuanbbs.demo.Service;

import com.zhongyuanbbs.demo.DemoApplicationTests;
import com.zhongyuanbbs.demo.dto.CommentDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentServiceTest extends DemoApplicationTests {

    @Autowired
    private CommentService commentService;

    @Test
    public void testQueryCommentByQuestionId(){
        List<CommentDto> commentDtos = commentService.listByQuestionId(1,1);
        System.out.println(commentDtos.size());
        System.out.println("3.5 * 4 /2 - 2.5 is");
    }

}
