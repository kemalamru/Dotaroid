package com.kar.dotaroid.ui.main;

import android.arch.lifecycle.ViewModelProvider;

import com.kar.dotaroid.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kemal Amru Ramadhan on 12/12/17.
 */

@Module
public class MainActivityModule {

    @Provides
    MainViewModel provideMainViewModel() {
        return new MainViewModel();
    }

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }
}
