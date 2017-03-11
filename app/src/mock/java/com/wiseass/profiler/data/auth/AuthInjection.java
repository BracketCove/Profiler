package com.wiseass.profiler.data.auth;

/**
 * Created by Ryan on 04/03/2017.
 */

public class AuthInjection {

    public static AuthSource provideAuthSource(){
        return FakeAuthService.getInstance();
    }

}
