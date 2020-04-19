package com.lollipop50.githubprofile.networking;

import com.lollipop50.githubprofile.model.Profile;

public interface RequestMaker {

    Profile makeRequest(String username);
}
