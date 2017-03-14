package com.wiseass.profiler.profilepage;

import com.wiseass.profiler.R;
import com.wiseass.profiler.data.auth.AuthSource;
import com.wiseass.profiler.data.auth.User;
import com.wiseass.profiler.data.database.DatabaseSource;
import com.wiseass.profiler.data.database.Profile;
import com.wiseass.profiler.util.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;

/**
 * Created by Ryan on 25/02/2017.
 */

public class ProfilePagePresenter implements ProfilePageContract.Presenter {

    private final BaseSchedulerProvider schedulerProvider;
    private AuthSource auth;
    private DatabaseSource database;
    private ProfilePageContract.View view;
    private User currentUser;
    private CompositeDisposable disposableSubscriptions;

    public ProfilePagePresenter(AuthSource auth,
                                ProfilePageContract.View view,
                                DatabaseSource database,
                                BaseSchedulerProvider schedulerProvider
    ) {
        this.auth = auth;
        this.view = view;
        this.database = database;
        this.schedulerProvider = schedulerProvider;
        disposableSubscriptions = new CompositeDisposable();
        view.setPresenter(this);

    }

    private void getUserData() {
        view.setThumbnailLoadingIndicator(true);
        view.setDetailLoadingIndicators(true);
        disposableSubscriptions.add(
                auth.getUser().subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribeWith(
                                new DisposableMaybeObserver<User>() {

                                    @Override
                                    public void onError(Throwable e) {
                                        view.makeToast(R.string.error_retrieving_data);
                                        view.startLoginActivity();
                                    }

                                    @Override
                                    public void onComplete() {
                                        view.makeToast(R.string.error_retrieving_data);
                                        view.startLoginActivity();
                                    }

                                    @Override
                                    public void onSuccess(User user) {
                                        ProfilePagePresenter.this.currentUser = user;
                                        getUserProfileFromDatabase();
                                    }
                                }
                        )
        );
    }

    private void getUserProfileFromDatabase() {
        disposableSubscriptions.add(database.getProfile(currentUser.getUserId())
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableMaybeObserver<Profile>() {
                    @Override
                    public void onSuccess(Profile profile) {
                        view.setBio(profile.getBio());
                        view.setInterests(profile.getInterests());
                        view.setName(profile.getName());
                        view.setEmail(profile.getEmail());

                        view.setDetailLoadingIndicators(false);
                        String photoURL = profile.getPhotoURL();
                        if (photoURL.equals("")){
                            view.setDefaultProfilePhoto();
                        } else {
                            view.setProfilePhotoURL(profile.getPhotoURL());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.makeToast(e.getMessage());
                        view.startLoginActivity();
                    }

                    @Override
                    public void onComplete() {
                        view.startLoginActivity();
                    }
                })
        );

    }

    @Override
    public void onThumbnailClick() {
        view.startPhotoGalleryActivity();
    }

    @Override
    public void onEditProfileClick() {
        view.startProfileDetailActivity();
    }

    @Override
    public void onLogoutClick() {
        view.showLogoutSnackbar();
    }

    @Override
    public void onLogoutConfirmed(){
        disposableSubscriptions.add(
                auth.logUserOut()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                view.startLoginActivity();
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.makeToast(e.getMessage());
                            }
                        })
        );
    }

    @Override
    public void onAccountSettingsClick() {
        view.startProfileSettingsActivity();
    }

    @Override
    public void onThumbnailLoaded() {
        view.setThumbnailLoadingIndicator(false);
    }

    @Override
    public void subscribe() {
        getUserData();
    }

    @Override
    public void unsubscribe() {
        disposableSubscriptions.clear();
    }
}
