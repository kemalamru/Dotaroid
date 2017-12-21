package com.kar.dotaroid.ui.player.player_profile;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kar.dotaroid.R;
import com.kar.dotaroid.ViewModelProviderFactory;
import com.kar.dotaroid.data.model.PlayerProfileDetail;
import com.kar.dotaroid.databinding.ActivityHeroProfileBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

public class PlayerProfileActivity extends AppCompatActivity {

    private final String TAG = "PlayerProfileActivity";
    public final String INTENT_ACCOUNT_ID = "intent_account_id";

    private ActivityHeroProfileBinding mBinding;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    PlayerProfileMatchAdapter mAdapter;

    @Inject
    ViewModelProviderFactory mViewModelFactory;

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
        performRecyclerViewSetups();
        fetchData(accountId);
    }

    private void performRecyclerViewSetups() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mBinding.rvPlayerMatches.setLayoutManager(linearLayoutManager);
        mBinding.rvPlayerMatches.setHasFixedSize(true);
        mBinding.rvPlayerMatches.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mBinding.rvPlayerMatches.setAdapter(mAdapter);
    }

    private void fetchData(String accountId) {
        mDisposable.add(mViewModel.fetchData(accountId)
                .subscribe(
                    profileDetail -> {
                        performDataBinding(profileDetail);
                        Log.d(TAG, "Connection Succes, result: " + profileDetail.getPlayerName());
                    },
                    throwable -> {
                        if (throwable instanceof HttpException) {
                            String error = ((HttpException) throwable).response().errorBody().toString();
                            Log.d(TAG, "Connection Error: " + error);
                        }
                    }
                ));
    }

    private void performDataBinding(PlayerProfileDetail profileDetail) {
        mAdapter.addMatchList(profileDetail.getPlayerMatchList());
        mBinding.tvAccountName.setText(profileDetail.getPlayerName());
        mBinding.tvMmr.setText(profileDetail.getMmr());
        mBinding.tvWin.setText(profileDetail.getWin());
        mBinding.tvLose.setText(profileDetail.getLose());
        mBinding.tvWinPercentage.setText(profileDetail.getWinPercentage());
    }
}
