package com.unity3d.ads.android.webapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.ads.AdError;
import com.facebook.internal.ServerProtocol;
import com.google.android.exoplayer.C;
import com.mopub.mobileads.CustomEventInterstitialAdapter;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.cache.UnityAdsCache;
import com.unity3d.ads.android.campaign.UnityAdsCampaign;
import com.unity3d.ads.android.campaign.UnityAdsCampaign.UnityAdsCampaignStatus;
import com.unity3d.ads.android.data.UnityAdsDevice;
import com.unity3d.ads.android.item.UnityAdsRewardItemManager;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import com.unity3d.ads.android.zone.UnityAdsIncentivizedZone;
import com.unity3d.ads.android.zone.UnityAdsZone;
import com.unity3d.ads.android.zone.UnityAdsZoneManager;
import gs.gram.mopub.BuildConfig;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.Yytoken;

@TargetApi(9)
public class UnityAdsWebData {
    private static JSONObject _campaignJson;
    private static Timer _campaignRefreshTimer;
    private static long _campaignRefreshTimerDeadline;
    private static ArrayList _campaigns;
    private static boolean _initInProgress;
    private static boolean _isLoading;
    private static IUnityAdsWebDataListener _listener;
    private static boolean _refreshAfterShowAds;
    private static int _totalLoadersCreated;
    private static int _totalLoadersHaveRun;
    private static int _totalUrlsSent;
    private static final Object _urlLoaderLock;
    private static ArrayList _urlLoaders;
    private static UnityAdsZoneManager _zoneManager;
    private static boolean installedAppsSent;
    private static boolean whitelistRequested;

    final class 1 implements Runnable {
        1() {
        }

        public final void run() {
            UnityAdsWebData.campaignDataFailed();
        }
    }

    final class 2 implements Runnable {
        2() {
        }

        public final void run() {
            UnityAdsWebData.campaignDataFailed();
        }
    }

    final class 3 implements Runnable {
        3() {
        }

        public final void run() {
            UnityAdsWebData.campaignDataFailed();
        }
    }

    final class 4 implements Runnable {
        4() {
        }

        public final void run() {
            UnityAdsWebData.initCampaigns();
        }
    }

    final class 5 extends TimerTask {
        5() {
        }

        public final void run() {
            if (UnityAdsProperties.isShowingAds()) {
                UnityAdsDeviceLog.debug("Refreshing ad plan after current ad");
                UnityAdsWebData._refreshAfterShowAds = true;
                return;
            }
            UnityAdsDeviceLog.debug("Refreshing ad plan to get new data");
            UnityAdsWebData.initCampaigns();
        }
    }

