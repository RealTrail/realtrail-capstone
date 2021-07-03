package com.codeup.realtrail.models;

import com.codeup.realtrail.models.User;

public class AjaxResponseBody {
    String msg;
    User user;
    Event event;
    String mapPointsJson;

    public AjaxResponseBody() {
    }

    public AjaxResponseBody(String msg, String mapPointsJson) {
        this.msg = msg;
        this.mapPointsJson = mapPointsJson;
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

    public String getMapPointsJson() {
        return mapPointsJson;
    }
    public void setMapPointsJson(String mapPointsJson) {
        this.mapPointsJson = mapPointsJson;
    }
}
