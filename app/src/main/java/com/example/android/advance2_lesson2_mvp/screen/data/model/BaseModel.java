package com.example.android.advance2_lesson2_mvp.screen.data.model;

import com.google.gson.Gson;

/**
 * Created by VinhTL on 18/10/2017.
 */

public abstract  class BaseModel implements Cloneable{
    public BaseModel clone() throws CloneNotSupportedException{
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(this), this.getClass());
    }
}
