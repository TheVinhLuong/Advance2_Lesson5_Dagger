package com.example.android.advance2_lesson2_mvp.screen.searchresult;

import android.app.Activity;
import com.example.android.advance2_lesson2_mvp.screen.data.model.User;
import com.example.android.advance2_lesson2_mvp.screen.utils.Constant;
import com.example.android.advance2_lesson2_mvp.screen.utils.dagger.ActivityScope;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;

/**
 * Created by VinhTL on 20/10/2017.
 */

@Module
public class SearchResultModule {
    private SearchResultContract.View mView;

    public SearchResultModule(SearchResultContract.View view) {
        mView = view;
    }
    
    @ActivityScope
    @Provides
    public SearchResultContract.Presenter providePresenter(){
        SearchResultPresenter searchResultPresenter = new SearchResultPresenter();
        searchResultPresenter.setView(mView);
        return searchResultPresenter;
    }
    
    @ActivityScope
    @Provides
    public SearchResultAdapter provideSearchResultAdapter(){
        Activity activity = (Activity) mView;
        ArrayList<User> users = activity.getIntent().getParcelableArrayListExtra(Constant
                .LIST_USER_ARGS);
        return new SearchResultAdapter(activity, users);
    }
}
