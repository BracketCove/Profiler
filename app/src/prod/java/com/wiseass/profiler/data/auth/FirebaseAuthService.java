package com.wiseass.profiler.data.auth;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;

/**
 * Created by Ryan on 01/03/2017.
 */

public class FirebaseAuthService implements AuthSource {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener listener;

    private FirebaseAuthService() {
        auth = FirebaseAuth.getInstance();
    }

    public static FirebaseAuthService getInstance() {
        return new FirebaseAuthService();
    }


    @Override
    public Completable createAccount(final Credentials cred) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(final CompletableEmitter e) throws Exception {
                if (auth == null) {
                    auth = FirebaseAuth.getInstance();
                }

                auth.createUserWithEmailAndPassword(cred.getEmail(), cred.getPassword())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    e.onComplete();
                                } else {
                                    e.onError(task.getException());
                                }
                            }
                        });

            }
        });
    }

    @Override
    public Completable attemptLogin(final Credentials cred) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(final CompletableEmitter e) throws Exception {
                if (auth == null) {
                    auth = FirebaseAuth.getInstance();
                }

                auth.signInWithEmailAndPassword(cred.getEmail(), cred.getPassword())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    e.onComplete();
                                } else {
                                    e.onError(task.getException());
                                }
                            }
                        });
            }
        });
    }

    @Override
    public Completable deleteUser() {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(final CompletableEmitter e) throws Exception {
                if (auth == null) {
                    auth = FirebaseAuth.getInstance();
                }

                final FirebaseUser user = auth.getCurrentUser();

                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            e.onComplete();
                        } else {
                            e.onError(task.getException());
                        }
                    }
                });

            }
        });
    }


    @Override
    public Maybe<User> getUser() {
        return Maybe.create(
                new MaybeOnSubscribe<User>() {
                    @Override
                    public void subscribe(final MaybeEmitter<User> e) throws Exception {
                        if (auth == null) {
                            auth = FirebaseAuth.getInstance();
                        }

                        if (listener != null) {
                            auth.removeAuthStateListener(listener);
                        }

                        listener = new FirebaseAuth.AuthStateListener() {
                            @Override
                            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                auth.removeAuthStateListener(listener);
                                if (firebaseUser != null) {
                                    User user = new User(
                                            firebaseUser.getEmail(),
                                            firebaseUser.getUid()
                                    );
                                    Maybe.just(user);
                                    e.onSuccess(user);
                                } else {
                                    e.onComplete();
                                }
                            }
                        };

                        auth.addAuthStateListener(listener);
                    }
                }
        );

    }


    @Override
    public Completable logUserOut() {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(final CompletableEmitter e) throws Exception {
                if (auth == null) {
                    auth = FirebaseAuth.getInstance();
                }

                listener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        auth.removeAuthStateListener(listener);
                        if (firebaseAuth.getCurrentUser() == null) {
                            e.onComplete();
                        } else {
                            e.onError(new Exception());
                        }
                    }
                };

                auth.addAuthStateListener(listener);
                auth.signOut();
            }
        });


    }

    @Override
    public Completable reauthenticateUser(final String password) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(final CompletableEmitter e) throws Exception {
                if (auth == null) {
                    auth = FirebaseAuth.getInstance();
                }

                final FirebaseUser user = auth.getCurrentUser();
                AuthCredential credential = EmailAuthProvider
                        .getCredential(user.getEmail(), password);

                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    e.onComplete();
                                } else {
                                    e.onError(task.getException());
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void setReturnFail(boolean bool) {
        //Do nothing. Method only has use during testing
    }

}
