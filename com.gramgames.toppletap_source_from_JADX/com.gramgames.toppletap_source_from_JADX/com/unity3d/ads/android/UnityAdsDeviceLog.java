package com.unity3d.ads.android;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import org.json.simple.parser.Yytoken;

public class UnityAdsDeviceLog {
    public static final int LOGLEVEL_DEBUG = 8;
    private static final int LOGLEVEL_ERROR = 1;
    public static final int LOGLEVEL_INFO = 4;
    private static final int LOGLEVEL_WARNING = 2;
    private static boolean LOG_DEBUG;
    private static boolean LOG_ERROR;
    private static boolean LOG_INFO;
    private static boolean LOG_WARNING;
    private static final HashMap _deviceLogLevel;
    private static UnityAdsShowMsg _previousMsg;
    private static HashMap _showStatusMessages;

    /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$unity3d$ads$android$UnityAdsDeviceLog$UnityAdsLogLevel;

        static {
            $SwitchMap$com$unity3d$ads$android$UnityAdsDeviceLog$UnityAdsLogLevel = new int[UnityAdsLogLevel.values().length];
            try {
                $SwitchMap$com$unity3d$ads$android$UnityAdsDeviceLog$UnityAdsLogLevel[UnityAdsLogLevel.INFO.ordinal()] = UnityAdsDeviceLog.LOGLEVEL_ERROR;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$UnityAdsDeviceLog$UnityAdsLogLevel[UnityAdsLogLevel.DEBUG.ordinal()] = UnityAdsDeviceLog.LOGLEVEL_WARNING;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$UnityAdsDeviceLog$UnityAdsLogLevel[UnityAdsLogLevel.WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$UnityAdsDeviceLog$UnityAdsLogLevel[UnityAdsLogLevel.ERROR.ordinal()] = UnityAdsDeviceLog.LOGLEVEL_INFO;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public enum UnityAdsLogLevel {
        INFO,
        DEBUG,
        WARNING,
        ERROR
    }

    public enum UnityAdsShowMsg {
        READY,
        NOT_INITIALIZED,
        WEBAPP_NOT_INITIALIZED,
        SHOWING_ADS,
        NO_INTERNET,
        NO_ADS,
        ZERO_ADS,
        VIDEO_NOT_CACHED
    }

    static {
        LOG_ERROR = true;
        LOG_WARNING = true;
        LOG_DEBUG = false;
        LOG_INFO = true;
        HashMap hashMap = new HashMap();
        _deviceLogLevel = hashMap;
        if (hashMap.size() == 0) {
            _deviceLogLevel.put(UnityAdsLogLevel.INFO, new UnityAdsDeviceLogLevel("i"));
            _deviceLogLevel.put(UnityAdsLogLevel.DEBUG, new UnityAdsDeviceLogLevel("d"));
            _deviceLogLevel.put(UnityAdsLogLevel.WARNING, new UnityAdsDeviceLogLevel("w"));
            _deviceLogLevel.put(UnityAdsLogLevel.ERROR, new UnityAdsDeviceLogLevel("e"));
        }
    }

    public static void setLogLevel(int i) {
        if (i >= LOGLEVEL_DEBUG) {
            LOG_ERROR = true;
            LOG_WARNING = true;
            LOG_INFO = true;
            LOG_DEBUG = true;
        } else if (i >= LOGLEVEL_INFO) {
            LOG_ERROR = true;
            LOG_WARNING = true;
            LOG_INFO = true;
            LOG_DEBUG = false;
        } else if (i >= LOGLEVEL_WARNING) {
            LOG_ERROR = true;
            LOG_WARNING = true;
            LOG_INFO = false;
            LOG_DEBUG = false;
        } else if (i > 0) {
            LOG_ERROR = true;
            LOG_WARNING = false;
            LOG_INFO = false;
            LOG_DEBUG = false;
        } else {
            LOG_ERROR = false;
            LOG_WARNING = false;
            LOG_INFO = false;
            LOG_DEBUG = false;
        }
    }

    public static void entered() {
        debug("ENTERED METHOD");
    }

    public static void info(String str) {
        write(UnityAdsLogLevel.INFO, checkMessage(str));
    }

    public static void info(String str, Object... objArr) {
        info(String.format(Locale.US, str, objArr));
    }

    public static void debug(String str) {
        while (str.length() > 3072) {
            debug(str.substring(0, 3072));
            if (str.length() < 30720) {
                str = str.substring(3072);
            } else {
                return;
            }
        }
        write(UnityAdsLogLevel.DEBUG, checkMessage(str));
    }

    public static void debug(String str, Object... objArr) {
        debug(String.format(Locale.US, str, objArr));
    }

    public static void warning(String str) {
        write(UnityAdsLogLevel.WARNING, checkMessage(str));
    }

    public static void warning(String str, Object... objArr) {
        warning(String.format(Locale.US, str, objArr));
    }

    public static void error(String str) {
        write(UnityAdsLogLevel.ERROR, checkMessage(str));
    }

    public static void error(String str, Object... objArr) {
        error(String.format(Locale.US, str, objArr));
    }

    private static void write(UnityAdsLogLevel unityAdsLogLevel, String str) {
        boolean z = true;
        switch (1.$SwitchMap$com$unity3d$ads$android$UnityAdsDeviceLog$UnityAdsLogLevel[unityAdsLogLevel.ordinal()]) {
            case LOGLEVEL_ERROR /*1*/:
                z = LOG_INFO;
                break;
            case LOGLEVEL_WARNING /*2*/:
                z = LOG_DEBUG;
                break;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                z = LOG_WARNING;
                break;
            case LOGLEVEL_INFO /*4*/:
                z = LOG_ERROR;
                break;
        }
        if (z) {
            writeToLog(createLogEntry(unityAdsLogLevel, str));
        }
    }

    private static String checkMessage(String str) {
        if (str == null || str.length() == 0) {
            return "DO NOT USE EMPTY MESSAGES, use UnityAdsDeviceLog.entered() instead";
        }
        return str;
    }

    private static UnityAdsDeviceLogLevel getLogLevel(UnityAdsLogLevel unityAdsLogLevel) {
        return (UnityAdsDeviceLogLevel) _deviceLogLevel.get(unityAdsLogLevel);
    }

    private static UnityAdsDeviceLogEntry createLogEntry(UnityAdsLogLevel unityAdsLogLevel, String str) {
        Object obj = null;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        UnityAdsDeviceLogLevel logLevel = getLogLevel(unityAdsLogLevel);
        if (logLevel != null) {
            StackTraceElement stackTraceElement;
            int i = 0;
            while (i < stackTrace.length) {
                StackTraceElement stackTraceElement2 = stackTrace[i];
                if (stackTraceElement2.getClassName().equals(UnityAdsDeviceLog.class.getName())) {
                    obj = LOGLEVEL_ERROR;
                }
                if (!stackTraceElement2.getClassName().equals(UnityAdsDeviceLog.class.getName()) && r0 != null) {
                    break;
                }
                i += LOGLEVEL_ERROR;
            }
            if (i < stackTrace.length) {
                stackTraceElement = stackTrace[i];
            } else {
                stackTraceElement = null;
            }
            if (stackTraceElement != null) {
                return new UnityAdsDeviceLogEntry(logLevel, str, stackTraceElement);
            }
        }
        return null;
    }

    private static void writeToLog(UnityAdsDeviceLogEntry unityAdsDeviceLogEntry) {
        Method method = null;
        if (unityAdsDeviceLogEntry != null && unityAdsDeviceLogEntry.getLogLevel() != null) {
            try {
                String receivingMethodName = unityAdsDeviceLogEntry.getLogLevel().getReceivingMethodName();
                Class[] clsArr = new Class[LOGLEVEL_WARNING];
                clsArr[0] = String.class;
                clsArr[LOGLEVEL_ERROR] = String.class;
                method = Log.class.getMethod(receivingMethodName, clsArr);
            } catch (Exception e) {
                Log.e("UnityAds", "Writing to log failed!");
            }
            if (method != null) {
                try {
                    Object[] objArr = new Object[LOGLEVEL_WARNING];
                    objArr[0] = unityAdsDeviceLogEntry.getLogLevel().getLogTag();
                    objArr[LOGLEVEL_ERROR] = unityAdsDeviceLogEntry.getParsedMessage();
                    method.invoke(null, objArr);
                } catch (Exception e2) {
                    Log.e("UnityAds", "Writing to log failed!");
                }
            }
        }
    }

    private static void buildShowStatusMessages() {
        if (_showStatusMessages == null || _showStatusMessages.size() == 0) {
            HashMap hashMap = new HashMap();
            _showStatusMessages = hashMap;
            hashMap.put(UnityAdsShowMsg.READY, "Unity Ads is ready to show ads");
            _showStatusMessages.put(UnityAdsShowMsg.NOT_INITIALIZED, "not initialized");
            _showStatusMessages.put(UnityAdsShowMsg.WEBAPP_NOT_INITIALIZED, "webapp not initialized");
            _showStatusMessages.put(UnityAdsShowMsg.SHOWING_ADS, "already showing ads");
            _showStatusMessages.put(UnityAdsShowMsg.NO_INTERNET, "no internet connection available");
            _showStatusMessages.put(UnityAdsShowMsg.NO_ADS, "no ads are available");
            _showStatusMessages.put(UnityAdsShowMsg.ZERO_ADS, "zero ads available");
            _showStatusMessages.put(UnityAdsShowMsg.VIDEO_NOT_CACHED, "video not cached");
        }
    }

    public static void logShowStatus(UnityAdsShowMsg unityAdsShowMsg) {
        if (unityAdsShowMsg != _previousMsg) {
            buildShowStatusMessages();
            _previousMsg = unityAdsShowMsg;
            String str = (String) _showStatusMessages.get(unityAdsShowMsg);
            if (unityAdsShowMsg != UnityAdsShowMsg.READY) {
                str = "Unity Ads cannot show ads: " + str;
            }
            info(str);
        }
    }
}
