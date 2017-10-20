package com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api;

import android.app.Application;
import com.example.android.advance2_lesson2_mvp.BuildConfig;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.middleware
        .InterceptorImplementation;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.service
        .BooleanAdapter;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.service
        .IntegerAdapter;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.service.NameApi;
import com.example.android.advance2_lesson2_mvp.screen.utils.Constant;
import com.example.android.advance2_lesson2_mvp.screen.utils.dagger.AppScope;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VinhTL on 20/10/2017.
 */

@Module
public class NetworkModule {
    private static final int CONNECTION_TIMEOUT = 60;
    private Application mApplication;

    public NetworkModule(Application application) {
        mApplication = application;
    }
    
    @AppScope
    @Provides
    Application provideApplication(){
        return mApplication;
    }
    
    @AppScope
    @Provides
    Gson provideGson(){
        BooleanAdapter booleanAdapter = new BooleanAdapter();
        IntegerAdapter integerAdapter = new IntegerAdapter();
        return new GsonBuilder()
                .registerTypeAdapter(Boolean.class, booleanAdapter)
                .registerTypeAdapter(boolean.class, booleanAdapter)
                .registerTypeAdapter(Integer.class, integerAdapter)
                .registerTypeAdapter(int.class, integerAdapter)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @AppScope
    @Provides
    Interceptor provideInterceptor() {
        return new InterceptorImplementation();
    }
    
    @AppScope
    @Provides
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @AppScope
    @Provides
    OkHttpClient provideOkHttpClient(Cache cache, Interceptor interceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.cache(cache);
        httpClientBuilder.addInterceptor(interceptor);
        httpClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            httpClientBuilder.addInterceptor(logging);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        return httpClientBuilder.build();
    }

    @AppScope
    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constant.END_POINT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @AppScope
    @Provides
    NameApi provideNameApi(Retrofit retrofit) {
        return retrofit.create(NameApi.class);
    }
}
