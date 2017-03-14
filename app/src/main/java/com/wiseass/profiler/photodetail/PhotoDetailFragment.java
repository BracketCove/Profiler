package com.wiseass.profiler.photodetail;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wiseass.profiler.R;
import com.wiseass.profiler.data.auth.AuthInjection;
import com.wiseass.profiler.data.database.DatabaseInjection;
import com.wiseass.profiler.data.scheduler.SchedulerInjection;
import com.wiseass.profiler.photogallery.PhotoGalleryActivity;
import com.wiseass.profiler.profilepage.ProfilePageActivity;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link PhotoDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotoDetailFragment extends android.app.Fragment implements PhotoDetailContract.View {
    private static final String PHOTO_URL = "PHOTO_URL";

    private PhotoDetailContract.Presenter presenter;
    private ImageButton back, done;
    private ImageView photo;
    private ProgressBar progressBar;
    private String photoURL;

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }


    public PhotoDetailFragment() {
    }

    public static PhotoDetailFragment newInstance(String photoURL) {
        PhotoDetailFragment f = new PhotoDetailFragment();
        Bundle args = new Bundle();
        args.putString(PHOTO_URL, photoURL);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
        if (getArguments() != null){
            this.photoURL = getArguments().getString(PHOTO_URL);
        }
    }


    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if (presenter == null) {
             presenter = new PhotoDetailPresenter(AuthInjection.provideAuthSource(),
                     DatabaseInjection.provideDatabaseSource(),
                    this,
                    SchedulerInjection.provideSchedulerProvider());
        }
        presenter.subscribe();
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        android.view.View v = inflater.inflate(R.layout.fragment_photo_detail, container, false);
        back = (ImageButton) v.findViewById(R.id.imb_photo_detail_back);
        done = (ImageButton) v.findViewById(R.id.imb_photo_detail_done);
        photo = (ImageView) v.findViewById(R.id.imv_photo_detail);

        progressBar = (ProgressBar) v.findViewById(R.id.pro_photo_loading);

        back.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                presenter.onBackButtonPress();
            }
        });

        done.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                presenter.onDoneButtonPress();
            }
        });

        return v;
    }

    @Override
    public void makeToast(@StringRes int stringId) {
        Toast.makeText(getActivity().getApplicationContext(), getString(stringId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeToast(String message) {

    }

    @Override
    public void setBitmap() {
        Picasso.with(getActivity())
                .load(photoURL)
                .fit()
                .into(photo, new Callback() {
                    @Override
                    public void onSuccess() {
                        presenter.onImageLoaded();
                    }

                    @Override
                    public void onError() {
                        presenter.onImageLoadFailure();
                    }
                });
    }

    @Override
    public void startProfilePageActivity() {
        Intent i = new Intent(getActivity(), ProfilePageActivity.class);
        startActivity(i);
    }

    @Override
    public void startPhotoGalleryActivity() {
        Intent i = new Intent(getActivity(), PhotoGalleryActivity.class);
        startActivity(i);
    }

    @Override
    public void setPresenter(PhotoDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgressIndicator(boolean show) {
        if (show) {
            progressBar.setVisibility(android.view.View.VISIBLE);
            photo.setVisibility(android.view.View.INVISIBLE);
        } else {
            progressBar.setVisibility(android.view.View.INVISIBLE);
            photo.setVisibility(android.view.View.VISIBLE);
        }
    }

    @Override
    public String getPhotoURL() {
        return this.photoURL;
    }

    @Override
    public void onDestroy(){
        presenter.unsubscribe();
        super.onDestroy();
    }
}
