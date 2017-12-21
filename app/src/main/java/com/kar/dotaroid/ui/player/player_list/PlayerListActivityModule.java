package com.kar.dotaroid.ui.player.player_list;

import android.arch.lifecycle.ViewModelProvider;

import com.kar.dotaroid.ViewModelProviderFactory;
import com.kar.dotaroid.data.OpendotaRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kemal Amru Ramadhan on 12/12/17.
 */

@Module
public class PlayerListActivityModule {


    @Provides
    PlayerListViewModel providePlayerListViewModel(OpendotaRepository repository) {
        return new PlayerListViewModel(repository);
    }

    @Provides
    ViewModelProvider.Factory providePlayerListViewModelProvider(PlayerListViewModel playerListViewModel) {
        return new ViewModelProviderFactory<>(playerListViewModel);
    }

    @Provides
    PlayerListAdapter providePlayerListAdapter() {
        return new PlayerListAdapter();
    }
}
