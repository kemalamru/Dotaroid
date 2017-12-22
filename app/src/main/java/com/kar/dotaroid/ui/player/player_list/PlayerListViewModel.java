package com.kar.dotaroid.ui.player.player_list;

import android.arch.lifecycle.ViewModel;
import com.kar.dotaroid.data.OpendotaRepository;
import com.kar.dotaroid.data.model.PlayerSearchReponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Kemal Amru Ramadhan on 12/12/17.
 */

public class PlayerListViewModel extends ViewModel {

    private OpendotaRepository mRepository;

    public PlayerListViewModel(OpendotaRepository repository) {
        mRepository = repository;
    }

    public Observable<List<PlayerSearchReponse>> searchPlayer(String playerName) {
        return mRepository.searchPlayer(playerName);
    }
}
