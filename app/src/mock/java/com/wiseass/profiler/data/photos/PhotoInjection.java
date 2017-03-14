package com.wiseass.profiler.data.photos;

/**
 * Created by Ryan on 31/01/2017.
 */

public class PhotoInjection {

    public static PhotoSource providePhotoSource() {
        return FakePhotoService.getInstance();
    }

}
