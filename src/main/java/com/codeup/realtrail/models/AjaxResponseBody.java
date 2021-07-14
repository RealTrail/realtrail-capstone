package com.codeup.realtrail.models;

import java.util.List;

public class AjaxResponseBody {
    String msg;
    User user;
    Trail trail;

    public AjaxResponseBody() {
    }

    public AjaxResponseBody(String msg, User user, Event event, Trail trail) {
        this.msg = msg;
        this.user = user;
        this.trail = trail;
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

    public Trail getTrail() {
        return trail;
    }
    public void setTrail(Trail trail) {

    }
}
