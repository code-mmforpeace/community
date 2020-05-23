package com.zhongyuanbbs.demo.Mapper;

import com.zhongyuanbbs.demo.domain.Comment;
import com.zhongyuanbbs.demo.domain.GitHubUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    Integer insertComment(Comment comment);

    Comment queryCommentById(Integer id);

    List<Comment> queryCommentByQuestionId(@Param("questionId") Integer questionId,@Param("type") Integer type);

    Integer queryCommentCountByQuestionId(Integer questionId);
}
