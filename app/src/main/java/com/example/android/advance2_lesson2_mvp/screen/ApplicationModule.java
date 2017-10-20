package com.example.android.advance2_lesson2_mvp.screen;

import android.content.Context;
import com.example.android.advance2_lesson2_mvp.screen.data.source.local.sharedpref.SharedPrefApi;
import com.example.android.advance2_lesson2_mvp.screen.data.source.local.sharedpref
        .SharedPrefsImplement;
import com.example.android.advance2_lesson2_mvp.screen.utils.dagger.AppScope;
import com.example.android.advance2_lesson2_mvp.screen.utils.rx.BaseSchedulerProvider;
import com.example.android.advance2_lesson2_mvp.screen.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

/**
 * Created by VinhTL on 20/10/2017.
 */

@Module
public class ApplicationModule {
    private Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }
    
    @Provides
    @AppScope
    public Context provideApplicationContext(){
        return mContext;
    }
    
    @Provides
    @AppScope
    public SharedPrefApi provideSharedPrefApi(){
        return new SharedPrefsImplement(mContext);
    };
    
    @Provides
    @AppScope
    public BaseSchedulerProvider provideBaseSchedulerProvider(){
        return SchedulerProvider.getInstance();
    }
}
