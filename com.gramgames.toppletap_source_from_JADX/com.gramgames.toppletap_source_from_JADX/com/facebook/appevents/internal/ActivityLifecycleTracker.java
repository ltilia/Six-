package com.facebook.appevents.internal;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.ads.AdError;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.internal.SourceApplicationInfo.Factory;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.FetchedAppSettings;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ActivityLifecycleTracker {
    private static final String INCORRECT_IMPL_WARNING = "Unexpected activity pause without a matching activity resume. Logging data may be incorrect. Make sure you call activateApp from your Application's onCreate method";
    private static final long INTERRUPTION_THRESHOLD_MILLISECONDS = 1000;
    private static final String TAG;
    private static String appId;
    private static volatile ScheduledFuture currentFuture;
    private static volatile SessionInfo currentSession;
    private static AtomicInteger foregroundActivityCount;
    private static final ScheduledExecutorService singleThreadExecutor;
    private static AtomicBoolean tracking;

    static class 1 implements ActivityLifecycleCallbacks {
        1() {
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            ActivityLifecycleTracker.assertIsMainThread();
            ActivityLifecycleTracker.onActivityCreated(activity);
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
            ActivityLifecycleTracker.assertIsMainThread();
            ActivityLifecycleTracker.onActivityResumed(activity);
        }

        public void onActivityPaused(Activity activity) {
            ActivityLifecycleTracker.assertIsMainThread();
            ActivityLifecycleTracker.onActivityPaused(activity);
        }

        public void onActivityStopped(Activity activity) {
            AppEventsLogger.onContextStop();
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public void onActivityDestroyed(Activity activity) {
        }
    }

    static class 2 implements Runnable {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ long val$currentTime;

        2(Activity activity, long j) {
            this.val$activity = activity;
            this.val$currentTime = j;
        }

        public void run() {
            if (ActivityLifecycleTracker.currentSession == null) {
                Context applicationContext = this.val$activity.getApplicationContext();
                String activityName = Utility.getActivityName(this.val$activity);
                SessionInfo lastSession = SessionInfo.getStoredSessionInfo();
                if (lastSession != null) {
                    SessionLogger.logDeactivateApp(applicationContext, activityName, lastSession, ActivityLifecycleTracker.appId);
                }
                ActivityLifecycleTracker.currentSession = new SessionInfo(Long.valueOf(this.val$currentTime), null);
                SourceApplicationInfo sourceApplicationInfo = Factory.create(this.val$activity);
                ActivityLifecycleTracker.currentSession.setSourceApplicationInfo(sourceApplicationInfo);
                SessionLogger.logActivateApp(applicationContext, activityName, sourceApplicationInfo, ActivityLifecycleTracker.appId);
            }
        }
    }

    static class 3 implements Runnable {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ long val$currentTime;

        3(Activity activity, long j) {
            this.val$activity = activity;
            this.val$currentTime = j;
        }

        public void run() {
            Context applicationContext = this.val$activity.getApplicationContext();
            String activityName = Utility.getActivityName(this.val$activity);
            if (ActivityLifecycleTracker.currentSession == null) {
                ActivityLifecycleTracker.currentSession = new SessionInfo(Long.valueOf(this.val$currentTime), null);
                SessionLogger.logActivateApp(applicationContext, activityName, null, ActivityLifecycleTracker.appId);
            } else if (ActivityLifecycleTracker.currentSession.getSessionLastEventTime() != null) {
                long suspendTime = this.val$currentTime - ActivityLifecycleTracker.currentSession.getSessionLastEventTime().longValue();
                if (suspendTime > ((long) (ActivityLifecycleTracker.getSessionTimeoutInSeconds() * AdError.NETWORK_ERROR_CODE))) {
                    SessionLogger.logDeactivateApp(applicationContext, activityName, ActivityLifecycleTracker.currentSession, ActivityLifecycleTracker.appId);
                    SessionLogger.logActivateApp(applicationContext, activityName, null, ActivityLifecycleTracker.appId);
                    ActivityLifecycleTracker.currentSession = new SessionInfo(Long.valueOf(this.val$currentTime), null);
                } else if (suspendTime > ActivityLifecycleTracker.INTERRUPTION_THRESHOLD_MILLISECONDS) {
                    ActivityLifecycleTracker.currentSession.incrementInterruptionCount();
                }
            }
            ActivityLifecycleTracker.currentSession.setSessionLastEventTime(Long.valueOf(this.val$currentTime));
            ActivityLifecycleTracker.currentSession.writeSessionToDisk();
        }
    }

    static class 4 implements Runnable {
        final /* synthetic */ String val$activityName;
        final /* synthetic */ Context val$applicationContext;
        final /* synthetic */ long val$currentTime;

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                if (ActivityLifecycleTracker.foregroundActivityCount.get() <= 0) {
                    SessionLogger.logDeactivateApp(4.this.val$applicationContext, 4.this.val$activityName, ActivityLifecycleTracker.currentSession, ActivityLifecycleTracker.appId);
                    SessionInfo.clearSavedSessionFromDisk();
                    ActivityLifecycleTracker.currentSession = null;
                }
                ActivityLifecycleTracker.currentFuture = null;
            }
        }

        4(long j, Context context, String str) {
            this.val$currentTime = j;
            this.val$applicationContext = context;
            this.val$activityName = str;
        }

        public void run() {
            if (ActivityLifecycleTracker.currentSession == null) {
                ActivityLifecycleTracker.currentSession = new SessionInfo(Long.valueOf(this.val$currentTime), null);
            }
            ActivityLifecycleTracker.currentSession.setSessionLastEventTime(Long.valueOf(this.val$currentTime));
            if (ActivityLifecycleTracker.foregroundActivityCount.get() <= 0) {
                ActivityLifecycleTracker.currentFuture = ActivityLifecycleTracker.singleThreadExecutor.schedule(new 1(), (long) ActivityLifecycleTracker.getSessionTimeoutInSeconds(), TimeUnit.SECONDS);
            }
            ActivityLifecycleTracker.currentSession.writeSessionToDisk();
        }
    }

    static {
        TAG = ActivityLifecycleTracker.class.getCanonicalName();
        singleThreadExecutor = Executors.newSingleThreadScheduledExecutor();
        foregroundActivityCount = new AtomicInteger(0);
        tracking = new AtomicBoolean(false);
    }

    public static void startTracking(Application application, String appId) {
        if (tracking.compareAndSet(false, true)) {
            appId = appId;
            application.registerActivityLifecycleCallbacks(new 1());
        }
    }

    public static boolean isTracking() {
        return tracking.get();
    }

    public static UUID getCurrentSessionGuid() {
        return currentSession != null ? currentSession.getSessionId() : null;
    }

    public static void onActivityCreated(Activity activity) {
        singleThreadExecutor.execute(new 2(activity, System.currentTimeMillis()));
    }

    public static void onActivityResumed(Activity activity) {
        foregroundActivityCount.incrementAndGet();
        cancelCurrentTask();
        singleThreadExecutor.execute(new 3(activity, System.currentTimeMillis()));
    }

    private static void onActivityPaused(Activity activity) {
        if (foregroundActivityCount.decrementAndGet() < 0) {
            foregroundActivityCount.set(0);
            Log.w(TAG, INCORRECT_IMPL_WARNING);
        }
        cancelCurrentTask();
        singleThreadExecutor.execute(new 4(System.currentTimeMillis(), activity.getApplicationContext(), Utility.getActivityName(activity)));
    }

    private static int getSessionTimeoutInSeconds() {
        FetchedAppSettings settings = Utility.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if (settings == null) {
            return Constants.getDefaultAppEventsSessionTimeoutInSeconds();
        }
        return settings.getSessionTimeoutInSeconds();
    }

    private static void cancelCurrentTask() {
        if (currentFuture != null) {
            currentFuture.cancel(false);
        }
        currentFuture = null;
    }

    private static void assertIsMainThread() {
    }
}
