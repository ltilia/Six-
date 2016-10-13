package com.adjust.sdk;

import android.content.Context;

public class AdjustConfig {
    public static final String ENVIRONMENT_PRODUCTION = "production";
    public static final String ENVIRONMENT_SANDBOX = "sandbox";
    String appToken;
    Context context;
    Class deepLinkComponent;
    String defaultTracker;
    Boolean deviceKnown;
    String environment;
    Boolean eventBufferingEnabled;
    LogLevel logLevel;
    OnAttributionChangedListener onAttributionChangedListener;
    OnEventTrackingFailedListener onEventTrackingFailedListener;
    OnEventTrackingSucceededListener onEventTrackingSucceededListener;
    OnSessionTrackingFailedListener onSessionTrackingFailedListener;
    OnSessionTrackingSucceededListener onSessionTrackingSucceededListener;
    String processName;
    String referrer;
    long referrerClickTime;
    String sdkPrefix;

    public AdjustConfig(Context context, String appToken, String environment) {
        if (isValid(context, appToken, environment)) {
            this.context = context.getApplicationContext();
            this.appToken = appToken;
            this.environment = environment;
            this.logLevel = LogLevel.INFO;
            this.eventBufferingEnabled = Boolean.valueOf(false);
        }
    }

    public void setEventBufferingEnabled(Boolean eventBufferingEnabled) {
        this.eventBufferingEnabled = eventBufferingEnabled;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void setSdkPrefix(String sdkPrefix) {
        this.sdkPrefix = sdkPrefix;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public void setDefaultTracker(String defaultTracker) {
        this.defaultTracker = defaultTracker;
    }

    public void setOnAttributionChangedListener(OnAttributionChangedListener onAttributionChangedListener) {
        this.onAttributionChangedListener = onAttributionChangedListener;
    }

    public void setDeviceKnown(boolean deviceKnown) {
        this.deviceKnown = Boolean.valueOf(deviceKnown);
    }

    public void setDeepLinkComponent(Class deepLinkComponent) {
        this.deepLinkComponent = deepLinkComponent;
    }

    public void setOnEventTrackingSucceededListener(OnEventTrackingSucceededListener onEventTrackingSucceededListener) {
        this.onEventTrackingSucceededListener = onEventTrackingSucceededListener;
    }

    public void setOnEventTrackingFailedListener(OnEventTrackingFailedListener onEventTrackingFailedListener) {
        this.onEventTrackingFailedListener = onEventTrackingFailedListener;
    }

    public void setOnSessionTrackingSucceededListener(OnSessionTrackingSucceededListener onSessionTrackingSucceededListener) {
        this.onSessionTrackingSucceededListener = onSessionTrackingSucceededListener;
    }

    public void setOnSessionTrackingFailedListener(OnSessionTrackingFailedListener onSessionTrackingFailedListener) {
        this.onSessionTrackingFailedListener = onSessionTrackingFailedListener;
    }

    public boolean hasAttributionChangedListener() {
        return this.onAttributionChangedListener != null;
    }

    public boolean hasListener() {
        return (this.onAttributionChangedListener == null && this.onEventTrackingSucceededListener == null && this.onEventTrackingFailedListener == null && this.onSessionTrackingSucceededListener == null && this.onSessionTrackingFailedListener == null) ? false : true;
    }

    public boolean isValid() {
        return this.appToken != null;
    }

    private boolean isValid(Context context, String appToken, String environment) {
        if (checkAppToken(appToken) && checkEnvironment(environment) && checkContext(context)) {
            return true;
        }
        return false;
    }

    private static boolean checkContext(Context context) {
        ILogger logger = AdjustFactory.getLogger();
        if (context == null) {
            logger.error("Missing context", new Object[0]);
            return false;
        } else if (Util.checkPermission(context, "android.permission.INTERNET")) {
            return true;
        } else {
            logger.error("Missing permission: INTERNET", new Object[0]);
            return false;
        }
    }

    private static boolean checkAppToken(String appToken) {
        ILogger logger = AdjustFactory.getLogger();
        if (appToken == null) {
            logger.error("Missing App Token", new Object[0]);
            return false;
        } else if (appToken.length() == 12) {
            return true;
        } else {
            logger.error("Malformed App Token '%s'", appToken);
            return false;
        }
    }

    private static boolean checkEnvironment(String environment) {
        ILogger logger = AdjustFactory.getLogger();
        if (environment == null) {
            logger.error("Missing environment", new Object[0]);
            return false;
        } else if (environment.equals(ENVIRONMENT_SANDBOX)) {
            logger.Assert("SANDBOX: Adjust is running in Sandbox mode. Use this setting for testing. Don't forget to set the environment to `production` before publishing!", new Object[0]);
            return true;
        } else if (environment.equals(ENVIRONMENT_PRODUCTION)) {
            logger.Assert("PRODUCTION: Adjust is running in Production mode. Use this setting only for the build that you want to publish. Set the environment to `sandbox` if you want to test your app!", new Object[0]);
            return true;
        } else {
            logger.error("Unknown environment '%s'", environment);
            return false;
        }
    }
}
