package com.example.android.advance2_lesson2_mvp.screen.data.source.local.sharedpref;

/**
 * Created by VinhTL on 18/10/2017.
 */

public interface SharedPrefApi {
    <T> T get(String key, Class<T> clazz);
    <T> void put(String key, T data);
    void clear();
}
