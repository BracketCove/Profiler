package com.wiseass.profiler.data.database;

/**
 * Created by Ryan on 12/02/2017.
 */

public class DatabaseInjection {
    public static DatabaseSource provideDatabaseSource() {
        return FakeDatabaseSource.getInstance();
    }
}
