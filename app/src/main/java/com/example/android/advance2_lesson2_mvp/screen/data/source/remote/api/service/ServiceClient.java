package com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.service;

import android.app.Application;
import android.support.annotation.NonNull;
import com.example.android.advance2_lesson2_mvp.BuildConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VinhTL on 18/10/2017.
 */

public class ServiceClient {
    private static final int CONNECTION_TIMEOUT = 60;

    static <T> T createService(Application application, String endPoint, Class<T> serviceClass) {
        return createService(application, endPoint, serviceClass, getGsonConfig(), null);
    }

    static <T> T createService(Application application, String endPoint, Class<T> serviceClass,
            Gson gson) {
        return createService(application, endPoint, serviceClass, gson, null);
    }

    static <T> T createService(Application application, String endPoint, Class<T> serviceClass,
            @NonNull Gson gson, Interceptor interceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        httpClientBuilder.cache(new Cache(application.getCacheDir(), cacheSize));
        if (interceptor != null) {
            httpClientBuilder.addInterceptor(interceptor);
        }
        httpClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(endPoint)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //TODO: unhide when mastered Rx
                //                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            httpClientBuilder.addInterceptor(logging);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        Retrofit retrofit = builder.client(httpClientBuilder.build())
                //TODO: unhide when mastered Rx
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    static Gson getGsonConfig() {
        BooleanAdapter booleanAdapter = new BooleanAdapter();
        IntegerAdapter integerAdapter = new IntegerAdapter();
        return new GsonBuilder().registerTypeAdapter(Boolean.class, booleanAdapter)
                .registerTypeAdapter(boolean.class, booleanAdapter)
                .registerTypeAdapter(Integer.class, integerAdapter)
                .registerTypeAdapter(int.class, integerAdapter)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }
}