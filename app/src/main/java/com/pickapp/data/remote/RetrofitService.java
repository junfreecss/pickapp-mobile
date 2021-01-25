package com.pickapp.data.remote;

import android.util.Log;

import com.pickapp.app.AppConstant;
import com.pickapp.data.remote.interceptor.ReceiveCookie;

import javax.inject.Inject;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    @Inject
    public RetrofitService() {
        Log.d("ddd", "RetrofitService");
    }

    public Retrofit getDefault() {
        OkHttpClient.Builder client = Client.get();
        client.interceptors().add(new ReceiveCookie());

        return new Retrofit.Builder()
                .baseUrl(AppConstant.HOST)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(Client.get().build())
                .build();
    }

}
