package com.lollipop50.githubprofile.model;

import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("avatar_url")
    private String avatarUrl;
    private String login;
    private long id;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public long getId() {
        return id;
    }
}
