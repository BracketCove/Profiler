package com.wiseass.profiler.login;

import android.support.annotation.StringRes;

import com.wiseass.profiler.BasePresenter;
import com.wiseass.profiler.BaseView;

/**
 * Created by Ryan on 25/02/2017.
 */

public interface LoginContract {
    interface View extends BaseView<LoginContract.Presenter> {
        void makeToast(@StringRes int stringId);

        void makeToast(String message);

        String getEmail();

        String getPassword();

        void startProfileActivity();

        void startCreateAccountActivity();

        void setPresenter(LoginContract.Presenter presenter);

        void showProgressIndicator(boolean show);
    }

    interface Presenter extends BasePresenter {
        void onLogInClick();

        void onCreateClick();

    }
}
