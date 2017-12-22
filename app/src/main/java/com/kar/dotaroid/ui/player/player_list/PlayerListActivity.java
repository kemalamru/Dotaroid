package com.kar.dotaroid.ui.player.player_list;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.kar.dotaroid.R;
import com.kar.dotaroid.databinding.ActivityPlayerListBinding;
import com.kar.dotaroid.ui.player.player_profile.PlayerProfileActivity;
import com.kar.dotaroid.utils.SearchUtils;
import com.mancj.materialsearchbar.MaterialSearchBar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class PlayerListActivity extends AppCompatActivity {

    private final String TAG = "PlayerListActivity";

    private ActivityPlayerListBinding mBinding;
    private MaterialSearchBar mSearchBar;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    PlayerListAdapter mAdapter;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    PlayerListViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);
        setUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSearchListener(mBinding.searchBar.searchBarPlayer);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDisposable.clear();
    }

    private void setUp() {
        AndroidInjection.inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_player_list);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PlayerListViewModel.class);

        performMaterialSearchBarSetup();
        performRecyclerViewSetup();
    }

    private void performRecyclerViewSetup() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mBinding.rvPlayerList.setLayoutManager(linearLayoutManager);
        mBinding.rvPlayerList.setHasFixedSize(true);
        mBinding.rvPlayerList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mBinding.rvPlayerList.setAdapter(mAdapter);

        PlayerListAdapter.PlayerListClickListener listener = (View view, int accountId) -> {
            Intent intent = new Intent(this, PlayerProfileActivity.class);
            intent.putExtra(PlayerProfileActivity.INTENT_ACCOUNT_ID, Integer.toString(accountId));
            this.startActivity(intent);
        };
        mAdapter.setOnItemClickListener(listener);
    }

    private void performMaterialSearchBarSetup() {
        mSearchBar = mBinding.searchBar.searchBarPlayer;
        mSearchBar.enableSearch();
        mSearchBar.hideSuggestionsList();
    }

    private void setSearchListener(MaterialSearchBar searchBar) {
        mDisposable.add(SearchUtils.setSearchListener(searchBar)
                .doOnNext(query -> Log.d(TAG, "Search Query: " + query))
                .subscribe(query -> searchPlayer(query)));
    }

    private void searchPlayer(String playerName) {
        mDisposable.add(mViewModel.searchPlayer(playerName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(query -> Log.d(TAG, "Connect Query: " + query))
                .subscribe(
                        playerList -> {
                            mAdapter.addPlayerList(playerList);
                            Log.d(TAG, "Connection Succes, Data Length: " + playerList.size());
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                String error = ((HttpException) throwable).response().errorBody().toString();
                                Log.d(TAG, "Connection Error: " + error);
                            }
                        }
                ));
    }
}
