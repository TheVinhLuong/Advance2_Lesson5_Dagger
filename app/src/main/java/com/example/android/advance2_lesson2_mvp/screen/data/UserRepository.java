package com.example.android.advance2_lesson2_mvp.screen.data;

import com.example.android.advance2_lesson2_mvp.screen.data.model.User;
import io.reactivex.Observable;
import java.util.List;

public interface UserRepository {
    //TODO: implement observable
    Observable<List<User>> searchUsers(int limit, String keyWord);
}