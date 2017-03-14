package com.wiseass.profiler.util;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * This one for use in Unit Tests
 * Created by Ryan on 04/03/2017.
 */

public class ImmediateSchedulerProvider implements BaseSchedulerProvider {
    private static ImmediateSchedulerProvider INSTANCE;

    private ImmediateSchedulerProvider(){
        //prevents direct instantiation
    }

    public static synchronized ImmediateSchedulerProvider getInstance (){
        if (INSTANCE == null) {
            INSTANCE = new ImmediateSchedulerProvider();
        }
        return INSTANCE;
    }

    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}
