package com.zhongyuanbbs.demo.Enums;

public enum NotifyStatusEnum {

    NOT_READ(0,"未读"),READ(1,"已读");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    NotifyStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
