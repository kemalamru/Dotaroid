package com.kar.dotaroid.ui.player.player_profile;

import android.arch.lifecycle.ViewModelProvider;

import com.kar.dotaroid.ViewModelProviderFactory;
import com.kar.dotaroid.data.OpendotaRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kemal Amru Ramadhan on 12/12/17.
 */

@Module
public class PlayerProfileActivityModule {

    @Provides
    PlayerProfileViewModel providePlayerProfileViewModel(OpendotaRepository repository) {
        return new PlayerProfileViewModel(repository);
    }

    @Provides
    ViewModelProvider.Factory playerProfileViewModelProvider(PlayerProfileViewModel playerProfileViewModel) {
        return new ViewModelProviderFactory<>(playerProfileViewModel);
    }

    @Provides
    PlayerProfileMatchAdapter providePlayerProfileAdapter() {
        return new PlayerProfileMatchAdapter();
    }
}
