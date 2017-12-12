package com.kar.dotaroid.ui.hero.hero_profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kar.dotaroid.R;

import dagger.android.AndroidInjection;

public class HeroProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_profile);
        AndroidInjection.inject(this);
    }
}
