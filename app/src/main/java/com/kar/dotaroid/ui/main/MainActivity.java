package com.kar.dotaroid.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kar.dotaroid.R;
import com.kar.dotaroid.ui.player.player_list.PlayerListActivity;
import com.mancj.materialsearchbar.MaterialSearchBar;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    private MaterialSearchBar mSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutSetup();
    }

    private void layoutSetup() {
        mSearchBar = findViewById(R.id.search_bar_player);
        mSearchBar.setOnClickListener(view -> {
            Intent intent = new Intent(this, PlayerListActivity.class);
            startActivity(intent);
        });
    }
}
