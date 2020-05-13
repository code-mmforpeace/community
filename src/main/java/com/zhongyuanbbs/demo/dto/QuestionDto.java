package com.zhongyuanbbs.demo.dto;

import com.zhongyuanbbs.demo.domain.GitHubUser;
import com.zhongyuanbbs.demo.domain.Question;
import lombok.Data;

@Data
public class QuestionDto {

    private Question question;
    private GitHubUser gitHubUser;

}
