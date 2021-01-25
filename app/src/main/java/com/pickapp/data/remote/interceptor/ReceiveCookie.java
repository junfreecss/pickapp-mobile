package com.pickapp.data.remote.interceptor;

import com.pickapp.app.Preferences;
import com.pickapp.data.repository.sources.IPreferencesSource;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Response;

public class ReceiveCookie implements okhttp3.Interceptor {

    @Inject
    IPreferencesSource preferencesSource;

    public ReceiveCookie() {}
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (!response.headers("Set-Cookie").isEmpty()) {
            preferencesSource.setString(Preferences.COOKIE, response.header("Set-Cookie"));
        }
        return response;
    }
}
