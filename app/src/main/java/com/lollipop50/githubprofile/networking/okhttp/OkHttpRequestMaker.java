package com.lollipop50.githubprofile.networking.okhttp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lollipop50.githubprofile.model.Profile;
import com.lollipop50.githubprofile.networking.RequestMaker;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpRequestMaker implements RequestMaker {

    @Override
    public Profile makeRequest(String username) {
        OkHttpClient httpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/users/" + username)
                .build();

        String responseBody = null;

        try {
            Response response = httpClient.newCall(request).execute();
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Type type = new TypeToken<Profile>() {}.getType();
        Profile result = new Gson().fromJson(responseBody, type);

        return result;
    }
}
