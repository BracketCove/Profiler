package com.wiseass.profiler.data.auth;

/**
 * Created by Ryan on 01/03/2017.
 */

public class FirebaseAuthService implements AuthSource {

    public FirebaseAuthService () {
            
    }

    public static AuthSource getInstance() {
        return new FirebaseAuthService();
    }

}
