package com.zhongyuanbbs.demo.Service;

import com.zhongyuanbbs.demo.domain.Question;
import com.zhongyuanbbs.demo.dto.PageDto;

public interface QuestionService {

    PageDto getQuestionLists(Integer pageIndex, Integer pageSize);

    PageDto getQuestionListsById(Integer userId,Integer pageIndex,Integer pageSize);

    Question getQuestionById(Integer questionId);

}
