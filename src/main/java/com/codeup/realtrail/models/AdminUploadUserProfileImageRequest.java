package com.codeup.realtrail.models;

public class AdminUploadUserProfileImageRequest {
    private long id;
    private String profileImageUrl;

    public AdminUploadUserProfileImageRequest() {
    }

    public AdminUploadUserProfileImageRequest(long id, String profileImageUrl) {
        this.id = id;
        this.profileImageUrl = profileImageUrl;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
