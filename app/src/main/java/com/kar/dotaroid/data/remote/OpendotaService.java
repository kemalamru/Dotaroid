package com.kar.dotaroid.data.remote;

import com.kar.dotaroid.data.model.Player;
import com.kar.dotaroid.data.model.PlayerSearchReponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Kemal Amru Ramadhan on 12/11/17.
 */

public interface OpendotaService {

    String ENDPOINT = "https://api.opendota.com/api/";

    // Search Dota2 Player
    @GET("search")
    Single<List<PlayerSearchReponse>> searchPlayer(@Query("q") String playerName);

    // Get Player Profile
    @GET("players/{userId}")
    Single<List<Player>> getPlayerProfile(@Path("userId") String userId);



    /******** Helper class that sets up a new services *******/
    class Creator {

        public static OpendotaService newOpendotaService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(OpendotaService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(OpendotaService.class);
        }
    }
}
