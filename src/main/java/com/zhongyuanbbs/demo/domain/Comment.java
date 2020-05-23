package com.zhongyuanbbs.demo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer id;
    private String zkComment;
    private int type;
    private Integer questionId;
    private Integer createor;
    private Date commentCreateTime;
    private Date commentModified;
    private Integer likeCount;
}
