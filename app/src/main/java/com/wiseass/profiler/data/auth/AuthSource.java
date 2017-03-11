package com.wiseass.profiler.data.auth;


import android.support.annotation.VisibleForTesting;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Ryan on 04/03/2017.
 */

public interface AuthSource {

    Completable createAccount(Credentials cred);

    Completable attemptLogin(Credentials cred);

    Completable deleteUser();

    Maybe<User> getUser();

    Completable logUserOut();

    Completable reauthenticateUser(String password);

    void setReturnFail(boolean bool);
}
