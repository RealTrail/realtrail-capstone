package com.codeup.realtrail.models;

import java.util.List;

public class AjaxResponseBody {
    String msg;
    User user;
    Event event;

    public AjaxResponseBody() {
    }

    public AjaxResponseBody(String msg, User user, Event event) {
        this.msg = msg;
        this.user = user;
        this.event = event;
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

    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
}
