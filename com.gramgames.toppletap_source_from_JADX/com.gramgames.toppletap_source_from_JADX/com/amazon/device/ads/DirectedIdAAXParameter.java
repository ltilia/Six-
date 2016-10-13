package com.amazon.device.ads;

import com.amazon.device.ads.Configuration.ConfigOption;

/* compiled from: AAXParameterGroupParameter */
class DirectedIdAAXParameter extends AAXParameterGroupParameter {
    private final Configuration configuration;
    private final DirectedIdRetriever directedIdRetriever;

    DirectedIdAAXParameter() {
        this(DebugProperties.getInstance(), new MobileAdsLoggerFactory(), Configuration.getInstance(), (DirectedIdRetriever) Settings.getInstance().getObject(DirectedIdRetriever.SETTINGS_KEY, null, DirectedIdRetriever.class));
    }

    DirectedIdAAXParameter(DebugProperties debugProperties, MobileAdsLoggerFactory mobileAdsLoggerFactory, Configuration configuration, DirectedIdRetriever directedIdRetriever) {
        super(debugProperties, "directedId", DebugProperties.DEBUG_DIRECTEDID, mobileAdsLoggerFactory);
        this.configuration = configuration;
        this.directedIdRetriever = directedIdRetriever;
    }

    protected String getDerivedValue(ParameterData parameterData) {
        if (!this.configuration.getBoolean(ConfigOption.WHITELISTED_CUSTOMER) || this.directedIdRetriever == null) {
            return null;
        }
        return this.directedIdRetriever.getDirectedId();
    }
}
