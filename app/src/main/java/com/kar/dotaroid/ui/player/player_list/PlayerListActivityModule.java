package com.kar.dotaroid.ui.player.player_list;

import android.arch.lifecycle.ViewModelProvider;

import com.kar.dotaroid.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kemal Amru Ramadhan on 12/12/17.
 */

@Module
public class PlayerListActivityModule {

    @Provides
    PlayerListViewModel providePlayerListViewModel() {
        return new PlayerListViewModel();
    }

    @Provides
    ViewModelProvider.Factory playerListViewModelFactory(PlayerListViewModel playerListViewModel) {
        return new ViewModelProviderFactory<>(playerListViewModel);
    }
}
