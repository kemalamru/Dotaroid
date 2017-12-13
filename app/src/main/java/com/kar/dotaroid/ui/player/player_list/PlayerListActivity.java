package com.kar.dotaroid.ui.player.player_list;

import android.databinding.DataBindingUtil;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kar.dotaroid.R;
import com.kar.dotaroid.data.model.Player;
import com.kar.dotaroid.databinding.ActivityPlayerListBinding;
import com.mancj.materialsearchbar.MaterialSearchBar;

import dagger.android.AndroidInjection;

public class PlayerListActivity extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener {

    private final String TAG = "PlayerListActivity";

    private ActivityPlayerListBinding mBinding;
    private MaterialSearchBar mSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);
        AndroidInjection.inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_player_list);

        performMaterialSearchBarSetup();
    }

    private void performRecyclerViewSetups() {

    }

    private void performMaterialSearchBarSetup() {
        mSearchBar = mBinding.searchBar.searchBarPlayer;
        mSearchBar.setOnSearchActionListener(this);
        mSearchBar.enableSearch();
    }

    // MaterialSearchBar OnSearchActionListener Implementation

    @Override
    public void onSearchConfirmed(CharSequence text) {
        if (mSearchBar instanceof MaterialSearchBar) {
            Log.d(TAG, "Search Confirmed");
            mSearchBar.disableSearch();
        }
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
        mSearchBar.clearSuggestions();
    }

    @Override
    public void onButtonClicked(int buttonCode) {
        if (buttonCode == MaterialSearchBar.BUTTON_BACK) {
            mSearchBar.disableSearch();
        }
    }
}
