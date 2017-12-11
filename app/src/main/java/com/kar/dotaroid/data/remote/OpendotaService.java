package com.kar.dotaroid.data.remote;

import com.kar.dotaroid.data.model.PlayerSearchResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kemal Amru Ramadhan on 12/11/17.
 */

public interface OpendotaService {

    String ENDPOINT = "https://api.opendota.com/api/";

    @GET("search")
    Observable<List<PlayerSearchResult>> getSearchPlayer(@Query("q") String playerName);

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static OpendotaService newOpendotaService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(OpendotaService.ENDPOINT)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(OpendotaService.class);
        }
    }
}
