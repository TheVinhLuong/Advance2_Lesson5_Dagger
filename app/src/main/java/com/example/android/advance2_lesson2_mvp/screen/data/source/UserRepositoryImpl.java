package com.example.android.advance2_lesson2_mvp.screen.data.source;

import com.example.android.advance2_lesson2_mvp.screen.data.UserRepository;
import com.example.android.advance2_lesson2_mvp.screen.data.model.User;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.UserRemoteDataSource;
import io.reactivex.Observable;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    
    private UserDataSource.RemoteDataSource mRemoteDataSource;

    public UserRepositoryImpl(UserRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public Observable<List<User>> searchUsers(int limit, String keyWord) {
        return mRemoteDataSource.searchUsers(limit, keyWord);
    }
}