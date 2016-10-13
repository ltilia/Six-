package com.amazon.device.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.amazon.device.ads.Configuration.ConfigOption;
import com.amazon.device.ads.JSONUtils.JSONUtilities;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONObject;

class Settings {
    private static final String LOGTAG;
    private static final String PREFS_NAME = "AmazonMobileAds";
    public static final String SETTING_ENABLE_WEBVIEW_PAUSE_LOGIC = "shouldPauseWebViewTimersInWebViewRelatedActivities";
    protected static final String SETTING_TESTING_ENABLED = "testingEnabled";
    protected static final String SETTING_TLS_ENABLED = "tlsEnabled";
    private static Settings instance;
    private final ConcurrentHashMap<String, Value> cache;
    private DebugProperties debugProperties;
    private JSONUtilities jsonUtilities;
    private LinkedBlockingQueue<SettingsListener> listeners;
    private final MobileAdsLogger logger;
    private final CountDownLatch settingsLoadedLatch;
    private SharedPreferences sharedPreferences;
    private final ReentrantLock writeToSharedPreferencesLock;

    public interface SettingsListener {
        void settingsLoaded();
    }

    class 1 implements Runnable {
        final /* synthetic */ Context val$context;

        1(Context context) {
            this.val$context = context;
        }

        public void run() {
            Settings.this.fetchSharedPreferences(this.val$context);
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ SharedPreferences val$sharedPreferences;

        2(SharedPreferences sharedPreferences) {
            this.val$sharedPreferences = sharedPreferences;
        }

        public void run() {
            Settings.this.writeToSharedPreferencesLock.lock();
            Editor editor = this.val$sharedPreferences.edit();
            editor.clear();
            for (Entry<String, Value> entry : Settings.this.cache.entrySet()) {
                Value value = (Value) entry.getValue();
                if (!value.isTransientData) {
                    if (value.clazz == String.class) {
                        editor.putString((String) entry.getKey(), (String) value.value);
                    } else if (value.clazz == Long.class) {
                        editor.putLong((String) entry.getKey(), ((Long) value.value).longValue());
                    } else if (value.clazz == Integer.class) {
                        editor.putInt((String) entry.getKey(), ((Integer) value.value).intValue());
                    } else if (value.clazz == Boolean.class) {
                        editor.putBoolean((String) entry.getKey(), ((Boolean) value.value).booleanValue());
                    }
                }
            }
            Settings.this.commit(editor);
            Settings.this.writeToSharedPreferencesLock.unlock();
        }
    }

    static final class SettingsStatics {
        static final String IU_SERVICE_LAST_CHECKIN = "amzn-ad-iu-last-checkin";
        static final String VIEWABLE_JS_SETTINGS_NAME = "viewableJSSettingsNameAmazonAdSDK";
        static final String VIEWABLE_JS_VERSION_STORED = "viewableJSVersionStored";

        SettingsStatics() {
        }
    }

    class Value {
        public Class<?> clazz;
        public boolean isTransientData;
        public Object value;

        public Value(Class<?> clazz, Object value) {
            this.clazz = clazz;
            this.value = value;
        }
    }

    class TransientValue extends Value {
        public TransientValue(Class<?> clazz, Object value) {
            super(clazz, value);
            this.isTransientData = true;
        }
    }

    static {
        LOGTAG = Settings.class.getSimpleName();
        instance = new Settings();
    }

    public Settings() {
        this(new JSONUtilities(), DebugProperties.getInstance());
    }

    Settings(JSONUtilities jsonUtilities, DebugProperties debugProperties) {
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        this.listeners = new LinkedBlockingQueue();
        this.writeToSharedPreferencesLock = new ReentrantLock();
        this.settingsLoadedLatch = new CountDownLatch(1);
        this.cache = new ConcurrentHashMap();
        this.jsonUtilities = jsonUtilities;
        this.debugProperties = debugProperties;
    }

    public static Settings getInstance() {
        return instance;
    }

    void contextReceived(Context context) {
        if (context != null) {
            beginFetch(context);
        }
    }

    void beginFetch(Context context) {
        ThreadUtils.scheduleRunnable(new 1(context));
    }

