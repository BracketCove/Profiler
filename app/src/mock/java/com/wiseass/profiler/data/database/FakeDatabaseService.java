package com.wiseass.profiler.data.database;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Ryan on 10/02/2017.
 */

public class FakeDatabaseService implements DatabaseSource {
    boolean returnFailure = false;
    boolean returnEmpty = false;

    private static final Profile fakeProfile =
            new Profile(
                    "",
                    "",
                    "someId",
                    "email@example.com",
                    "someUrl",
                    "Derp"
            );

    @Override
    public void setReturnEmpty(boolean returnEmpty) {
        this.returnEmpty = returnEmpty;
    }

    public FakeDatabaseService(){

    }

    public static FakeDatabaseService getInstance (){
        return new FakeDatabaseService();
    }

    @Override
    public void setReturnFail(boolean returnFailure){
        this.returnFailure = returnFailure;
    }

    @Override
    public Completable createProfile(Profile profile) {
        if (returnFailure){
            return Completable.error(new Exception());
        }
        return Completable.complete();
    }

    @Override
    public Maybe<Profile> getProfile(String uid) {
        if (returnFailure){
            return Maybe.error(new Exception());
        } else if (returnEmpty) {
            return Maybe.empty();
        }
        //TODO: make static fake Profile for testing
        return Maybe.just(fakeProfile);
    }

    @Override
    public Completable deleteProfile(String uid) {
        if (returnFailure){
            return Completable.error(new Exception());
        }
        return Completable.complete();
    }

    @Override
    public Completable updateProfile(Profile profile) {
        if (returnFailure){
            return Completable.error(new Exception());
        }
        return Completable.complete();
    }
}
