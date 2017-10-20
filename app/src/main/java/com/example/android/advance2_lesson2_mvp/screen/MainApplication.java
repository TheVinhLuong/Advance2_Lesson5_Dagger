package com.example.android.advance2_lesson2_mvp.screen;

import android.app.Application;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.NetworkModule;

public class MainApplication extends Application {

    private AppComponent mAppComponent;

    public AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .applicationModule(new ApplicationModule(getApplicationContext()))
                    .networkModule(new NetworkModule(this))
                    .build();
        }
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

   
}