    public boolean isSettingsLoaded() {
        return this.sharedPreferences != null;
    }

    public void listenForSettings(SettingsListener listener) {
        if (isSettingsLoaded()) {
            listener.settingsLoaded();
            return;
        }
        try {
            this.listeners.put(listener);
        } catch (InterruptedException e) {
            this.logger.e("Interrupted exception while adding listener: %s", e.getMessage());
        }
    }

    SharedPreferences getSharedPreferencesFromContext(Context context) {
        return context.getSharedPreferences(PREFS_NAME, 0);
    }

    SharedPreferences getSharedPreferences() {
        return this.sharedPreferences;
    }

    ConcurrentHashMap<String, Value> getCache() {
        return this.cache;
    }

    private void putSetting(String key, Value value) {
        if (value.value == null) {
            this.logger.w("Could not set null value for setting: %s", key);
            return;
        }
        putSettingWithNoFlush(key, value);
        if (!value.isTransientData && isSettingsLoaded()) {
            flush();
        }
    }

    private void putSettingWithNoFlush(String key, Value value) {
        if (value.value == null) {
            this.logger.w("Could not set null value for setting: %s", key);
            return;
        }
        this.cache.put(key, value);
    }

    void readSharedPreferencesIntoCache(SharedPreferences sharedPreferences) {
        cacheAdditionalEntries(sharedPreferences.getAll());
        loadDebugProperties();
    }

    void cacheAdditionalEntries(Map<String, ?> entries) {
        for (Entry<String, ?> entry : entries.entrySet()) {
            String key = (String) entry.getKey();
            if (!(key == null || this.cache.containsKey(key))) {
                Object value = entry.getValue();
                if (value != null) {
                    this.cache.put(key, new Value(value.getClass(), value));
                } else {
                    this.logger.w("Could not cache null value for SharedPreferences setting: %s", key);
                }
            }
        }
    }

    private void writeCacheToSharedPreferences() {
        writeCacheToSharedPreferences(this.sharedPreferences);
    }

    void writeCacheToSharedPreferences(SharedPreferences sharedPreferences) {
        ThreadUtils.scheduleRunnable(new 2(sharedPreferences));
    }

    void flush() {
        writeCacheToSharedPreferences();
    }

    public boolean containsKey(String key) {
        return this.cache.containsKey(key);
    }

    public JSONObject getJSONObject(String key, JSONObject defaultValue) {
        Value value = (Value) this.cache.get(key);
        if (value == null) {
            return defaultValue;
        }
        JSONObject json = this.jsonUtilities.getJSONObjectFromString((String) value.value);
        if (json != null) {
            return json;
        }
        return defaultValue;
    }

    public void putJSONObject(String key, JSONObject value) {
        putSetting(key, new Value(String.class, value.toString()));
    }

    public void putJSONObjectWithNoFlush(String key, JSONObject value) {
        putSettingWithNoFlush(key, new Value(String.class, value.toString()));
    }

    public void putTransientJSONObject(String key, JSONObject value) {
        putSettingWithNoFlush(key, new TransientValue(String.class, value.toString()));
    }

    public JSONObject getWrittenJSONObject(String key, JSONObject defaultValue) {
        if (!isSettingsLoaded()) {
            return defaultValue;
        }
        return this.jsonUtilities.getJSONObjectFromString(this.sharedPreferences.getString(key, defaultValue.toString()));
    }

    public String getString(String key, String defaultValue) {
        Value value = (Value) this.cache.get(key);
        return value == null ? defaultValue : (String) value.value;
    }

    void putString(String key, String value) {
        putSetting(key, new Value(String.class, value));
    }

    void putStringWithNoFlush(String key, String value) {
        putSettingWithNoFlush(key, new Value(String.class, value));
    }

    void putTransientString(String key, String value) {
        putSettingWithNoFlush(key, new TransientValue(String.class, value));
    }

    public String getWrittenString(String key, String defaultValue) {
        if (isSettingsLoaded()) {
            return this.sharedPreferences.getString(key, defaultValue);
        }
        return defaultValue;
    }

