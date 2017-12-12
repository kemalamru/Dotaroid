package com.kar.dotaroid.ui.player.player_profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kar.dotaroid.R;

import dagger.android.AndroidInjection;

public class PlayerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
        AndroidInjection.inject(this);
    }
}
