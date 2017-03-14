package com.wiseass.profiler.photogallery;

import android.app.Activity;
import android.support.annotation.StringRes;

import com.wiseass.profiler.BasePresenter;
import com.wiseass.profiler.BaseView;
import com.wiseass.profiler.data.photos.Photo;

import java.util.List;

/**
 *
 * Created by Ryan on 04/01/2017.
 */

public interface PhotoGalleryContract {

    interface View extends BaseView<Presenter> {
        void setAdapterData(List<Photo> photos);

        void setNoListDataFound();

        Activity getActivityContext();

        void makeToast(@StringRes int message);

        void setPresenter(Presenter presenter);

        void startPhotoDetailActivity(String photoURL);

        void showProgressIndicator(boolean show);

    }

    interface Presenter extends BasePresenter {
        void onPhotoItemClick(int itemPosition);
    }
}
