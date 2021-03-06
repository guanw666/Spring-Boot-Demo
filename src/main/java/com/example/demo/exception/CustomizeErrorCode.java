package com.example.demo.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001, "问题未找到！"),
    QUESTION_UPDATE_FAIL(2002, "问题更新失败！"),
    TARGET_PARAM_NOT_FOUND(2003, "问题或评论不存在"),
    NO_LOGIN(2004, "用户未登录，请登录后重试！"),
    SYS_ERROR(2005, "服务器错误"),
    TYPE_PATAM_ERROR(2006, "类型参数错误"),
    COMMENT_NOT_FOUND(2007, "评论不存在"),
    CONTENT_IS_BLANK(2008, "内容不能为空"),
    READ_NOTIFICATION_FAIL(2009, "读通知失败"),
    NOTIFICATION_NOT_FOUND(2010, "通知不存在"),
    FILE_UPLOAD_FAIL(2011, "图片上传失败"),

    ;
    private Integer code;

    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
