package com.kar.dotaroid.utils;

/**
 * Created by Kemal Amru Ramadhan on 12/21/17.
 */

public class HeroUtil {

    static String[] heroList = new String[] {
            "antimage",
            "axe",
            "bane",
            "bloodseeker",
            "crystal_maiden",
            "drow_ranger",
            "earthshaker",
            "juggernaut",
            "mirana",
            "morphling",
            "nevermore",
            "phantom_lancer",
            "puck",
            "pudge",
            "razor",
            "sand_king",
            "storm_spirit",
            "sven",
            "tiny",
            "vengefulspirit",
            "windrunner",
            "zuus",
            "kunkka",
            "lina",
            "lion",
            "shadow_shaman",
            "slardar",
            "tidehunter",
            "witch_doctor",
            "lich",
            "riki",
            "enigma",
            "tinker",
            "sniper",
            "necrolyte",
            "warlock",
            "beastmaster",
            "queenofpain",
            "venomancer",
            "faceless_void",
            "skeleton_king",
            "death_prophet",
            "phantom_assassin",
            "pugna",
            "templar_assassin",
            "viper",
            "luna",
            "dragon_knight",
            "dazzle",
            "rattletrap",
            "leshrac",
            "furion",
            "life_stealer",
            "dark_seer",
            "clinkz",
            "omniknight",
            "enchantress",
            "huskar",
            "night_stalker",
            "broodmother",
            "bounty_hunter",
            "weaver",
            "jakiro",
            "batrider",
            "chen",
            "spectre",
            "ancient_apparition",
            "doom_bringer",
            "ursa",
            "spirit_breaker",
            "gyrocopter",
            "alchemist",
            "invoker",
            "silencer",
            "obsidian_destroyer",
            "lycan",
            "brewmaster",
            "shadow_demon",
            "lone_druid",
            "chaos_knight",
            "meepo",
            "treant",
            "ogre_magi",
            "undying",
            "rubick",
            "disruptor",
            "nyx_assassin",
            "naga_siren",
            "keeper_of_the_light",
            "wisp",
            "visage",
            "slark",
            "medusa",
            "troll_warlord",
            "centaur",
            "magnataur",
            "shredder",
            "bristleback",
            "tusk",
            "skywrath_mage",
            "abaddon",
            "elder_titan",
            "legion_commander",
            "techies",
            "ember_spirit",
            "earth_spirit",
            "abyssal_underlord",
            "terrorblade",
            "phoenix",
            "oracle",
            "winter_wyvern",
            "arc_warden",
            "monkey_king",
            "dark_willow",
            "pangolier"};

    public static String getHeroName(int heroId) {
        return heroList[heroId - 1];
    }
}