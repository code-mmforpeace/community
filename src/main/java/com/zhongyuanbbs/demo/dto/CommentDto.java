package com.zhongyuanbbs.demo.dto;

import com.zhongyuanbbs.demo.domain.GitHubUser;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private Integer id;
    private String zkComment;
    private Integer type;
    private Integer questionId;
    private Integer createor;
    private Date commentCreateTime;
    private Date commentModified;
    private Integer likeCount;
    private GitHubUser user;
}
