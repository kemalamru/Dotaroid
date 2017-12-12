package com.kar.dotaroid.ui.player.player_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kar.dotaroid.R;

import dagger.android.AndroidInjection;

public class PlayerListActivity extends AppCompatActivity {

    private ActivityPlayerListBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);
        AndroidInjection.inject(this);
    }

    private setUp() {

    }
}
