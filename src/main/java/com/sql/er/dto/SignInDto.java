package com.sql.er.dto;

import java.io.Serializable;

public class SignInDto implements Serializable {
    private static final long serialVersionUID = 8317318827680146823L;
    private String userPhone;
    private String imgCode;
    private String userPassword;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
