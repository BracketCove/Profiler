package com.wiseass.profiler;

import com.wiseass.profiler.createaccount.CreateAccountContract;
import com.wiseass.profiler.createaccount.CreateAccountPresenter;
import com.wiseass.profiler.data.auth.AuthInjection;
import com.wiseass.profiler.data.auth.AuthSource;
import com.wiseass.profiler.data.database.DatabaseInjection;
import com.wiseass.profiler.data.database.DatabaseSource;
import com.wiseass.profiler.data.scheduler.SchedulerInjection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;


/**
 * Responsible for Creating a new User in the AuthService.
 * Created by Ryan on 13/01/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountPresenterTest {

    public static final String VALID_PASSWORD = "123456";

    public static final String INVALID_PASSWORD = "12345";

    public static final String LONG_PASSWORD = "12345678912345678912";

    public static final String USERNAME = "Derp";

    public static final String INVALID_EMAIL = "userexample.com";

    public static final String VALID_EMAIL = "user@example.com";

    @Mock
    private CreateAccountContract.View view;

    private AuthSource authSource;
    private DatabaseSource databaseSource;

    private CreateAccountPresenter presenter;

    @Before
    public void setUp() throws Exception {
        //Automatically Mocks fields with @Mock annotation
        MockitoAnnotations.initMocks(this);
        authSource = AuthInjection.provideAuthSource();
        databaseSource = DatabaseInjection.provideDatabaseSource();
        presenter = new CreateAccountPresenter(
                authSource,
                databaseSource,
                view,
                SchedulerInjection.provideSchedulerProvider()
        );
    }

    @Test
    public void whenNameInputEmpty() throws Exception {
        Mockito.when(view.getName()).thenReturn("");
        Mockito.when(view.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(view.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(view.getPasswordConfirmation()).thenReturn(VALID_PASSWORD);

        presenter.onCreateAccountClick();
        Mockito.verify(view).makeToast(R.string.error_empty_input);
    }

    @Test
    public void whenEmailInputEmpty () throws Exception {
        Mockito.when(view.getName()).thenReturn(USERNAME);
        Mockito.when(view.getEmail()).thenReturn("");
        Mockito.when(view.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(view.getPasswordConfirmation()).thenReturn(VALID_PASSWORD);

        presenter.onCreateAccountClick();
        Mockito.verify(view).makeToast(R.string.error_empty_input);
    }

    @Test
    public void whenPasswordInputEmpty () throws Exception {
        Mockito.when(view.getName()).thenReturn(USERNAME);
        Mockito.when(view.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(view.getPassword()).thenReturn("");
        Mockito.when(view.getPasswordConfirmation()).thenReturn(VALID_PASSWORD);

        presenter.onCreateAccountClick();
        Mockito.verify(view).makeToast(R.string.error_empty_input);
    }

    @Test
    public void whenPasswordsDoNotMatch () throws Exception {
        Mockito.when(view.getName()).thenReturn(USERNAME);
        Mockito.when(view.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(view.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(view.getPasswordConfirmation()).thenReturn(INVALID_PASSWORD);

        presenter.onCreateAccountClick();
        Mockito.verify(view).makeToast(R.string.error_password_mismatch);
    }

    /**
     * Verify that DispatchActivity is launched when onComplete is called by the Observer. This
     * test only works when the FakeAuthService returns an actual Observable, via hard coding.
     * This is problematic, as we must change it's code each time we wish to perform this test,
     * or the one below it.
     * @throws Exception
     */
    @Test
    public void whenAccountCreationSucceeds () throws Exception {
        Mockito.when(view.getName()).thenReturn(USERNAME);
        Mockito.when(view.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(view.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(view.getPasswordConfirmation()).thenReturn(VALID_PASSWORD);

        presenter.onCreateAccountClick();

        Mockito.verify(view).startProfilePageActivity();
    }


    @Test
    public void whenAccountCreationFails () throws Exception {
        Mockito.when(view.getName()).thenReturn(USERNAME);
        Mockito.when(view.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(view.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(view.getPasswordConfirmation()).thenReturn(VALID_PASSWORD);

        authSource.setReturnFail(true);

        presenter.onCreateAccountClick();

        Mockito.verify(view).makeToast(Mockito.anyString());
    }

}
