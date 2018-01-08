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
import com.kar.dotaroid.data.model.PlayerSearchReponse;
import com.kar.dotaroid.databinding.ActivityPlayerListBinding;
import com.kar.dotaroid.ui.player.player_profile.PlayerProfileActivity;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class PlayerListActivity extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener {

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

        AndroidInjection.inject(this);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_player_list);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PlayerListViewModel.class);

        performMaterialSearchBarSetup();
        performRecyclerViewSetup();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDisposable.clear();
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
        mSearchBar.setOnSearchActionListener(this);
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        searchPlayer(text.toString());
        mSearchBar.disableSearch();
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
    }

    @Override
    public void onButtonClicked(int buttonCode) {
    }

    private void searchPlayer(String playerName) {
        mDisposable.add(mViewModel.searchPlayer(playerName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> showProgressBar())
                .subscribe(
                        playerList -> {
                            hideProgressBar();
                            showResult(playerList);
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

    private void showResult(List<PlayerSearchReponse> playerList) {
        mAdapter.addPlayerList(playerList);
    }

    private void showProgressBar() {
        mBinding.rvPlayerList.setVisibility(View.GONE);
        mBinding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mBinding.progressBar.setVisibility(View.GONE);
        mBinding.rvPlayerList.setVisibility(View.VISIBLE);
    }
}
