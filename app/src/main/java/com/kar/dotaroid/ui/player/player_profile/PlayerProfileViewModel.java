package com.kar.dotaroid.ui.player.player_profile;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kar.dotaroid.data.OpendotaRepository;
import com.kar.dotaroid.utils.Response;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Kemal Amru Ramadhan on 12/12/17.
 */

public class PlayerProfileViewModel extends ViewModel{

    private OpendotaRepository mRepository;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    private MutableLiveData<Response> mProfileLiveData = new MutableLiveData<>();
    private MutableLiveData<Response> mWinLoseLiveData = new MutableLiveData<>();
    private MutableLiveData<Response> mRecentMatchLiveData = new MutableLiveData<>();

    public PlayerProfileViewModel(OpendotaRepository repository) {
        mRepository = repository;
    }

    public MutableLiveData<Response> fetchProfileResponse() {
        return mProfileLiveData;
    }

    public MutableLiveData<Response> fetchWinLoseResponse() {
        return mWinLoseLiveData;
    }

    public MutableLiveData<Response> fetchRecentMatchResponse() {
        return mRecentMatchLiveData;
    }

    public void fetchPlayerData(String accountId) {
        getPlayerProfile(accountId);
        getPlayerWinLose(accountId);
        getPlayerRecentMatch(accountId);
    }

    private void getPlayerProfile(String accountId) {
        mDisposable.add(mRepository.getPlayerProfile(accountId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        playerProfile -> mProfileLiveData.setValue(Response.success(playerProfile)),
                        throwable -> mProfileLiveData.setValue(Response.error(throwable))
                ));
    }

    private void getPlayerWinLose(String accountId) {
        mDisposable.add(mRepository.getPlayerWinLose(accountId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        winLose -> mWinLoseLiveData.setValue(Response.success(winLose)),
                        throwable -> mWinLoseLiveData.setValue(Response.error(throwable))
                ));
    }

    private void getPlayerRecentMatch(String accountId) {
        mDisposable.add(mRepository.getPlayerMatch(accountId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        recentMatch -> mRecentMatchLiveData.setValue(Response.success(recentMatch)),
                        throwable -> mRecentMatchLiveData.setValue(Response.error(throwable))
                ));
    }
}
