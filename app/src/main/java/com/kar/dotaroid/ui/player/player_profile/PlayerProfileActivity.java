package com.kar.dotaroid.ui.player.player_profile;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.kar.dotaroid.R;
import com.kar.dotaroid.databinding.ActivityPlayerProfileBinding;
import com.kar.dotaroid.utils.ImageUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class PlayerProfileActivity extends AppCompatActivity {

    private final String TAG = "PlayerProfileActivity";
    public static final String INTENT_ACCOUNT_ID = "intent_account_id";

    private ActivityPlayerProfileBinding mBinding;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    PlayerProfileMatchAdapter mAdapter;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    PlayerProfileViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
        setUp();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDisposable.clear();
    }

    private void setUp() {
        AndroidInjection.inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_player_profile);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PlayerProfileViewModel.class);

        String accountId = getIntent().getStringExtra(INTENT_ACCOUNT_ID);
        performRecyclerViewSetup();
        fetchData(accountId);
    }

    private void performRecyclerViewSetup() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mBinding.rvPlayerMatches.setLayoutManager(linearLayoutManager);
        mBinding.rvPlayerMatches.setHasFixedSize(true);
        mBinding.rvPlayerMatches.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mBinding.rvPlayerMatches.setAdapter(mAdapter);
    }

    private void fetchData(String accountId) {
        fetchProfileData(accountId);
        fetchWinLoseData(accountId);
        fetchRecentMatchData(accountId);
    }

    private void fetchProfileData(String accountId) {
        mDisposable.add(mViewModel.getPlayerProfile(accountId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        player -> {
                            mBinding.tvAccountName.setText(player.getProfile().getPersonaname());
                            ImageUtils.setImageUrl(mBinding.imagePlayer, player.getProfile().getAvatarfull());
                            mBinding.tvMmr.setText(Integer.toString(player.getMmrEstimate().getEstimate()));
                        },
                        throwable -> {
                            Log.d(TAG, "Connection Error: " + throwable.getMessage());
                        }
                ));
    }

    private void fetchWinLoseData(String accountId) {
        mDisposable.add(mViewModel.getPlayerWinLose(accountId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        playerWinLose -> {
                            mBinding.tvWin.setText(Integer.toString(playerWinLose.getWin()));
                            mBinding.tvLose.setText(Integer.toString(playerWinLose.getLose()));
                            mBinding.tvWinPercentage.setText(playerWinLose.getWinPercentage());
                        },
                        throwable -> {
                            Log.d(TAG, "Connection Error: " + throwable.getMessage());
                        }
                ));
    }

    private void fetchRecentMatchData(String accountId) {
        mDisposable.add(mViewModel.getRecentMatch(accountId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(5)
                .subscribe(
                        matchList -> {
                            mAdapter.addMatchList(matchList);
                        },
                        throwable -> {
                            Log.d(TAG, "Connection Error: " + throwable.getMessage());
                        }
                ));
    }
}
