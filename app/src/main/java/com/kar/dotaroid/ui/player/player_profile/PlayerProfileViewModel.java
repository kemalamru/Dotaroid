package com.kar.dotaroid.ui.player.player_profile;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.kar.dotaroid.data.OpendotaRepository;
import com.kar.dotaroid.data.model.Player;
import com.kar.dotaroid.data.model.PlayerMatch;
import com.kar.dotaroid.data.model.PlayerProfileDetail;
import com.kar.dotaroid.data.model.PlayerWinLose;
import com.kar.dotaroid.utils.PlayerUtils;

import java.util.List;

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

    public Observable<Player> getPlayerProfile(String accountId) {
        return mRepository.getPlayerProfile(accountId);
    }

    public Observable<PlayerWinLose> getPlayerWinLose(String accountId) {
        return mRepository.getPlayerWinLose(accountId);
    }

    public Observable<List<PlayerMatch>> getRecentMatch(String accountId) {
        return mRepository.getPlayerMatch(accountId);
    }
}
