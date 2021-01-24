package com.sql.er.dto;

import java.io.Serializable;

public class UserFileDto  implements Serializable {
    private static final long serialVersionUID = 5556742804573359607L;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
