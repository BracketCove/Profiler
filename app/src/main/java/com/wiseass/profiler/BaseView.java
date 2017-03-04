package com.wiseass.profiler;

import android.support.annotation.StringRes;

/**
 * Created by Ryan on 01/03/2017.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);

    void makeToast(@StringRes int stringId);

    void makeToast(String message);
}
