package com.example.android.advance2_lesson2_mvp.screen.main;

import com.example.android.advance2_lesson2_mvp.screen.data.UserRepository;
import com.example.android.advance2_lesson2_mvp.screen.data.model.User;
import com.example.android.advance2_lesson2_mvp.screen.utils.rx.BaseSchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.List;


final class MainPresenter implements MainContract.Presenter {
    private static final String TAG = MainPresenter.class.getSimpleName();

    private UserRepository mUserRepository;
    private MainContract.View mView;
    private BaseSchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MainPresenter(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    @Override
    public void setView(MainContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
   
    }

    @Override
    public void setSchedulerProvider(BaseSchedulerProvider schedulerProvider) {
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void searchUsers(int limit, String keyWord) {
        final Disposable disposable = mUserRepository.searchUsers(limit, keyWord)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        mView.onSearchUsersSuccess(users);
                        mCompositeDisposable.clear();
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
