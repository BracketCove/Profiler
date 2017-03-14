package com.wiseass.profiler.data.photos;

import android.content.ContentResolver;

import java.util.List;

import io.reactivex.Maybe;


/**
 * Created by Ryan on 25/01/2017.
 */

public interface PhotoSource {
    Maybe<List<Photo>> getThumbnails(ContentResolver resolver);

    void setReturnFail(boolean bool);
}
