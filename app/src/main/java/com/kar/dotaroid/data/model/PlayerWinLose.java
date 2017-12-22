package com.kar.dotaroid.data.model;

/**
 * Created by Kemal Amru Ramadhan on 12/19/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;

public class PlayerWinLose {

    @SerializedName("win")
    @Expose
    private Integer win;

    @SerializedName("lose")
    @Expose
    private Integer lose;

    public Integer getWin() {
        return win;
    }

    public Integer getLose() {
        return lose;
    }

    public String getWinPercentage() {
        DecimalFormat df2 = new DecimalFormat(".##");
        double winPercentage = (double) win / ((double) win + (double) lose) * 100;
        return df2.format(winPercentage) + "%";
    }
}
