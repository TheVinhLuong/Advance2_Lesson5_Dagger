package com.example.android.advance2_lesson2_mvp.screen;

import android.content.Context;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.NetworkModule;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.service.NameApi;
import com.example.android.advance2_lesson2_mvp.screen.utils.dagger.AppScope;
import com.example.android.advance2_lesson2_mvp.screen.utils.rx.BaseSchedulerProvider;
import dagger.Component;

/**
 * Created by VinhTL on 20/10/2017.
 */

@AppScope
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface AppComponent {
    Context applicationContext();
    NameApi nameApi();
    BaseSchedulerProvider baseSchedulerProvider();
}
