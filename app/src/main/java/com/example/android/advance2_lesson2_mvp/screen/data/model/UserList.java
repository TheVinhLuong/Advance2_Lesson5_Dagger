package com.example.android.advance2_lesson2_mvp.screen.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhTL on 18/10/2017.
 */

public class UserList {
   
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    @SerializedName("items")
    @Expose
    private List<User> items = new ArrayList<>();
    
    public UserList(List<User> items){
        this.items = items;
    }

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
