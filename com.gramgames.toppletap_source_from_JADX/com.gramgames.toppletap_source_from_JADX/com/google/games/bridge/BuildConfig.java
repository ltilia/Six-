package com.google.games.bridge;

import com.facebook.internal.ServerProtocol;

public final class BuildConfig {
    public static final String APPLICATION_ID = "com.google.games.bridge";
    public static final String BUILD_TYPE = "debug";
    public static final boolean DEBUG;
    public static final String FLAVOR = "";
    public static final int VERSION_CODE = 1;
    public static final String VERSION_NAME = "";

    static {
        DEBUG = Boolean.parseBoolean(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
    }
}
