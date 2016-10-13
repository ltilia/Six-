package com.vungle.publisher.protocol.message;

import com.facebook.internal.ServerProtocol;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.AdPlay;
import com.vungle.publisher.db.model.AdReport;
import com.vungle.publisher.db.model.AdReportEvent;
import com.vungle.publisher.db.model.AdReportEvent.a;
import com.vungle.publisher.db.model.Video;
import com.vungle.publisher.fm;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public abstract class ReportAd<Q extends RequestAd<Q>, O extends ReportAd<Q, O>> extends BaseJsonObject {
    protected Integer a;
    protected Long b;
    protected String c;
    protected String d;
    protected Integer e;
    protected String f;
    protected Boolean g;
    protected String h;
    protected Play[] i;
    protected Q j;
    protected String k;
    protected String l;

    /* compiled from: vungle */
    public static abstract class Factory<Q extends RequestAd<Q>, R extends RequestAdResponse, O extends ReportAd<Q, O>, T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>> extends MessageFactory<O> {
        @Inject
        public com.vungle.publisher.protocol.message.ExtraInfo.Factory a;

        protected abstract com.vungle.publisher.protocol.message.RequestAd.Factory<Q> b();

        protected Factory() {
        }

        public O a(T t) {
            Play[] playArr = null;
            int i = 0;
            if (t == null) {
                return null;
            }
            Ad e = t.e();
            ReportAd reportAd = (ReportAd) a();
            reportAd.b = t.l();
            reportAd.c = e.e();
            reportAd.d = e.d();
            reportAd.e = Integer.valueOf(t.k());
            reportAd.f = t.h();
            reportAd.g = Boolean.valueOf(t.g());
            reportAd.h = t.i();
            AdPlay[] t2 = t.t();
            int length = t2 == null ? 0 : t2.length;
            if (length > 0) {
                playArr = new Play[length];
                int length2 = t2.length;
                length = 0;
                while (i < length2) {
                    int i2 = length + 1;
                    playArr[length] = Factory.a(t2[i]);
                    i++;
                    length = i2;
                }
            }
            reportAd.i = playArr;
            reportAd.j = b().b();
            return reportAd;
        }
    }

    /* compiled from: vungle */
    public static class Play extends BaseJsonObject {
        protected Integer a;
        protected Integer b;
        protected Long c;
        protected UserAction[] d;

        /* compiled from: vungle */
        public static abstract class Factory<R extends RequestAdResponse, T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>> extends MessageFactory<Play> {
            protected final /* synthetic */ Object a() {
                return new Play();
            }

            protected Factory() {
            }

            static Play a(P p) {
                Play play = null;
                if (p != null) {
                    play = new Play();
                    play.d = Factory.a(p.d());
                    try {
                        play.a = p.a.j();
                    } catch (NullPointerException e) {
                        Logger.w(Logger.PROTOCOL_TAG, "null play report parent");
                    }
                    play.c = p.c;
                    play.b = p.b;
                }
                return play;
            }
        }

        /* compiled from: vungle */
        public static class UserAction extends BaseJsonObject {
            protected String a;
            protected Long b;
            protected String c;

            /* compiled from: vungle */
            public static abstract class Factory<R extends RequestAdResponse, T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>> extends MessageFactory<UserAction> {
                protected final /* synthetic */ Object a() {
                    return new UserAction();
                }

                protected Factory() {
                }

                protected static UserAction[] a(E[] eArr) {
                    int length = eArr == null ? 0 : eArr.length;
                    if (length <= 0) {
                        return null;
                    }
                    UserAction[] userActionArr = new UserAction[length];
                    int length2 = eArr.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length2) {
                        UserAction userAction;
                        AdReportEvent adReportEvent = eArr[i];
                        int i3 = i2 + 1;
                        if (adReportEvent != null) {
                            userAction = new UserAction();
                            userAction.a = String.valueOf(adReportEvent.b);
                            userAction.b = Long.valueOf(adReportEvent.c);
                            userAction.c = adReportEvent.d;
                        } else {
                            userAction = null;
                        }
                        userActionArr[i2] = userAction;
                        i++;
                        i2 = i3;
                    }
                    return userActionArr;
                }
            }

            protected UserAction() {
            }

            public final /* synthetic */ Object b() throws JSONException {
                return a();
            }

            public final JSONObject a() throws JSONException {
                JSONObject a = super.a();
                a.putOpt(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_ACTION_KEY, this.a);
                a.putOpt("timestamp_millis", this.b);
                a.putOpt("value", this.c);
                return a;
            }
        }

        public final /* synthetic */ Object b() throws JSONException {
            return a();
        }

        Play() {
        }

        public final JSONObject a() throws JSONException {
            JSONObject a = super.a();
            a.putOpt("userActions", fm.a(this.d));
            a.putOpt("videoLength", this.a);
            a.putOpt("videoViewed", this.b);
            a.putOpt("startTime", this.c);
            return a;
        }
    }

    public /* synthetic */ Object b() throws JSONException {
        return a();
    }

    protected ReportAd() {
    }

    public final Q d() {
        return this.j;
    }

    private List<String> e() {
        List<String> arrayList = new ArrayList();
        if (this.i != null && this.i.length > 0) {
            String aVar = a.volume.toString();
            for (Play play : this.i) {
                UserAction[] userActionArr = play.d;
                if (userActionArr != null) {
                    for (UserAction userAction : userActionArr) {
                        if (!aVar.equals(userAction.a)) {
                            arrayList.add(userAction.a);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public JSONObject a() throws JSONException {
        Object valueOf;
        JSONObject a = this.j == null ? super.a() : this.j.a();
        a.putOpt("ttDownload", this.a);
        a.putOpt("adStartTime", this.b);
        a.putOpt(ServerProtocol.FALLBACK_DIALOG_PARAM_APP_ID, this.c);
        a.putOpt("campaign", this.d);
        a.putOpt("adDuration", this.e);
        if (Boolean.TRUE.equals(this.g)) {
            a.putOpt(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY, this.f);
        }
        String str = "incentivized";
        Boolean bool = this.g;
        if (bool != null) {
            valueOf = Integer.valueOf(bool.booleanValue() ? 1 : 0);
        } else {
            valueOf = null;
        }
        a.putOpt(str, valueOf);
        a.putOpt("placement", this.h);
        a.putOpt("plays", fm.a(this.i));
        a.putOpt(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, this.k);
        a.putOpt("clickedThrough", new JSONArray(e()));
        a.putOpt(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, this.l);
        return a;
    }
}
