package com.wiseass.profiler.data.auth;


public class AuthInjection {

    public static AuthSource provideAuthSource(){
        return FirebaseAuthService.getInstance();
    }

}