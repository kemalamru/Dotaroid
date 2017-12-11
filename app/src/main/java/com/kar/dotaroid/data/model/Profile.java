package com.kar.dotaroid.data.model;

/**
 * Created by Kemal Amru Ramadhan on 12/11/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("account_id")
    @Expose
    private Integer accountId;

    @SerializedName("personaname")
    @Expose
    private String personaname;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("cheese")
    @Expose
    private Integer cheese;

    @SerializedName("steamid")
    @Expose
    private String steamid;

    @SerializedName("avatar")
    @Expose
    private String avatar;

    @SerializedName("avatarmedium")
    @Expose
    private String avatarmedium;

    @SerializedName("avatarfull")
    @Expose
    private String avatarfull;

    @SerializedName("profileurl")
    @Expose
    private String profileurl;

    @SerializedName("last_login")
    @Expose
    private Object lastLogin;

    @SerializedName("loccountrycode")
    @Expose
    private Object loccountrycode;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getPersonaname() {
        return personaname;
    }

    public void setPersonaname(String personaname) {
        this.personaname = personaname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCheese() {
        return cheese;
    }

    public void setCheese(Integer cheese) {
        this.cheese = cheese;
    }

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarmedium() {
        return avatarmedium;
    }

    public void setAvatarmedium(String avatarmedium) {
        this.avatarmedium = avatarmedium;
    }

    public String getAvatarfull() {
        return avatarfull;
    }

    public void setAvatarfull(String avatarfull) {
        this.avatarfull = avatarfull;
    }

    public String getProfileurl() {
        return profileurl;
    }

    public void setProfileurl(String profileurl) {
        this.profileurl = profileurl;
    }

    public Object getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Object lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Object getLoccountrycode() {
        return loccountrycode;
    }

    public void setLoccountrycode(Object loccountrycode) {
        this.loccountrycode = loccountrycode;
    }

}
