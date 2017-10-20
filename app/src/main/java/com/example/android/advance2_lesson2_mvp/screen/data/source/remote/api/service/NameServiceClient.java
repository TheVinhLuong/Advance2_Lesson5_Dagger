package com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.service;

import android.app.Application;
import android.support.annotation.NonNull;
import com.example.android.advance2_lesson2_mvp.screen.utils.Constant;

/**
 * Created by VinhTL on 18/10/2017.
 */

public class NameServiceClient extends ServiceClient {
    private static NameApi mNameApiInstance;
    
    
    public static NameApi getInstance(@NonNull Application application){
        if(mNameApiInstance == null){
            mNameApiInstance = createService(application, Constant.END_POINT_URL, NameApi.class);
        }
        return mNameApiInstance;
    }
}
