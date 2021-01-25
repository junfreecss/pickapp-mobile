package com.pickapp.data.remote.api;

import com.pickapp.data.remote.model.Response;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("/api/user/register")
    Observable<Response>  register(@Body RequestBody body);

    @POST("/api/user/login")
    Observable<Response>  login(@Body RequestBody body);

}
