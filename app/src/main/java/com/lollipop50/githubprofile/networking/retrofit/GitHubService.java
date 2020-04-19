package com.lollipop50.githubprofile.networking.retrofit;

import com.lollipop50.githubprofile.model.Profile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("/users/{user}")
    Call<Profile> getUser(@Path("user")String user);
}
