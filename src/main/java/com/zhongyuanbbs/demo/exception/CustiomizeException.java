package com.zhongyuanbbs.demo.exception;

public class CustiomizeException extends RuntimeException {

    public String message;

    public CustiomizeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
