package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.a.e;
import com.chartboost.sdk.c;
import com.chartboost.sdk.d.b;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.ExecutionOptions;
import com.mopub.common.Constants;
import com.mopub.mobileads.CustomEventBannerAdapter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public final class ba {
    private static ba c;
    private a a;
    private com.chartboost.sdk.Model.a b;

    public interface a {
        void a(com.chartboost.sdk.Model.a aVar, boolean z, String str, CBClickError cBClickError, b bVar);
    }

    class 1 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ Activity b;
        final /* synthetic */ b c;
        final /* synthetic */ ba d;

        class 1 implements Runnable {
            final /* synthetic */ String a;
            final /* synthetic */ 1 b;

            1(1 1, String str) {
                this.b = 1;
                this.a = str;
            }

            public void run() {
                this.b.d.a(this.a, this.b.b, this.b.c);
            }
        }

        1(ba baVar, String str, Activity activity, b bVar) {
            this.d = baVar;
            this.a = str;
            this.b = activity;
            this.c = bVar;
        }

        public void run() {
            String str;
            Throwable th;
            String str2 = this.a;
            if (ax.a().c()) {
                HttpURLConnection httpURLConnection = null;
                try {
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(this.a).openConnection();
                    try {
                        httpURLConnection2.setInstanceFollowRedirects(false);
                        httpURLConnection2.setConnectTimeout(CustomEventBannerAdapter.DEFAULT_BANNER_TIMEOUT_DELAY);
                        httpURLConnection2.setReadTimeout(CustomEventBannerAdapter.DEFAULT_BANNER_TIMEOUT_DELAY);
                        String headerField = httpURLConnection2.getHeaderField("Location");
                        if (headerField != null) {
                            str2 = headerField;
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                            str = str2;
                            a(str);
                        }
                    } catch (Throwable e) {
                        Throwable th2 = e;
                        httpURLConnection = httpURLConnection2;
                        th = th2;
                        try {
                            CBLogging.b("CBURLOpener", "Exception raised while opening a HTTP Conection", th);
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                                str = str2;
                                a(str);
                            }
                            str = str2;
                            a(str);
                        } catch (Throwable th3) {
                            th = th3;
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        httpURLConnection = httpURLConnection2;
                        th = th4;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    th = e2;
                    CBLogging.b("CBURLOpener", "Exception raised while opening a HTTP Conection", th);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                        str = str2;
                        a(str);
                    }
                    str = str2;
                    a(str);
                }
            }
            str = str2;
            a(str);
        }

        public void a(String str) {
            Runnable 1 = new 1(this, str);
            if (this.b != null) {
                this.b.runOnUiThread(1);
            } else {
                CBUtility.e().post(1);
            }
        }
    }

    public static ba a(a aVar) {
        if (c == null) {
            c = new ba(aVar);
        }
        return c;
    }

    private ba(a aVar) {
        this.a = aVar;
    }

    public void a(com.chartboost.sdk.Model.a aVar, String str, Activity activity, b bVar) {
        this.b = aVar;
        try {
            String scheme = new URI(str).getScheme();
            if (scheme == null) {
                if (this.a != null) {
                    this.a.a(aVar, false, str, CBClickError.URI_INVALID, bVar);
                }
            } else if (scheme.equals(Constants.HTTP) || scheme.equals(Constants.HTTPS)) {
                aw.a().execute(new 1(this, str, activity, bVar));
            } else {
                a(str, activity, bVar);
            }
        } catch (URISyntaxException e) {
            if (this.a != null) {
                this.a.a(aVar, false, str, CBClickError.URI_INVALID, bVar);
            }
        }
    }

    private void a(String str, Context context, b bVar) {
        Intent intent;
        if (this.b != null && this.b.a()) {
            this.b.c = e.NONE;
        }
        if (context == null) {
            context = c.y();
        }
        if (context != null) {
            String str2;
            try {
                intent = new Intent("android.intent.action.VIEW");
                if (!(context instanceof Activity)) {
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                }
                intent.setData(Uri.parse(str));
                context.startActivity(intent);
                str2 = str;
            } catch (Exception e) {
                if (str.startsWith("market://")) {
                    try {
                        str = "http://market.android.com/" + str.substring(9);
                        intent = new Intent("android.intent.action.VIEW");
                        if (!(context instanceof Activity)) {
                            intent.addFlags(DriveFile.MODE_READ_ONLY);
                        }
                        intent.setData(Uri.parse(str));
                        context.startActivity(intent);
                        str2 = str;
                    } catch (Throwable e2) {
                        str2 = str;
                        CBLogging.b("CBURLOpener", "Exception raised openeing an inavld playstore URL", e2);
                        if (this.a != null) {
                            this.a.a(this.b, false, str2, CBClickError.URI_UNRECOGNIZED, bVar);
                            return;
                        }
                        return;
                    }
                }
                if (this.a != null) {
                    this.a.a(this.b, false, str, CBClickError.URI_UNRECOGNIZED, bVar);
                }
                str2 = str;
            }
            if (this.a != null) {
                this.a.a(this.b, true, str2, null, bVar);
            }
        } else if (this.a != null) {
            this.a.a(this.b, false, str, CBClickError.NO_HOST_ACTIVITY, bVar);
        }
    }

    public static boolean a(String str) {
        try {
            Context y = c.y();
            Intent intent = new Intent("android.intent.action.VIEW");
            if (!(y instanceof Activity)) {
                intent.addFlags(DriveFile.MODE_READ_ONLY);
            }
            intent.setData(Uri.parse(str));
            if (y.getPackageManager().queryIntentActivities(intent, ExecutionOptions.MAX_TRACKING_TAG_STRING_LENGTH).size() > 0) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            CBLogging.b("CBURLOpener", "Cannot open URL", e);
            return false;
        }
    }
}
