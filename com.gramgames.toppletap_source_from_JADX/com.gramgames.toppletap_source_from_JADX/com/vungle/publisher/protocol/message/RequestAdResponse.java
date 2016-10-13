package com.vungle.publisher.protocol.message;

import com.facebook.internal.ServerProtocol;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.publisher.fm;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.Comparator;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public abstract class RequestAdResponse {
    protected Integer a;
    protected Integer b;
    protected Integer c;
    protected String d;
    protected String e;
    protected CallToActionOverlay f;
    protected String g;
    protected String h;
    protected String i;
    protected String j;
    protected Integer k;
    protected Integer l;
    protected Integer m;
    protected ThirdPartyAdTracking n;
    protected Integer o;
    protected String p;
    protected Integer q;

    /* compiled from: vungle */
    public static class CallToActionOverlay extends BaseJsonObject {
        protected Float a;
        protected Integer b;
        protected Boolean c;
        protected Boolean d;
        protected Integer e;

        @Singleton
        /* compiled from: vungle */
        public static class Factory extends JsonDeserializationFactory<CallToActionOverlay> {
            protected final /* synthetic */ Object a() {
                return new CallToActionOverlay();
            }

            protected final /* synthetic */ Object a(JSONObject jSONObject) throws JSONException {
                return b(jSONObject);
            }

            @Inject
            Factory() {
            }

            protected static CallToActionOverlay b(JSONObject jSONObject) throws JSONException {
                if (jSONObject == null) {
                    return null;
                }
                CallToActionOverlay callToActionOverlay = new CallToActionOverlay();
                callToActionOverlay.a = fm.b(jSONObject, "click_area");
                callToActionOverlay.c = fm.a(jSONObject, "enabled");
                callToActionOverlay.d = fm.a(jSONObject, "show_onclick");
                callToActionOverlay.e = fm.c(jSONObject, "time_show");
                callToActionOverlay.b = fm.c(jSONObject, "time_enabled");
                return callToActionOverlay;
            }
        }

        /* compiled from: vungle */
        public final class Factory_Factory implements dagger.internal.Factory<Factory> {
            static final /* synthetic */ boolean a;
            private final MembersInjector<Factory> b;

            static {
                a = !Factory_Factory.class.desiredAssertionStatus();
            }

            public Factory_Factory(MembersInjector<Factory> factoryMembersInjector) {
                if (a || factoryMembersInjector != null) {
                    this.b = factoryMembersInjector;
                    return;
                }
                throw new AssertionError();
            }

            public final Factory get() {
                return (Factory) MembersInjectors.injectMembers(this.b, new Factory());
            }

            public static dagger.internal.Factory<Factory> create(MembersInjector<Factory> factoryMembersInjector) {
                return new Factory_Factory(factoryMembersInjector);
            }
        }

        public final /* synthetic */ Object b() throws JSONException {
            return a();
        }

        protected CallToActionOverlay() {
        }

        public final Float d() {
            return this.a;
        }

        public final Boolean e() {
            return this.c;
        }

        public final Boolean f() {
            return this.d;
        }

        public final Integer g() {
            return this.e;
        }

        public final Integer h() {
            return this.b;
        }

        public final JSONObject a() throws JSONException {
            JSONObject a = super.a();
            a.putOpt("click_area", this.a);
            a.putOpt("enabled", this.c);
            a.putOpt("show_onclick", this.d);
            a.putOpt("time_show", this.e);
            a.putOpt("time_enabled", this.b);
            return a;
        }
    }

    /* compiled from: vungle */
    public static abstract class Factory<T extends RequestAdResponse> extends JsonDeserializationFactory<T> {
        @Inject
        public Factory a;
        @Inject
        public Factory b;

        public /* synthetic */ Object a(JSONObject jSONObject) throws JSONException {
            return b(jSONObject);
        }

        protected Factory() {
        }

        public T b(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            RequestAdResponse requestAdResponse = (RequestAdResponse) a();
            requestAdResponse.a = fm.c(jSONObject, "delay");
            requestAdResponse.c = fm.c(jSONObject, "asyncThreshold");
            requestAdResponse.b = fm.c(jSONObject, "retryCount");
            requestAdResponse.d = fm.e(jSONObject, ServerProtocol.FALLBACK_DIALOG_PARAM_APP_ID);
            requestAdResponse.e = fm.e(jSONObject, "callToActionDest");
            requestAdResponse.f = Factory.b(jSONObject.optJSONObject("cta_overlay"));
            requestAdResponse.g = fm.e(jSONObject, "callToActionUrl");
            requestAdResponse.h = fm.e(jSONObject, "campaign");
            JsonDeserializationFactory.a(jSONObject, "campaign", requestAdResponse.h);
            requestAdResponse.i = fm.e(jSONObject, UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
            requestAdResponse.j = fm.e(jSONObject, "chk");
            requestAdResponse.k = fm.c(jSONObject, "showCloseIncentivized");
            requestAdResponse.l = fm.c(jSONObject, "showClose");
            requestAdResponse.m = fm.c(jSONObject, "countdown");
            requestAdResponse.n = this.b.b(jSONObject.optJSONObject("tpat"));
            requestAdResponse.o = fm.c(jSONObject, "videoHeight");
            JsonDeserializationFactory.a(jSONObject, "videoHeight", requestAdResponse.o);
            requestAdResponse.p = fm.e(jSONObject, UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY);
            JsonDeserializationFactory.a(jSONObject, UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, requestAdResponse.p);
            requestAdResponse.q = fm.c(jSONObject, "videoWidth");
            JsonDeserializationFactory.a(jSONObject, "videoWidth", requestAdResponse.q);
            return requestAdResponse;
        }
    }

    /* compiled from: vungle */
    public static class ThirdPartyAdTracking extends BaseJsonObject {
        protected String[] a;
        protected String[] b;
        protected String[] c;
        protected String[] d;
        protected String[] e;
        protected String[] f;
        protected PlayCheckpoint[] g;
        protected String[] h;
        protected String[] i;
        protected String[] j;

        @Singleton
        /* compiled from: vungle */
        public static class Factory extends JsonDeserializationFactory<ThirdPartyAdTracking> {
            @Inject
            public Factory a;

            protected final /* synthetic */ java.lang.Object a() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r1 = this;
                r0 = new com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking;
                r0.<init>();
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory.a():java.lang.Object");
            }

            protected final /* synthetic */ java.lang.Object a(org.json.JSONObject r2) throws org.json.JSONException {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r1 = this;
                r0 = r1.b(r2);
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory.a(org.json.JSONObject):java.lang.Object");
            }

            @javax.inject.Inject
            Factory() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r0 = this;
                r0.<init>();
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory.<init>():void");
            }

            protected final com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking b(org.json.JSONObject r4) throws org.json.JSONException {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r3 = this;
                r0 = 0;
                if (r4 == 0) goto L_0x0061;
            L_0x0003:
                r1 = new com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking;
                r1.<init>();
                r0 = "postroll_click";
                r0 = com.vungle.publisher.fm.f(r4, r0);
                r1.a = r0;
                r0 = "video_click";
                r0 = com.vungle.publisher.fm.f(r4, r0);
                r1.b = r0;
                r0 = "video_close";
                r0 = com.vungle.publisher.fm.f(r4, r0);
                r1.c = r0;
                r0 = "error";
                r0 = com.vungle.publisher.fm.f(r4, r0);
                r1.d = r0;
                r0 = "mute";
                r0 = com.vungle.publisher.fm.f(r4, r0);
                r1.e = r0;
                r0 = "pause";
                r0 = com.vungle.publisher.fm.f(r4, r0);
                r1.f = r0;
                r0 = r3.a;
                r2 = "play_percentage";
                r2 = r4.optJSONArray(r2);
                r0 = r0.a(r2);
                r0 = (com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint[]) r0;
                r1.g = r0;
                r0 = "postroll_view";
                r0 = com.vungle.publisher.fm.f(r4, r0);
                r1.h = r0;
                r0 = "resume";
                r0 = com.vungle.publisher.fm.f(r4, r0);
                r1.i = r0;
                r0 = "unmute";
                r0 = com.vungle.publisher.fm.f(r4, r0);
                r1.j = r0;
                r0 = r1;
            L_0x0061:
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory.b(org.json.JSONObject):com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking");
            }
        }

        /* compiled from: vungle */
        public final class Factory_Factory implements dagger.internal.Factory<Factory> {
            static final /* synthetic */ boolean a;
            private final MembersInjector<Factory> b;

            static {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r0 = com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory_Factory.class;
                r0 = r0.desiredAssertionStatus();
                if (r0 != 0) goto L_0x000c;
            L_0x0008:
                r0 = 1;
            L_0x0009:
                a = r0;
                return;
            L_0x000c:
                r0 = 0;
                goto L_0x0009;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory_Factory.<clinit>():void");
            }

            public Factory_Factory(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory> r2) {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r1 = this;
                r1.<init>();
                r0 = a;
                if (r0 != 0) goto L_0x000f;
            L_0x0007:
                if (r2 != 0) goto L_0x000f;
            L_0x0009:
                r0 = new java.lang.AssertionError;
                r0.<init>();
                throw r0;
            L_0x000f:
                r1.b = r2;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory_Factory.<init>(dagger.MembersInjector):void");
            }

            public final com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory get() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r2 = this;
                r0 = r2.b;
                r1 = new com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$Factory;
                r1.<init>();
                r0 = dagger.internal.MembersInjectors.injectMembers(r0, r1);
                r0 = (com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory) r0;
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory_Factory.get():com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$Factory");
            }

            public static dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory> create(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory> r1) {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r0 = new com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$Factory_Factory;
                r0.<init>(r1);
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory_Factory.create(dagger.MembersInjector):dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$Factory>");
            }
        }

        /* compiled from: vungle */
        public static class PlayCheckpoint extends BaseJsonObject {
            public Float a;
            public String[] b;

            @Singleton
            /* compiled from: vungle */
            public static class Factory extends JsonDeserializationFactory<PlayCheckpoint> {
                protected final /* synthetic */ java.lang.Object a() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r1 = this;
                    r0 = new com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$PlayCheckpoint;
                    r0.<init>();
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.Factory.a():java.lang.Object");
                }

                protected final /* synthetic */ java.lang.Object a(org.json.JSONObject r4) throws org.json.JSONException {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r3 = this;
                    r0 = 0;
                    if (r4 == 0) goto L_0x0026;
                L_0x0003:
                    r0 = new com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$PlayCheckpoint;
                    r0.<init>();
                    r1 = "checkpoint";
                    r1 = com.vungle.publisher.fm.b(r4, r1);
                    r0.a = r1;
                    r1 = "checkpoint";
                    r2 = r0.a;
                    com.vungle.publisher.protocol.message.JsonDeserializationFactory.a(r4, r1, r2);
                    r1 = "urls";
                    r1 = com.vungle.publisher.fm.f(r4, r1);
                    r0.b = r1;
                    r1 = "urls";
                    r2 = r0.b;
                    com.vungle.publisher.protocol.message.JsonDeserializationFactory.a(r4, r1, r2);
                L_0x0026:
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.Factory.a(org.json.JSONObject):java.lang.Object");
                }

                @javax.inject.Inject
                Factory() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = this;
                    r0.<init>();
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.Factory.<init>():void");
                }
            }

            /* compiled from: vungle */
            public final class Factory_Factory implements dagger.internal.Factory<Factory> {
                static final /* synthetic */ boolean a;
                private final MembersInjector<Factory> b;

                static {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.Factory_Factory.class;
                    r0 = r0.desiredAssertionStatus();
                    if (r0 != 0) goto L_0x000c;
                L_0x0008:
                    r0 = 1;
                L_0x0009:
                    a = r0;
                    return;
                L_0x000c:
                    r0 = 0;
                    goto L_0x0009;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.Factory_Factory.<clinit>():void");
                }

                public Factory_Factory(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.Factory> r2) {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r1 = this;
                    r1.<init>();
                    r0 = a;
                    if (r0 != 0) goto L_0x000f;
                L_0x0007:
                    if (r2 != 0) goto L_0x000f;
                L_0x0009:
                    r0 = new java.lang.AssertionError;
                    r0.<init>();
                    throw r0;
                L_0x000f:
                    r1.b = r2;
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.Factory_Factory.<init>(dagger.MembersInjector):void");
                }

                public final com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.Factory get() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r2 = this;
                    r0 = r2.b;
                    r1 = new com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$PlayCheckpoint$Factory;
                    r1.<init>();
                    r0 = dagger.internal.MembersInjectors.injectMembers(r0, r1);
                    r0 = (com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.Factory) r0;
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.Factory_Factory.get():com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$PlayCheckpoint$Factory");
                }

                public static dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.Factory> create(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.Factory> r1) {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = new com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$PlayCheckpoint$Factory_Factory;
                    r0.<init>(r1);
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.Factory_Factory.create(dagger.MembersInjector):dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$PlayCheckpoint$Factory>");
                }
            }

            @Singleton
            /* compiled from: vungle */
            public static class PlayPercentAscendingComparator implements Comparator<PlayCheckpoint> {
                public /* synthetic */ int compare(java.lang.Object r5, java.lang.Object r6) {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r4 = this;
                    r0 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
                    r3 = 0;
                    r5 = (com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint) r5;
                    r6 = (com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint) r6;
                    r1 = r5.a;
                    r2 = r6.a;
                    if (r1 != 0) goto L_0x0018;
                L_0x000d:
                    r1 = r0;
                L_0x000e:
                    if (r2 != 0) goto L_0x001d;
                L_0x0010:
                    r0 = r1 - r0;
                    r1 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1));
                    if (r1 >= 0) goto L_0x0022;
                L_0x0016:
                    r0 = -1;
                L_0x0017:
                    return r0;
                L_0x0018:
                    r1 = r1.floatValue();
                    goto L_0x000e;
                L_0x001d:
                    r0 = r2.floatValue();
                    goto L_0x0010;
                L_0x0022:
                    r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1));
                    if (r0 != 0) goto L_0x0028;
                L_0x0026:
                    r0 = 0;
                    goto L_0x0017;
                L_0x0028:
                    r0 = 1;
                    goto L_0x0017;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.PlayPercentAscendingComparator.compare(java.lang.Object, java.lang.Object):int");
                }

                @javax.inject.Inject
                PlayPercentAscendingComparator() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = this;
                    r0.<init>();
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.PlayPercentAscendingComparator.<init>():void");
                }
            }

            /* compiled from: vungle */
            public enum PlayPercentAscendingComparator_Factory implements dagger.internal.Factory<PlayPercentAscendingComparator> {
                ;

                private PlayPercentAscendingComparator_Factory(java.lang.String r2) {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r1 = this;
                    r0 = 0;
                    r1.<init>(r2, r0);
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.PlayPercentAscendingComparator_Factory.<init>(java.lang.String):void");
                }

                public final com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.PlayPercentAscendingComparator get() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r1 = this;
                    r0 = new com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$PlayCheckpoint$PlayPercentAscendingComparator;
                    r0.<init>();
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.PlayPercentAscendingComparator_Factory.get():com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$PlayCheckpoint$PlayPercentAscendingComparator");
                }

                public static dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.PlayPercentAscendingComparator> create() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = INSTANCE;
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.PlayPercentAscendingComparator_Factory.create():dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$PlayCheckpoint$PlayPercentAscendingComparator>");
                }
            }

            public final /* synthetic */ java.lang.Object b() throws org.json.JSONException {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r1 = this;
                r0 = r1.a();
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.b():java.lang.Object");
            }

            protected PlayCheckpoint() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r0 = this;
                r0.<init>();
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.<init>():void");
            }

            public final org.json.JSONObject a() throws org.json.JSONException {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r3 = this;
                r0 = super.a();
                r1 = "checkpoint";
                r2 = r3.a;
                r0.putOpt(r1, r2);
                r1 = "urls";
                r2 = r3.b;
                r0.putOpt(r1, r2);
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.a():org.json.JSONObject");
            }
        }

        public final /* synthetic */ java.lang.Object b() throws org.json.JSONException {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = r1.a();
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.b():java.lang.Object");
        }

        protected ThirdPartyAdTracking() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r0 = this;
            r0.<init>();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.<init>():void");
        }

        public final java.lang.String[] d() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = r1.a;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.d():java.lang.String[]");
        }

        public final java.lang.String[] e() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = r1.b;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.e():java.lang.String[]");
        }

        public final java.lang.String[] f() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = r1.c;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.f():java.lang.String[]");
        }

        public final java.lang.String[] g() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = r1.d;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.g():java.lang.String[]");
        }

        public final java.lang.String[] h() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = r1.e;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.h():java.lang.String[]");
        }

        public final java.lang.String[] i() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = r1.f;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.i():java.lang.String[]");
        }

        public final com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint[] j() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = r1.g;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.j():com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$PlayCheckpoint[]");
        }

        public final java.lang.String[] k() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = r1.h;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.k():java.lang.String[]");
        }

        public final java.lang.String[] l() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = r1.i;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.l():java.lang.String[]");
        }

        public final java.lang.String[] m() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = r1.j;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.m():java.lang.String[]");
        }

        public final org.json.JSONObject a() throws org.json.JSONException {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r3 = this;
            r0 = super.a();
            r1 = "postroll_click";
            r2 = r3.a;
            r0.putOpt(r1, r2);
            r1 = "video_click";
            r2 = r3.b;
            r0.putOpt(r1, r2);
            r1 = "video_close";
            r2 = r3.c;
            r0.putOpt(r1, r2);
            r1 = "error";
            r2 = r3.d;
            r0.putOpt(r1, r2);
            r1 = "mute";
            r2 = r3.e;
            r0.putOpt(r1, r2);
            r1 = "pause";
            r2 = r3.f;
            r0.putOpt(r1, r2);
            r1 = "play_percentage";
            r2 = r3.g;
            r2 = com.vungle.publisher.fm.a(r2);
            r0.putOpt(r1, r2);
            r1 = "postroll_view";
            r2 = r3.h;
            r0.putOpt(r1, r2);
            r1 = "resume";
            r2 = r3.i;
            r0.putOpt(r1, r2);
            r1 = "unmute";
            r2 = r3.j;
            r0.putOpt(r1, r2);
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.a():org.json.JSONObject");
        }
    }

    protected RequestAdResponse() {
    }

    public final Integer a() {
        return this.a;
    }

    public final String b() {
        return this.d;
    }

    public final String c() {
        return this.e;
    }

    public final CallToActionOverlay d() {
        return this.f;
    }

    public final String e() {
        return this.g;
    }

    public final String f() {
        return this.h;
    }

    public final String g() {
        return this.i;
    }

    public final Integer h() {
        return this.k;
    }

    public final Integer i() {
        return this.l;
    }

    public final Integer j() {
        return this.m;
    }

    public final ThirdPartyAdTracking k() {
        return this.n;
    }

    public final Integer l() {
        return this.o;
    }

    public final String m() {
        return this.p;
    }

    public final Integer n() {
        return this.q;
    }
}
