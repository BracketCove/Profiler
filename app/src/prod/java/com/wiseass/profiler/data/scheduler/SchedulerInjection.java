package com.wiseass.profiler.data.scheduler;

import com.wiseass.profiler.util.BaseSchedulerProvider;
import com.wiseass.profiler.util.SchedulerProvider;

/**
 * Get the real schedulers
 * Created by Ryan on 04/03/2017.
 */

public class SchedulerInjection {
    public static BaseSchedulerProvider provideSchedulerProvider(){
        return SchedulerProvider.getInstance();
    }
}
