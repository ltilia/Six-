package com.amazon.device.ads;

interface DirectedIdRetriever {
    public static final String SETTINGS_KEY = "directedIdRetriever";

    String getDirectedId();
}
