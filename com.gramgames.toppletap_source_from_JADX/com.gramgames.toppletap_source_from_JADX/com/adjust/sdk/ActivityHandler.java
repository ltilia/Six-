package com.adjust.sdk;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.google.android.gms.drive.DriveFile;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;

public class ActivityHandler extends HandlerThread implements IActivityHandler {
    private static final String ACTIVITY_STATE_NAME = "Activity state";
    private static final String ADJUST_PREFIX = "adjust_";
    private static final String ATTRIBUTION_NAME = "Attribution";
    private static long SESSION_INTERVAL = 0;
    private static long SUBSESSION_INTERVAL = 0;
    private static long TIMER_INTERVAL = 0;
    private static long TIMER_START = 0;
    private static final String TIME_TRAVEL = "Time travel!";
    private ActivityState activityState;
    private AdjustConfig adjustConfig;
    private AdjustAttribution attribution;
    private IAttributionHandler attributionHandler;
    private DeviceInfo deviceInfo;
    private boolean enabled;
    private ILogger logger;
    private boolean offline;
    private IPackageHandler packageHandler;
    private SessionHandler sessionHandler;
    private TimerCycle timer;

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            ActivityHandler.this.timerFired();
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ EventResponseData val$eventResponseData;

        2(EventResponseData eventResponseData) {
            this.val$eventResponseData = eventResponseData;
        }

        public void run() {
            ActivityHandler.this.adjustConfig.onEventTrackingSucceededListener.onFinishedEventTrackingSucceeded(this.val$eventResponseData.getSuccessResponseData());
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ EventResponseData val$eventResponseData;

        3(EventResponseData eventResponseData) {
            this.val$eventResponseData = eventResponseData;
        }

        public void run() {
            ActivityHandler.this.adjustConfig.onEventTrackingFailedListener.onFinishedEventTrackingFailed(this.val$eventResponseData.getFailureResponseData());
        }
    }

    class 4 implements Runnable {
        final /* synthetic */ SessionResponseData val$sessionResponseData;

        4(SessionResponseData sessionResponseData) {
            this.val$sessionResponseData = sessionResponseData;
        }

        public void run() {
            ActivityHandler.this.adjustConfig.onSessionTrackingSucceededListener.onFinishedSessionTrackingSucceeded(this.val$sessionResponseData.getSuccessResponseData());
        }
    }

    class 5 implements Runnable {
        final /* synthetic */ SessionResponseData val$sessionResponseData;

        5(SessionResponseData sessionResponseData) {
            this.val$sessionResponseData = sessionResponseData;
        }

        public void run() {
            ActivityHandler.this.adjustConfig.onSessionTrackingFailedListener.onFinishedSessionTrackingFailed(this.val$sessionResponseData.getFailureResponseData());
        }
    }

    class 6 implements Runnable {
        6() {
        }

        public void run() {
            ActivityHandler.this.adjustConfig.onAttributionChangedListener.onAttributionChanged(ActivityHandler.this.attribution);
        }
    }

    class 7 implements Runnable {
        final /* synthetic */ String val$deeplink;
        final /* synthetic */ Intent val$mapIntent;

        7(String str, Intent intent) {
            this.val$deeplink = str;
            this.val$mapIntent = intent;
        }

        public void run() {
            ActivityHandler.this.logger.info("Open deep link (%s)", this.val$deeplink);
            ActivityHandler.this.adjustConfig.context.startActivity(this.val$mapIntent);
        }
    }

    private class ReferrerClickTime {
        long clickTime;
        String referrer;

        ReferrerClickTime(String referrer, long clickTime) {
            this.referrer = referrer;
            this.clickTime = clickTime;
        }
    }

