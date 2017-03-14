package com.wiseass.profiler.data.photos;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;


/**
 *
 * Created by Ryan on 25/01/2017.
 */

public class PhotoService implements PhotoSource {

    //TODO: MAKE THIS INTO PHOTO_UTIL and talk to it via rxjava. It can probably be injected in presneter.
    private PhotoService() {

    }

    public static PhotoService getInstance() {
        return new PhotoService();
    }

    @Override
    public Maybe<List<Photo>> getThumbnails(final ContentResolver resolver) {
        return Maybe.create(
                new MaybeOnSubscribe<List<Photo>>() {
                    @Override
                    public void subscribe(final MaybeEmitter<List<Photo>> e) throws Exception {
                        Uri uri;
                        List<Photo> listOfAllPhotos = new ArrayList<Photo>();
                        Cursor cursor;
                        int column_index_data;
                        Uri imageUri;
                        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

                        String[] projection = {MediaStore.MediaColumns.DATA};

                        cursor = resolver.query(uri, projection, null,
                                null, null);
                        try {
                            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                            while (cursor.moveToNext()) {
                                imageUri = Uri.parse("file://" + cursor.getString(column_index_data));
                                listOfAllPhotos.add(new Photo(imageUri.toString()));
                            }
                            cursor.close();
                        } catch (Throwable t) {
                            if (cursor != null) {
                                cursor.close();
                            }
                            e.onError(t);
                        }

                        if (listOfAllPhotos.size() == 0) {
                            e.onComplete();
                        } else {
                            e.onSuccess(listOfAllPhotos);
                        }
                    }
                }
        );
    }

    @Override
    public void setReturnFail(boolean bool) {

    }
}
