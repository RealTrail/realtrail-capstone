package com.codeup.realtrail.models;

import com.codeup.realtrail.models.User;

public class AjaxResponseBody {
    String msg;
    User result;
    Event results;

    public AjaxResponseBody() {
    }

    public AjaxResponseBody(String msg, User result, Event results) {
        this.msg = msg;
        this.result = result;
        this.results = results;
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

    public Event getResults() {
        return results;
    }

    public void setResults(Event results) {
        this.results = results;
    }
}
