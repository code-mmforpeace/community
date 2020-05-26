package com.zhongyuanbbs.demo.Enums;

public enum CommentEnum {
    COMMENT_SUCCESS(2000,"评论成功"),
    COMMENT_FAILURE(2001,"评论失败"),
    No_LOGIN_COMMENT(2002,"未登录不能评论，请先登录"),
    TARGET_NOT_FOUND(2003,"未选中任何问题或评论进行回复"),
    TYPE_WRONG(2004,"评论的类型错误或不存在"),
    COMMENT_NOT_FOUND(2005,"回复的评论不存在"),
    EMPTY_COMMENT(2006,"暂无评论"),
    QUESTION_COMMENT(1,"一级评论"),SECOND_COMMENT(2,"二级评论");


    private Integer code;
    private String message;

    CommentEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessage(int code){
        for (CommentEnum value : CommentEnum.values()) {
            if(value.getCode() == code){
                return value.getMessage();
            }
        }
        return "";
    }
}
