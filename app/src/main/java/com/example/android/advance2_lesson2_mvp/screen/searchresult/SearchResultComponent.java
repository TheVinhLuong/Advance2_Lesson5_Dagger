package com.example.android.advance2_lesson2_mvp.screen.searchresult;

import com.example.android.advance2_lesson2_mvp.screen.AppComponent;
import com.example.android.advance2_lesson2_mvp.screen.utils.dagger.ActivityScope;
import dagger.Component;

/**
 * Created by VinhTL on 20/10/2017.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = SearchResultModule.class)
public interface SearchResultComponent {
    void inject(SearchResultActivity searchResultActivity);
}
