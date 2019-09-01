package com.example.demo.enums;

/**
 * 评论类型
 */
public enum CommentTypeEnum {
    /**
     * 对问题进行评论
     */
    QUESTION(1),
    /**
     * 对评论进行评论
     */
    COMMENT(2);

    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
