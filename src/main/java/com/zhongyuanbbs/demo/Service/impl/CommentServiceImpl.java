package com.zhongyuanbbs.demo.Service.impl;

import com.zhongyuanbbs.demo.Enums.CommentEnum;
import com.zhongyuanbbs.demo.Enums.CommentTypeEnum;
import com.zhongyuanbbs.demo.Mapper.CommentMapper;
import com.zhongyuanbbs.demo.Mapper.GithubUserMapper;
import com.zhongyuanbbs.demo.Mapper.QuestionMapper;
import com.zhongyuanbbs.demo.Service.CommentService;
import com.zhongyuanbbs.demo.Service.GithubUserService;
import com.zhongyuanbbs.demo.domain.Comment;
import com.zhongyuanbbs.demo.domain.GitHubUser;
import com.zhongyuanbbs.demo.domain.Question;
import com.zhongyuanbbs.demo.dto.CommentDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private GithubUserMapper githubUserMapper;

    @Autowired
    private GithubUserService githubUserService;

    @Override
    @Transactional
    public Integer insertComment(Comment comment) {
        if(comment.getQuestionId() == null || comment.getQuestionId() == 0){
            return CommentEnum.TARGET_NOT_FOUND.getCode();
        }
        if(comment.getType() == 0 || !CommentTypeEnum.isExist(comment.getType())){
            return CommentEnum.TYPE_WRONG.getCode();
        }
        if(comment.getType() == CommentTypeEnum.QUESTION.getType()){
            //回复问题
            Question questionById = questionMapper.getQuestionById(comment.getQuestionId());
            if(questionById != null) {
                commentMapper.insertComment(comment);
                questionMapper.insCommentCount(questionById);
                return CommentEnum.COMMENT_SUCCESS.getCode();
            }else {
                return CommentEnum.TARGET_NOT_FOUND.getCode();
            }
        }else {
            //评论回复
            Comment comment1 = commentMapper.queryCommentById(comment.getQuestionId());
            if(comment1 == null){
                return CommentEnum.COMMENT_NOT_FOUND.getCode();
            }else {
                commentMapper.insertComment(comment);
                comment1.setCommentCount(1);
                commentMapper.insCommentCount(comment1);
                return CommentEnum.COMMENT_SUCCESS.getCode();
            }
        }
    }

    @Override
    public List<CommentDto> listByQuestionId(Integer questionById,Integer type) {
        List<CommentDto> commentDtos = new ArrayList<>();
        List<Comment> comments = commentMapper.queryCommentByQuestionId(questionById,type);
//        for (Comment comment : comments) {
//            CommentDto commentDto = new CommentDto();
//            GitHubUser githunUserByTId = githubUserService.getGithunUserByTId(comment.getCreateor());
//            BeanUtils.copyProperties(comment,commentDto);
//            commentDto.setUser(githunUserByTId);
//            commentDtos.add(commentDto);
//        }
        if(comments.size() == 0){
            return new ArrayList<>();
        }
        //获取去重的评论人id
        Set<Integer> creators = comments.stream().map(comment -> comment.getCreateor()).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(creators);

        //获取评论人信息
        List<GitHubUser> gitHubUsers = githubUserMapper.queryUserByUserIds(userIds);
        Map<Integer, GitHubUser> collect = gitHubUsers.stream().collect(Collectors.toMap(gitHubUser -> gitHubUser.getId(), gitHubUser -> gitHubUser));

        //装载
        List<CommentDto> commentDtos1 = comments.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
            commentDto.setUser(collect.get(comment.getCreateor()));
            return commentDto;
        }).collect(Collectors.toList());
        return commentDtos1;
    }

    @Override
    public Integer queryCommentCountByQuestionId(Integer questionId) {
        return commentMapper.queryCommentCountByQuestionId(questionId);
    }
}
