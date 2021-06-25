package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.User;

public class AjaxResponseBody {
    String msg;
    User result;

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