    private static final class SessionHandler extends Handler {
        private static final int ATTRIBUTION_TASKS = 72641;
        private static final int BASE_ADDRESS = 72630;
        private static final int DEEP_LINK = 72636;
        private static final int END = 72633;
        private static final int EVENT = 72634;
        private static final int EVENT_TASKS = 72635;
        private static final int INIT = 72631;
        private static final int SEND_REFERRER = 72637;
        private static final int SESSION_TASKS = 72640;
        private static final int START = 72632;
        private static final int TIMER_FIRED = 72639;
        private static final int UPDATE_HANDLERS_STATUS = 72638;
        private final WeakReference<ActivityHandler> sessionHandlerReference;

        protected SessionHandler(Looper looper, ActivityHandler sessionHandler) {
            super(looper);
            this.sessionHandlerReference = new WeakReference(sessionHandler);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            ActivityHandler sessionHandler = (ActivityHandler) this.sessionHandlerReference.get();
            if (sessionHandler != null) {
                switch (message.arg1) {
                    case INIT /*72631*/:
                        sessionHandler.initInternal();
                    case START /*72632*/:
                        sessionHandler.startInternal();
                    case END /*72633*/:
                        sessionHandler.endInternal();
                    case EVENT /*72634*/:
                        sessionHandler.trackEventInternal(message.obj);
                    case EVENT_TASKS /*72635*/:
                        sessionHandler.launchEventResponseTasksInternal(message.obj);
                    case DEEP_LINK /*72636*/:
                        UrlClickTime urlClickTime = message.obj;
                        sessionHandler.readOpenUrlInternal(urlClickTime.url, urlClickTime.clickTime);
                    case SEND_REFERRER /*72637*/:
                        ReferrerClickTime referrerClickTime = message.obj;
                        sessionHandler.sendReferrerInternal(referrerClickTime.referrer, referrerClickTime.clickTime);
                    case UPDATE_HANDLERS_STATUS /*72638*/:
                        sessionHandler.updateHandlersStatusInternal();
                    case TIMER_FIRED /*72639*/:
                        sessionHandler.timerFiredInternal();
                    case SESSION_TASKS /*72640*/:
                        sessionHandler.launchSessionResponseTasksInternal(message.obj);
                    case ATTRIBUTION_TASKS /*72641*/:
                        sessionHandler.launchAttributionResponseTasksInternal(message.obj);
                    default:
                }
            }
        }
    }

    private class UrlClickTime {
        long clickTime;
        Uri url;

        UrlClickTime(Uri url, long clickTime) {
            this.url = url;
            this.clickTime = clickTime;
        }
    }

    private ActivityHandler(AdjustConfig adjustConfig) {
        super(Constants.LOGTAG, 1);
        setDaemon(true);
        start();
        this.logger = AdjustFactory.getLogger();
        this.sessionHandler = new SessionHandler(getLooper(), this);
        this.enabled = true;
        init(adjustConfig);
        Message message = Message.obtain();
        message.arg1 = 72631;
        this.sessionHandler.sendMessage(message);
    }

    public void init(AdjustConfig adjustConfig) {
        this.adjustConfig = adjustConfig;
    }

    public static ActivityHandler getInstance(AdjustConfig adjustConfig) {
        if (adjustConfig == null) {
            AdjustFactory.getLogger().error("AdjustConfig missing", new Object[0]);
            return null;
        } else if (adjustConfig.isValid()) {
            if (adjustConfig.processName != null) {
                int currentPid = Process.myPid();
                ActivityManager manager = (ActivityManager) adjustConfig.context.getSystemService("activity");
                if (manager == null) {
                    return null;
                }
                for (RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
                    if (processInfo.pid == currentPid) {
                        if (!processInfo.processName.equalsIgnoreCase(adjustConfig.processName)) {
                            AdjustFactory.getLogger().info("Skipping initialization in background process (%s)", processInfo.processName);
                            return null;
                        }
                    }
                }
            }
            return new ActivityHandler(adjustConfig);
        } else {
            AdjustFactory.getLogger().error("AdjustConfig not initialized correctly", new Object[0]);
            return null;
        }
    }

