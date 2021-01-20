package com.sql.er.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class UserTokenDto implements Serializable {
    private static final long serialVersionUID = 5001886627249712496L;

    private Long userId;// 用户id

    private String todo;// 备用

    private Long visitorId;// 访客id

    private Integer expMinute;// 过期时间(分钟)

    private Integer expTimestamp;// 过期时间戳(秒)

    private String token;

    private Boolean isRegister = false;// 是否是注册

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public Integer getExpMinute() {
        return expMinute;
    }

    public void setExpMinute(Integer expMinute) {
        this.expMinute = expMinute;
    }

    public Integer getExpTimestamp() {
        return expTimestamp;
    }

    public void setExpTimestamp(Integer expTimestamp) {
        this.expTimestamp = expTimestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getRegister() {
        return isRegister;
    }

    public void setRegister(Boolean register) {
        isRegister = register;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
