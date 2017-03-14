package com.wiseass.profiler;

import com.wiseass.profiler.data.auth.AuthInjection;
import com.wiseass.profiler.data.auth.AuthSource;
import com.wiseass.profiler.data.database.DatabaseInjection;
import com.wiseass.profiler.data.database.DatabaseSource;
import com.wiseass.profiler.data.scheduler.SchedulerInjection;
import com.wiseass.profiler.photodetail.PhotoDetailFragment;
import com.wiseass.profiler.photodetail.PhotoDetailPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Responsible for Displaying an Image to the user, and writing that images' URL to said User's
 * Profile in the Database.
 *
 * This Class retrieves the current user's profile, then rights to it in the event that the user
 * clicks the done button.
 * Created by Ryan on 13/01/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class PhotoDetailPresenterTest {
    public static final String SOME_PHOTO_URI = "SOME_PHOTO_URI";

    private PhotoDetailPresenter presenter;

    @Mock
    private PhotoDetailFragment view;

    private AuthSource authSource;
    private DatabaseSource databaseSource;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authSource = AuthInjection.provideAuthSource();
        databaseSource = DatabaseInjection.provideDatabaseSource();
        presenter = new PhotoDetailPresenter(authSource,
                databaseSource,
                view,
                SchedulerInjection.provideSchedulerProvider()
        );

        presenter.subscribe();
    }

    @Test
    public void whenUserProfilePicURLUpdateSucceeds(){
        Mockito.when(view.getPhotoURL()).thenReturn(SOME_PHOTO_URI);
        presenter.onDoneButtonPress();

        Mockito.verify(view).startProfilePageActivity();
    }

    @Test
    public void whenUserProfilePicURLUpdateFails(){
        databaseSource.setReturnFail(true);
        presenter.onDoneButtonPress();

        Mockito.verify(view).makeToast(Mockito.anyString());
    }

    @Test
    public void whenUserHitsBackButton(){

        presenter.onBackButtonPress();

        Mockito.verify(view).startPhotoGalleryActivity();
    }

}
