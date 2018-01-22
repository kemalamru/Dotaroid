package com.kar.dotaroid.data.remote;

import com.kar.dotaroid.data.model.Player;
import com.kar.dotaroid.data.model.PlayerMatches;
import com.kar.dotaroid.data.model.PlayerSearchReponse;
import com.kar.dotaroid.data.model.PlayerWinLose;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    Observable<List<PlayerSearchReponse>> searchPlayer(@Query("q") String searchQuery);

    // Get Player Profile
    @GET("players/{accountId}")
    Observable<Player> getPlayerProfile(@Path("accountId") String accountId);

    // Get Player Profile
    @GET("players/{accountId}/matches")
    Observable<List<PlayerMatches>> getPlayerMatch(@Path("accountId") String accountId);

    // Get Player Win Lose
    @GET("players/{accountId}/wl")
    Observable<PlayerWinLose> getPlayerWinLose(@Path("accountId") String accountId);


    // Helper class that sets up a new services
    class Creator {
        public static OpendotaService newOpendotaService() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(OpendotaService.ENDPOINT)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(OpendotaService.class);
        }
    }
}
