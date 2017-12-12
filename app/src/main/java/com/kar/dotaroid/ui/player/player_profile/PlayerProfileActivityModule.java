package com.kar.dotaroid.ui.player.player_profile;

import android.arch.lifecycle.ViewModelProvider;

import com.kar.dotaroid.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kemal Amru Ramadhan on 12/12/17.
 */

@Module
public class PlayerProfileActivityModule {

    @Provides
    PlayerProfileViewModel providePlayerProfileViewModel() {
        return new PlayerProfileViewModel();
    }

    @Provides
    ViewModelProvider.Factory playerProfileViewModelProvider(PlayerProfileViewModel playerProfileViewModel) {
        return new ViewModelProviderFactory<>(playerProfileViewModel);
    }
}
