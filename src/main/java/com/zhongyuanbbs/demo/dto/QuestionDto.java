package com.zhongyuanbbs.demo.dto;

import com.zhongyuanbbs.demo.domain.GitHubUser;
import com.zhongyuanbbs.demo.domain.Question;
import lombok.Data;

import java.util.Date;

@Data
public class QuestionDto {

    private Integer id;
    private String title;
    private String description;
    private Date qsCreateTime;
    private Date qsLastEditTime;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private GitHubUser gitHubUser;

}
