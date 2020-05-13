package com.example.demo.enums;

/**
 * 通知类型
 */
public enum NotificationTypeEnum {
    /**
     * 回复问题
     */
    REPLY_QUESTION(1, "回复了问题"),
    /**
     * 回复了评论
     */
    REPLY_COMMENT(2, "回复了评论");

    private Integer type;
    private String name;

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String nameOfType(int type) {
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()) {
            if (notificationTypeEnum.getType().equals(type)) {
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }
}
