package com.zhongyuanbbs.demo.Service;

import com.zhongyuanbbs.demo.domain.Comment;
import com.zhongyuanbbs.demo.domain.Question;
import com.zhongyuanbbs.demo.dto.CommentDto;

import java.util.List;

public interface CommentService {
    Integer insertComment(Comment comment);

    List<CommentDto> listByQuestionId(Integer questionById,Integer type);

    Integer queryCommentCountByQuestionId(Integer questionId);
}
