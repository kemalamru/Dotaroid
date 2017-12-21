package com.kar.dotaroid.data.model;

import java.util.List;

/**
 * Created by Kemal Amru Ramadhan on 12/19/17.
 */

public class PlayerProfileDetail {

    private String playerName;
    private Integer mmr;
    private Integer win;
    private Integer lose;
    private List<PlayerMatch> playerMatchList;

    public PlayerProfileDetail(String playerName, Integer mmr, Integer win, Integer lose, List<PlayerMatch> playerMatchList) {
        this.playerName = playerName;
        this.mmr = mmr;
        this.win = win;
        this.lose = lose;
        this.playerMatchList = playerMatchList;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getMmr() {
        return mmr;
    }

    public void setMmr(Integer mmr) {
        this.mmr = mmr;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getLose() {
        return lose;
    }

    public void setLose(Integer lose) {
        this.lose = lose;
    }

    public List<PlayerMatch> getPlayerMatchList() {
        return playerMatchList;
    }

    public void setPlayerMatchList(List<PlayerMatch> playerMatchList) {
        this.playerMatchList = playerMatchList;
    }

    public String getWinPercentage() {
        Double winPercentage = (double) win / ((double) win + (double) lose);
        return winPercentage.toString() + "%";
    }
}
