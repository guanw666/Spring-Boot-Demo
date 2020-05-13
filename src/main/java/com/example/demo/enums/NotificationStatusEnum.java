package com.example.demo.enums;

/**
 * 通知状态
 */
public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1);

    private Integer status;

    public static boolean isExist(Integer status) {
        for (NotificationStatusEnum value : NotificationStatusEnum.values()) {
            if (value.getStatus().equals(status)) {
                return true;
            }
        }
        return false;
    }

    public Integer getStatus() {
        return status;
    }

    NotificationStatusEnum(Integer type) {
        this.status = type;
    }
}
