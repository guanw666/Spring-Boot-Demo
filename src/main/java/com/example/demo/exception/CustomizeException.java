package com.example.demo.exception;

public class CustomizeException extends RuntimeException {

    private Integer code;

    private String message;

    public CustomizeException(ICustomizeErrorCode customizeErrorCode) {
        this.code = customizeErrorCode.getCode();
        this.message = customizeErrorCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
