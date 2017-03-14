package com.wiseass.profiler.profilesettings;

import com.wiseass.profiler.BasePresenter;
import com.wiseass.profiler.BaseView;

/**
 * Created by Ryan on 14/02/2017.
 */

public interface ProfileSettingsContract {
    interface View extends BaseView<Presenter> {
        void startLogInActivity();

        void showAuthCard(boolean show);

        void showProgressIndicator(boolean show);
    }

    interface Presenter extends BasePresenter {
        void onDeleteAccountPress();

        void onDeleteAccountConfirmed(String password);
    }
}
