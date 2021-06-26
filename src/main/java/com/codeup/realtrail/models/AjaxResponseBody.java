package com.codeup.realtrail.models;

import com.codeup.realtrail.models.User;

public class AjaxResponseBody {
    String msg;
    User result;

    public AjaxResponseBody() {
    }

    public AjaxResponseBody(String msg, User result) {
        this.msg = msg;
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }
}
