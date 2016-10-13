package com.amazon.device.ads;

import android.os.AsyncTask;
import com.amazon.device.ads.WebRequest.WebRequestException;
import com.amazon.device.ads.WebRequest.WebRequestStatus;
import org.json.simple.parser.Yytoken;

/* compiled from: AdMetricsTasks */
class AdMetricsSubmitAaxTask extends AsyncTask<WebRequest, Void, Void> {
    private static final String LOGTAG;
    private final MobileAdsLogger logger;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$WebRequest$WebRequestStatus;

        static {
            $SwitchMap$com$amazon$device$ads$WebRequest$WebRequestStatus = new int[WebRequestStatus.values().length];
            try {
                $SwitchMap$com$amazon$device$ads$WebRequest$WebRequestStatus[WebRequestStatus.INVALID_CLIENT_PROTOCOL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$WebRequest$WebRequestStatus[WebRequestStatus.NETWORK_FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$WebRequest$WebRequestStatus[WebRequestStatus.MALFORMED_URL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$WebRequest$WebRequestStatus[WebRequestStatus.UNSUPPORTED_ENCODING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    AdMetricsSubmitAaxTask() {
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    }

    static {
        LOGTAG = AdMetricsSubmitAaxTask.class.getSimpleName();
    }

    public Void doInBackground(WebRequest... webRequests) {
        for (WebRequest webRequest : webRequests) {
            try {
                webRequest.makeCall();
            } catch (WebRequestException e) {
                switch (1.$SwitchMap$com$amazon$device$ads$WebRequest$WebRequestStatus[e.getStatus().ordinal()]) {
                    case Yytoken.TYPE_LEFT_BRACE /*1*/:
                        this.logger.e("Unable to submit metrics for ad due to an Invalid Client Protocol, msg: %s", e.getMessage());
                        continue;
                    case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                        this.logger.e("Unable to submit metrics for ad due to Network Failure, msg: %s", e.getMessage());
                        continue;
                    case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                        this.logger.e("Unable to submit metrics for ad due to a Malformed Pixel URL, msg: %s", e.getMessage());
                        break;
                    case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                        break;
                    default:
                        continue;
                }
                this.logger.e("Unable to submit metrics for ad because of unsupported character encoding, msg: %s", e.getMessage());
            }
        }
        return null;
    }
}
