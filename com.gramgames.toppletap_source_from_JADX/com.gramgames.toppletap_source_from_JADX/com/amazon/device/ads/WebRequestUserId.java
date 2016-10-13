package com.amazon.device.ads;

class WebRequestUserId {
    private final AdvertisingIdParameter adIdParam;
    private final Settings settings;
    private UserIdParameter userIdParam;

    public WebRequestUserId() {
        this(Settings.getInstance(), new AdvertisingIdParameter());
    }

    WebRequestUserId(Settings settings, AdvertisingIdParameter adIdParam) {
        this.settings = settings;
        this.adIdParam = adIdParam;
    }

    private void setupUserIdParam() {
        if (this.userIdParam == null) {
            this.userIdParam = (UserIdParameter) this.settings.getObject(UserIdParameter.SETTINGS_KEY, this.adIdParam, UserIdParameter.class);
        }
    }

    public boolean populateWebRequestUserId(WebRequest webRequest) {
        setupUserIdParam();
        boolean paramPopulated = this.userIdParam.evaluate(webRequest);
        if (paramPopulated || this.userIdParam == this.adIdParam) {
            return paramPopulated;
        }
        return this.adIdParam.evaluate(webRequest);
    }
}
