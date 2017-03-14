package com.wiseass.profiler.data.photos;

import android.content.Context;
import android.support.annotation.NonNull;

import com.wiseass.profiler.data.database.DatabaseSource;
import com.wiseass.profiler.data.database.FirebaseDatabaseService;


/**
 * Created by Ryan on 31/01/2017.
 */

public class PhotoInjection {

    public static PhotoSource providePhotoSource() {
        return PhotoService.getInstance();
    }
}
