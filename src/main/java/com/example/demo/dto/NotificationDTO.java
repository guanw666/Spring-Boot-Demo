package com.example.demo.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;

    private Long notifer;

    private String notiferName;

    private Long outerId;

    private String outerTitle;

    private Integer type;

    private String typeName;

    private Long gmtCreate;

    private Integer status;
}