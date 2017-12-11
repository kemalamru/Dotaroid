package com.kar.dotaroid.data;

import com.kar.dotaroid.data.remote.OpendotaService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Kemal Amru Ramadhan on 12/11/17.
 */

@Singleton
public class OpendotaRespository {

    private final OpendotaService mOpendotaService;

    @Inject
    public OpendotaRespository(OpendotaService opendotaService) {
        mOpendotaService = opendotaService;
    }


}
