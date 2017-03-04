package com.wiseass.profiler.login;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wiseass.profiler.R;
import com.wiseass.profiler.createaccount.CreateAccountActivity;
import com.wiseass.profiler.profilepage.ProfilePageActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View {
    private Button login, register;
    private TextView emailLabel, passwordLabel;
    private EditText emailInput, passwordInput;
    //private ProgressBar progressBar;
    private View contentContainer;

    private LoginContract.Presenter presenter;

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if (presenter == null) {
            presenter = new LoginPresenter(
                    AuthInjection.provideAuthSource(),
                    this,
                    SchedulerInjection.provideSchedulerProvider());
        }

        presenter.subscribe();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);


        contentContainer = v.findViewById(R.id.cont_login_content);

        emailInput = (EditText) v.findViewById(R.id.edt_login_email);
        passwordInput = (EditText) v.findViewById(R.id.edt_login_password);

        emailLabel = (TextView) v.findViewById(R.id.lbl_login_email_sub);
        passwordLabel = (TextView) v.findViewById(R.id.lbl_login_password_sub);

        login = (Button) v.findViewById(R.id.btn_login);
        register = (Button) v.findViewById(R.id.btn_create_account);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCreateClick();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLogInClick();
            }
        });

        setUpListeners();
        emailInput.requestFocus();


        return v;
    }

    @Override
    public void showProgressIndicator(boolean show) {
        if (show){
            //progressBar.setVisibility(View.VISIBLE);
            //contentContainer.setVisibility(View.INVISIBLE);
        } else {
            //progressBar.setVisibility(View.INVISIBLE);
            //contentContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void makeToast(@StringRes int stringId) {
        Toast.makeText(getActivity().getApplicationContext(), getString(stringId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getEmail() {
        return emailInput.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordInput.getText().toString();
    }

    @Override
    public void startProfileActivity() {
        presenter.unsubscribe();
        Intent i = new Intent(getActivity(), ProfilePageActivity.class);
        startActivity(i);
    }

    @Override
    public void startCreateAccountActivity() {
        presenter.unsubscribe();
        Intent i = new Intent(getActivity(), CreateAccountActivity.class);
        startActivity(i);
    }

    /**
     * Sets Listeners to manage highlight colors for Inputs/Labels, based on hasFocus
     */
    public void setUpListeners() {
        emailInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View label, boolean hasFocus) {
                if (hasFocus) {
                    emailLabel.setTextColor(
                            ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorAccent)
                    );
                } else {
                    emailLabel.setTextColor(
                            ContextCompat.getColor(getActivity().getApplicationContext(), android.R.color.white)
                    );
                }
            }
        });

        passwordInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View label, boolean hasFocus) {
                if (hasFocus) {
                    passwordLabel.setTextColor(
                            ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorAccent)
                    );
                } else {
                    passwordLabel.setTextColor(
                            ContextCompat.getColor(getActivity().getApplicationContext(), android.R.color.white)
                    );
                }
            }
        });
    }

    @Override
    public void onDestroy(){
        presenter.unsubscribe();
        super.onDestroy();
    }

}
