package com.kar.dotaroid.ui.hero.hero_list;

import android.arch.lifecycle.ViewModelProvider;

import com.kar.dotaroid.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kemal Amru Ramadhan on 12/12/17.
 */

@Module
public class HeroListActivityModule {

    @Provides
    HeroListViewModel provideHeroListViewModel() {
        return new HeroListViewModel();
    }

    @Provides
    ViewModelProvider.Factory heroListViewModelProvider(HeroListViewModel heroListViewModel) {
        return new ViewModelProviderFactory<>(heroListViewModel);
    }
}