    /* synthetic */ class 6 {
        static final /* synthetic */ int[] $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsRequestType;
        static final /* synthetic */ int[] $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsVideoPosition;

        static {
            $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsRequestType = new int[UnityAdsRequestType.values().length];
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsRequestType[UnityAdsRequestType.VideoPlan.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsRequestType[UnityAdsRequestType.VideoViewed.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsRequestType[UnityAdsRequestType.Unsent.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsRequestType[UnityAdsRequestType.Analytics.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsRequestType[UnityAdsRequestType.AppWhitelist.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsRequestType[UnityAdsRequestType.InstalledApps.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsVideoPosition = new int[UnityAdsVideoPosition.values().length];
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsVideoPosition[UnityAdsVideoPosition.FirstQuartile.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsVideoPosition[UnityAdsVideoPosition.MidPoint.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsVideoPosition[UnityAdsVideoPosition.ThirdQuartile.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsVideoPosition[UnityAdsVideoPosition.End.ordinal()] = 4;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsVideoPosition[UnityAdsVideoPosition.Start.ordinal()] = 5;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    class UnityAdsCancelUrlLoaderRunner implements Runnable {
        private UnityAdsUrlLoader _loader;

        public UnityAdsCancelUrlLoaderRunner(UnityAdsUrlLoader unityAdsUrlLoader) {
            this._loader = null;
            this._loader = unityAdsUrlLoader;
        }

        public void run() {
            try {
                this._loader.cancel(true);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Cancelling urlLoader got exception: " + e.getMessage());
            }
        }
    }

    enum UnityAdsRequestType {
        Analytics,
        VideoPlan,
        VideoViewed,
        Unsent,
        AppWhitelist,
        InstalledApps;

        @SuppressLint({"DefaultLocale"})
        public final String toString() {
            return name();
        }

        @SuppressLint({"DefaultLocale"})
        public static UnityAdsRequestType getValueOf(String str) {
            if (VideoPlan.toString().equals(str.toLowerCase(Locale.US))) {
                return VideoPlan;
            }
            if (VideoViewed.toString().equals(str.toLowerCase(Locale.US))) {
                return VideoViewed;
            }
            if (Unsent.toString().equals(str.toLowerCase(Locale.US))) {
                return Unsent;
            }
            return null;
        }
    }

    class UnityAdsUrlLoader extends AsyncTask {
        private String _baseUrl;
        private BufferedInputStream _binput;
        private HttpURLConnection _connection;
        private Boolean _done;
        private int _downloadLength;
        private String _finalUrl;
        private String _httpMethod;
        private InputStream _input;
        private String _postBody;
        private String _queryParams;
        private UnityAdsRequestType _requestType;
        private int _retries;
        private URL _url;
        private String _urlData;

        public UnityAdsUrlLoader(String str, String str2, String str3, UnityAdsRequestType unityAdsRequestType, int i) {
            this._url = null;
            this._connection = null;
            this._downloadLength = 0;
            this._input = null;
            this._binput = null;
            this._urlData = BuildConfig.FLAVOR;
            this._requestType = null;
            this._finalUrl = null;
            this._retries = 0;
            this._httpMethod = UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET;
            this._queryParams = null;
            this._baseUrl = null;
            this._done = Boolean.valueOf(false);
            this._postBody = null;
            try {
                this._finalUrl = str;
                this._baseUrl = str;
                if (str3.equals(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET) && str2 != null && str2.length() > 2) {
                    this._finalUrl += "?" + str2;
                }
                this._url = new URL(this._finalUrl);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Problems with url! Error-message: " + e.getMessage());
            }
            this._queryParams = str2;
            this._httpMethod = str3;
            UnityAdsWebData.access$408();
            UnityAdsDeviceLog.debug("Total urlLoaders created: " + UnityAdsWebData._totalLoadersCreated);
            this._requestType = unityAdsRequestType;
            this._retries = i;
        }

        public int getRetries() {
            return this._retries;
        }

        public String getUrl() {
            return this._url.toString();
        }

        public String getBaseUrl() {
            return this._baseUrl;
        }

        public String getData() {
            return this._urlData;
        }

        public String getQueryParams() {
            return this._queryParams;
        }

        public String getHTTPMethod() {
            return this._httpMethod;
        }

        public UnityAdsRequestType getRequestType() {
            return this._requestType;
        }

        public void setPostBody(String str) {
            if (this._queryParams != null && this._queryParams.length() > 2) {
                this._finalUrl = this._baseUrl + "?" + this._queryParams;
                try {
                    this._url = new URL(this._finalUrl);
                } catch (MalformedURLException e) {
                    UnityAdsDeviceLog.error("Error when creating adding query parameters to URL " + e);
                }
            }
            this._postBody = str;
        }

        public void clear() {
            this._url = null;
            this._downloadLength = 0;
            this._urlData = BuildConfig.FLAVOR;
            this._requestType = null;
            this._finalUrl = null;
            this._retries = 0;
            this._httpMethod = null;
            this._queryParams = null;
            this._baseUrl = null;
            this._postBody = null;
        }

        private void cancelInMainThread() {
            UnityAdsUtils.runOnUiThread(new UnityAdsCancelUrlLoaderRunner(this));
        }

        protected String doInBackground(String... strArr) {
            try {
                if (this._url.toString().startsWith("https://")) {
                    this._connection = (HttpsURLConnection) this._url.openConnection();
                } else {
                    this._connection = (HttpURLConnection) this._url.openConnection();
                }
                this._connection.setConnectTimeout(WebRequest.DEFAULT_TIMEOUT);
                this._connection.setReadTimeout(CustomEventInterstitialAdapter.DEFAULT_INTERSTITIAL_TIMEOUT_DELAY);
                this._connection.setRequestMethod(this._httpMethod);
                if (this._postBody == null) {
                    this._connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
                } else {
                    this._connection.setRequestProperty("Content-type", WebRequest.CONTENT_TYPE_JSON);
                }
                this._connection.setDoInput(true);
                if (this._httpMethod.equals(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST)) {
                    this._connection.setDoOutput(true);
                }
                if (this._connection != null) {
                    if (this._httpMethod.equals(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST)) {
                        try {
                            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(this._connection.getOutputStream(), C.UTF8_NAME), true);
                            if (this._postBody == null) {
                                printWriter.print(this._queryParams);
                            } else {
                                printWriter.print(this._postBody);
                            }
                            printWriter.flush();
                        } catch (Exception e) {
                            UnityAdsDeviceLog.error(String.format(Locale.US, "Problems writing post-data: %s, %s", new Object[]{e.getMessage(), Arrays.toString(e.getStackTrace())}));
                            cancelInMainThread();
                        }
                    }
                    try {
                        UnityAdsDeviceLog.debug("Connection response: " + this._connection.getResponseCode() + ", " + this._connection.getResponseMessage() + ", " + this._connection.getURL().toString() + " : " + this._queryParams);
                        this._input = this._connection.getInputStream();
                        this._binput = new BufferedInputStream(this._input);
                        long j = 0;
                        this._downloadLength = this._connection.getContentLength();
                        try {
                            UnityAdsWebData.access$508();
                            UnityAdsDeviceLog.debug("Total urlLoaders that have started running: " + UnityAdsWebData._totalLoadersHaveRun);
                            UnityAdsDeviceLog.debug("Reading data from: " + this._url.toString() + " Content-length: " + this._downloadLength);
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            do {
                                int read = this._binput.read();
                                if (read == -1) {
                                    this._urlData = new String(byteArrayOutputStream.toByteArray());
                                    UnityAdsDeviceLog.debug("Read total of: " + j);
                                    break;
                                }
                                j++;
                                byteArrayOutputStream.write(read);
                            } while (!isCancelled());
                        } catch (Exception e2) {
                            UnityAdsDeviceLog.error("Problems loading url! Error-message: " + e2.getMessage());
                            cancelInMainThread();
                        }
                    } catch (Exception e22) {
                        UnityAdsDeviceLog.error("Problems opening stream: " + e22.getMessage());
                        cancelInMainThread();
                    }
                }
            } catch (Exception e222) {
                UnityAdsDeviceLog.error("Problems opening connection: " + e222.getMessage());
                cancelInMainThread();
            }
            return null;
        }

        protected void onCancelled() {
            this._done = Boolean.valueOf(true);
            closeAndFlushConnection();
            UnityAdsWebData.urlLoadFailed(this);
        }

        protected void onPostExecute(String str) {
            if (!(isCancelled() || this._done.booleanValue())) {
                this._done = Boolean.valueOf(true);
                closeAndFlushConnection();
                UnityAdsWebData.urlLoadCompleted(this);
            }
            super.onPostExecute(str);
        }

        protected void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate(numArr);
        }

        private void closeAndFlushConnection() {
            try {
                if (this._input != null) {
                    this._input.close();
                    this._input = null;
                }
                if (this._binput != null) {
                    this._binput.close();
                    this._binput = null;
                }
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Problems closing streams: " + e.getMessage());
            }
        }
    }

    class UnityAdsUrlLoaderCreator implements Runnable {
        private String _postBody;
        private String _queryParams;
        private String _requestMethod;
        private UnityAdsRequestType _requestType;
        private int _retries;
        private String _url;

        public UnityAdsUrlLoaderCreator(String str, String str2, String str3, UnityAdsRequestType unityAdsRequestType, int i) {
            this._url = null;
            this._queryParams = null;
            this._requestMethod = null;
            this._requestType = null;
            this._retries = 0;
            this._postBody = null;
            this._url = str;
            this._queryParams = str2;
            this._requestMethod = str3;
            this._requestType = unityAdsRequestType;
            this._retries = i;
        }

        public void setPostBody(String str) {
            this._postBody = str;
        }

        public void run() {
            UnityAdsUrlLoader unityAdsUrlLoader = new UnityAdsUrlLoader(this._url, this._queryParams, this._requestMethod, this._requestType, this._retries);
            UnityAdsDeviceLog.debug("URL: " + unityAdsUrlLoader.getUrl());
            if (this._postBody != null) {
                unityAdsUrlLoader.setPostBody(this._postBody);
            }
            if (this._retries <= 5) {
                UnityAdsWebData.addLoader(unityAdsUrlLoader);
            }
            UnityAdsWebData.startNextLoader();
        }
    }

    public enum UnityAdsVideoPosition {
        Start,
        FirstQuartile,
        MidPoint,
        ThirdQuartile,
        End;

        @SuppressLint({"DefaultLocale"})
        public final String toString() {
            switch (6.$SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsVideoPosition[ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    return "first_quartile";
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    return "mid_point";
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    return "third_quartile";
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    return "video_end";
                case Yytoken.TYPE_COMMA /*5*/:
                    return "video_start";
                default:
                    return name();
            }
        }
    }

    static /* synthetic */ int access$408() {
        int i = _totalLoadersCreated;
        _totalLoadersCreated = i + 1;
        return i;
    }

    static /* synthetic */ int access$508() {
        int i = _totalLoadersHaveRun;
        _totalLoadersHaveRun = i + 1;
        return i;
    }

    static {
        _campaignJson = null;
        _campaigns = null;
        _listener = null;
        _urlLoaders = null;
        _urlLoaderLock = new Object();
        _zoneManager = null;
        _totalUrlsSent = 0;
        _totalLoadersCreated = 0;
        _totalLoadersHaveRun = 0;
        _isLoading = false;
        _initInProgress = false;
        _refreshAfterShowAds = false;
        whitelistRequested = false;
        installedAppsSent = false;
        _campaignRefreshTimer = null;
        _campaignRefreshTimerDeadline = 0;
    }

    public static boolean hasViewableAds() {
        return getViewableVideoPlanCampaigns() != null && getViewableVideoPlanCampaigns().size() > 0;
    }

    public static void setWebDataListener(IUnityAdsWebDataListener iUnityAdsWebDataListener) {
        _listener = iUnityAdsWebDataListener;
    }

    public static ArrayList getVideoPlanCampaigns() {
        return _campaigns;
    }

    public static UnityAdsCampaign getCampaignById(String str) {
        if (!(str == null || _campaigns == null)) {
            int i = 0;
            while (i < _campaigns.size()) {
                if (_campaigns.get(i) != null && ((UnityAdsCampaign) _campaigns.get(i)).getCampaignId() != null && ((UnityAdsCampaign) _campaigns.get(i)).getCampaignId().equals(str)) {
                    return (UnityAdsCampaign) _campaigns.get(i);
                }
                i++;
            }
        }
        return null;
    }

    public static ArrayList getViewableVideoPlanCampaigns() {
        if (_campaigns == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < _campaigns.size(); i++) {
            UnityAdsCampaign unityAdsCampaign = (UnityAdsCampaign) _campaigns.get(i);
            if (!(unityAdsCampaign == null || unityAdsCampaign.getCampaignStatus().equals(UnityAdsCampaignStatus.VIEWED))) {
                arrayList.add(unityAdsCampaign);
            }
        }
        return arrayList;
    }

    public static boolean initInProgress() {
        return _initInProgress;
    }

    public static boolean initCampaigns() {
        if (_initInProgress) {
            return true;
        }
        if (!UnityAdsUtils.isDebuggable() || UnityAdsProperties.TEST_DATA == null) {
            _initInProgress = true;
            try {
                boolean z;
                ConnectivityManager connectivityManager = (ConnectivityManager) UnityAdsProperties.APPLICATION_CONTEXT.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
                } else {
                    z = false;
                }
                if (z) {
                    InetAddress byName = InetAddress.getByName("impact.applifier.com");
                    UnityAdsDeviceLog.debug("Ad server resolves to " + byName);
                    if (byName.isLoopbackAddress()) {
                        UnityAdsDeviceLog.error("initCampaigns failed, ad server resolves to loopback address (due to ad blocker?)");
                        UnityAdsUtils.runOnUiThread(new 2());
                        return false;
                    }
                    String campaignQueryUrl = UnityAdsProperties.getCampaignQueryUrl();
                    UnityAdsDeviceLog.info("Requesting Unity Ads ad plan from " + campaignQueryUrl);
                    String[] split = campaignQueryUrl.split("\\?");
                    UnityAdsUtils.runOnUiThread(new UnityAdsUrlLoaderCreator(split[0], split[1], UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET, UnityAdsRequestType.VideoPlan, 0));
                    checkFailedUrls();
                    return true;
                }
                UnityAdsDeviceLog.error("Device offline, can't init campaigns");
                UnityAdsUtils.runOnUiThread(new 1());
                return false;
            } catch (UnknownHostException e) {
                UnityAdsDeviceLog.error("initCampaigns failed due to DNS error, unable to resolve ad server address");
                UnityAdsUtils.runOnUiThread(new 3());
                return false;
            } catch (Exception e2) {
                UnityAdsDeviceLog.debug("Unknown exception during DNS test: " + e2);
            }
        } else {
            campaignDataReceived(UnityAdsProperties.TEST_DATA);
            return true;
        }
    }

    public static boolean sendCampaignViewProgress(UnityAdsCampaign unityAdsCampaign, UnityAdsVideoPosition unityAdsVideoPosition) {
        if (unityAdsCampaign == null) {
            return false;
        }
        UnityAdsDeviceLog.info("Unity Ads video position: " + unityAdsVideoPosition.toString() + ", gamer id: " + UnityAdsProperties.UNITY_ADS_GAMER_ID);
        if (UnityAdsProperties.UNITY_ADS_GAMER_ID == null) {
            return false;
        }
        String format;
        String format2 = String.format(Locale.US, "%s%s", new Object[]{UnityAdsProperties.UNITY_ADS_BASE_URL, UnityAdsConstants.UNITY_ADS_ANALYTICS_TRACKING_PATH});
        format2 = String.format(Locale.US, "%s%s/video/%s/%s", new Object[]{format2, UnityAdsProperties.UNITY_ADS_GAMER_ID, unityAdsVideoPosition.toString(), unityAdsCampaign.getCampaignId()});
        String format3 = String.format(Locale.US, "%s/%s", new Object[]{format2, UnityAdsProperties.UNITY_ADS_GAME_ID});
        UnityAdsZone currentZone = getZoneManager().getCurrentZone();
        String format4 = String.format(Locale.US, "%s=%s", new Object[]{UnityAdsConstants.UNITY_ADS_WEBVIEW_API_ZONE_KEY, currentZone.getZoneId()});
        try {
            format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_PLATFORM_KEY, "android"});
            String advertisingTrackingId = UnityAdsDevice.getAdvertisingTrackingId();
            if (advertisingTrackingId != null) {
                Locale locale = Locale.US;
                String str = "%s&%s=%d";
                Object[] objArr = new Object[3];
                objArr[0] = format4;
                objArr[1] = UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_TRACKINGENABLED_KEY;
                objArr[2] = Integer.valueOf(UnityAdsDevice.isLimitAdTrackingEnabled() ? 0 : 1);
                format4 = String.format(locale, str, objArr);
                format2 = UnityAdsUtils.Md5(advertisingTrackingId).toLowerCase(Locale.US);
                format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_ADVERTISINGTRACKINGID_KEY, URLEncoder.encode(format2, C.UTF8_NAME)});
                format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_RAWADVERTISINGTRACKINGID_KEY, URLEncoder.encode(advertisingTrackingId, C.UTF8_NAME)});
            } else if (!UnityAdsDevice.getAndroidId(false).equals(UnityAdsConstants.UNITY_ADS_DEVICEID_UNKNOWN)) {
                format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_ANDROIDID_KEY, URLEncoder.encode(UnityAdsDevice.getAndroidId(true), C.UTF8_NAME)});
                format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_RAWANDROIDID_KEY, URLEncoder.encode(UnityAdsDevice.getAndroidId(false), C.UTF8_NAME)});
            }
            format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_GAMEID_KEY, URLEncoder.encode(UnityAdsProperties.UNITY_ADS_GAME_ID, C.UTF8_NAME)});
            format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SDKVERSION_KEY, URLEncoder.encode(UnityAdsConstants.UNITY_ADS_VERSION, C.UTF8_NAME)});
            format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SOFTWAREVERSION_KEY, URLEncoder.encode(UnityAdsDevice.getSoftwareVersion(), C.UTF8_NAME)});
            format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_HARDWAREVERSION_KEY, URLEncoder.encode(UnityAdsDevice.getHardwareVersion(), C.UTF8_NAME)});
            format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_DEVICETYPE_KEY, Integer.valueOf(UnityAdsDevice.getDeviceType())});
            format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_CONNECTIONTYPE_KEY, URLEncoder.encode(UnityAdsDevice.getConnectionType(), C.UTF8_NAME)});
            if (!UnityAdsDevice.isUsingWifi()) {
                format4 = String.format(Locale.US, "%s&%s=%d", new Object[]{format4, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_ANDROIDNETWORKTYPE_KEY, Integer.valueOf(UnityAdsDevice.getNetworkType())});
            }
            if (UnityAdsProperties.CACHING_SPEED > 0) {
                format4 = String.format(Locale.US, "%s&%s=%d", new Object[]{format4, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_CACHINGSPEED_KEY, Long.valueOf(UnityAdsProperties.CACHING_SPEED)});
            }
            format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SCREENSIZE_KEY, Integer.valueOf(UnityAdsDevice.getScreenSize())});
            format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SCREENDENSITY_KEY, Integer.valueOf(UnityAdsDevice.getScreenDensity())});
            Locale locale2 = Locale.US;
            String str2 = "%s&%s=%s";
            Object[] objArr2 = new Object[3];
            objArr2[0] = format4;
            objArr2[1] = UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_CACHEDPLAYBACK_KEY;
            objArr2[2] = UnityAdsProperties.SELECTED_CAMPAIGN_CACHED.booleanValue() ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
            format4 = String.format(locale2, str2, objArr2);
        } catch (Exception e) {
            Exception exception = e;
            format2 = format4;
            Exception exception2 = exception;
            UnityAdsDeviceLog.error(String.format(Locale.US, "Problems creating campaigns query: %s", new Object[]{exception2.getMessage()}));
            format4 = format2;
        }
        if (currentZone.isIncentivized()) {
            UnityAdsRewardItemManager itemManager = ((UnityAdsIncentivizedZone) currentZone).itemManager();
            format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_REWARDITEM_KEY, itemManager.getCurrentItem().getKey()});
        }
        if (currentZone.getGamerSid() != null) {
            format = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_GAMERSID_KEY, currentZone.getGamerSid()});
        } else {
            format = format4;
        }
        UnityAdsUtils.runOnUiThread(new UnityAdsUrlLoaderCreator(format3, format, UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST, UnityAdsRequestType.VideoViewed, 0));
        return true;
    }

    public static void sendAnalyticsRequest(String str, UnityAdsCampaign unityAdsCampaign) {
        if (unityAdsCampaign != null) {
            String format;
            String format2 = String.format(Locale.US, "%s", new Object[]{UnityAdsProperties.ANALYTICS_BASE_URL});
            String format3 = String.format(Locale.US, "%s=%s", new Object[]{UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_GAMEID_KEY, UnityAdsProperties.UNITY_ADS_GAME_ID});
            format3 = String.format(Locale.US, "%s&%s=%s", new Object[]{format3, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, str});
            format3 = String.format(Locale.US, "%s&%s=%s", new Object[]{format3, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_TRACKINGID_KEY, UnityAdsProperties.UNITY_ADS_GAMER_ID});
            format3 = String.format(Locale.US, "%s&%s=%s", new Object[]{format3, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_PROVIDERID_KEY, unityAdsCampaign.getCampaignId()});
            UnityAdsZone currentZone = getZoneManager().getCurrentZone();
            String format4 = String.format(Locale.US, "%s&%s=%s", new Object[]{format3, UnityAdsConstants.UNITY_ADS_WEBVIEW_API_ZONE_KEY, currentZone.getZoneId()});
            if (currentZone.isIncentivized()) {
                UnityAdsRewardItemManager itemManager = ((UnityAdsIncentivizedZone) currentZone).itemManager();
                format3 = String.format(Locale.US, "%s&%s=%s", new Object[]{format4, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_REWARDITEM_KEY, itemManager.getCurrentItem().getKey()});
            } else {
                format3 = format4;
            }
            if (currentZone.getGamerSid() != null) {
                format = String.format(Locale.US, "%s&%s=%s", new Object[]{format3, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_GAMERSID_KEY, currentZone.getGamerSid()});
            } else {
                format = format3;
            }
            UnityAdsUtils.runOnUiThread(new UnityAdsUrlLoaderCreator(format2, format, UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET, UnityAdsRequestType.Analytics, 0));
        }
    }

    public static JSONObject getData() {
        return _campaignJson;
    }

    public static boolean refreshCampaignsIfNeeded() {
        boolean z;
        if (_refreshAfterShowAds) {
            _refreshAfterShowAds = false;
            UnityAdsDeviceLog.debug("Starting delayed ad plan refresh");
            z = true;
        } else if (_campaignRefreshTimerDeadline > 0 && SystemClock.elapsedRealtime() > _campaignRefreshTimerDeadline) {
            removeCampaignRefreshTimer();
            UnityAdsDeviceLog.debug("Refreshing ad plan from server due to timer deadline");
            z = true;
        } else if (UnityAdsProperties.CAMPAIGN_REFRESH_VIEWS_MAX > 0 && UnityAdsProperties.CAMPAIGN_REFRESH_VIEWS_COUNT >= UnityAdsProperties.CAMPAIGN_REFRESH_VIEWS_MAX) {
            UnityAdsDeviceLog.debug("Refreshing ad plan from server due to endscreen limit");
            z = true;
        } else if (getVideoPlanCampaigns() == null || getViewableVideoPlanCampaigns().size() != 0) {
            z = false;
        } else {
            UnityAdsDeviceLog.debug("All available videos watched, refreshing ad plan from server");
            z = true;
        }
        if (!z) {
            return false;
        }
        new Thread(new 4()).start();
        return true;
    }

    public static void setupCampaignRefreshTimer() {
        removeCampaignRefreshTimer();
        if (UnityAdsProperties.CAMPAIGN_REFRESH_SECONDS > 0) {
            TimerTask 5 = new 5();
            _campaignRefreshTimerDeadline = SystemClock.elapsedRealtime() + ((long) (UnityAdsProperties.CAMPAIGN_REFRESH_SECONDS * AdError.NETWORK_ERROR_CODE));
            Timer timer = new Timer();
            _campaignRefreshTimer = timer;
            timer.schedule(5, (long) (UnityAdsProperties.CAMPAIGN_REFRESH_SECONDS * AdError.NETWORK_ERROR_CODE));
        }
    }

    public static UnityAdsZoneManager getZoneManager() {
        return _zoneManager;
    }

    private static void removeCampaignRefreshTimer() {
        _campaignRefreshTimerDeadline = 0;
        if (_campaignRefreshTimer != null) {
            _campaignRefreshTimer.cancel();
        }
    }

    private static void addLoader(UnityAdsUrlLoader unityAdsUrlLoader) {
        synchronized (_urlLoaderLock) {
            if (_urlLoaders == null) {
                _urlLoaders = new ArrayList();
            }
            _urlLoaders.add(unityAdsUrlLoader);
        }
    }

    private static void startNextLoader() {
        synchronized (_urlLoaderLock) {
            if (!(_urlLoaders == null || _urlLoaders.size() <= 0 || _isLoading)) {
                UnityAdsDeviceLog.debug("Starting next URL loader");
                _isLoading = true;
                ((UnityAdsUrlLoader) _urlLoaders.remove(0)).execute(new String[0]);
            }
        }
    }

    private static void urlLoadCompleted(UnityAdsUrlLoader unityAdsUrlLoader) {
        if (unityAdsUrlLoader == null || unityAdsUrlLoader.getRequestType() == null) {
            UnityAdsDeviceLog.error("Got broken urlLoader!");
        } else {
            switch (6.$SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsRequestType[unityAdsUrlLoader.getRequestType().ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    campaignDataReceived(unityAdsUrlLoader.getData());
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    whitelistReceived(unityAdsUrlLoader.getData());
                    break;
            }
            unityAdsUrlLoader.clear();
        }
        _totalUrlsSent++;
        UnityAdsDeviceLog.debug("Total urls sent: " + _totalUrlsSent);
        _isLoading = false;
        startNextLoader();
    }

    private static void urlLoadFailed(UnityAdsUrlLoader unityAdsUrlLoader) {
        if (unityAdsUrlLoader == null || unityAdsUrlLoader.getRequestType() == null) {
            UnityAdsDeviceLog.error("Got broken urlLoader!");
        } else {
            switch (6.$SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebData$UnityAdsRequestType[unityAdsUrlLoader.getRequestType().ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    campaignDataFailed();
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    writeFailedUrl(unityAdsUrlLoader);
                    break;
            }
            unityAdsUrlLoader.clear();
        }
        _isLoading = false;
        startNextLoader();
    }

    private static void checkFailedUrls() {
        File file = new File(UnityAdsCache.getCacheDirectory() + "/UnityAds-pendingrequests.dat");
        if (file.exists()) {
            String readFile;
            synchronized (_urlLoaderLock) {
                readFile = UnityAdsUtils.readFile(file, true);
                if (!file.delete()) {
                    UnityAdsDeviceLog.debug("Could not remove pending requests file");
                }
            }
            try {
                JSONArray jSONArray = new JSONObject(readFile).getJSONArray(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY);
                if (jSONArray != null && jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        UnityAdsUtils.runOnUiThread(new UnityAdsUrlLoaderCreator(jSONObject.getString(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY), jSONObject.getString(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY), jSONObject.getString(UnityAdsConstants.UNITY_ADS_FAILED_URL_METHODTYPE_KEY), UnityAdsRequestType.getValueOf(jSONObject.getString(UnityAdsConstants.UNITY_ADS_FAILED_URL_REQUESTTYPE_KEY)), jSONObject.getInt(UnityAdsConstants.UNITY_ADS_FAILED_URL_RETRIES_KEY) + 1));
                    }
                }
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Problems while sending some of the failed urls.");
            }
        }
        startNextLoader();
    }

    private static void writeFailedUrl(UnityAdsUrlLoader unityAdsUrlLoader) {
        JSONObject jSONObject = null;
        if (unityAdsUrlLoader != null) {
            synchronized (_urlLoaderLock) {
                try {
                    JSONObject jSONObject2;
                    JSONArray jSONArray;
                    File file = new File(UnityAdsCache.getCacheDirectory() + "/UnityAds-pendingrequests.dat");
                    if (file.exists()) {
                        try {
                            jSONObject2 = new JSONObject(UnityAdsUtils.readFile(file, true));
                            UnityAdsDeviceLog.debug("JNIDEBUG read json: " + jSONObject2.toString());
                            jSONArray = jSONObject2.getJSONArray(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY);
                            UnityAdsDeviceLog.debug("JNIDEBUG read array: " + jSONArray.toString());
                            jSONObject = jSONObject2;
                        } catch (JSONException e) {
                            jSONArray = null;
                        }
                    } else {
                        jSONArray = null;
                    }
                    if (jSONArray == null) {
                        jSONArray = new JSONArray();
                    }
                    if (jSONObject == null) {
                        jSONObject = new JSONObject();
                    }
                    jSONObject2 = new JSONObject();
                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, unityAdsUrlLoader.getBaseUrl());
                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_REQUESTTYPE_KEY, unityAdsUrlLoader.getRequestType());
                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_METHODTYPE_KEY, unityAdsUrlLoader.getHTTPMethod());
                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY, unityAdsUrlLoader.getQueryParams());
                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_RETRIES_KEY, unityAdsUrlLoader.getRetries());
                    jSONArray.put(jSONObject2);
                    jSONObject.put(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY, jSONArray);
                    if (UnityAdsUtils.canUseExternalStorage() && !UnityAdsUtils.writeFile(file, jSONObject.toString())) {
                        UnityAdsDeviceLog.debug("Error while writing: " + file.getName());
                    }
                } catch (Exception e2) {
                    UnityAdsDeviceLog.debug("Exception when writing failed url: " + e2.getMessage());
                }
            }
        }
    }

    private static void campaignDataReceived(String str) {
        ArrayList arrayList = null;
        int i = 0;
        Boolean valueOf = Boolean.valueOf(true);
        _initInProgress = false;
        try {
            UnityAdsDeviceLog.debug("Ad plan: " + str);
            JSONObject jSONObject = new JSONObject(str);
            _campaignJson = jSONObject;
            if (jSONObject.has(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY)) {
                try {
                    jSONObject = _campaignJson.getJSONObject(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY);
                    if (!jSONObject.has(UnityAdsConstants.UNITY_ADS_WEBVIEW_URL_KEY)) {
                        valueOf = Boolean.valueOf(false);
                    }
                    if (!jSONObject.has(UnityAdsConstants.UNITY_ADS_ANALYTICS_URL_KEY)) {
                        valueOf = Boolean.valueOf(false);
                    }
                    if (!jSONObject.has(UnityAdsConstants.UNITY_ADS_URL_KEY)) {
                        valueOf = Boolean.valueOf(false);
                    }
                    if (!jSONObject.has(UnityAdsConstants.UNITY_ADS_GAMER_ID_KEY)) {
                        valueOf = Boolean.valueOf(false);
                    }
                    if (!jSONObject.has(UnityAdsConstants.UNITY_ADS_CAMPAIGNS_KEY)) {
                        valueOf = Boolean.valueOf(false);
                    }
                    if (!jSONObject.has(UnityAdsConstants.UNITY_ADS_ZONES_KEY)) {
                        valueOf = Boolean.valueOf(false);
                    }
                    if (valueOf.booleanValue()) {
                        JSONArray jSONArray = jSONObject.getJSONArray(UnityAdsConstants.UNITY_ADS_CAMPAIGNS_KEY);
                        if (jSONArray != null) {
                            arrayList = deserializeCampaigns(jSONArray);
                        }
                        if (jSONObject.has(UnityAdsConstants.UNITY_ADS_CAMPAIGN_APPFILTERING_KEY)) {
                            String string = jSONObject.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_APPFILTERING_KEY);
                            if (string != null && (string.equals("simple") || string.equals("advanced"))) {
                                if (string.equals("advanced") && jSONObject.has(UnityAdsConstants.UNITY_ADS_CAMPAIGN_INSTALLED_APPS_URL) && jSONObject.has(UnityAdsConstants.UNITY_ADS_CAMPAIGN_APP_WHITELIST_URL)) {
                                    UnityAdsProperties.INSTALLED_APPS_URL = jSONObject.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_INSTALLED_APPS_URL);
                                    requestAppWhitelist(jSONObject.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_APP_WHITELIST_URL));
                                }
                                if (arrayList != null && arrayList.size() > 0) {
                                    arrayList = filterCampaigns(arrayList);
                                    if (arrayList != null && arrayList.size() == 0) {
                                        initCampaigns();
                                        return;
                                    }
                                }
                            }
                        }
                        _campaigns = arrayList;
                    }
                    if (_campaigns == null) {
                        _campaigns = new ArrayList();
                    }
                    UnityAdsDeviceLog.debug("Parsed total of " + _campaigns.size() + " campaigns");
                    UnityAdsProperties.WEBVIEW_BASE_URL = jSONObject.getString(UnityAdsConstants.UNITY_ADS_WEBVIEW_URL_KEY);
                    UnityAdsProperties.ANALYTICS_BASE_URL = jSONObject.getString(UnityAdsConstants.UNITY_ADS_ANALYTICS_URL_KEY);
                    UnityAdsProperties.UNITY_ADS_BASE_URL = jSONObject.getString(UnityAdsConstants.UNITY_ADS_URL_KEY);
                    UnityAdsProperties.UNITY_ADS_GAMER_ID = jSONObject.getString(UnityAdsConstants.UNITY_ADS_GAMER_ID_KEY);
                    if (jSONObject.has(UnityAdsConstants.UNITY_ADS_CAMPAIGN_REFRESH_VIEWS_KEY)) {
                        UnityAdsProperties.CAMPAIGN_REFRESH_VIEWS_COUNT = 0;
                        UnityAdsProperties.CAMPAIGN_REFRESH_VIEWS_MAX = jSONObject.getInt(UnityAdsConstants.UNITY_ADS_CAMPAIGN_REFRESH_VIEWS_KEY);
                    }
                    if (jSONObject.has(UnityAdsConstants.UNITY_ADS_CAMPAIGN_REFRESH_SECONDS_KEY)) {
                        UnityAdsProperties.CAMPAIGN_REFRESH_SECONDS = jSONObject.getInt(UnityAdsConstants.UNITY_ADS_CAMPAIGN_REFRESH_SECONDS_KEY);
                    }
                    if (valueOf.booleanValue()) {
                        if (_zoneManager != null) {
                            _zoneManager.clear();
                            _zoneManager = null;
                        }
                        _zoneManager = new UnityAdsZoneManager(jSONObject.getJSONArray(UnityAdsConstants.UNITY_ADS_ZONES_KEY));
                    }
                    if (_listener == null || !valueOf.booleanValue() || _campaigns == null || _campaigns.size() <= 0) {
                        campaignDataFailed();
                        return;
                    }
                    StringBuilder append = new StringBuilder("Unity Ads initialized with ").append(_campaigns.size()).append(" campaigns and ");
                    if (_zoneManager != null) {
                        i = _zoneManager.zoneCount();
                    }
                    UnityAdsDeviceLog.info(append.append(i).append(" zones").toString());
                    _listener.onWebDataCompleted();
                    return;
                } catch (Exception e) {
                    UnityAdsDeviceLog.error("Malformed data JSON");
                    return;
                }
            }
            campaignDataFailed();
        } catch (Exception e2) {
            UnityAdsDeviceLog.error("Malformed JSON: " + e2.getMessage());
            if (e2.getStackTrace() != null) {
                StackTraceElement[] stackTrace = e2.getStackTrace();
                int length = stackTrace.length;
                while (i < length) {
                    UnityAdsDeviceLog.error("Malformed JSON: " + stackTrace[i].toString());
                    i++;
                }
            }
            campaignDataFailed();
        }
    }

    private static void campaignDataFailed() {
        if (_listener != null) {
            _listener.onWebDataFailed();
        }
    }

    private static ArrayList deserializeCampaigns(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                UnityAdsCampaign unityAdsCampaign = new UnityAdsCampaign(jSONArray.getJSONObject(i));
                if (unityAdsCampaign.hasValidData()) {
                    UnityAdsDeviceLog.debug("Adding campaign to cache");
                    arrayList.add(unityAdsCampaign);
                }
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Problem with the campaign, skipping.");
            }
        }
        return arrayList;
    }

    private static ArrayList filterCampaigns(ArrayList arrayList) {
        Iterable iterable = null;
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        PackageManager packageManager = UnityAdsProperties.APPLICATION_CONTEXT.getPackageManager();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int i;
            UnityAdsCampaign unityAdsCampaign = (UnityAdsCampaign) it.next();
            String storeId = unityAdsCampaign.getStoreId();
            if (storeId.indexOf(38) != -1) {
                storeId = storeId.substring(0, storeId.indexOf(38));
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(storeId, 0);
                if (packageInfo == null || !storeId.equals(packageInfo.packageName)) {
                    i = 0;
                } else {
                    i = 1;
                }
            } catch (NameNotFoundException e) {
                i = 0;
            }
            if (i != 0) {
                if (iterable == null) {
                    iterable = new ArrayList();
                }
                iterable.add(unityAdsCampaign.getGameId());
            }
            String filterMode = unityAdsCampaign.getFilterMode();
            if (filterMode == null || !filterMode.equals("whitelist")) {
                if (i != 0) {
                    UnityAdsDeviceLog.debug("Filtered game id " + unityAdsCampaign.getGameId() + " from ad plan (already installed)");
                } else {
                    arrayList2.add(unityAdsCampaign);
                }
            } else if (i != 0) {
                arrayList2.add(unityAdsCampaign);
            } else {
                UnityAdsDeviceLog.debug("Filtered game id " + unityAdsCampaign.getGameId() + " from ad plan (not installed)");
            }
        }
        if (iterable != null) {
            UnityAdsProperties.APPFILTER_LIST = TextUtils.join(",", iterable);
        }
        return arrayList2;
    }

    private static void requestAppWhitelist(String str) {
        if (!whitelistRequested) {
            whitelistRequested = true;
            UnityAdsUtils.runOnUiThread(new UnityAdsUrlLoaderCreator(str, null, UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET, UnityAdsRequestType.AppWhitelist, 0));
        }
    }

    private static void whitelistReceived(String str) {
        UnityAdsDeviceLog.debug("Received whitelist");
        try {
            JSONObject jSONObject = new JSONObject(str);
            Map hashMap = new HashMap();
            JSONArray jSONArray = jSONObject.getJSONArray("whitelist");
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    if (jSONObject2.has("game") && jSONObject2.has(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY)) {
                        hashMap.put(jSONObject2.getString("game").toUpperCase(Locale.US), jSONObject2.getString(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY));
                    }
                } catch (JSONException e) {
                }
            }
            sendInstalledApps(UnityAdsProperties.INSTALLED_APPS_URL, hashMap);
        } catch (Exception e2) {
            UnityAdsDeviceLog.debug("Failed to parse app whitelist " + e2);
        }
    }

    private static void sendInstalledApps(String str, Map map) {
        if (!installedAppsSent) {
            installedAppsSent = true;
            String packageDataJson = UnityAdsDevice.getPackageDataJson(map);
            if (packageDataJson != null) {
                Runnable unityAdsUrlLoaderCreator = new UnityAdsUrlLoaderCreator(str, UnityAdsProperties.getCampaignQueryArguments(), UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST, UnityAdsRequestType.InstalledApps, 0);
                unityAdsUrlLoaderCreator.setPostBody(packageDataJson);
                UnityAdsUtils.runOnUiThread(unityAdsUrlLoaderCreator);
                return;
            }
            UnityAdsDeviceLog.debug("Nothing to send for installed applications");
        }
    }
}
