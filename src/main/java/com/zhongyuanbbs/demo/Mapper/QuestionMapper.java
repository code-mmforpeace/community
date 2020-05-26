package com.zhongyuanbbs.demo.Mapper;

import com.zhongyuanbbs.demo.domain.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {

    Integer queryQuestionCount();

    Integer queryQuestionCountById(@Param("userId") Integer userId);

    List<Question> getQuestionLists(@Param("rowIndex") Integer rowIndex, @Param("pageSize") Integer pageSize);

    Integer newQuestion(Question question);

    List<Question> getQuestionListsById(@Param("userId")Integer userId,@Param("rowIndex") Integer rowIndex, @Param("pageSize") Integer pageSize);

    Question getQuestionById(@Param("questionId")Integer questionId);

    Integer updateQuestion(Question question);

    void insView(Question question);

    void insCommentCount(Question question);

    List<Question> selectRelated(Question question);
}
