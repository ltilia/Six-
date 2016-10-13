package com.amazon.device.ads;

interface UserIdParameter {
    public static final String SETTINGS_KEY = "userIdParam";

    boolean evaluate(WebRequest webRequest);
}
