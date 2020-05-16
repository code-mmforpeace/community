package com.zhongyuanbbs.demo.Service.impl;

import com.zhongyuanbbs.demo.Mapper.QuestionMapper;
import com.zhongyuanbbs.demo.Service.GithubUserService;
import com.zhongyuanbbs.demo.Service.QuestionService;
import com.zhongyuanbbs.demo.domain.GitHubUser;
import com.zhongyuanbbs.demo.domain.Question;
import com.zhongyuanbbs.demo.dto.PageDto;
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
    public PageDto getQuestionLists(Integer pageIndex, Integer pageSize) {
        PageDto pageDto = new PageDto();
        Integer questionCount = questionMapper.queryQuestionCount();
        pageDto.setPageNum(questionCount,pageIndex,pageSize);
        if(pageIndex<1){
           pageIndex = 1;
        }
        if(pageIndex > pageDto.getTotalPage()){
            pageIndex = pageDto.getTotalPage();
        }
        Integer rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Question> questions = new ArrayList<>();
        List<Question> questionLists = questionMapper.getQuestionLists(rowIndex,pageSize);
        for (Question question1 :questionLists) {
            GitHubUser githunUserById = githubUserService.getGithunUserByTId(question1.getCreator());
            question1.setGitHubUser(githunUserById);
            questions.add(question1);
        }
        pageDto.setQuestionCount(questionCount);
        pageDto.setQuestions(questions);
        return pageDto;
    }

    @Override
    public PageDto getQuestionListsById(Integer userId, Integer pageIndex, Integer pageSize) {
        PageDto pageDto = new PageDto();
        Integer questionCount = questionMapper.queryQuestionCountById(userId);
        pageDto.setPageNum(questionCount,pageIndex,pageSize);
        if(pageIndex<1){
            pageIndex = 1;
        }
        if(pageIndex > pageDto.getTotalPage()){
            pageIndex = pageDto.getTotalPage();
        }
        Integer rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Question> questions = new ArrayList<>();
        List<Question> questionLists = questionMapper.getQuestionListsById(userId,rowIndex,pageSize);
        for (Question question1 :questionLists) {
            GitHubUser githunUserById = githubUserService.getGithunUserByTId(question1.getCreator());
            question1.setGitHubUser(githunUserById);
            questions.add(question1);
        }
        pageDto.setQuestionCount(questionCount);
        pageDto.setQuestions(questions);
        return pageDto;
    }

    @Override
    public Question getQuestionById(Integer questionId) {
        Question questionById = questionMapper.getQuestionById(questionId);
        GitHubUser githunUserById = githubUserService.getGithunUserById(questionById.getCreator());
        questionById.setGitHubUser(githunUserById);
        return questionById;
    }
}
