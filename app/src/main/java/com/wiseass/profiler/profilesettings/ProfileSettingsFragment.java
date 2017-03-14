package com.wiseass.profiler.profilesettings;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wiseass.profiler.R;
import com.wiseass.profiler.data.auth.AuthInjection;
import com.wiseass.profiler.data.database.DatabaseInjection;
import com.wiseass.profiler.data.scheduler.SchedulerInjection;
import com.wiseass.profiler.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileSettingsFragment extends Fragment implements ProfileSettingsContract.View {

    private ProfileSettingsContract.Presenter presenter;
    private Button deleteAccount;
    private ProgressBar progressBar;
    private EditText passwordInput;
    private TextView cardTitle, cardSubTitle;
    private CardView authCard;

    public ProfileSettingsFragment() {
        // Required empty public constructor
    }

    public static ProfileSettingsFragment newInstance() {
        ProfileSettingsFragment fragment = new ProfileSettingsFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter == null) {
            presenter = new ProfileSettingsPresenter(
                    AuthInjection.provideAuthSource(),
                    DatabaseInjection.provideDatabaseSource(),
                    this,
                    SchedulerInjection.provideSchedulerProvider()
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
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {

        android.view.View v = inflater.inflate(R.layout.fragment_profile_settings, container, false);
        progressBar = (ProgressBar) v.findViewById(R.id.pro_settings_loading);

        authCard = (CardView) v.findViewById(R.id.card_settings_auth);
        passwordInput = (EditText) v.findViewById(R.id.edt_settings_card_input);

        cardTitle = (TextView)v.findViewById(R.id.lbl_settings_card_heading);

        cardSubTitle = (TextView)v.findViewById(R.id.lbl_settings_card_sub);


        Button cardProceed = (Button)v.findViewById(R.id.btn_settings_card_proceed);
        cardProceed.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                presenter.onDeleteAccountConfirmed(passwordInput.getText().toString());
            }
        });
        Button cardCancel = (Button)v.findViewById(R.id.btn_settings_card_cancel);
        cardCancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                showAuthCard(false);
            }
        });

        deleteAccount = (Button) v.findViewById(R.id.btn_settings_delete_account);
        deleteAccount.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                presenter.onDeleteAccountPress();
            }
        });

        return v;
    }


    @Override
    public void setPresenter(ProfileSettingsContract.Presenter presenter) {

    }

    @Override
    public void makeToast(@StringRes int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void startLogInActivity() {
        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void showAuthCard(boolean show) {
        if (show) {
            authCard.setVisibility(android.view.View.VISIBLE);
            authCard.setClickable(true);

            cardTitle.setText(R.string.prompt_delete_user);
            cardSubTitle.setText(R.string.prompt_enter_password);

            Log.d("PROFILE_SETTINGS", Integer.toString(cardSubTitle.getVisibility()));

            progressBar.setVisibility(android.view.View.INVISIBLE);
        } else {
            authCard.setVisibility(android.view.View.INVISIBLE);
            authCard.setClickable(false);
        }
    }

    @Override
    public void showProgressIndicator(boolean show) {
        if (show) {
            progressBar.setVisibility(android.view.View.VISIBLE);

        } else {
            progressBar.setVisibility(android.view.View.INVISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        presenter.unsubscribe();
        super.onDestroy();
    }
}
