package com.kar.dotaroid.injection.component;

import android.app.Application;

import com.kar.dotaroid.DotaroidApp;
import com.kar.dotaroid.injection.builder.ActivityBuilder;
import com.kar.dotaroid.injection.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Kemal Amru Ramadhan on 12/11/17.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(DotaroidApp app);

}
