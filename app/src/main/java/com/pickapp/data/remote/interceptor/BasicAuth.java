package com.pickapp.data.remote.interceptor;

import java.io.IOException;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuth implements Interceptor {

    private String credentials;
    private String cookie;

    public BasicAuth(String username, String password, String cookie) {
        credentials = Credentials.basic(username, password);
        this.cookie = cookie;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();

        if (cookie != null) {
            builder.addHeader("Cookie", cookie);
        }
        builder.addHeader("Authorization", credentials);

        return chain.proceed(builder.build());
    }
}
