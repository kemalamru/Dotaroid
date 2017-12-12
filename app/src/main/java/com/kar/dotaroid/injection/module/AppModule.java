package com.kar.dotaroid.injection.module;

import android.app.Application;
import android.content.Context;

import com.kar.dotaroid.data.remote.OpendotaService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kemal Amru Ramadhan on 12/11/17.
 */

@Module
public class AppModule {


    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    OpendotaService provideOpendotaService() {
        return OpendotaService.Creator.newOpendotaService();
    }
}
