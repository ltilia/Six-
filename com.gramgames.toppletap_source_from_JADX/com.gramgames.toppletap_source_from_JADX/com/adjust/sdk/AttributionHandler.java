package com.adjust.sdk;

import android.net.Uri;
import android.net.Uri.Builder;
import com.mopub.common.Constants;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AttributionHandler implements IAttributionHandler {
    private IActivityHandler activityHandler;
    private ActivityPackage attributionPackage;
    private boolean hasListener;
    private ILogger logger;
    private boolean paused;
    private ScheduledExecutorService scheduler;
    private TimerOnce timer;

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            AttributionHandler.this.getAttributionInternal();
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ SessionResponseData val$sessionResponseData;

        2(SessionResponseData sessionResponseData) {
            this.val$sessionResponseData = sessionResponseData;
        }

        public void run() {
            AttributionHandler.this.checkSessionResponseInternal(this.val$sessionResponseData);
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ AttributionResponseData val$attributionResponseData;

        3(AttributionResponseData attributionResponseData) {
            this.val$attributionResponseData = attributionResponseData;
        }

        public void run() {
            AttributionHandler.this.checkAttributionResponseInternal(this.val$attributionResponseData);
        }
    }

    public AttributionHandler(IActivityHandler activityHandler, ActivityPackage attributionPackage, boolean startPaused, boolean hasListener) {
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.logger = AdjustFactory.getLogger();
        if (this.scheduler != null) {
            this.timer = new TimerOnce(this.scheduler, new 1());
        } else {
            this.logger.error("Timer not initialized, attribution handler is disabled", new Object[0]);
        }
        init(activityHandler, attributionPackage, startPaused, hasListener);
    }

    public void init(IActivityHandler activityHandler, ActivityPackage attributionPackage, boolean startPaused, boolean hasListener) {
        this.activityHandler = activityHandler;
        this.attributionPackage = attributionPackage;
        this.paused = startPaused;
        this.hasListener = hasListener;
    }

    public void getAttribution() {
        getAttribution(0);
    }

    public void checkSessionResponse(SessionResponseData sessionResponseData) {
        this.scheduler.submit(new 2(sessionResponseData));
    }

    private void checkAttributionResponse(AttributionResponseData attributionResponseData) {
        this.scheduler.submit(new 3(attributionResponseData));
    }

    public void pauseSending() {
        this.paused = true;
    }

    public void resumeSending() {
        this.paused = false;
    }

    private void getAttribution(long delayInMilliseconds) {
        if (this.timer.getFireIn() <= delayInMilliseconds) {
            if (delayInMilliseconds != 0) {
                this.logger.debug("Waiting to query attribution in %d milliseconds", Long.valueOf(delayInMilliseconds));
            }
            this.timer.startIn(delayInMilliseconds);
        }
    }

    private void checkAttributionInternal(ResponseData responseData) {
        if (responseData.jsonResponse != null) {
            long timerMilliseconds = responseData.jsonResponse.optLong("ask_in", -1);
            if (timerMilliseconds >= 0) {
                this.activityHandler.setAskingAttribution(true);
                getAttribution(timerMilliseconds);
                return;
            }
            this.activityHandler.setAskingAttribution(false);
            responseData.attribution = AdjustAttribution.fromJson(responseData.jsonResponse.optJSONObject("attribution"));
        }
    }

    private void checkSessionResponseInternal(SessionResponseData sessionResponseData) {
        checkAttributionInternal(sessionResponseData);
        this.activityHandler.launchSessionResponseTasks(sessionResponseData);
    }

    private void checkAttributionResponseInternal(AttributionResponseData attributionResponseData) {
        checkAttributionInternal(attributionResponseData);
        this.activityHandler.launchAttributionResponseTasks(attributionResponseData);
    }

    private void getAttributionInternal() {
        if (!this.hasListener) {
            return;
        }
        if (this.paused) {
            this.logger.debug("Attribution handler is paused", new Object[0]);
            return;
        }
        this.logger.verbose("%s", this.attributionPackage.getExtendedString());
        try {
            ResponseData responseData = Util.readHttpResponse(Util.createGETHttpsURLConnection(buildUri(this.attributionPackage.getPath(), this.attributionPackage.getParameters()).toString(), this.attributionPackage.getClientSdk()), this.attributionPackage);
            if (responseData instanceof AttributionResponseData) {
                checkAttributionResponse((AttributionResponseData) responseData);
            }
        } catch (Exception e) {
            this.logger.error("Failed to get attribution (%s)", e.getMessage());
        }
    }

    private Uri buildUri(String path, Map<String, String> parameters) {
        Builder uriBuilder = new Builder();
        uriBuilder.scheme(Constants.HTTPS);
        uriBuilder.authority(Constants.AUTHORITY);
        uriBuilder.appendPath(path);
        for (Entry<String, String> entry : parameters.entrySet()) {
            uriBuilder.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        return uriBuilder.build();
    }
}
