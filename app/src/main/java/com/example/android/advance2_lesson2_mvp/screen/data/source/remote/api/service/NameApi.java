package com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.service;

import com.example.android.advance2_lesson2_mvp.screen.data.model.UserList;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by VinhTL on 18/10/2017.
 */

public interface NameApi {
    @GET("/search/users")
    Observable<UserList> searchGithubUsers(@Query("per_page") int limit,
            @Query("q") String searchTerm);
}
