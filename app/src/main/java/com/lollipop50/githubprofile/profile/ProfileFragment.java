package com.lollipop50.githubprofile.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lollipop50.githubprofile.R;

public class ProfileFragment extends Fragment {

    private static final String KEY_IS_OKHTTP = "is_ok_http";

    private ImageView avatar_view;
    private TextView usernameView;
    private TextView idView;
    private Button repositoriesButton;

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

        avatar_view = view.findViewById(R.id.avatar_view);
        usernameView = view.findViewById(R.id.username_view);
        idView = view.findViewById(R.id.id_view);
        repositoriesButton = view.findViewById(R.id.repositories_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        repositoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open user's repositories in browser
            }
        });
    }

    public static ProfileFragment makeInstance (boolean isOkHttp) {
        Bundle args = new Bundle();
        args.putBoolean(KEY_IS_OKHTTP, isOkHttp);

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
