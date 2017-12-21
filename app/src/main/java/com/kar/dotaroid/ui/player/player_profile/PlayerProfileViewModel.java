package com.kar.dotaroid.ui.player.player_profile;

import android.arch.lifecycle.ViewModel;

import com.kar.dotaroid.data.OpendotaRepository;
import com.kar.dotaroid.data.model.PlayerProfileDetail;
import com.kar.dotaroid.utils.PlayerUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Kemal Amru Ramadhan on 12/12/17.
 */

public class PlayerProfileViewModel extends ViewModel{

    private OpendotaRepository mRepository;

    public PlayerProfileViewModel(OpendotaRepository repository) {
        mRepository = repository;
    }

    public Observable<PlayerProfileDetail> fetchData(String accountId) {
        return Observable.zip(
                    mRepository.getPlayerProfile(accountId),
                    mRepository.getPlayerWinLose(accountId),
                    mRepository.getPlayerMatch(accountId),
                    (player, winLose, match) -> PlayerUtils.getProfileDetail(player, winLose, match))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
