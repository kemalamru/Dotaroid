package com.kar.dotaroid.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kemal Amru Ramadhan on 12/11/17.
 */


public class Player {

    @SerializedName("tracked_until")
    @Expose
    private Object trackedUntil;

    @SerializedName("competitive_rank")
    @Expose
    private Integer competitiveRank;

    @SerializedName("rank_tier")
    @Expose
    private Integer rankTier;

    @SerializedName("solo_competitive_rank")
    @Expose
    private Integer soloCompetitiveRank;

    @SerializedName("profile")
    @Expose
    private Profile profile;

    @SerializedName("leaderboard_rank")
    @Expose
    private Object leaderboardRank;

    @SerializedName("mmr_estimate")
    @Expose
    private MmrEstimate mmrEstimate;

    public Object getTrackedUntil() {
        return trackedUntil;
    }

    public void setTrackedUntil(Object trackedUntil) {
        this.trackedUntil = trackedUntil;
    }

    public Integer getCompetitiveRank() {
        return competitiveRank;
    }

    public void setCompetitiveRank(Integer competitiveRank) {
        this.competitiveRank = competitiveRank;
    }

    public Integer getRankTier() {
        return rankTier;
    }

    public void setRankTier(Integer rankTier) {
        this.rankTier = rankTier;
    }

    public Integer getSoloCompetitiveRank() {
        return soloCompetitiveRank;
    }

    public void setSoloCompetitiveRank(Integer soloCompetitiveRank) {
        this.soloCompetitiveRank = soloCompetitiveRank;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Object getLeaderboardRank() {
        return leaderboardRank;
    }

    public void setLeaderboardRank(Object leaderboardRank) {
        this.leaderboardRank = leaderboardRank;
    }

    public MmrEstimate getMmrEstimate() {
        return mmrEstimate;
    }

    public void setMmrEstimate(MmrEstimate mmrEstimate) {
        this.mmrEstimate = mmrEstimate;
    }

}
