package com.example.android.advance2_lesson2_mvp.screen.searchresult;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.android.advance2_lesson2_mvp.R;
import com.example.android.advance2_lesson2_mvp.screen.BaseActivity;
import com.example.android.advance2_lesson2_mvp.screen.MainApplication;
import javax.inject.Inject;

/**
 * SearchResult Screen.
 */
public class SearchResultActivity extends BaseActivity implements SearchResultContract.View {

    @Inject
    SearchResultContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;

    @Inject
    SearchResultAdapter mSearchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DaggerSearchResultComponent.builder().appComponent(((MainApplication) getApplication())
                .getAppComponent()).searchResultModule(new SearchResultModule(this)).build()
                .inject(this);
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        
        mRecyclerView = (RecyclerView) findViewById(R.id.resultRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSearchResultAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }
}
