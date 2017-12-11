package com.kar.dotaroid.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kemal Amru Ramadhan on 12/11/17.
 */


public class MmrEstimate {

    @SerializedName("estimate")
    @Expose
    private Integer estimate;

    public Integer getEstimate() {
        return estimate;
    }

    public void setEstimate(Integer estimate) {
        this.estimate = estimate;
    }

}
