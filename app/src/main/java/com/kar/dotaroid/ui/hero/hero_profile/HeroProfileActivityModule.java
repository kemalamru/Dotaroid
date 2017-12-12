package com.kar.dotaroid.ui.hero.hero_profile;

import android.arch.lifecycle.ViewModelProvider;

import com.kar.dotaroid.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kemal Amru Ramadhan on 12/12/17.
 */

@Module
public class HeroProfileActivityModule {

    @Provides
    HeroProfileViewModel provideHeroProfileViewModel() {
        return new HeroProfileViewModel();
    }

    @Provides
    ViewModelProvider.Factory heroProfileViewModelProvider(HeroProfileViewModel heroProfileViewModel) {
        return new ViewModelProviderFactory<>(heroProfileViewModel);
    }
}
