package com.kar.dotaroid.data;

import com.kar.dotaroid.data.model.Player;
import com.kar.dotaroid.data.model.PlayerMatches;
import com.kar.dotaroid.data.model.PlayerSearchReponse;
import com.kar.dotaroid.data.model.PlayerWinLose;
import com.kar.dotaroid.data.remote.OpendotaService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Kemal Amru Ramadhan on 12/11/17.
 */

@Singleton
public class OpendotaRepository {

    private final OpendotaService mOpendotaService;

    @Inject
    public OpendotaRepository(OpendotaService opendotaService) {
        mOpendotaService = opendotaService;
    }

    public Observable<List<PlayerSearchReponse>> searchPlayer(String searchQuery) {
        return mOpendotaService.searchPlayer(searchQuery);
    }

    public Observable<Player> getPlayerProfile(String accountId) {
        return mOpendotaService.getPlayerProfile(accountId);
    }

    public Observable<PlayerWinLose> getPlayerWinLose(String accountId) {
        return mOpendotaService.getPlayerWinLose(accountId);
    }

    public Observable<List<PlayerMatches>> getPlayerMatch(String accountId) {
        return mOpendotaService.getPlayerMatch(accountId);
    }
}
