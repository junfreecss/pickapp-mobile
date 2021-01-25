package com.pickapp.data.remote.interceptor;

import com.pickapp.app.Preferences;
import com.pickapp.data.repository.sources.IPreferencesSource;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class Authenticated implements Interceptor {

    @Inject
    IPreferencesSource preferencesSource;

    @Inject
    public Authenticated() {}

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Cookie", preferencesSource.getString(Preferences.COOKIE));
        builder.addHeader("Bearer", preferencesSource.getString(Preferences.TOKEN));

        return chain.proceed(builder.build());
    }
}
