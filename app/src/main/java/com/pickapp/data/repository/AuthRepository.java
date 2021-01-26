package com.pickapp.data.repository;

import android.util.Log;

import com.pickapp.app.Preferences;
import com.pickapp.data.local.pref.PrefDataSource;
import com.pickapp.data.remote.RemoteApi;
import com.pickapp.data.remote.model.Data;
import com.pickapp.data.remote.model.Response;
import com.pickapp.data.remote.model.User;
import com.pickapp.data.repository.sources.IPreferencesSource;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AuthRepository {

    @Inject
    RemoteApi api;

    @Inject
    PrefDataSource preferencesSource;

    @Inject
    public AuthRepository() {
        Log.d("ddd", "AuthRepository constructor");
    }

    /**
     * Register new user
     */
    public Observable<Data> register(User user) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("f_name", user.getFirstName())
                .addFormDataPart("l_name", user.getLastName())
                .addFormDataPart("email", user.getEmail())
                .addFormDataPart("password", "123456")
                .addFormDataPart("phone", user.getPhone())
                .addFormDataPart("picture", "")
                .build();

        return api.auth().register(body).map(Response::getData);
    }

    /**
     * User login
     */
    public Observable<Data> login(User user) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", user.getEmail())
                .addFormDataPart("password", user.getPassword())
                .build();

        return api.auth().login(body).map(new Function<Response, Data>() {
            @Override
            public Data apply(Response response) throws Throwable {
                preferencesSource.setObject(Preferences.USER, response.getData().getUser());
                return response.getData();
            }
        });
    }

    public User getUser() {
        return preferencesSource.getObject(Preferences.USER, User.class);
    }

}
