package com.example.android.advance2_lesson2_mvp.screen.utils.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.schedulers.Schedulers;

public final class SchedulerProvider implements BaseSchedulerProvider {
    @Nullable
    private static SchedulerProvider mInstance;

    // Prevent direct instantiation.
    private SchedulerProvider() {
    }

    public static synchronized SchedulerProvider getInstance() {
        if (mInstance == null) {
            mInstance = new SchedulerProvider();
        }
        return mInstance;
    }

    @Override
    @NonNull
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    @NonNull
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    @NonNull
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}