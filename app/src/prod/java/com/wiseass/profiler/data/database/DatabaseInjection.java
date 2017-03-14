package com.wiseass.profiler.data.database;

import android.content.Context;
import android.support.annotation.NonNull;

import com.wiseass.profiler.data.auth.AuthSource;
import com.wiseass.profiler.data.auth.FirebaseAuthService;


/**
 * Created by Ryan on 31/01/2017.
 */

public class DatabaseInjection {
    public static DatabaseSource provideDatabaseSource() {
        return FirebaseDatabaseService.getInstance();
    }
}
