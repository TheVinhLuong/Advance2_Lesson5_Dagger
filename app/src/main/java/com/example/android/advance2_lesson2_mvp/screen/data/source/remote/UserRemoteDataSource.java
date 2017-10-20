package com.example.android.advance2_lesson2_mvp.screen.data.source.remote;

import com.example.android.advance2_lesson2_mvp.screen.data.model.User;
import com.example.android.advance2_lesson2_mvp.screen.data.model.UserList;
import com.example.android.advance2_lesson2_mvp.screen.data.source.UserDataSource;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.service.NameApi;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.List;

/**
 * Created by VinhTL on 18/10/2017.
 */

public class UserRemoteDataSource extends BaseRemoteDataSource implements UserDataSource.RemoteDataSource{

    public UserRemoteDataSource(NameApi nameApi) {
        super(nameApi);
    }

    @Override
    public Observable<List<User>> searchUsers(int limit, String keyWord) {
        //TODO: add observale logic
        return mNameApi.searchGithubUsers(limit, keyWord).map(new Function<UserList, List<User>>() {
            @Override
            public List<User> apply(UserList userList) throws Exception {
                return userList.getItems();
            }
        });
    }
}
