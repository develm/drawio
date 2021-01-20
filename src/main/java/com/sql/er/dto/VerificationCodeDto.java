package com.sql.er.dto;

import java.io.Serializable;

public class VerificationCodeDto implements Serializable {
    private static final long serialVersionUID = -5498687615236321404L;

    private String phone;

    private String ip;

    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
