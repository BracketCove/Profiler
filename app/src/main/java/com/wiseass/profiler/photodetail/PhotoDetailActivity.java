package com.wiseass.profiler.photodetail;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wiseass.profiler.R;
import com.wiseass.profiler.createaccount.CreateAccountFragment;
import com.wiseass.profiler.photogallery.PhotoGalleryActivity;
import com.wiseass.profiler.util.ActivityUtils;

import static android.R.attr.fragment;

public class PhotoDetailActivity extends AppCompatActivity {

    private static final String PHOTO_DETAIL_FRAGMENT = "PHOTO_DETAIL_FRAGMENT";
    private static final String EXTRA_PHOTO_URL = "EXTRA_PHOTO_URL";

    private FragmentManager manager;
    private String photoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        photoURL = getIntent().getStringExtra(EXTRA_PHOTO_URL);

        if (photoURL == null){
            startPhotoGalleryActivity();
        }

        manager = this.getFragmentManager();

        //set up fragment
        PhotoDetailFragment fragment = (PhotoDetailFragment)
                manager.findFragmentByTag(PHOTO_DETAIL_FRAGMENT);

        if (fragment == null){
            fragment = PhotoDetailFragment.newInstance(photoURL);
        }

        ActivityUtils.addFragmentToActivity(manager,
                fragment,
                R.id.cont_photo_detail_fragment,
                PHOTO_DETAIL_FRAGMENT
        );

    }


    private void startPhotoGalleryActivity(){
        Intent i = new Intent(this, PhotoGalleryActivity.class);
        startActivity(i);
    }


}
