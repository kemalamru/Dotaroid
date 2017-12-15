package com.kar.dotaroid.data;

import com.kar.dotaroid.data.model.PlayerSearchReponse;
import com.kar.dotaroid.data.remote.OpendotaService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by Kemal Amru Ramadhan on 12/11/17.
 */

@Singleton
public class OpendotaRespository {

    private final OpendotaService mOpendotaService;

    @Inject
    public OpendotaRespository(OpendotaService opendotaService) {
        mOpendotaService = opendotaService;
    }

    public Single<List<PlayerSearchReponse>> searchPlayer(String playerName) {
        return mOpendotaService.searchPlayer(playerName);
    }
}
