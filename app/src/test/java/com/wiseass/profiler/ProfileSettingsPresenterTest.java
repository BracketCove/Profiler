package com.wiseass.profiler;


import com.wiseass.profiler.data.auth.AuthInjection;
import com.wiseass.profiler.data.auth.AuthSource;
import com.wiseass.profiler.data.database.DatabaseInjection;
import com.wiseass.profiler.data.database.DatabaseSource;
import com.wiseass.profiler.data.scheduler.SchedulerInjection;
import com.wiseass.profiler.profilesettings.ProfileSettingsContract;
import com.wiseass.profiler.profilesettings.ProfileSettingsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Responsible for Displaying a User's bio and interests based on their profile. The user may then
 * change those fields. The result is then written to the Database.
 * Created by Ryan on 13/01/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileSettingsPresenterTest {

    public static final String VALID_PASSWORD = "123456";

    private AuthSource authSource;

    private DatabaseSource databaseSource;

    private ProfileSettingsPresenter presenter;

    @Mock
    private ProfileSettingsContract.View view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        authSource = AuthInjection.provideAuthSource();
        databaseSource = DatabaseInjection.provideDatabaseSource();
        presenter = new ProfileSettingsPresenter(
                authSource,
                databaseSource,
                view,
                SchedulerInjection.provideSchedulerProvider()
        );
    }


    @Test
    public void whenGetUserFails(){
        authSource.setReturnFail(true);
        presenter.subscribe();

        Mockito.verify(view).startLogInActivity();
    }

    @Test
    public void whenGetUserSucceeds(){
        presenter.subscribe();

        Mockito.verify(view).showProgressIndicator(false);
    }

    @Test
    public void whenUserReauthenticationFails(){


        authSource.setReturnFail(true);

        presenter.onDeleteAccountConfirmed(VALID_PASSWORD);

        Mockito.verify(view).showProgressIndicator(false);
        Mockito.verify(view).makeToast(R.string.error_authenticating_credentails);
    }

    @Test
    public void whenDeleteProfileFromDatabaseFails(){
        databaseSource.setReturnFail(true);
        presenter.onDeleteAccountConfirmed(VALID_PASSWORD);


        Mockito.verify(view).showProgressIndicator(false);
    }

    @Test
    public void whenDeleteUserSucceeds(){
        presenter.subscribe();
        presenter.onDeleteAccountConfirmed(VALID_PASSWORD);

        Mockito.verify(view).startLogInActivity();
    }

}
