package com.lollipop50.githubprofile.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lollipop50.githubprofile.R;

public class OptionsFragment extends Fragment {

    private Button okHttpButton;
    private Button retrofitButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_options, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        okHttpButton = view.findViewById(R.id.okhttp_button);
        retrofitButton = view.findViewById(R.id.retrofit_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        okHttpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfile(true);
            }
        });

        retrofitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfile(false);
            }
        });
    }

    private void showProfile (boolean isOkHttp) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, ProfileFragment.makeInstance(isOkHttp))
                .addToBackStack(null)
                .commit();
    }
}
