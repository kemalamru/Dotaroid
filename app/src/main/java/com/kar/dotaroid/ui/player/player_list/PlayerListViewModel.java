package com.kar.dotaroid.ui.player.player_list;

import android.arch.lifecycle.ViewModel;

import com.kar.dotaroid.data.OpendotaRespository;
import com.kar.dotaroid.data.model.PlayerSearchReponse;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Kemal Amru Ramadhan on 12/12/17.
 */

public class PlayerListViewModel extends ViewModel {

    private OpendotaRespository mRepository;

    public PlayerListViewModel(OpendotaRespository repository) {
        mRepository = repository;
    }

    public Single<List<PlayerSearchReponse>> searchPlayer(String playerName) {
        return mRepository.searchPlayer(playerName);
    }
}
