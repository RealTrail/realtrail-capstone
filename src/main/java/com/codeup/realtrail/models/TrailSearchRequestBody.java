package com.codeup.realtrail.models;

public class TrailSearchRequestBody {
    private String keyWord;

    public TrailSearchRequestBody() {
    }

    public TrailSearchRequestBody(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWord() {
        return keyWord;
    }
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
