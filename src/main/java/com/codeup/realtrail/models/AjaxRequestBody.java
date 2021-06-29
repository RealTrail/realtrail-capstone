package com.codeup.realtrail.models;

import jakarta.validation.constraints.NotBlank;

public class AjaxRequestBody {

    @NotBlank(message = "profile image url can't be empty!")
    String profileImageUrl;

    String eventImageUrl;

    public AjaxRequestBody() {
    }

    public AjaxRequestBody(String profileImageUrl, String eventImageUrl) {
        this.profileImageUrl = profileImageUrl;
        this.eventImageUrl = eventImageUrl;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getEventImageUrl() {
        return eventImageUrl;
    }

    public void setEventImageUrl(String eventImageUrl) {
        this.eventImageUrl = eventImageUrl;
    }
}
