package com.example.android.advance2_lesson2_mvp.screen.searchresult;

import com.example.android.advance2_lesson2_mvp.screen.BasePresenter;
import com.example.android.advance2_lesson2_mvp.screen.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SearchResultContract {
    
    /**
     * View.
     */
    interface View extends BaseView {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<View> {
    }
}
