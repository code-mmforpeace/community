package com.zhongyuanbbs.demo.Service.impl;

import com.zhongyuanbbs.demo.Mapper.QuestionMapper;
import com.zhongyuanbbs.demo.Service.GithubUserService;
import com.zhongyuanbbs.demo.Service.QuestionService;
import com.zhongyuanbbs.demo.domain.GitHubUser;
import com.zhongyuanbbs.demo.domain.Question;
import com.zhongyuanbbs.demo.dto.PageDto;
import com.zhongyuanbbs.demo.dto.QuestionDto;
import com.zhongyuanbbs.demo.exception.CustiomizeException;
import com.zhongyuanbbs.demo.utils.PageCalculator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private GithubUserService githubUserService;

    @Override
    public PageDto<Question> getQuestionLists(Integer pageIndex, Integer pageSize) {
        PageDto<Question> pageDto = new PageDto();
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
        pageDto.setCount(questionCount);
        pageDto.setT(questions);
        return pageDto;
    }

    @Override
    public PageDto<Question> getQuestionListsById(Integer userId, Integer pageIndex, Integer pageSize) {
        PageDto<Question> pageDto = new PageDto();
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
        pageDto.setCount(questionCount);
        pageDto.setT(questions);
        return pageDto;
    }

    @Override
    public QuestionDto getQuestionById(Integer questionId) {
        QuestionDto result = new QuestionDto();
        Question questionById = questionMapper.getQuestionById(questionId);
        if(questionById == null){
            throw new CustiomizeException("你找的问题消失了，要不换个试试？");
        }
        BeanUtils.copyProperties(questionById,result);
        GitHubUser githunUserById = githubUserService.getGithunUserByTId(questionById.getCreator());
        result.setGitHubUser(githunUserById);
        return result;
    }

    @Override
    public Integer createOrUpdateQuestion(Question question) {
        if(question != null && question.getId() != null){
            question.setQsLastEditTime(new Date());
            Integer integer = questionMapper.updateQuestion(question);
            if(integer == 1) {
                return integer;
            }else {
                throw new CustiomizeException("你找的问题消失了，要不换个试试？");
            }
        }else {
            question.setQsCreateTime(new Date());
            question.setQsLastEditTime(new Date());
            return questionMapper.newQuestion(question);
        }
    }

    @Override
    public void insView(Integer questionId) {
        Question question = new Question();
        question.setId(questionId);
        question.setViewCount(1);
        questionMapper.insView(question);
    }

    @Override
    public List<QuestionDto> selectRelated(QuestionDto questionById) {
        if(StringUtils.isBlank(questionById.getTag())){
            return new ArrayList<>();
        }
        Question question = new Question();
        String replace = StringUtils.replace(questionById.getTag(), "，", "|");
        question.setTag(replace);
        question.setId(questionById.getId());
        List<Question> questions = questionMapper.selectRelated(question);
        List<QuestionDto> questionDtos = questions.stream().map(q -> {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(q,questionDto);
            return questionDto;
        }).collect(Collectors.toList());
        return questionDtos;
    }
}
