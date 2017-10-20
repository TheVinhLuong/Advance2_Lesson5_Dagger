package com.example.android.advance2_lesson2_mvp.screen.data.source;

import com.example.android.advance2_lesson2_mvp.screen.data.model.User;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by VinhTL on 18/10/2017.
 */

public interface UserDataSource{
    interface RemoteDataSource {
        Observable<List<User>> searchUsers(int limit, String keyWord);
    }
}
