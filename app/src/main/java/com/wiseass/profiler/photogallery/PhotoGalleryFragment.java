package com.wiseass.profiler.photogallery;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wiseass.profiler.R;
import com.wiseass.profiler.data.photos.Photo;
import com.wiseass.profiler.data.photos.PhotoInjection;
import com.wiseass.profiler.data.scheduler.SchedulerInjection;
import com.wiseass.profiler.photodetail.PhotoDetailActivity;
import com.wiseass.profiler.profilepage.ProfilePageActivity;

import java.util.List;

public class PhotoGalleryFragment extends Fragment implements PhotoGalleryContract.View {
    private static final String EXTRA_PHOTO_URL = "EXTRA_PHOTO_URL";
    private PhotoGalleryContract.Presenter presenter;
    private GalleryAdapter adapter;
    private RecyclerView photoGallery;
    private TextView noData;
    private ProgressBar progressBar;
    private ImageButton back;

    public PhotoGalleryFragment() {
    }

    public static PhotoGalleryFragment newInstance() {
        PhotoGalleryFragment fragment = new PhotoGalleryFragment();
        return fragment;
    }

                            //BACK IN 2 MINUTES

    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if (presenter == null) {
            presenter = new PhotoGalleryPresenter(
                    this,
                    PhotoInjection.providePhotoSource(),
                    SchedulerInjection.provideSchedulerProvider(),
                    getActivity().getContentResolver()
                    //TODO figure out what happens to content resolver when activity destroyed
            );
        }

        presenter.subscribe();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        photoGallery = (RecyclerView)v.findViewById(R.id.rec_gallery_gallery);
        photoGallery.setLayoutManager(new GridLayoutManager(getActivityContext(), 2));
        progressBar = (ProgressBar)v.findViewById(R.id.pro_gallery_loading);
        noData = (TextView)v.findViewById(R.id.lbl_gallery_no_data);
        back = (ImageButton) v.findViewById(R.id.imb_gallery_back);
        back.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                startProfilePageActivity();
            }
        });
        return v;
    }

    private void startProfilePageActivity() {
        Intent i = new Intent(getActivity(), ProfilePageActivity.class);
        startActivity(i);
    }

    @Override
    public void setAdapterData(List<Photo> photos) {
        adapter = new GalleryAdapter(photos, getActivity());
        photoGallery.setAdapter(adapter);
    }

    @Override
    public void setNoListDataFound() {
        progressBar.setVisibility(android.view.View.INVISIBLE);
        photoGallery.setVisibility(android.view.View.INVISIBLE);
        noData.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public Activity getActivityContext() {
        return getActivity();
    }

    @Override
    public void makeToast(@StringRes int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void makeToast(String message) {

    }

    @Override
    public void setPresenter(PhotoGalleryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void startPhotoDetailActivity(String photoURL) {
        Intent i = new Intent(getActivity(), PhotoDetailActivity.class);
        i.putExtra(EXTRA_PHOTO_URL, photoURL);
        startActivity(i);
    }

    @Override
    public void showProgressIndicator(boolean show) {
        if (show){
            progressBar.setVisibility(android.view.View.VISIBLE);
            photoGallery.setVisibility(android.view.View.INVISIBLE);
            noData.setVisibility(android.view.View.INVISIBLE);
        } else {
            progressBar.setVisibility(android.view.View.INVISIBLE);
            photoGallery.setVisibility(android.view.View.VISIBLE);
            noData.setVisibility(android.view.View.INVISIBLE);
        }
    }

    @Override
    public void onDestroy(){
        presenter.unsubscribe();
        super.onDestroy();
    }

    public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.PhotoHolder> {

        private List<Photo> photos;
        private LayoutInflater inflater;
        private Context context;

        public GalleryAdapter(List<Photo> photos, Context c){
            this.context = c;
            this.inflater = LayoutInflater.from(c);
            this.photos = photos;
        }

        @Override
        public GalleryAdapter.PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            android.view.View view = inflater.inflate(R.layout.item_photo, parent, false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(final PhotoHolder holder, int position) {
            final Photo photo = photos.get(position);

            //TODO: figure out a way to solve resizing which works on all device sizes
            Picasso.with(context)
                    .load(photo.getPhotoUri())
                    .resize(600, 600)
                    .centerCrop()
                    .into(holder.userImage);

            //holder.userImage.setImageBitmap(photo);
        }

        @Override
        public int getItemCount() {
            return photos.size();
        }

        class PhotoHolder extends RecyclerView.ViewHolder implements android.view.View.OnClickListener {

            private ImageView userImage;

            public PhotoHolder(android.view.View itemView) {
                super(itemView);
                userImage = (ImageView)itemView.findViewById(R.id.imb_photo_thumbnail);
                userImage.setOnClickListener(this);
            }

            @Override
            public void onClick(android.view.View view) {
                presenter.onPhotoItemClick(getAdapterPosition());
            }
        }
    }
}
