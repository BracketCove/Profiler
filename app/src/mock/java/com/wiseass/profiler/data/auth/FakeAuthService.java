package com.wiseass.profiler.data.auth;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Ryan on 01/03/2017.
 */

public class FakeAuthService implements AuthSource {

    boolean returnFailure = false;

    private static final User fakeUser =
            new User(
                    "email@example.com",
                    "someId"
            );

    public FakeAuthService () {

    }

    public static AuthSource getInstance() {
        return new FakeAuthService();
    }


    @Override
    public Completable createAccount(Credentials cred) {
        if (returnFailure){
           return Completable.error(new Exception());
        }
        return Completable.complete();
    }

    @Override
    public Completable attemptLogin(Credentials cred) {
        if (returnFailure){
            return  Completable.error(new Exception());
        }
        return Completable.complete();
    }

    @Override
    public Completable deleteUser() {
        if (returnFailure){
            return  Completable.error(new Exception());
        }
        return Completable.complete();
    }

    @Override
    public Maybe<User> getUser() {
        if (returnFailure){
            return Maybe.error(new Exception());
        }

        //TODO: make static user for this test
        return Maybe.just(fakeUser);
    }

    @Override
    public Completable logUserOut() {
        if (returnFailure){
            return Completable.error(new Exception());
        }
        return Completable.complete();
    }

    @Override
    public Completable reauthenticateUser(String password) {
        if (returnFailure){
            return Completable.error(new Exception());
        }
        return Completable.complete();
    }

    @Override
    public void setReturnFail(boolean bool) {
        returnFailure = bool;
    }
}
