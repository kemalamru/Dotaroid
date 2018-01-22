package com.kar.dotaroid.ui.player.player_profile;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kar.dotaroid.R;
import com.kar.dotaroid.data.model.Player;
import com.kar.dotaroid.data.model.PlayerMatches;
import com.kar.dotaroid.data.model.PlayerWinLose;
import com.kar.dotaroid.data.model.Profile;
import com.kar.dotaroid.utils.ImageUtil;
import com.kar.dotaroid.utils.Response;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;

import static com.kar.dotaroid.utils.Response.ERROR;
import static com.kar.dotaroid.utils.Response.SUCCESS;

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

    private String accountId;

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
        mViewModel.fetchProfileResponse().observe(this, playerProfile -> processProfileResponse(playerProfile));
        mViewModel.fetchWinLoseResponse().observe(this, winLose -> processWinLoseResponse(winLose));
        mViewModel.fetchRecentMatchResponse().observe(this, recentMatch-> processRecentMatchResponse(recentMatch));

        layoutSetup();

        accountId = getIntent().getStringExtra(INTENT_ACCOUNT_ID);
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
        mTvPlayerId = findViewById(R.id.tv_player_id);
        mTvPlayerMmr = findViewById(R.id.tv_player_mmr);
        mTvPlayerWin = findViewById(R.id.tv_player_win);
        mTvPlayerLose = findViewById(R.id.tv_player_lose);
        mTvPlayerWinPercentage = findViewById(R.id.tv_player_percentage);

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

    private void processProfileResponse(Response response) {
        switch (response.status) {
            case SUCCESS:
                Player player = (Player) response.data;
                ImageUtil.setImageUrl(mIvPlayer, player.getProfile().getAvatarfull());
                mTvPlayerName.setText(player.getProfile().getPersonaname());
                mTvPlayerId.setText("ID" + accountId);
                mTvPlayerMmr.setText(String.valueOf(player.getMmrEstimate().getEstimate()));
                break;
            case ERROR:
                break;
        }
    }

    private void processWinLoseResponse(Response response) {
        switch (response.status) {
            case SUCCESS:
                PlayerWinLose winLose = (PlayerWinLose) response.data;
                mTvPlayerWin.setText(String.valueOf(winLose.getWin()));
                mTvPlayerLose.setText(String.valueOf(winLose.getLose()));
                mTvPlayerWinPercentage.setText(String.valueOf(winLose.getWinPercentage()));
                break;
            case ERROR:
                break;
        }
    }

    private void processRecentMatchResponse(Response response) {
        switch (response.status) {
            case SUCCESS:
                List<PlayerMatches> playerMatchesList = (List<PlayerMatches>) response.data;
                mAdapter.addMatchList(playerMatchesList);
                break;
            case ERROR:
                break;
        }
    }

}
