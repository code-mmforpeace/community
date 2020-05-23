package com.zhongyuanbbs.demo.dto;

import lombok.Data;

@Data
public class CommentCreateDto {
    private Integer questionId;
    private String comment;
    private Integer type;
}
