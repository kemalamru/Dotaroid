package com.kar.dotaroid.ui.player.player_list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.kar.dotaroid.R;
import com.kar.dotaroid.ViewModelProviderFactory;
import com.kar.dotaroid.data.model.Player;
import com.kar.dotaroid.databinding.ActivityPlayerListBinding;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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
        mSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                searchPlayer(text.toString());
                mSearchBar.disableSearch();
                Log.d(TAG, "Enter Search");
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
        setSearchObservable(mSearchBar);
    }

    // MaterialSearchBar Listener

    public static Observable<String> fromSearchView(MaterialSearchBar materialSearchBar) {

        final PublishSubject<String> subject = PublishSubject.create();

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                subject.onNext(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                subject.onComplete();
            }
        });

        return subject;
    }

    private void setSearchObservable(MaterialSearchBar searchBar) {
        PlayerListActivity.fromSearchView(searchBar)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(text -> !text.isEmpty())
                .distinctUntilChanged()
                .doOnNext(player -> Log.d(TAG, "Search Query: " + player))
                .subscribe(playerName -> searchPlayer(playerName));
    }

    private void searchPlayer(String playerName) {
        mDisposable.add(mViewModel.searchPlayer(playerName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        playerList -> {
                            mAdapter.addPlayerList(playerList);
                            Log.d(TAG, "Connection Succes, example: " + playerList.get(0).getPersonaname());
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                String error = ((HttpException) throwable).response().errorBody().toString();
                                Log.d(TAG, "Connection Error: " + error);
                            }
                        }
                ));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDisposable.clear();
    }
}
