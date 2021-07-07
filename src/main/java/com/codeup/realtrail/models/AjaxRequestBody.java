package com.codeup.realtrail.models;


import javax.validation.constraints.NotBlank;

public class AjaxRequestBody {

    @NotBlank(message = "profile image url can't be empty!")
    String profileImageUrl;

    public AjaxRequestBody(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