    public void trackSubsessionStart() {
        Message message = Message.obtain();
        message.arg1 = 72632;
        this.sessionHandler.sendMessage(message);
    }

    public void trackSubsessionEnd() {
        Message message = Message.obtain();
        message.arg1 = 72633;
        this.sessionHandler.sendMessage(message);
    }

    public void trackEvent(AdjustEvent event) {
        if (this.activityState == null) {
            this.logger.warn("Event triggered before first application launch.\nThis will trigger the SDK start and an install without user interactionPlease check https://github.com/adjust/android_sdk#can-i-trigger-an-event-at-application-launch for more information.", new Object[0]);
            trackSubsessionStart();
        }
        Message message = Message.obtain();
        message.arg1 = 72634;
        message.obj = event;
        this.sessionHandler.sendMessage(message);
    }

    public void finishedTrackingActivity(ResponseData responseData) {
        if (responseData instanceof SessionResponseData) {
            this.attributionHandler.checkSessionResponse((SessionResponseData) responseData);
        } else if (responseData instanceof EventResponseData) {
            launchEventResponseTasks((EventResponseData) responseData);
        }
    }

    public void setEnabled(boolean enabled) {
        if (hasChangedState(isEnabled(), enabled, "Adjust already enabled", "Adjust already disabled")) {
            this.enabled = enabled;
            if (this.activityState == null) {
                trackSubsessionStart();
            } else {
                this.activityState.enabled = enabled;
                writeActivityState();
            }
            updateStatus(!enabled, "Pausing package handler and attribution handler to disable the SDK", "Package and attribution handler remain paused due to the SDK is offline", "Resuming package handler and attribution handler to enabled the SDK");
        }
    }

    private void updateStatus(boolean pausingState, String pausingMessage, String remainsPausedMessage, String unPausingMessage) {
        if (pausingState) {
            this.logger.info(pausingMessage, new Object[0]);
            trackSubsessionEnd();
        } else if (paused()) {
            this.logger.info(remainsPausedMessage, new Object[0]);
        } else {
            this.logger.info(unPausingMessage, new Object[0]);
            trackSubsessionStart();
        }
    }

    private boolean hasChangedState(boolean previousState, boolean newState, String trueMessage, String falseMessage) {
        if (previousState != newState) {
            return true;
        }
        if (previousState) {
            this.logger.debug(trueMessage, new Object[0]);
            return false;
        }
        this.logger.debug(falseMessage, new Object[0]);
        return false;
    }

    public void setOfflineMode(boolean offline) {
        if (hasChangedState(this.offline, offline, "Adjust already in offline mode", "Adjust already in online mode")) {
            this.offline = offline;
            if (this.activityState == null) {
                trackSubsessionStart();
            }
            updateStatus(offline, "Pausing package and attribution handler to put in offline mode", "Package and attribution handler remain paused because the SDK is disabled", "Resuming package handler and attribution handler to put in online mode");
        }
    }

    public boolean isEnabled() {
        if (this.activityState != null) {
            return this.activityState.enabled;
        }
        return this.enabled;
    }

    public void readOpenUrl(Uri url, long clickTime) {
        Message message = Message.obtain();
        message.arg1 = 72636;
        message.obj = new UrlClickTime(url, clickTime);
        this.sessionHandler.sendMessage(message);
    }

    public boolean updateAttribution(AdjustAttribution attribution) {
        if (attribution == null || attribution.equals(this.attribution)) {
            return false;
        }
        saveAttribution(attribution);
        return true;
    }

    private void saveAttribution(AdjustAttribution attribution) {
        this.attribution = attribution;
        writeAttribution();
    }

    public void setAskingAttribution(boolean askingAttribution) {
        this.activityState.askingAttribution = askingAttribution;
        writeActivityState();
    }

