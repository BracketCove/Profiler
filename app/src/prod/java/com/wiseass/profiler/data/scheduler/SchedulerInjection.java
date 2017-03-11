package com.wiseass.profiler.data.scheduler;

/**
 * Get the real schedulers
 * Created by Ryan on 04/03/2017.
 */

public class SchedulerInjection {
    public static BaseSchedulerProvider provideSchedulerProvider(){
        return SchedulerProvider.getInstance();
    }
}
