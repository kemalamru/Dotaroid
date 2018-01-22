package com.kar.dotaroid.ui.player.player_list;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kar.dotaroid.R;
import com.kar.dotaroid.data.model.PlayerSearchReponse;
import com.kar.dotaroid.ui.player.player_profile.PlayerProfileActivity;
import com.kar.dotaroid.utils.Response;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.SimpleOnSearchActionListener;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

import static com.kar.dotaroid.utils.Response.ERROR;
import static com.kar.dotaroid.utils.Response.LOADING;
import static com.kar.dotaroid.utils.Response.SUCCESS;

public class PlayerListActivity extends AppCompatActivity {

    private final String TAG = "PlayerListActivity";

    private MaterialSearchBar mSearchBar;
    private RecyclerView mRvPlayerList;
    private ProgressBar mProgressBar;

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

        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PlayerListViewModel.class);
        mViewModel.searchResponse().observe(this, response -> processResponse(response));

        layoutSetup();
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

    private void layoutSetup() {
        mProgressBar = findViewById(R.id.progress_bar);
        recyclerViewSetup();
        materialSearchBarSetup();
    }

    private void recyclerViewSetup() {
        mRvPlayerList = findViewById(R.id.rv_player_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvPlayerList.setLayoutManager(linearLayoutManager);
        mRvPlayerList.setHasFixedSize(true);
        mRvPlayerList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRvPlayerList.setAdapter(mAdapter);

        PlayerListAdapter.PlayerListClickListener listener = (View view, int accountId) -> {
            Intent intent = new Intent(this, PlayerProfileActivity.class);
            intent.putExtra(PlayerProfileActivity.INTENT_ACCOUNT_ID, Integer.toString(accountId));
            this.startActivity(intent);
        };

        mAdapter.setOnItemClickListener(listener);
    }

    private void materialSearchBarSetup() {
        mSearchBar = findViewById(R.id.search_bar_player);
        mSearchBar.enableSearch();
        mSearchBar.hideSuggestionsList();
        mSearchBar.setOnSearchActionListener(new SimpleOnSearchActionListener() {
            @Override
            public void onSearchConfirmed(CharSequence text) {
                mViewModel.searchPlayer(text.toString());
                mSearchBar.disableSearch();
            }
        });
    }

    private void processResponse(Response response) {
        switch (response.status) {
            case LOADING:
                mRvPlayerList.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.VISIBLE);
                break;

            case SUCCESS:
                List<PlayerSearchReponse> playerList = (List<PlayerSearchReponse>) response.data;

                if (playerList.isEmpty()) {
                    Toast.makeText(
                            this,
                            "Player Not Found",
                            Toast.LENGTH_SHORT).show();
                } else {
                    mAdapter.addPlayerList(playerList);
                    mProgressBar.setVisibility(View.GONE);
                    mRvPlayerList.setVisibility(View.VISIBLE);
                }
                break;

            case ERROR:
                if (response.error instanceof HttpException) {
                    Toast.makeText(
                            this,
                            "Please Check Your Connection",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(
                            this,
                            "Please Check Your Connection",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
