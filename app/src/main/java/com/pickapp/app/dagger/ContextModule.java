package com.pickapp.app.dagger;

import com.pickapp.app.PickApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private PickApp pickApp;

    public ContextModule(PickApp pickApp) {
        this.pickApp = pickApp;
    }

    @Provides
    @Singleton
    public PickApp provideApplicationContext() {
        return pickApp;
    }
}
