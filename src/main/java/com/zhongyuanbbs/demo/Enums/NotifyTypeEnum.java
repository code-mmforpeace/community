package com.zhongyuanbbs.demo.Enums;

public enum NotifyTypeEnum {
    QUESTION_NOTIFY(0,"回复了问题"),COMMENT_NOTIFY(1,"回复了评论");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    NotifyTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