    public int getInt(String key, int defaultValue) {
        Value value = (Value) this.cache.get(key);
        return value == null ? defaultValue : ((Integer) value.value).intValue();
    }

    void putInt(String key, int value) {
        putSetting(key, new Value(Integer.class, Integer.valueOf(value)));
    }

    void putIntWithNoFlush(String key, int value) {
        putSettingWithNoFlush(key, new Value(Integer.class, Integer.valueOf(value)));
    }

    void putTransientInt(String key, int value) {
        putSettingWithNoFlush(key, new TransientValue(Integer.class, Integer.valueOf(value)));
    }

    public int getWrittenInt(String key, int defaultValue) {
        if (isSettingsLoaded()) {
            return this.sharedPreferences.getInt(key, defaultValue);
        }
        return defaultValue;
    }

    public long getLong(String key, long defaultValue) {
        Value value = (Value) this.cache.get(key);
        return value == null ? defaultValue : ((Long) value.value).longValue();
    }

    void putLong(String key, long value) {
        putSetting(key, new Value(Long.class, Long.valueOf(value)));
    }

    void putLongWithNoFlush(String key, long value) {
        putSettingWithNoFlush(key, new Value(Long.class, Long.valueOf(value)));
    }

    void putTransientLong(String key, long value) {
        putSettingWithNoFlush(key, new TransientValue(Long.class, Long.valueOf(value)));
    }

    public long getWrittenLong(String key, long defaultValue) {
        if (isSettingsLoaded()) {
            return this.sharedPreferences.getLong(key, defaultValue);
        }
        return defaultValue;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        Boolean value = getBoolean(key, null);
        return value == null ? defaultValue : value.booleanValue();
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        Value value = (Value) this.cache.get(key);
        return value == null ? defaultValue : (Boolean) value.value;
    }

    void putBoolean(String key, boolean value) {
        putSetting(key, new Value(Boolean.class, Boolean.valueOf(value)));
    }

    void putBooleanWithNoFlush(String key, boolean value) {
        putSettingWithNoFlush(key, new Value(Boolean.class, Boolean.valueOf(value)));
    }

    void putTransientBoolean(String key, boolean value) {
        putSettingWithNoFlush(key, new TransientValue(Boolean.class, Boolean.valueOf(value)));
    }

    public boolean getWrittenBoolean(String key, boolean defaultValue) {
        if (isSettingsLoaded()) {
            return this.sharedPreferences.getBoolean(key, defaultValue);
        }
        return defaultValue;
    }

    public void putTransientObject(String key, Object value) {
        putSettingWithNoFlush(key, new TransientValue(value.getClass(), value));
    }

    public <T> T getObject(String key, T defaultValue, Class<T> clazz) {
        Value value = (Value) this.cache.get(key);
        if (value == null || !clazz.isInstance(value.value)) {
            return defaultValue;
        }
        return value.value;
    }

    void remove(String key) {
        Value value = (Value) this.cache.remove(key);
        if (value != null && !value.isTransientData && isSettingsLoaded()) {
            flush();
        }
    }

    void removeWithNoFlush(String key) {
        this.cache.remove(key);
    }

    private void commit(Editor editor) {
        editor.apply();
    }

    void notifySettingsListeners() {
        while (true) {
            SettingsListener listener = (SettingsListener) this.listeners.poll();
            if (listener != null) {
                listener.settingsLoaded();
            } else {
                return;
            }
        }
    }

    void fetchSharedPreferences(Context context) {
        if (!isSettingsLoaded()) {
            SharedPreferences sharedPreferences = getSharedPreferencesFromContext(context);
            readSharedPreferencesIntoCache(sharedPreferences);
            this.sharedPreferences = sharedPreferences;
            writeCacheToSharedPreferences(sharedPreferences);
        }
        this.settingsLoadedLatch.countDown();
        notifySettingsListeners();
    }

    private void loadDebugProperties() {
        if (containsKey(ConfigOption.DEBUG_PROPERTIES.getSettingsName())) {
            JSONObject json = getJSONObject(ConfigOption.DEBUG_PROPERTIES.getSettingsName(), null);
            if (json != null) {
                this.debugProperties.overwriteDebugProperties(json);
            }
        }
    }
}
