package com.kar.dotaroid.ui.player.player_profile;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.kar.dotaroid.R;
import com.kar.dotaroid.utils.ImageUtils;
import com.kar.dotaroid.utils.Response;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class PlayerProfileActivity extends AppCompatActivity {

    private final String TAG = "PlayerProfileActivity";
    public static final String INTENT_ACCOUNT_ID = "intent_account_id";

    private ImageView mIvPlayer;
    private TextView mTvPlayerName;
    private TextView mTvPlayerId;
    private TextView mTvPlayerMmr;
    private TextView mTvPlayerWin;
    private TextView mTvPlayerLose;
    private TextView mTvPlayerWinPercentage;
    private RecyclerView mRvPlayerMatches;


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
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_player_profile);

        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PlayerProfileViewModel.class);
        mViewModel.fetchProfileResponse().observe(this, playerProfile -> processResponse(playerProfile));
        mViewModel.fetchWinLoseResponse().observe(this, winLose -> processResponse(winLose));
        mViewModel.fetchRecentMatchResponse().observe(this, recentMatch-> processResponse(recentMatch));

        layoutSetup();

        String accountId = getIntent().getStringExtra(INTENT_ACCOUNT_ID);
        mViewModel.fetchPlayerData(accountId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDisposable.clear();
    }

    private void layoutSetup() {
        mIvPlayer = findViewById(R.id.iv_player);
        mTvPlayerName = findViewById(R.id.tv_player_name);
//        mTvPlayerId = findViewById(R.id.tv_player_id);

        recyclerViewSetup();
    }

    private void recyclerViewSetup() {
        mRvPlayerMatches = findViewById(R.id.rv_player_matches);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvPlayerMatches.setLayoutManager(linearLayoutManager);
        mRvPlayerMatches.setHasFixedSize(true);
        mRvPlayerMatches.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRvPlayerMatches.setAdapter(mAdapter);
    }

    private void processResponse(Response response) {

    }

//    private void fetchData(String accountId) {
//        fetchProfileData(accountId);
//        fetchWinLoseData(accountId);
//        fetchRecentMatchData(accountId);
//    }
//
//    private void fetchProfileData(String accountId) {
//        mDisposable.add(mViewModel.getPlayerProfile(accountId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        player -> {
//                            mTvPlayerName.setText(player.getProfile().getPersonaname());
//                            ImageUtils.setImageUrl(mIvPlayer, player.getProfile().getAvatarfull());
//                            mBinding.tvMmr.setText(Integer.toString(player.getMmrEstimate().getEstimate()));
//                        },
//                        throwable -> {
//                            Log.d(TAG, "Connection Error: " + throwable.getMessage());
//                        }
//                ));
//    }
//
//    private void fetchWinLoseData(String accountId) {
//        mDisposable.add(mViewModel.getPlayerWinLose(accountId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        playerWinLose -> {
//                            mBinding.tvWin.setText(Integer.toString(playerWinLose.getWin()));
//                            mBinding.tvLose.setText(Integer.toString(playerWinLose.getLose()));
//                            mBinding.tvWinPercentage.setText(playerWinLose.getWinPercentage());
//                        },
//                        throwable -> {
//                            Log.d(TAG, "Connection Error: " + throwable.getMessage());
//                        }
//                ));
//    }
//
//    private void fetchRecentMatchData(String accountId) {
//        mDisposable.add(mViewModel.getRecentMatch(accountId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        matchList -> {
//                            mAdapter.addMatchList(matchList);
//                        },
//                        throwable -> {
//                            Log.d(TAG, "Connection Error: " + throwable.getMessage());
//                        }
//                ));
//    }
}
