package com.zhongyuanbbs.demo.dto;

import com.zhongyuanbbs.demo.domain.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDto {

    private List<Question> questions;
    private Integer questionCount;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer currentPage;
    private List<Integer> pageLists = new ArrayList<>();
    private Integer totalPage;

    public void setPageNum(Integer questionCount, Integer pageIndex, Integer pageSize) {
        if(questionCount % pageSize == 0){
            totalPage = questionCount / pageSize;
        }else {
            totalPage = questionCount / pageSize + 1;
        }
        if(pageIndex < 1){
            pageIndex = 1;
        }
        if(pageIndex > totalPage){
            pageIndex = totalPage;
        }
        this.currentPage = pageIndex;
        pageLists.add(pageIndex);
        for (int i = 1;i<=3;i++){
            if(pageIndex - i >0){
                pageLists.add(0,pageIndex - i);
            }
            if(pageIndex + i <= totalPage){
                pageLists.add(pageIndex + i);
            }
        }
        if(pageIndex == 1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        if(pageIndex == totalPage){
            showNext = false;
        }else {
            showNext = true;
        }
        if(pageLists.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        if(pageLists.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
