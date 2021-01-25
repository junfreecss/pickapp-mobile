package com.pickapp.app;

import android.app.Application;
import android.util.Log;

import com.pickapp.app.dagger.AppComponent;
import com.pickapp.app.dagger.ContextModule;
import com.pickapp.app.dagger.DaggerAppComponent;


public class PickApp extends Application {

    private static PickApp instance;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance =  PickApp.this;

        appComponent = DaggerAppComponent
                .builder()
                .contextModule(new ContextModule(instance))
                .build();
    }

    public static PickApp getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public void print() {
        Log.d("ddd", "Printing in Pick app application");
    }
}
