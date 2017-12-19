package com.kar.dotaroid.ui.player.player_list;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.kar.dotaroid.R;
import com.kar.dotaroid.databinding.ActivityPlayerListBinding;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.HttpException;

public class PlayerListActivity extends AppCompatActivity {

    private final String TAG = "PlayerListActivity";

    private ActivityPlayerListBinding mBinding;
    private MaterialSearchBar mSearchBar;
    private RecyclerView mRecyclerView;

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
    protected void onStop() {
        super.onStop();
        mDisposable.clear();
    }

    private void setUp() {
        AndroidInjection.inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_player_list);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PlayerListViewModel.class);

        performMaterialSearchBarSetup();
        performRecyclerViewSetups();
    }

    private void performRecyclerViewSetups() {
        mRecyclerView = mBinding.rvPlayerList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void performMaterialSearchBarSetup() {
        mSearchBar = mBinding.searchBar.searchBarPlayer;
        mSearchBar.enableSearch();
        mSearchBar.hideSuggestionsList();
        setSearchListener(mSearchBar);
    }

    private void setSearchListener(MaterialSearchBar searchBar) {
        mDisposable
                .add(mViewModel
                .setSearchListener(searchBar)
                .subscribe(query -> searchPlayer(query)));
    }

    private void searchPlayer(String playerName) {
        mDisposable
                .add(mViewModel.searchPlayer(playerName)
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
