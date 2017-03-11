package com.wiseass.profiler.profilepage;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wiseass.profiler.R;
import com.wiseass.profiler.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePageFragment extends Fragment implements ProfilePageContract.View {
    private ProfilePageContract.Presenter presenter;

    private TextView userBio, userInterests, userName, userEmail;
    private ImageView thumbnail;
    private FloatingActionButton editDetails;
    private ImageButton settings, logout;

    public ProfilePageFragment() {
        // Required empty public constructor
    }

    public static ProfilePageFragment newInstance(){
        ProfilePageFragment fragment = new ProfilePageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This is important. It helps our View/Presenter/Service survive orientation changes
        this.setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_page, container, false);

        userBio =  (TextView)v.findViewById(R.id.lbl_page_user_bio);
        userInterests =  (TextView)v.findViewById(R.id.lbl_page_user_interests);
        userName =  (TextView)v.findViewById(R.id.lbl_page_user_name);
        userEmail =  (TextView)v.findViewById(R.id.lbl_page_user_email);

        thumbnail = (ImageView) v.findViewById(R.id.imb_page_thumbnail);
        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onThumbnailClick();
            }
        });

        settings = (ImageButton)v.findViewById(R.id.imb_page_user_settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAccountSettingsClick();
            }
        });

        logout = (ImageButton)v.findViewById(R.id.imb_page_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onLogoutClick();
            }
        });

        //Where we assign our Views
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //Where we create our presenter if necessary
        if (presenter == null) {
            //presenter = new ProfilePagePresenter();
        }

        presenter.subscribe();

    }

    @Override
    public void onDestroy(){
        presenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    public void setPresenter(ProfilePageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void makeToast(@StringRes int stringId) {
        Toast.makeText(getActivity(), getString(stringId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setName(String name) {
        userName.setText(name);
    }

    @Override
    public void setEmail(String email) {
        userEmail.setText(email);
    }

    @Override
    public void setBio(String bio) {
        userBio.setText(bio);
    }

    @Override
    public void setInterests(String interests) {
        userInterests.setText(interests);
    }

    @Override
    public void setProfilePhotoURL(String profilePhotoURL) {
        Picasso.with(getActivity())
                .load(profilePhotoURL)
                .noFade()
                .into(thumbnail, new Callback() {
                    @Override
                    public void onSuccess() {
                        presenter.onThumbnailLoaded();
                    }

                    @Override
                    public void onError() {
                        setDefaultProfilePhoto();
                    }
                });
    }

    @Override
    public void setDefaultProfilePhoto() {
        Picasso.with(getActivity())
                .load(R.drawable.default_profile_pic)
                .noFade()
                .into(thumbnail, new Callback() {
                    @Override
                    public void onSuccess() {
                        presenter.onThumbnailLoaded();
                    }

                    @Override
                    public void onError() {
                        //TODO handle this better later on
                        makeToast("Unable to load Image.");
                    }
                });
    }

    @Override
    public void showLogoutSnackbar() {
        Snackbar.make(
                getView(),
                getString(R.string.action_logout),
                Snackbar.LENGTH_SHORT
                )
                .setAction(R.string.action_logout, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.onLogoutConfirmed();
                    }
                })
                .show();
    }

    @Override
    public void startLoginActivity() {
        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void setThumbnailLoadingIndictator(boolean show) {
        //TODO handle this
    }

    @Override
    public void setDetailLoadingIndicators(boolean show) {
        //TODO handle this
    }
}
