package com.example.android.advance2_lesson2_mvp.screen.main;

import android.app.Activity;
import com.example.android.advance2_lesson2_mvp.screen.data.UserRepository;
import com.example.android.advance2_lesson2_mvp.screen.data.source.UserRepositoryImpl;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.UserRemoteDataSource;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.service.NameApi;
import com.example.android.advance2_lesson2_mvp.screen.utils.dagger.ActivityScope;
import com.example.android.advance2_lesson2_mvp.screen.utils.navigator.Navigator;
import com.example.android.advance2_lesson2_mvp.screen.utils.rx.BaseSchedulerProvider;
import com.example.android.advance2_lesson2_mvp.screen.widget.dialog.DialogManager;
import com.example.android.advance2_lesson2_mvp.screen.widget.dialog.DialogManagerImplement;
import dagger.Module;
import dagger.Provides;

/**
 * Created by VinhTL on 20/10/2017.
 */

@Module
public class MainModule {
    private MainContract.View mView;

    public MainModule(MainContract.View view) {
        mView = view;
    }
    
    @ActivityScope
    @Provides
    public MainContract.Presenter providePresenter(UserRepository userRepository, 
            BaseSchedulerProvider provider){
        MainPresenter mainPresenter = new MainPresenter(userRepository);
        mainPresenter.setSchedulerProvider(provider);
        return mainPresenter;
    }
    
    @ActivityScope
    @Provides
    public UserRepository provideUserRepository(NameApi nameApi){
        return new UserRepositoryImpl(new UserRemoteDataSource(nameApi));
    }
    
    @ActivityScope
    @Provides
    public DialogManager provideDialogManager(){
        return new DialogManagerImplement(mView);
    }
    
    @ActivityScope
    @Provides
    public Navigator provideNavigator(){
        return new Navigator((Activity)(mView));
    }
}
