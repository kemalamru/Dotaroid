package com.kar.dotaroid.ui.player.player_list;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.kar.dotaroid.data.OpendotaRepository;
import com.kar.dotaroid.data.model.PlayerSearchReponse;
import com.kar.dotaroid.utils.Response;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kemal Amru Ramadhan on 12/12/17.
 */

public class PlayerListViewModel extends ViewModel {

    private OpendotaRepository mRepository;
    private CompositeDisposable mDisposable = new CompositeDisposable();
    private MutableLiveData<Response> mResponseLiveData = new MutableLiveData<>();

    public PlayerListViewModel(OpendotaRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }

    public MutableLiveData<Response> searchResponse() {
        return mResponseLiveData;
    }

    public void searchPlayer(String playerName) {
        mDisposable.add(mRepository.searchPlayer(playerName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> mResponseLiveData.setValue(Response.loading()))
                .subscribe(
                        playerSearchReponses -> mResponseLiveData.setValue(Response.success(playerSearchReponses)),
                        throwable -> mResponseLiveData.setValue(Response.error(throwable))
                ));
    }
}
