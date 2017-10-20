package com.example.android.advance2_lesson2_mvp.screen.main;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import com.example.android.advance2_lesson2_mvp.R;
import com.example.android.advance2_lesson2_mvp.screen.BaseActivity;
import com.example.android.advance2_lesson2_mvp.screen.MainApplication;
import com.example.android.advance2_lesson2_mvp.screen.data.model.User;
import com.example.android.advance2_lesson2_mvp.screen.data.source.UserRepositoryImpl;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.UserRemoteDataSource;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.error.BaseException;
import com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.service.NameServiceClient;
import com.example.android.advance2_lesson2_mvp.screen.searchresult.SearchResultActivity;
import com.example.android.advance2_lesson2_mvp.screen.utils.navigator.Navigator;
import com.example.android.advance2_lesson2_mvp.screen.utils.rx.SchedulerProvider;
import com.example.android.advance2_lesson2_mvp.screen.widget.dialog.DialogManager;
import com.fstyle.library.DialogAction;
import com.fstyle.library.MaterialDialog;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import static com.example.android.advance2_lesson2_mvp.screen.utils.Constant.LIST_USER_ARGS;

/**
 * Main Screen.
 */
public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainContract.Presenter mPresenter;
    private TextInputLayout mTextInputLayoutKeyword;
    //TODO: Implement validation as sample;
    private EditText mEditTextKeyword;
    private TextInputLayout mTextInputLayoutNumberLimit;
    //TODO: Implement validation as sample
    private EditText mEditNumberLimit;
    @Inject
    DialogManager mDialogManager;
    @Inject
    Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainComponent.builder()
                .appComponent(((MainApplication) getApplication()).getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
        UserRepositoryImpl userRepository = new UserRepositoryImpl(new UserRemoteDataSource(NameServiceClient.getInstance(getApplication())));
        mPresenter.setView(this);
        mPresenter.setSchedulerProvider(SchedulerProvider.getInstance());
        mTextInputLayoutKeyword = (TextInputLayout) findViewById(R.id.txtInputLayoutKeyword);
        mEditTextKeyword = (EditText) findViewById(R.id.edtKeyword);
        mTextInputLayoutNumberLimit =
                (TextInputLayout) findViewById(R.id.txtInputLayoutNumberLimit);
        mEditNumberLimit = (EditText) findViewById(R.id.edtNumberLimit);

      
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

    @Override
    public void onSearchError(BaseException e) {
        mDialogManager.dismissProgressDialog();
        mDialogManager.dialogError(e.getMessage(),
                new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog,
                            @NonNull DialogAction dialogAction) {
                        onSearchButtonClicked(null);
                    }
                });
    }

    @Override
    public void onSearchUsersSuccess(List<User> users) {
        mDialogManager.dismissProgressDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(LIST_USER_ARGS, (ArrayList<? extends Parcelable>) users);
        mNavigator.startActivity(SearchResultActivity.class, bundle);
    }

    @Override
    public void onInvalidKeyWord(String errorMsg) {
        mTextInputLayoutKeyword.setError(errorMsg);
    }

    @Override
    public void onInvalidLimitNumber(String errorMsg) {
        mTextInputLayoutNumberLimit.setError(errorMsg);
    }

    public void onSearchButtonClicked(View view) {
        String keyWord = mEditTextKeyword.getText().toString().trim();
        String limit = mEditNumberLimit.getText().toString().trim();

//        if (!mPresenter.validateDataInput(keyWord, limit)) {
//            return;
//        }
        mDialogManager.showIndeterminateProgressDialog();
        mPresenter.searchUsers(Integer.parseInt(limit), keyWord);
    }
}
