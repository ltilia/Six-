package com.facebook.login;

public enum LoginBehavior {
    NATIVE_WITH_FALLBACK(true, true, false, true),
    NATIVE_ONLY(true, false, false, false),
    WEB_ONLY(false, true, false, true),
    WEB_VIEW_ONLY(false, true, false, false),
    DEVICE_AUTH(false, false, true, false);
    
    private final boolean allowsCustomTabAuth;
    private final boolean allowsDeviceAuth;
    private final boolean allowsKatanaAuth;
    private final boolean allowsWebViewAuth;

    private LoginBehavior(boolean allowsKatanaAuth, boolean allowsWebViewAuth, boolean allowsDeviceAuth, boolean allowsCustomTabAuth) {
        this.allowsKatanaAuth = allowsKatanaAuth;
        this.allowsWebViewAuth = allowsWebViewAuth;
        this.allowsDeviceAuth = allowsDeviceAuth;
        this.allowsCustomTabAuth = allowsCustomTabAuth;
    }

    boolean allowsKatanaAuth() {
        return this.allowsKatanaAuth;
    }

    boolean allowsWebViewAuth() {
        return this.allowsWebViewAuth;
    }

    boolean allowsDeviceAuth() {
        return this.allowsDeviceAuth;
    }

    boolean allowsCustomTabAuth() {
        return this.allowsCustomTabAuth;
    }
}
