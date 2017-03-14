package com.wiseass.profiler.profiledetail;

import android.support.annotation.StringRes;

import com.wiseass.profiler.BasePresenter;
import com.wiseass.profiler.BaseView;

/**
 * Created by Ryan on 04/01/2017.
 */

public interface ProfileDetailContract {

    interface View extends BaseView<Presenter> {

        void setBioText(String bio);

        void setInterestsText(String interests);

        String getInterests();

        String getBio();

        void startProfilePageActivity();

        void setPresenter(Presenter presenter);

        void makeToast(@StringRes int message);
    }

    interface Presenter extends BasePresenter {
        void onBackButtonClick();

        void onDoneButtonClick();
    }
}
