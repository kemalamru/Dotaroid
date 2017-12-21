package com.kar.dotaroid.utils;

import java.util.ArrayList;

/**
 * Created by Kemal Amru Ramadhan on 12/21/17.
 */

public class HeroUtils {

    static ArrayList<String> heroList = new ArrayList<>();

    private HeroUtils() {
        heroList.add("antimage");
        heroList.add("axe");
        heroList.add("bane");
        heroList.add("bloodseeker");
        heroList.add("crystal_maiden");
        heroList.add("drow_ranger");
        heroList.add("earthshaker");
        heroList.add("juggernaut");
        heroList.add("mirana");
        heroList.add("morphling");
        heroList.add("nevermore");
        heroList.add("phantom_lancer");
        heroList.add("puck");
        heroList.add("pudge");
        heroList.add("razor");
        heroList.add("sand_king");
        heroList.add("storm_spirit");
        heroList.add("sven");
        heroList.add("tiny");
        heroList.add("vengefulspirit");
        heroList.add("windrunner");
        heroList.add("zuus");
        heroList.add("kunkka");
        heroList.add("lina");
        heroList.add("lion");
        heroList.add("shadow_shaman");
        heroList.add("slardar");
        heroList.add("tidehunter");
        heroList.add("witch_doctor");
        heroList.add("lich");
        heroList.add("riki");
        heroList.add("enigma");
        heroList.add("tinker");
        heroList.add("sniper");
        heroList.add("necrolyte");
        heroList.add("warlock");
        heroList.add("beastmaster");
        heroList.add("queenofpain");
        heroList.add("venomancer");
        heroList.add("faceless_void");
        heroList.add("skeleton_king");
        heroList.add("death_prophet");
        heroList.add("phantom_assassin");
        heroList.add("pugna");
        heroList.add("templar_assassin");
        heroList.add("viper");
        heroList.add("luna");
        heroList.add("dragon_knight");
        heroList.add("dazzle");
        heroList.add("rattletrap");
        heroList.add("leshrac");
        heroList.add("furion");
        heroList.add("life_stealer");
        heroList.add("dark_seer");
        heroList.add("clinkz");
        heroList.add("omniknight");
        heroList.add("enchantress");
        heroList.add("huskar");
        heroList.add("night_stalker");
        heroList.add("broodmother");
        heroList.add("bounty_hunter");
        heroList.add("weaver");
        heroList.add("jakiro");
        heroList.add("batrider");
        heroList.add("chen");
        heroList.add("spectre");
        heroList.add("ancient_apparition");
        heroList.add("doom_bringer");
        heroList.add("ursa");
        heroList.add("spirit_breaker");
        heroList.add("gyrocopter");
        heroList.add("alchemist");
        heroList.add("invoker");
        heroList.add("silencer");
        heroList.add("obsidian_destroyer");
        heroList.add("lycan");
        heroList.add("brewmaster");
        heroList.add("shadow_demon");
        heroList.add("lone_druid");
        heroList.add("chaos_knight");
        heroList.add("meepo");
        heroList.add("treant");
        heroList.add("ogre_magi");
        heroList.add("undying");
        heroList.add("rubick");
        heroList.add("disruptor");
        heroList.add("nyx_assassin");
        heroList.add("naga_siren");
        heroList.add("keeper_of_the_light");
        heroList.add("wisp");
        heroList.add("visage");
        heroList.add("slark");
        heroList.add("medusa");
        heroList.add("troll_warlord");
        heroList.add("centaur");
        heroList.add("magnataur");
        heroList.add("shredder");
        heroList.add("bristleback");
        heroList.add("tusk");
        heroList.add("skywrath_mage");
        heroList.add("abaddon");
        heroList.add("elder_titan");
        heroList.add("legion_commander");
        heroList.add("techies");
        heroList.add("ember_spirit");
        heroList.add("earth_spirit");
        heroList.add("abyssal_underlord");
        heroList.add("terrorblade");
        heroList.add("phoenix");
        heroList.add("oracle");
        heroList.add("winter_wyvern");
        heroList.add("arc_warden");
        heroList.add("monkey_king");
        heroList.add("dark_willow");
        heroList.add("pangolier");
    }

    public static String getHeroName(int heroId) {
        return heroList.get(heroId);
    }
}
