package com.example.demo.model;

public class Notification {
    private Long id;

    private Long notifer;

    private String notiferName;

    private Long receiver;

    private Long outerId;

    private String outerTitle;

    private Integer type;

    private Long gmtCreate;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNotifer() {
        return notifer;
    }

    public void setNotifer(Long notifer) {
        this.notifer = notifer;
    }

    public String getNotiferName() {
        return notiferName;
    }

    public void setNotiferName(String notiferName) {
        this.notiferName = notiferName == null ? null : notiferName.trim();
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    public Long getOuterId() {
        return outerId;
    }

    public void setOuterId(Long outerId) {
        this.outerId = outerId;
    }

    public String getOuterTitle() {
        return outerTitle;
    }

    public void setOuterTitle(String outerTitle) {
        this.outerTitle = outerTitle == null ? null : outerTitle.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}