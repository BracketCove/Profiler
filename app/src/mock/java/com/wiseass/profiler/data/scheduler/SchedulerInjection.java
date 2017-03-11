package com.wiseass.profiler.data.scheduler;

import com.wiseass.profiler.util.BaseSchedulerProvider;
import com.wiseass.profiler.util.ImmediateSchedulerProvider;
import com.wiseass.profiler.util.SchedulerProvider;

/**
 * Get the Immediate Scheduler for tests
 * Created by Ryan on 04/03/2017.
 */

public class SchedulerInjection {

    public static BaseSchedulerProvider provideSchedulerProvider(){
        return ImmediateSchedulerProvider.getInstance();
    }

}
