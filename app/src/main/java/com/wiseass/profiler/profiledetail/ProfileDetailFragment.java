package com.wiseass.profiler.profiledetail;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.wiseass.profiler.R;
import com.wiseass.profiler.data.auth.AuthInjection;
import com.wiseass.profiler.data.database.DatabaseInjection;
import com.wiseass.profiler.data.scheduler.SchedulerInjection;
import com.wiseass.profiler.profilepage.ProfilePageActivity;


public class ProfileDetailFragment extends Fragment implements ProfileDetailContract.View {

    private EditText bioInput, interestsInput;
    private ImageButton back, done;
    private ProfileDetailContract.Presenter presenter;

    public ProfileDetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfileDetailFragment newInstance() {
        ProfileDetailFragment fragment = new ProfileDetailFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if (presenter == null) {
            presenter = new ProfileDetailPresenter(
                    AuthInjection.provideAuthSource(),
                    this,
                    DatabaseInjection.provideDatabaseSource(),
                    SchedulerInjection.provideSchedulerProvider());
        }
        presenter.subscribe();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        android.view.View v = inflater.inflate(R.layout.fragment_profile_detail, container, false);
        bioInput = (EditText)v.findViewById(R.id.edt_profile_bio_input);
        interestsInput = (EditText)v.findViewById(R.id.edt_profile_interests_input);

        back = (ImageButton)v.findViewById(R.id.imb_profile_detail_back);
        back.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                presenter.onBackButtonClick();
            }
        });

        done = (ImageButton)v.findViewById(R.id.imb_profile_detail_done);
        done.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                presenter.onDoneButtonClick();
            }
        });

        return v;
    }


    @Override
    public void setBioText(String bio) {
        bioInput.setText(bio);
    }

    @Override
    public void setInterestsText(String interests) {
        interestsInput.setText(interests);
    }

    @Override
    public String getInterests() {
        return interestsInput.getText().toString();
    }

    @Override
    public String getBio() {
        return bioInput.getText().toString();
    }

    @Override
    public void startProfilePageActivity() {
        Intent i = new Intent(getActivity(), ProfilePageActivity.class);
        startActivity(i);
    }

    @Override
    public void setPresenter(ProfileDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void makeToast(@StringRes int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy(){
        presenter.unsubscribe();
        super.onDestroy();
    }
}
