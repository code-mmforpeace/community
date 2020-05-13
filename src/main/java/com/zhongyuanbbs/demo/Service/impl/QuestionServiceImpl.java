package com.zhongyuanbbs.demo.Service.impl;

import com.zhongyuanbbs.demo.Mapper.QuestionMapper;
import com.zhongyuanbbs.demo.Service.GithubUserService;
import com.zhongyuanbbs.demo.Service.QuestionService;
import com.zhongyuanbbs.demo.domain.GitHubUser;
import com.zhongyuanbbs.demo.domain.Question;
import com.zhongyuanbbs.demo.dto.QuestionDto;
import com.zhongyuanbbs.demo.utils.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private GithubUserService githubUserService;

    @Override
    public List<Question> getQuestionLists(Integer pageIndex,Integer pageSize) {
        Integer rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Question> questions = new ArrayList<>();
        List<Question> questionLists = questionMapper.getQuestionLists(rowIndex,pageSize);
        for (Question question1 :questionLists) {
            GitHubUser githunUserById = githubUserService.getGithunUserById(question1.getCreator());
            question1.setGitHubUser(githunUserById);
            questions.add(question1);
        }
        return questions;
    }
}
