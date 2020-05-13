package com.zhongyuanbbs.demo.utils;

public class PageCalculator {

    public static Integer calculateRowIndex(Integer pageIndex,Integer pageSize){
        return (pageIndex>0)?(pageIndex-1)*pageSize : 0;
    }

}
