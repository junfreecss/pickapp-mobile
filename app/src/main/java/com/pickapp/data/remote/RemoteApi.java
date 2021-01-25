package com.pickapp.data.remote;

import android.util.Log;

import com.pickapp.data.remote.api.AuthApi;

import javax.inject.Inject;

public class RemoteApi {

    @Inject
    RetrofitService retrofitService;

    @Inject
    public RemoteApi() {
        Log.d("ddd", "RemoteApi");
    }

    public AuthApi auth() {
        return retrofitService.getDefault().create(AuthApi.class);
    }
}
