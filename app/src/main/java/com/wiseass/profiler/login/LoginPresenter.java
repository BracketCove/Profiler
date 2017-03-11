package com.wiseass.profiler.login;

import com.wiseass.profiler.data.auth.AuthSource;
import com.wiseass.profiler.util.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;

/**
 * Created by Ryan on 25/02/2017.
 */
public class LoginPresenter implements LoginContract.Presenter {
    private AuthSource authSource;
    private LoginContract.View view;
    private CompositeDisposable compositeDisposable;
    private BaseSchedulerProvider schedulerProvider;


    public LoginPresenter (AuthSource authSource,
                           LoginContract.View view,
                           BaseSchedulerProvider schedulerProvider){
        this.authSource = authSource;
        this.view = view;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = new CompositeDisposable();

    }

    @Override
    public void onLogInClick() {

    }

    @Override
    public void onCreateClick() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
