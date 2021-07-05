package com.codeup.realtrail.models;

import java.util.List;

public class AjaxResponseBody {
    String msg;
    User user;

    public AjaxResponseBody() {
    }

    public AjaxResponseBody(String msg, User user, Event event) {
        this.msg = msg;
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
