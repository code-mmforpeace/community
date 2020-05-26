package com.zhongyuanbbs.demo.Service;

import com.zhongyuanbbs.demo.domain.Question;
import com.zhongyuanbbs.demo.dto.PageDto;
import com.zhongyuanbbs.demo.dto.QuestionDto;

import java.util.List;

public interface QuestionService {

    PageDto getQuestionLists(Integer pageIndex, Integer pageSize);

    PageDto getQuestionListsById(Integer userId,Integer pageIndex,Integer pageSize);

    QuestionDto getQuestionById(Integer questionId);

    Integer createOrUpdateQuestion(Question question);

    void insView(Integer questionId);

    List<QuestionDto> selectRelated(QuestionDto questionById);
}
