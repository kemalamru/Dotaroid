package com.kar.dotaroid.injection.builder;

import com.kar.dotaroid.ui.hero.hero_list.HeroListActivity;
import com.kar.dotaroid.ui.hero.hero_list.HeroListViewModel;
import com.kar.dotaroid.ui.hero.hero_profile.HeroProfileActivity;
import com.kar.dotaroid.ui.hero.hero_profile.HeroProfileViewModel;
import com.kar.dotaroid.ui.main.MainActivity;
import com.kar.dotaroid.ui.main.MainActivityModule;
import com.kar.dotaroid.ui.player.player_list.PlayerListActivity;
import com.kar.dotaroid.ui.player.player_list.PlayerListViewModel;
import com.kar.dotaroid.ui.player.player_profile.PlayerProfileActivity;
import com.kar.dotaroid.ui.player.player_profile.PlayerProfileViewModel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Kemal Amru Ramadhan on 12/11/17.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {PlayerListViewModel.class})
    abstract PlayerListActivity bindPlayerListActivity();

    @ContributesAndroidInjector(modules = {PlayerProfileViewModel.class})
    abstract PlayerProfileActivity bindPlayerProfileActivity();

    @ContributesAndroidInjector(modules = {HeroListViewModel.class})
    abstract HeroListActivity bindHeroListActivity();

    @ContributesAndroidInjector(modules = {HeroProfileViewModel.class})
    abstract HeroProfileActivity bindHeroProfileActivity();
}
