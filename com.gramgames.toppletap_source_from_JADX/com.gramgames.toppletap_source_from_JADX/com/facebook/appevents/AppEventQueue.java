package com.facebook.appevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger.FlushBehavior;
import com.facebook.internal.Logger;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.FetchedAppSettings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;

class AppEventQueue {
    private static final int FLUSH_PERIOD_IN_SECONDS = 15;
    private static final int NUM_LOG_EVENTS_TO_TRY_TO_FLUSH_AFTER = 100;
    private static final String TAG;
    private static volatile AppEventCollection appEventCollection;
    private static final Runnable flushRunnable;
    private static ScheduledFuture scheduledFuture;
    private static final ScheduledExecutorService singleThreadExecutor;

    static class 1 implements Runnable {
        1() {
        }

        public void run() {
            AppEventQueue.scheduledFuture = null;
            if (AppEventsLogger.getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
                AppEventQueue.flushAndWait(FlushReason.TIMER);
            }
        }
    }

    static class 2 implements Runnable {
        2() {
        }

        public void run() {
            AppEventStore.persistEvents(AppEventQueue.appEventCollection);
            AppEventQueue.appEventCollection = new AppEventCollection();
        }
    }

    static class 3 implements Runnable {
        final /* synthetic */ FlushReason val$reason;

        3(FlushReason flushReason) {
            this.val$reason = flushReason;
        }

        public void run() {
            AppEventQueue.flushAndWait(this.val$reason);
        }
    }

    static class 4 implements Runnable {
        final /* synthetic */ AccessTokenAppIdPair val$accessTokenAppId;
        final /* synthetic */ AppEvent val$appEvent;

        4(AccessTokenAppIdPair accessTokenAppIdPair, AppEvent appEvent) {
            this.val$accessTokenAppId = accessTokenAppIdPair;
            this.val$appEvent = appEvent;
        }

        public void run() {
            AppEventQueue.appEventCollection.addEvent(this.val$accessTokenAppId, this.val$appEvent);
            if (AppEventsLogger.getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY && AppEventQueue.appEventCollection.getEventCount() > AppEventQueue.NUM_LOG_EVENTS_TO_TRY_TO_FLUSH_AFTER) {
                AppEventQueue.flushAndWait(FlushReason.EVENT_THRESHOLD);
            } else if (AppEventQueue.scheduledFuture == null) {
                AppEventQueue.scheduledFuture = AppEventQueue.singleThreadExecutor.schedule(AppEventQueue.flushRunnable, 15, TimeUnit.SECONDS);
            }
        }
    }

    static class 5 implements Callback {
        final /* synthetic */ AccessTokenAppIdPair val$accessTokenAppId;
        final /* synthetic */ SessionEventsState val$appEvents;
        final /* synthetic */ FlushStatistics val$flushState;
        final /* synthetic */ GraphRequest val$postRequest;

        5(AccessTokenAppIdPair accessTokenAppIdPair, GraphRequest graphRequest, SessionEventsState sessionEventsState, FlushStatistics flushStatistics) {
            this.val$accessTokenAppId = accessTokenAppIdPair;
            this.val$postRequest = graphRequest;
            this.val$appEvents = sessionEventsState;
            this.val$flushState = flushStatistics;
        }

        public void onCompleted(GraphResponse response) {
            AppEventQueue.handleResponse(this.val$accessTokenAppId, this.val$postRequest, response, this.val$appEvents, this.val$flushState);
        }
    }

    static class 6 implements Runnable {
        final /* synthetic */ AccessTokenAppIdPair val$accessTokenAppId;
        final /* synthetic */ SessionEventsState val$appEvents;

        6(AccessTokenAppIdPair accessTokenAppIdPair, SessionEventsState sessionEventsState) {
            this.val$accessTokenAppId = accessTokenAppIdPair;
            this.val$appEvents = sessionEventsState;
        }

        public void run() {
            AppEventStore.persistEvents(this.val$accessTokenAppId, this.val$appEvents);
        }
    }

    AppEventQueue() {
    }

    static {
        TAG = AppEventQueue.class.getName();
        appEventCollection = new AppEventCollection();
        singleThreadExecutor = Executors.newSingleThreadScheduledExecutor();
        flushRunnable = new 1();
    }

    public static void persistToDisk() {
        singleThreadExecutor.execute(new 2());
    }

    public static void flush(FlushReason reason) {
        singleThreadExecutor.execute(new 3(reason));
    }

    public static void add(AccessTokenAppIdPair accessTokenAppId, AppEvent appEvent) {
        singleThreadExecutor.execute(new 4(accessTokenAppId, appEvent));
    }

    public static Set<AccessTokenAppIdPair> getKeySet() {
        return appEventCollection.keySet();
    }

