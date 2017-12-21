package com.kar.dotaroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Kemal Amru Ramadhan on 12/15/17.
 */

public class ImageUtils {

    ArrayList<String> heroList = new ArrayList<>();


    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
    }

    public static void setImageHeroSmall(ImageView imageView, int heroId) {
        String imageUrl = new StringBuilder()
                .append("http://cdn.dota2.com/apps/dota2/images/heroes/")
                .append(HeroUtils.getHeroName(heroId))
                .append("_sb.png")
                .toString();

        setImageUrl(imageView, imageUrl);
    }
}
