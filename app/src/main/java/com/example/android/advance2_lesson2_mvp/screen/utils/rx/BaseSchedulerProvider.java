package com.example.android.advance2_lesson2_mvp.screen.utils.rx;

import android.support.annotation.NonNull;
import io.reactivex.Scheduler;

/**
 * Created by VinhTL on 19/10/2017.
 */

public interface BaseSchedulerProvider {
    @NonNull
    Scheduler computation();
    
    @NonNull
    Scheduler io();
    
    @NonNull
    Scheduler ui();
}
