package com.zhongyuanbbs.demo.controller;

import com.zhongyuanbbs.demo.Enums.CommentEnum;
import com.zhongyuanbbs.demo.Service.CommentService;
import com.zhongyuanbbs.demo.Service.QuestionService;
import com.zhongyuanbbs.demo.domain.Comment;
import com.zhongyuanbbs.demo.domain.GitHubUser;
import com.zhongyuanbbs.demo.dto.CommentCreateDto;
import com.zhongyuanbbs.demo.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> comment(@RequestBody CommentCreateDto commentCreateDto, HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        GitHubUser user = (GitHubUser) request.getSession().getAttribute("user");
        if(user == null){
            modelMap.put("success",false);
            modelMap.put("errMsg", CommentEnum.No_LOGIN_COMMENT.getMessage());
            return modelMap;
        }
        if(commentCreateDto == null || commentCreateDto.getComment() == null || commentCreateDto.getComment() ==""){
            modelMap.put("success",false);
            modelMap.put("errMsg",CommentEnum.COMMENT_FAILURE.getMessage()+"，违规评论");
            return modelMap;
        }
        Comment comment = new Comment();
        comment.setQuestionId(commentCreateDto.getQuestionId());
        comment.setZkComment(commentCreateDto.getComment());
        comment.setType(commentCreateDto.getType());
        comment.setCommentCreateTime(new Date());
        comment.setCommentModified(new Date());
        comment.setCreateor(user.getId());
        Integer code = commentService.insertComment(comment);
        for (CommentEnum commentEnum : CommentEnum.values()) {
            if(commentEnum.COMMENT_SUCCESS.getCode() == code){
                modelMap.put("success",true);
                modelMap.put("toast",commentEnum.COMMENT_SUCCESS.getMessage());
            }else {
                modelMap.put("success",false);
                modelMap.put("errCode",code);
                modelMap.put("errMsg",CommentEnum.getMessage(code));
            }
        }
        return modelMap;
    }

    @RequestMapping("/secondComment/{id}")
    @ResponseBody
    public Map<String,Object> secondComment(@PathVariable(name="id")Integer id){
        Map<String,Object> modelMap = new HashMap<>();
        List<CommentDto> commentDtos = commentService.listByQuestionId(id, CommentEnum.SECOND_COMMENT.getCode());
        if(commentDtos != null && commentDtos.size() > 0 ){
            modelMap.put("success",true);
            modelMap.put("secondComments",commentDtos);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg",CommentEnum.EMPTY_COMMENT.getMessage());
        }
        return modelMap;
    }


}
