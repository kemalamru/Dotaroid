package com.kar.dotaroid.injection.builder;

import com.kar.dotaroid.ui.hero.hero_list.HeroListActivity;
import com.kar.dotaroid.ui.hero.hero_list.HeroListActivityModule;
import com.kar.dotaroid.ui.hero.hero_profile.HeroProfileActivity;
import com.kar.dotaroid.ui.hero.hero_profile.HeroProfileActivityModule;
import com.kar.dotaroid.ui.main.MainActivity;
import com.kar.dotaroid.ui.main.MainActivityModule;
import com.kar.dotaroid.ui.player.player_list.PlayerListActivity;
import com.kar.dotaroid.ui.player.player_list.PlayerListActivityModule;
import com.kar.dotaroid.ui.player.player_profile.PlayerProfileActivity;
import com.kar.dotaroid.ui.player.player_profile.PlayerProfileActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Kemal Amru Ramadhan on 12/11/17.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {PlayerListActivityModule.class})
    abstract PlayerListActivity bindPlayerListActivity();

    @ContributesAndroidInjector(modules = {PlayerProfileActivityModule.class})
    abstract PlayerProfileActivity bindPlayerProfileActivity();

    @ContributesAndroidInjector(modules = {HeroListActivityModule.class})
    abstract HeroListActivity bindHeroListActivity();

    @ContributesAndroidInjector(modules = {HeroProfileActivityModule.class})
    abstract HeroProfileActivity bindHeroProfileActivity();
}
