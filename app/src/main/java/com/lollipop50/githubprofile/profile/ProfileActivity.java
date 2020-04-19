package com.lollipop50.githubprofile.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.lollipop50.githubprofile.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentById(R.id.fragmentContainer) == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new OptionsFragment())
                    .commit();
        }
    }
}
