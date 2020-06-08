package com.zhongyuanbbs.demo.dto;

import com.zhongyuanbbs.demo.domain.GitHubUser;
import lombok.Data;

import java.util.Date;

@Data
public class NotifyDto {

    private Integer id;
    private Date createTime;
    private Integer status;
    private GitHubUser gitHubUser;
    private String title;
    private String type;

}
