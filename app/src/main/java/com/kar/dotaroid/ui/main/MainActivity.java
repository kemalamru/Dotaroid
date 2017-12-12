package com.kar.dotaroid.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.kar.dotaroid.R;
import com.kar.dotaroid.databinding.ActivityMainBinding;
import com.kar.dotaroid.ui.player.player_list.PlayerListActivity;

import dagger.android.AndroidInjection;
import dagger.android.HasActivityInjector;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    private void setUp() {
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.include.searchBarPlayer.setOnClickListener(view -> {
            Intent intent = new Intent(this, PlayerListActivity.class);
            startActivity(intent);
        });
    }
}
