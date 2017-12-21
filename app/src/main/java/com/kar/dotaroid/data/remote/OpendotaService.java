package com.kar.dotaroid.data.remote;

import com.kar.dotaroid.data.model.Player;
import com.kar.dotaroid.data.model.PlayerMatch;
import com.kar.dotaroid.data.model.PlayerSearchReponse;
import com.kar.dotaroid.data.model.PlayerWinLose;

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
    Observable<List<PlayerSearchReponse>> searchPlayer(@Query("q") String playerName);

    // Get Player Profile
    @GET("players/{accountId}")
    Observable<Player> getPlayerProfile(@Path("accountId") String accountId);

    // Get Player Profile
    @GET("players/{accountId}/matches")
    Observable<List<PlayerMatch>> getPlayerMatch(@Path("accountId") String accountId);

    // Get Player Win Lose
    @GET("players/{accountId}/wl")
    Observable<PlayerWinLose> getPlayerWinLose(@Path("accountId") String accountId);


    // Helper class that sets up a new services
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
