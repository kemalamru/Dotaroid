package com.kar.dotaroid.ui.player.player_list;

import android.arch.lifecycle.ViewModel;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.kar.dotaroid.data.OpendotaRespository;
import com.kar.dotaroid.data.model.PlayerSearchReponse;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Kemal Amru Ramadhan on 12/12/17.
 */

public class PlayerListViewModel extends ViewModel {

    private OpendotaRespository mRepository;

    public PlayerListViewModel(OpendotaRespository repository) {
        mRepository = repository;
    }

    public Single<List<PlayerSearchReponse>> searchPlayer(String playerName) {
        return mRepository
                .searchPlayer(playerName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    // Material Search Bar Listener
    public Observable<String> setSearchListener(MaterialSearchBar materialSearchBar) {
        final PublishSubject<String> subject = PublishSubject.create();

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                subject.onNext(charSequence.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchConfirmed(CharSequence text) {
                //searchPlayer(text.toString());
                materialSearchBar.disableSearch();
                //Log.d(TAG, "Enter Search");
                subject.onComplete();
            }

            @Override
            public void onSearchStateChanged(boolean enabled) {}
            @Override
            public void onButtonClicked(int buttonCode) {
            }
        });

        return subject
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(text -> text.length() > 1)
                .distinctUntilChanged()
                .doOnNext(player -> Log.d("Player List Activity", "Search Query: " + player));
    }
}
