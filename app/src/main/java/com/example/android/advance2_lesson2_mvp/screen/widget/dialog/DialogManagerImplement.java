package com.example.android.advance2_lesson2_mvp.screen.widget.dialog;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import com.example.android.advance2_lesson2_mvp.R;
import com.example.android.advance2_lesson2_mvp.screen.BaseView;
import com.fstyle.library.DialogAction;
import com.fstyle.library.MaterialDialog;

/**
 * Created by VinhTL on 18/10/2017.
 */

public class DialogManagerImplement implements DialogManager{

    private Context mContext;
    private MaterialDialog mProgressDialog;

    public DialogManagerImplement(Context context) {
        mContext = context;
    }
    
    public <V extends BaseView> DialogManagerImplement(@NonNull V view) {
        if (view instanceof Activity) {
            mContext = (Activity) view;
        }
    }

    @Override
    public void showIndeterminateProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new MaterialDialog.Builder(mContext).content("Please wait…")
                    .progress(true, 0)
                    .canceledOnTouchOutside(false)
                    .build();
        }
        mProgressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (mProgressDialog == null) {
            return;
        }
        mProgressDialog.dismiss();
    }

    @Override
    public void dialogError(String content,
            MaterialDialog.SingleButtonCallback positiveButtonListener) {
        new MaterialDialog.Builder(mContext).content(content)
                .positiveText(R.string.retry)
                .onPositive(positiveButtonListener)
                .show();
    }

    @Override
    public void dialogBasicWithoutTitle(String content,
            MaterialDialog.SingleButtonCallback positiveButtonListener) {
        new MaterialDialog.Builder(mContext).content(content)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .onPositive(positiveButtonListener)
                .show();
    }

    @Override
    public void dialogBasic(String title, String content,
            MaterialDialog.SingleButtonCallback positiveButtonListener) {
        new MaterialDialog.Builder(mContext).title(title)
                .content(content)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .onPositive(positiveButtonListener)
                .show();
    }

    @Override
    public void dialogBasicIcon(String title, String content, @DrawableRes int icon,
            MaterialDialog.SingleButtonCallback positiveButtonListener) {
        new MaterialDialog.Builder(mContext).title(title)
                .content(content)
                .iconRes(icon)
                .limitIconToDefaultSize()
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .onPositive(positiveButtonListener)
                .show();
    }

    @Override
    public void dialogBasicCheckPrompt(String title, MaterialDialog.SingleButtonCallback callback) {
        new MaterialDialog.Builder(mContext).iconRes(R.mipmap.ic_launcher)
                .limitIconToDefaultSize()
                .title(title)
                .checkBoxPrompt(mContext.getString(R.string.dont_ask_again), false, null)
                .positiveText(R.string.allow)
                .negativeText(R.string.deny)
                .onAny(callback)
                .show();
    }

    @Override
    public void dialogNeutral(String title, String content,
            MaterialDialog.SingleButtonCallback callback) {
        new MaterialDialog.Builder(mContext).title(title)
                .content(content)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .neutralText(R.string.more_info)
                .onAny(callback)
                .show();
    }

    @Override
    public void dialogList(String title, @ArrayRes int arrayId,
            MaterialDialog.ListCallback callback) {
        new MaterialDialog.Builder(mContext).title(title)
                .items(arrayId)
                .itemsCallback(callback)
                .show();
    }

    @Override
    public void dialogListWithoutTitle(@ArrayRes int arrayId,
            MaterialDialog.ListCallback callback) {
        new MaterialDialog.Builder(mContext).items(arrayId).itemsCallback(callback).show();
    }

    @Override
    public void dialogListSingleChoice(String title, @ArrayRes int arrayId, int selectedIndex,
            MaterialDialog.ListCallbackSingleChoice callback) {
        new MaterialDialog.Builder(mContext).title(title)
                .items(arrayId)
                .itemsCallbackSingleChoice(selectedIndex, callback)
                .positiveText(R.string.choose)
                .show();
    }

    @Override
    public void dialogListMultiChoice(String title, @ArrayRes int arrayId,
            Integer[] selectedIndices, MaterialDialog.ListCallbackMultiChoice callback) {
        new MaterialDialog.Builder(mContext).title(title)
                .items(arrayId)
                .positiveText(R.string.choose)
                .autoDismiss(false)
                .neutralText(R.string.clear)
                .itemsCallbackMultiChoice(selectedIndices, callback)
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog,
                            @NonNull DialogAction dialogAction) {
                        materialDialog.clearSelectedIndices();
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog,
                            @NonNull DialogAction dialogAction) {
                        materialDialog.dismiss();
                    }
                })
                .show();
    }
}
