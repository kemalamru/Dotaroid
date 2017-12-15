package com.kar.dotaroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Kemal Amru Ramadhan on 12/15/17.
 */

public class UiUtils {

    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
    }
}
