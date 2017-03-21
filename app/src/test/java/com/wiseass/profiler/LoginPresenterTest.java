package com.wiseass.profiler;

import com.wiseass.profiler.data.auth.AuthSource;
import com.wiseass.profiler.data.auth.Credentials;
import com.wiseass.profiler.data.scheduler.SchedulerInjection;
import com.wiseass.profiler.login.LoginContract;
import com.wiseass.profiler.login.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Completable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This is a Unit Test for our Presenter
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private LoginContract.View view;

    private LoginPresenter presenter;

    @Mock
    private AuthSource authSource;

    public static final String VALID_PASSWORD = "123456";

    public static final String INVALID_PASSWORD = "12345";

    public static final String LONG_PASSWORD = "12345678912345678912";

    public static final String USERNAME = "Derp";

    public static final String INVALID_EMAIL = "userexample.com";

    public static final String VALID_EMAIL = "user@example.com";

    @Before
    public void SetUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        //authSource = AuthInjection.provideAuthSource();
        presenter = new LoginPresenter(
                authSource,
                view,
                SchedulerInjection.provideSchedulerProvider()
        );

    }

    @Test
    public void whenUserClicksCreateButton () {
        presenter.onCreateClick();
        verify(view).startCreateAccountActivity();
    }

    @Test
    public void whenLogInAuthFails () throws Exception {
        Credentials cred = new Credentials(VALID_PASSWORD, "", VALID_EMAIL);
        Mockito.when(authSource.attemptLogin(cred))
                .thenReturn(Completable.error(new Exception()));
        presenter.attemptLogIn(cred);

        verify(view).makeToast(Mockito.anyString());
    }

    @Test
    public void whenActiveUserFound(){
        presenter.getUser();

        verify(view).startProfileActivity();
    }

    @Test
    public void whenActiveUserNotFound(){
        //TODO: make this test work
        presenter.getUser();

        verify(view).showProgressIndicator(false);
    }


    @Test
    public void whenLogInAuthSucceeds () throws Exception {
        when(view.getEmail()).thenReturn(VALID_EMAIL);
        when(view.getPassword()).thenReturn(VALID_PASSWORD);
        presenter.onLogInClick();
        verify(view).startProfileActivity();
    }

    @Test
    public void whenEmailEmpty() throws Exception {
        when(view.getEmail()).thenReturn("");
        when(view.getPassword()).thenReturn(VALID_PASSWORD);
        presenter.onLogInClick();
        verify(view).makeToast(R.string.error_empty_input);
    }

    @Test
    public void whenEmailInvalid() throws Exception {
        when(view.getEmail()).thenReturn(INVALID_EMAIL);
        when(view.getPassword()).thenReturn(VALID_PASSWORD);
        presenter.onLogInClick();
        verify(view).makeToast(R.string.error_invalid_email);
    }

    @Test
    public void whenPasswordEmpty() throws Exception {
        when(view.getEmail()).thenReturn(VALID_EMAIL);
        when(view.getPassword()).thenReturn("");
        presenter.onLogInClick();
        verify(view).makeToast(R.string.error_empty_input);
    }

    @Test
    public void whenPasswordTooLong() throws Exception {
        when(view.getEmail()).thenReturn(VALID_EMAIL);
        when(view.getPassword()).thenReturn(LONG_PASSWORD);
        presenter.onLogInClick();
        verify(view).makeToast(R.string.error_password_too_long);
    }
}