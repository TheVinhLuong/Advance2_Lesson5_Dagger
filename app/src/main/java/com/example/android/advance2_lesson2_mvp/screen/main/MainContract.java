package com.example.android.advance2_lesson2_mvp.screen.main;

import com.example.android.advance2_lesson2_mvp.screen.BasePresenter;
import com.example.android.advance2_lesson2_mvp.screen.BaseView;
import com.example.android.advance2_lesson2_mvp.screen.data.model.User;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.error.BaseException;
import com.example.android.advance2_lesson2_mvp.screen.utils.rx.BaseSchedulerProvider;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainContract {
    /**
     * View.
     */
    interface View extends BaseView {
        void onSearchError(BaseException throwable);

        void onSearchUsersSuccess(List<User> users);

        void onInvalidKeyWord(String errorMsg);

        void onInvalidLimitNumber(String errorMsg);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<View> {
        //TODO: Implement validate
//        boolean validateKeywordInput(String keyword);
//
//        boolean validateLimitNumberInput(String limit);

//        boolean validateDataInput(String keyword, String limit);

        void setSchedulerProvider(BaseSchedulerProvider schedulerProvider);

        void searchUsers(int limit, String keyWord);
    }
}
