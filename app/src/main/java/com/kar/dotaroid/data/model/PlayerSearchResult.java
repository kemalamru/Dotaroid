package com.kar.dotaroid.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kemal Amru Ramadhan on 12/11/17.
 */


public class PlayerSearchResult {

    @SerializedName("account_id")
    @Expose
    private Integer accountId;

    @SerializedName("avatarfull")
    @Expose
    private String avatarfull;

    @SerializedName("personaname")
    @Expose
    private String personaname;

    @SerializedName("last_match_time")
    @Expose
    private String lastMatchTime;

    @SerializedName("similarity")
    @Expose
    private Double similarity;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAvatarfull() {
        return avatarfull;
    }

    public void setAvatarfull(String avatarfull) {
        this.avatarfull = avatarfull;
    }

    public String getPersonaname() {
        return personaname;
    }

    public void setPersonaname(String personaname) {
        this.personaname = personaname;
    }

    public String getLastMatchTime() {
        return lastMatchTime;
    }

    public void setLastMatchTime(String lastMatchTime) {
        this.lastMatchTime = lastMatchTime;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

}