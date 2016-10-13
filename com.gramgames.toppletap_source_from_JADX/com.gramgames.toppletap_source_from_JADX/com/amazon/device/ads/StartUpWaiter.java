package com.amazon.device.ads;

import android.util.SparseArray;
import com.amazon.device.ads.Settings.SettingsListener;
import com.amazon.device.ads.ThreadUtils.MainThreadScheduler;
import com.amazon.device.ads.ThreadUtils.RunnableExecutor;
import com.amazon.device.ads.ThreadUtils.ThreadPoolScheduler;

abstract class StartUpWaiter implements ConfigurationListener, SettingsListener {
    static final int CALLBACK_ON_MAIN_THREAD = 0;
    static final int DEFAULT = 1;
    private static final SparseArray<RunnableExecutor> executors;
    private int callbackType;
    private final Configuration configuration;
    private final Settings settings;

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            StartUpWaiter.this.startUpReady();
        }
    }

    class 2 implements Runnable {
        2() {
        }

        public void run() {
            StartUpWaiter.this.startUpFailed();
        }
    }

    protected abstract void startUpFailed();

    protected abstract void startUpReady();

    static {
        executors = new SparseArray();
        putRunnableExecutor(CALLBACK_ON_MAIN_THREAD, new MainThreadScheduler());
        putRunnableExecutor(DEFAULT, new ThreadPoolScheduler());
    }

    public StartUpWaiter(Settings settings, Configuration configuration) {
        this.callbackType = DEFAULT;
        this.settings = settings;
        this.configuration = configuration;
    }

    public void start() {
        this.settings.listenForSettings(this);
    }

    public void startAndCallbackOnMainThread() {
        this.callbackType = CALLBACK_ON_MAIN_THREAD;
        start();
    }

    public void settingsLoaded() {
        this.configuration.queueConfigurationListener(this);
    }

    public void onConfigurationReady() {
        onFinished(new 1());
    }

    public void onConfigurationFailure() {
        onFinished(new 2());
    }

    private void onFinished(Runnable proc) {
        getExecutor(this.callbackType).execute(proc);
    }

    Settings getSettings() {
        return this.settings;
    }

    Configuration getConfiguration() {
        return this.configuration;
    }

    static RunnableExecutor getExecutor(int callbackType) {
        return (RunnableExecutor) executors.get(callbackType, executors.get(DEFAULT));
    }

    static void putRunnableExecutor(int callbackType, RunnableExecutor executor) {
        if (executor == null) {
            executors.remove(callbackType);
        } else {
            executors.put(callbackType, executor);
        }
    }
}
