package com.wiseass.profiler.data.photos;

/**
 * Created by Ryan on 04/01/2017.
 */

public class Photo {
   private String photoUri;

    public Photo(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }
}
