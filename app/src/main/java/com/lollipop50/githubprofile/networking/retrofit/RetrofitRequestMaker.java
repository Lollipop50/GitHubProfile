package com.lollipop50.githubprofile.networking.retrofit;

import com.lollipop50.githubprofile.model.Profile;
import com.lollipop50.githubprofile.networking.RequestMaker;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequestMaker implements RequestMaker {

    @Override
    public Profile makeRequest(String username) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);
        Call<Profile> getUserCall = gitHubService.getUser(username);

        try {
            return getUserCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