    static void flushAndWait(FlushReason reason) {
        appEventCollection.addPersistedEvents(AppEventStore.readAndClearStore());
        try {
            FlushStatistics flushResults = sendEventsToServer(reason, appEventCollection);
            if (flushResults != null) {
                Intent intent = new Intent(AppEventsLogger.ACTION_APP_EVENTS_FLUSHED);
                intent.putExtra(AppEventsLogger.APP_EVENTS_EXTRA_NUM_EVENTS_FLUSHED, flushResults.numEvents);
                intent.putExtra(AppEventsLogger.APP_EVENTS_EXTRA_FLUSH_RESULT, flushResults.result);
                LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()).sendBroadcast(intent);
            }
        } catch (Exception e) {
            Log.w(TAG, "Caught unexpected exception while flushing app events: ", e);
        }
    }

    private static FlushStatistics sendEventsToServer(FlushReason reason, AppEventCollection appEventCollection) {
        FlushStatistics flushResults = new FlushStatistics();
        boolean limitEventUsage = FacebookSdk.getLimitEventAndDataUsage(FacebookSdk.getApplicationContext());
        List<GraphRequest> requestsToExecute = new ArrayList();
        for (AccessTokenAppIdPair accessTokenAppId : appEventCollection.keySet()) {
            GraphRequest request = buildRequestForSession(accessTokenAppId, appEventCollection.get(accessTokenAppId), limitEventUsage, flushResults);
            if (request != null) {
                requestsToExecute.add(request);
            }
        }
        if (requestsToExecute.size() <= 0) {
            return null;
        }
        Logger.log(LoggingBehavior.APP_EVENTS, TAG, "Flushing %d events due to %s.", Integer.valueOf(flushResults.numEvents), reason.toString());
        for (GraphRequest request2 : requestsToExecute) {
            request2.executeAndWait();
        }
        return flushResults;
    }

    private static GraphRequest buildRequestForSession(AccessTokenAppIdPair accessTokenAppId, SessionEventsState appEvents, boolean limitEventUsage, FlushStatistics flushState) {
        FetchedAppSettings fetchedAppSettings = Utility.queryAppSettings(accessTokenAppId.getApplicationId(), false);
        GraphRequest postRequest = GraphRequest.newPostRequest(null, String.format("%s/activities", new Object[]{applicationId}), null, null);
        Bundle requestParameters = postRequest.getParameters();
        if (requestParameters == null) {
            requestParameters = new Bundle();
        }
        requestParameters.putString(ServerProtocol.DIALOG_PARAM_ACCESS_TOKEN, accessTokenAppId.getAccessTokenString());
        String pushNotificationsRegistrationId = AppEventsLogger.getPushNotificationsRegistrationId();
        if (pushNotificationsRegistrationId != null) {
            requestParameters.putString("device_token", pushNotificationsRegistrationId);
        }
        postRequest.setParameters(requestParameters);
        if (fetchedAppSettings == null) {
            return null;
        }
        int numEvents = appEvents.populateRequest(postRequest, FacebookSdk.getApplicationContext(), fetchedAppSettings.supportsImplicitLogging(), limitEventUsage);
        if (numEvents == 0) {
            return null;
        }
        flushState.numEvents += numEvents;
        postRequest.setCallback(new 5(accessTokenAppId, postRequest, appEvents, flushState));
        return postRequest;
    }

    private static void handleResponse(AccessTokenAppIdPair accessTokenAppId, GraphRequest request, GraphResponse response, SessionEventsState appEvents, FlushStatistics flushState) {
        FacebookRequestError error = response.getError();
        String resultDescription = "Success";
        FlushResult flushResult = FlushResult.SUCCESS;
        if (error != null) {
            if (error.getErrorCode() == -1) {
                resultDescription = "Failed: No Connectivity";
                flushResult = FlushResult.NO_CONNECTIVITY;
            } else {
                resultDescription = String.format("Failed:\n  Response: %s\n  Error %s", new Object[]{response.toString(), error.toString()});
                flushResult = FlushResult.SERVER_ERROR;
            }
        }
        if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.APP_EVENTS)) {
            String prettyPrintedEvents;
            try {
                prettyPrintedEvents = new JSONArray((String) request.getTag()).toString(2);
            } catch (JSONException e) {
                prettyPrintedEvents = "<Can't encode events for debug logging>";
            }
            Logger.log(LoggingBehavior.APP_EVENTS, TAG, "Flush completed\nParams: %s\n  Result: %s\n  Events JSON: %s", request.getGraphObject().toString(), resultDescription, prettyPrintedEvents);
        }
        appEvents.clearInFlightAndStats(error != null);
        if (flushResult == FlushResult.NO_CONNECTIVITY) {
            FacebookSdk.getExecutor().execute(new 6(accessTokenAppId, appEvents));
        }
        if (flushResult != FlushResult.SUCCESS && flushState.result != FlushResult.NO_CONNECTIVITY) {
            flushState.result = flushResult;
        }
    }
}
