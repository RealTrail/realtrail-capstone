package com.codeup.realtrail.models;

public class ProfileImageUpdateRequest {
    private String profileImageUrl;

    public ProfileImageUpdateRequest() {
    }

    public ProfileImageUpdateRequest(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