    public ActivityPackage getAttributionPackage() {
        return new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, System.currentTimeMillis()).buildAttributionPackage();
    }

    public void sendReferrer(String referrer, long clickTime) {
        Message message = Message.obtain();
        message.arg1 = 72637;
        message.obj = new ReferrerClickTime(referrer, clickTime);
        this.sessionHandler.sendMessage(message);
    }

    public void launchEventResponseTasks(EventResponseData eventResponseData) {
        Message message = Message.obtain();
        message.arg1 = 72635;
        message.obj = eventResponseData;
        this.sessionHandler.sendMessage(message);
    }

    public void launchSessionResponseTasks(SessionResponseData sessionResponseData) {
        Message message = Message.obtain();
        message.arg1 = 72640;
        message.obj = sessionResponseData;
        this.sessionHandler.sendMessage(message);
    }

    public void launchAttributionResponseTasks(AttributionResponseData attributionResponseData) {
        Message message = Message.obtain();
        message.arg1 = 72641;
        message.obj = attributionResponseData;
        this.sessionHandler.sendMessage(message);
    }

    private void updateHandlersStatus() {
        Message message = Message.obtain();
        message.arg1 = 72638;
        this.sessionHandler.sendMessage(message);
    }

    private void timerFired() {
        Message message = Message.obtain();
        message.arg1 = 72639;
        this.sessionHandler.sendMessage(message);
    }

    private void initInternal() {
        TIMER_INTERVAL = AdjustFactory.getTimerInterval();
        TIMER_START = AdjustFactory.getTimerStart();
        SESSION_INTERVAL = AdjustFactory.getSessionInterval();
        SUBSESSION_INTERVAL = AdjustFactory.getSubsessionInterval();
        this.deviceInfo = new DeviceInfo(this.adjustConfig.context, this.adjustConfig.sdkPrefix);
        if (AdjustConfig.ENVIRONMENT_PRODUCTION.equals(this.adjustConfig.environment)) {
            this.logger.setLogLevel(LogLevel.ASSERT);
        } else {
            this.logger.setLogLevel(this.adjustConfig.logLevel);
        }
        if (this.adjustConfig.eventBufferingEnabled.booleanValue()) {
            this.logger.info("Event buffering is enabled", new Object[0]);
        }
        if (Util.getPlayAdId(this.adjustConfig.context) == null) {
            this.logger.info("Unable to get Google Play Services Advertising ID at start time", new Object[0]);
        }
        if (this.adjustConfig.defaultTracker != null) {
            this.logger.info("Default tracker: '%s'", this.adjustConfig.defaultTracker);
        }
        if (this.adjustConfig.referrer != null) {
            sendReferrer(this.adjustConfig.referrer, this.adjustConfig.referrerClickTime);
        }
        readAttribution();
        readActivityState();
        this.packageHandler = AdjustFactory.getPackageHandler(this, this.adjustConfig.context, paused());
        this.attributionHandler = AdjustFactory.getAttributionHandler(this, getAttributionPackage(), paused(), this.adjustConfig.hasAttributionChangedListener());
        this.timer = new TimerCycle(new 1(), TIMER_START, TIMER_INTERVAL);
    }

    private void startInternal() {
        if (this.activityState == null || this.activityState.enabled) {
            updateHandlersStatusInternal();
            processSession();
            checkAttributionState();
            startTimer();
        }
    }

    private void processSession() {
        long now = System.currentTimeMillis();
        if (this.activityState == null) {
            this.activityState = new ActivityState();
            this.activityState.sessionCount = 1;
            transferSessionPackage(now);
            this.activityState.resetSessionAttributes(now);
            this.activityState.enabled = this.enabled;
            writeActivityState();
            return;
        }
        long lastInterval = now - this.activityState.lastActivity;
        if (lastInterval < 0) {
            this.logger.error(TIME_TRAVEL, new Object[0]);
            this.activityState.lastActivity = now;
            writeActivityState();
        } else if (lastInterval > SESSION_INTERVAL) {
            r4 = this.activityState;
            r4.sessionCount++;
            this.activityState.lastInterval = lastInterval;
            transferSessionPackage(now);
            this.activityState.resetSessionAttributes(now);
            writeActivityState();
        } else if (lastInterval > SUBSESSION_INTERVAL) {
            r4 = this.activityState;
            r4.subsessionCount++;
            r4 = this.activityState;
            r4.sessionLength += lastInterval;
            this.activityState.lastActivity = now;
            writeActivityState();
            this.logger.info("Started subsession %d of session %d", Integer.valueOf(this.activityState.subsessionCount), Integer.valueOf(this.activityState.sessionCount));
        }
    }

    private void checkAttributionState() {
        if (!checkActivityState(this.activityState) || this.activityState.subsessionCount <= 1) {
            return;
        }
        if (this.attribution == null || this.activityState.askingAttribution) {
            this.attributionHandler.getAttribution();
        }
    }

    private void endInternal() {
        this.packageHandler.pauseSending();
        this.attributionHandler.pauseSending();
        stopTimer();
        if (updateActivityState(System.currentTimeMillis())) {
            writeActivityState();
        }
    }

    private void trackEventInternal(AdjustEvent event) {
        if (checkActivityState(this.activityState) && isEnabled() && checkEvent(event)) {
            long now = System.currentTimeMillis();
            ActivityState activityState = this.activityState;
            activityState.eventCount++;
            updateActivityState(now);
            this.packageHandler.addPackage(new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, now).buildEventPackage(event));
            if (this.adjustConfig.eventBufferingEnabled.booleanValue()) {
                this.logger.info("Buffered event %s", eventPackage.getSuffix());
            } else {
                this.packageHandler.sendFirstPackage();
            }
            writeActivityState();
        }
    }

    private void launchEventResponseTasksInternal(EventResponseData eventResponseData) {
        Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        if (eventResponseData.success && this.adjustConfig.onEventTrackingSucceededListener != null) {
            this.logger.debug("Launching success event tracking listener", new Object[0]);
            handler.post(new 2(eventResponseData));
        } else if (!eventResponseData.success && this.adjustConfig.onEventTrackingFailedListener != null) {
            this.logger.debug("Launching failed event tracking listener", new Object[0]);
            handler.post(new 3(eventResponseData));
        }
    }

    private void launchSessionResponseTasksInternal(SessionResponseData sessionResponseData) {
        Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        if (updateAttribution(sessionResponseData.attribution)) {
            launchAttributionListener(handler);
        }
        launchSessionResponseListener(sessionResponseData, handler);
        launchDeeplink(sessionResponseData, handler);
    }

    private void launchSessionResponseListener(SessionResponseData sessionResponseData, Handler handler) {
        if (sessionResponseData.success && this.adjustConfig.onSessionTrackingSucceededListener != null) {
            this.logger.debug("Launching success session tracking listener", new Object[0]);
            handler.post(new 4(sessionResponseData));
        } else if (!sessionResponseData.success && this.adjustConfig.onSessionTrackingFailedListener != null) {
            this.logger.debug("Launching failed session tracking listener", new Object[0]);
            handler.post(new 5(sessionResponseData));
        }
    }

    private void launchAttributionResponseTasksInternal(AttributionResponseData responseData) {
        Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        if (updateAttribution(responseData.attribution)) {
            launchAttributionListener(handler);
        }
    }

    private void launchAttributionListener(Handler handler) {
        if (this.adjustConfig.onAttributionChangedListener != null) {
            handler.post(new 6());
        }
    }

    private void launchDeeplink(ResponseData responseData, Handler handler) {
        if (responseData.jsonResponse != null) {
            String deeplink = responseData.jsonResponse.optString("deeplink", null);
            if (deeplink != null) {
                Intent mapIntent;
                boolean isIntentSafe;
                Uri location = Uri.parse(deeplink);
                if (this.adjustConfig.deepLinkComponent == null) {
                    mapIntent = new Intent("android.intent.action.VIEW", location);
                } else {
                    mapIntent = new Intent("android.intent.action.VIEW", location, this.adjustConfig.context, this.adjustConfig.deepLinkComponent);
                }
                mapIntent.setFlags(DriveFile.MODE_READ_ONLY);
                mapIntent.setPackage(this.adjustConfig.context.getPackageName());
                if (this.adjustConfig.context.getPackageManager().queryIntentActivities(mapIntent, 0).size() > 0) {
                    isIntentSafe = true;
                } else {
                    isIntentSafe = false;
                }
                if (isIntentSafe) {
                    handler.post(new 7(deeplink, mapIntent));
                    return;
                }
                this.logger.error("Unable to open deep link (%s)", deeplink);
            }
        }
    }

    private void sendReferrerInternal(String referrer, long clickTime) {
        ActivityPackage clickPackage = buildQueryStringClickPackage(referrer, Constants.REFTAG, clickTime);
        if (clickPackage != null) {
            this.packageHandler.addPackage(clickPackage);
            this.packageHandler.sendFirstPackage();
        }
    }

    private void readOpenUrlInternal(Uri url, long clickTime) {
        if (url != null) {
            ActivityPackage clickPackage = buildQueryStringClickPackage(url.getQuery(), "deeplink", clickTime);
            if (clickPackage != null) {
                this.packageHandler.addPackage(clickPackage);
                this.packageHandler.sendFirstPackage();
            }
        }
    }

    private ActivityPackage buildQueryStringClickPackage(String queryString, String source, long clickTime) {
        if (queryString == null) {
            return null;
        }
        Map<String, String> queryStringParameters = new LinkedHashMap();
        AdjustAttribution queryStringAttribution = new AdjustAttribution();
        boolean hasAdjustTags = false;
        this.logger.verbose("Reading query string (%s) from %s", queryString, source);
        for (String pair : queryString.split("&")) {
            if (readQueryString(pair, queryStringParameters, queryStringAttribution)) {
                hasAdjustTags = true;
            }
        }
        if (!hasAdjustTags) {
            return null;
        }
        String reftag = (String) queryStringParameters.remove(Constants.REFTAG);
        PackageBuilder builder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, System.currentTimeMillis());
        builder.extraParameters = queryStringParameters;
        builder.attribution = queryStringAttribution;
        builder.reftag = reftag;
        if (source == Constants.REFTAG) {
            builder.referrer = queryString;
        }
        return builder.buildClickPackage(source, clickTime);
    }

    private boolean readQueryString(String queryString, Map<String, String> extraParameters, AdjustAttribution queryStringAttribution) {
        String[] pairComponents = queryString.split("=");
        if (pairComponents.length != 2) {
            return false;
        }
        String key = pairComponents[0];
        if (!key.startsWith(ADJUST_PREFIX)) {
            return false;
        }
        String value = pairComponents[1];
        if (value.length() == 0) {
            return false;
        }
        String keyWOutPrefix = key.substring(ADJUST_PREFIX.length());
        if (keyWOutPrefix.length() == 0) {
            return false;
        }
        if (!trySetAttribution(queryStringAttribution, keyWOutPrefix, value)) {
            extraParameters.put(keyWOutPrefix, value);
        }
        return true;
    }

    private boolean trySetAttribution(AdjustAttribution queryStringAttribution, String key, String value) {
        if (key.equals("tracker")) {
            queryStringAttribution.trackerName = value;
            return true;
        } else if (key.equals("campaign")) {
            queryStringAttribution.campaign = value;
            return true;
        } else if (key.equals("adgroup")) {
            queryStringAttribution.adgroup = value;
            return true;
        } else if (!key.equals("creative")) {
            return false;
        } else {
            queryStringAttribution.creative = value;
            return true;
        }
    }

    private void updateHandlersStatusInternal() {
        updateAttributionHandlerStatus();
        updatePackageHandlerStatus();
    }

    private void updateAttributionHandlerStatus() {
        if (paused()) {
            this.attributionHandler.pauseSending();
        } else {
            this.attributionHandler.resumeSending();
        }
    }

    private void updatePackageHandlerStatus() {
        if (paused()) {
            this.packageHandler.pauseSending();
        } else {
            this.packageHandler.resumeSending();
        }
    }

    private boolean updateActivityState(long now) {
        if (!checkActivityState(this.activityState)) {
            return false;
        }
        long lastInterval = now - this.activityState.lastActivity;
        if (lastInterval > SESSION_INTERVAL) {
            return false;
        }
        this.activityState.lastActivity = now;
        if (lastInterval < 0) {
            this.logger.error(TIME_TRAVEL, new Object[0]);
        } else {
            ActivityState activityState = this.activityState;
            activityState.sessionLength += lastInterval;
            activityState = this.activityState;
            activityState.timeSpent += lastInterval;
        }
        return true;
    }

    public static boolean deleteActivityState(Context context) {
        return context.deleteFile(Constants.ACTIVITY_STATE_FILENAME);
    }

    public static boolean deleteAttribution(Context context) {
        return context.deleteFile(Constants.ATTRIBUTION_FILENAME);
    }

    private void transferSessionPackage(long now) {
        this.packageHandler.addPackage(new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, now).buildSessionPackage());
        this.packageHandler.sendFirstPackage();
    }

    private void startTimer() {
        if (!paused()) {
            this.timer.start();
        }
    }

    private void stopTimer() {
        this.timer.suspend();
    }

    private void timerFiredInternal() {
        if (paused()) {
            stopTimer();
            return;
        }
        this.logger.debug("Session timer fired", new Object[0]);
        this.packageHandler.sendFirstPackage();
        if (updateActivityState(System.currentTimeMillis())) {
            writeActivityState();
        }
    }

    private void readActivityState() {
        try {
            this.activityState = (ActivityState) Util.readObject(this.adjustConfig.context, Constants.ACTIVITY_STATE_FILENAME, ACTIVITY_STATE_NAME, ActivityState.class);
        } catch (Exception e) {
            this.logger.error("Failed to read %s file (%s)", ACTIVITY_STATE_NAME, e.getMessage());
            this.activityState = null;
        }
    }

    private void readAttribution() {
        try {
            this.attribution = (AdjustAttribution) Util.readObject(this.adjustConfig.context, Constants.ATTRIBUTION_FILENAME, ATTRIBUTION_NAME, AdjustAttribution.class);
        } catch (Exception e) {
            this.logger.error("Failed to read %s file (%s)", ATTRIBUTION_NAME, e.getMessage());
            this.attribution = null;
        }
    }

    private synchronized void writeActivityState() {
        Util.writeObject(this.activityState, this.adjustConfig.context, Constants.ACTIVITY_STATE_FILENAME, ACTIVITY_STATE_NAME);
    }

    private void writeAttribution() {
        Util.writeObject(this.attribution, this.adjustConfig.context, Constants.ATTRIBUTION_FILENAME, ATTRIBUTION_NAME);
    }

    private boolean checkEvent(AdjustEvent event) {
        if (event == null) {
            this.logger.error("Event missing", new Object[0]);
            return false;
        } else if (event.isValid()) {
            return true;
        } else {
            this.logger.error("Event not initialized correctly", new Object[0]);
            return false;
        }
    }

    private boolean checkActivityState(ActivityState activityState) {
        if (activityState != null) {
            return true;
        }
        this.logger.error("Missing activity state.", new Object[0]);
        return false;
    }

    private boolean paused() {
        return this.offline || !isEnabled();
    }
}
