package com.lollipop50.githubprofile.profile;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lollipop50.githubprofile.R;

public class OptionsFragment extends Fragment {

    private EditText usernameEditText;
    private Button okHttpButton;
    private Button retrofitButton;

    private String username = "";

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

        usernameEditText = view.findViewById(R.id.username_edittext);
        okHttpButton = view.findViewById(R.id.okhttp_button);
        retrofitButton = view.findViewById(R.id.retrofit_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                username = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        okHttpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfile(username, true);
            }
        });

        retrofitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfile(username, false);
            }
        });
    }

    private void showProfile (String username, boolean isOkHttp) {
        if (username.length() != 0) {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, ProfileFragment.makeInstance(username, isOkHttp))
                    .addToBackStack(null)
                    .commit();
        } else {
            Toast.makeText(getContext(), "You haven't entered your username!", Toast.LENGTH_SHORT).show();
        }
    }
}
