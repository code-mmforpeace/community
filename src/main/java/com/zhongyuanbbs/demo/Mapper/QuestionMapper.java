package com.zhongyuanbbs.demo.Mapper;

import com.zhongyuanbbs.demo.domain.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {

    Integer queryQuestionCount();

    List<Question> getQuestionLists(@Param("rowIndex") Integer rowIndex, @Param("pageSize") Integer pageSize);

    Integer newQuestion(Question question);

}
