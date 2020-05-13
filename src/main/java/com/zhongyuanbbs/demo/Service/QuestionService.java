package com.zhongyuanbbs.demo.Service;

import com.zhongyuanbbs.demo.domain.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestionLists(Integer pageIndex,Integer pageSize);

}
