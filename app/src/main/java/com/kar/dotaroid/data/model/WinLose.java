package com.kar.dotaroid.data.model;

/**
 * Created by Kemal Amru Ramadhan on 12/19/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WinLose {

    @SerializedName("win")
    @Expose
    private Integer win;

    @SerializedName("lose")
    @Expose
    private Integer lose;

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
}
