package com.wiseass.profiler.data.database;

import com.wiseass.profiler.data.GenericTestData;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Ryan on 10/02/2017.
 */

public class FakeDatabaseSource implements DatabaseSource {
    boolean returnFailure = false;
    boolean returnEmpty = false;

    @Override
    public void setReturnEmpty(boolean returnEmpty) {
        this.returnEmpty = returnEmpty;
    }

    public FakeDatabaseSource(){

    }

    public static FakeDatabaseSource getInstance (){
        return new FakeDatabaseSource();
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
        return Maybe.just(GenericTestData.getProfile());
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
