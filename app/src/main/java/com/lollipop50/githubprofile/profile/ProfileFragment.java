package com.lollipop50.githubprofile.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.fragment.app.Fragment;

import com.lollipop50.githubprofile.R;
import com.lollipop50.githubprofile.model.Profile;
import com.lollipop50.githubprofile.networking.RequestMaker;
import com.lollipop50.githubprofile.networking.okhttp.OkHttpRequestMaker;
import com.lollipop50.githubprofile.networking.retrofit.RetrofitRequestMaker;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private static final String KEY_USERNAME = "username";
    private static final String KEY_IS_OKHTTP = "is_okhttp";

    private ImageView avatarView;
    private TextView usernameView;
    private TextView idView;
    private Button repositoriesButton;

    private String reposUrl = "https://github.com/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        avatarView = view.findViewById(R.id.avatar_view);
        usernameView = view.findViewById(R.id.username_view);
        idView = view.findViewById(R.id.id_view);
        repositoriesButton = view.findViewById(R.id.repositories_button);
        repositoriesButton.setEnabled(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new InternetRequestTask().execute();

        repositoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(reposUrl));
                startActivity(browserIntent);
            }
        });
    }

    @MainThread
    private void applyProfile(Profile profile) {
        try {
            String login = profile.getLogin();

            if (login == null) {
                setUserIsNotFound();
            } else {
                Picasso.get()
                        .load(profile.getAvatarUrl())
                        .into(avatarView);

                usernameView.setText(login);

                reposUrl += login + "?tab=repositories";

                String id = "ID: " + profile.getId();
                idView.setText(id);

                repositoriesButton.setEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            setUserIsNotFound();
        }
    }

    @WorkerThread
    private Profile executeRequest() {
        String username = getArguments().getString(KEY_USERNAME);
        boolean isOkHttp = getArguments().getBoolean(KEY_IS_OKHTTP);

        RequestMaker requestMaker = isOkHttp ? new OkHttpRequestMaker() : new RetrofitRequestMaker();

        return requestMaker.makeRequest(username);
    }

    public static ProfileFragment makeInstance(String username, boolean isOkHttp) {
        Bundle args = new Bundle();
        args.putString(KEY_USERNAME, username);
        args.putBoolean(KEY_IS_OKHTTP, isOkHttp);

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private void setUserIsNotFound() {
        usernameView.setText(R.string.not_found_text);
        idView.setText(R.string.no_id_text);
    }

    private class InternetRequestTask extends AsyncTask<Void, Void, Profile> {

        @Override
        protected Profile doInBackground(Void... voids) {
            return executeRequest();
        }

        @Override
        protected void onPostExecute(Profile profile) {
            applyProfile(profile);
        }
    }
}
