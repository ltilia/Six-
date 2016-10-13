package com.vungle.publisher.protocol.message;

import android.content.Context;
import com.vungle.publisher.AdConfig;
import com.vungle.publisher.Demographic.Gender;
import com.vungle.publisher.ek;
import com.vungle.publisher.em;
import com.vungle.publisher.fm;
import com.vungle.publisher.ft;
import com.vungle.publisher.fv;
import com.vungle.publisher.fw;
import dagger.MembersInjector;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public abstract class RequestAd<Q extends RequestAd<Q>> extends BaseJsonObject {
    protected String a;
    protected String b;
    protected Demographic c;
    protected DeviceInfo d;
    protected Boolean e;
    protected String f;

    /* compiled from: vungle */
    public static class Demographic extends BaseJsonObject {
        protected Integer a;
        protected Gender b;
        protected Location c;

        @Singleton
        /* compiled from: vungle */
        public static class Factory extends MessageFactory<Demographic> {
            @Inject
            public Context a;
            @Inject
            public com.vungle.publisher.Demographic b;
            @Inject
            public Factory c;

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
                r0 = new com.vungle.publisher.protocol.message.RequestAd$Demographic;
                r0.<init>();
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory.a():java.lang.Object");
            }

            protected final /* bridge */ /* synthetic */ java.lang.Object[] a(int r2) {
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
                r0 = new com.vungle.publisher.protocol.message.RequestAd.Demographic[r2];
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory.a(int):java.lang.Object[]");
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory.<init>():void");
            }

            protected final com.vungle.publisher.protocol.message.RequestAd.Demographic b() {
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
                r0 = r3.b;
                r1 = new com.vungle.publisher.protocol.message.RequestAd$Demographic;
                r1.<init>();
                r2 = r0.getAge();
                r1.a = r2;
                r0 = r0.getGender();
                r1.b = r0;
                r0 = r3.a;
                r2 = "android.permission.ACCESS_FINE_LOCATION";
                r0 = r0.checkCallingOrSelfPermission(r2);
                if (r0 != 0) goto L_0x0029;
            L_0x001d:
                r0 = 1;
            L_0x001e:
                if (r0 == 0) goto L_0x0028;
            L_0x0020:
                r0 = r3.c;
                r0 = r0.b();
                r1.c = r0;
            L_0x0028:
                return r1;
            L_0x0029:
                r0 = 0;
                goto L_0x001e;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory.b():com.vungle.publisher.protocol.message.RequestAd$Demographic");
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
                r0 = com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory_Factory.class;
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory_Factory.<clinit>():void");
            }

            public Factory_Factory(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory> r2) {
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory_Factory.<init>(dagger.MembersInjector):void");
            }

            public final com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory get() {
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
                r1 = new com.vungle.publisher.protocol.message.RequestAd$Demographic$Factory;
                r1.<init>();
                r0 = dagger.internal.MembersInjectors.injectMembers(r0, r1);
                r0 = (com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory) r0;
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory_Factory.get():com.vungle.publisher.protocol.message.RequestAd$Demographic$Factory");
            }

            public static dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory> create(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory> r1) {
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
                r0 = new com.vungle.publisher.protocol.message.RequestAd$Demographic$Factory_Factory;
                r0.<init>(r1);
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory_Factory.create(dagger.MembersInjector):dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAd$Demographic$Factory>");
            }
        }

        /* compiled from: vungle */
        public static class Location extends BaseJsonObject {
            protected Float a;
            protected Double b;
            protected Double c;
            protected Float d;
            protected Long e;

            @Singleton
            /* compiled from: vungle */
            public static class Factory extends MessageFactory<Location> {
                @Inject
                public ft a;

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
                    r0 = new com.vungle.publisher.protocol.message.RequestAd$Demographic$Location;
                    r0.<init>();
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory.a():java.lang.Object");
                }

                protected final /* bridge */ /* synthetic */ java.lang.Object[] a(int r2) {
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
                    r0 = new com.vungle.publisher.protocol.message.RequestAd.Demographic.Location[r2];
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory.a(int):java.lang.Object[]");
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
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory.<init>():void");
                }

                protected final com.vungle.publisher.protocol.message.RequestAd.Demographic.Location b() {
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
                    r0 = 0;
                    r1 = r4.a;
                    r1 = r1.b();
                    if (r1 != 0) goto L_0x0011;
                L_0x0009:
                    r1 = "VungleProtocol";
                    r2 = "detailed location not available";
                    com.vungle.log.Logger.d(r1, r2);
                L_0x0010:
                    return r0;
                L_0x0011:
                    r0 = new com.vungle.publisher.protocol.message.RequestAd$Demographic$Location;
                    r0.<init>();
                    r2 = r1.getAccuracy();
                    r2 = java.lang.Float.valueOf(r2);
                    r0.a = r2;
                    r2 = r1.getLatitude();
                    r2 = java.lang.Double.valueOf(r2);
                    r0.b = r2;
                    r2 = r1.getLongitude();
                    r2 = java.lang.Double.valueOf(r2);
                    r0.c = r2;
                    r2 = r1.getSpeed();
                    r2 = java.lang.Float.valueOf(r2);
                    r0.d = r2;
                    r2 = r1.getTime();
                    r1 = java.lang.Long.valueOf(r2);
                    r0.e = r1;
                    goto L_0x0010;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory.b():com.vungle.publisher.protocol.message.RequestAd$Demographic$Location");
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
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory_Factory.class;
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
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory_Factory.<clinit>():void");
                }

                public Factory_Factory(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory> r2) {
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
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory_Factory.<init>(dagger.MembersInjector):void");
                }

                public final com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory get() {
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
                    r1 = new com.vungle.publisher.protocol.message.RequestAd$Demographic$Location$Factory;
                    r1.<init>();
                    r0 = dagger.internal.MembersInjectors.injectMembers(r0, r1);
                    r0 = (com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory) r0;
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory_Factory.get():com.vungle.publisher.protocol.message.RequestAd$Demographic$Location$Factory");
                }

                public static dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory> create(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory> r1) {
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
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = new com.vungle.publisher.protocol.message.RequestAd$Demographic$Location$Factory_Factory;
                    r0.<init>(r1);
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory_Factory.create(dagger.MembersInjector):dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAd$Demographic$Location$Factory>");
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.b():java.lang.Object");
            }

            @javax.inject.Inject
            Location() {
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.<init>():void");
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
                r1 = "accuracyMeters";
                r2 = r3.a;
                r0.putOpt(r1, r2);
                r1 = "lat";
                r2 = r3.b;
                r0.putOpt(r1, r2);
                r1 = "long";
                r2 = r3.c;
                r0.putOpt(r1, r2);
                r1 = "speedMetersPerSecond";
                r2 = r3.d;
                r0.putOpt(r1, r2);
                r1 = "timestampMillis";
                r2 = r3.e;
                r0.putOpt(r1, r2);
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.a():org.json.JSONObject");
            }
        }

        /* compiled from: vungle */
        public final class Location_Factory implements dagger.internal.Factory<Location> {
            static final /* synthetic */ boolean a;
            private final MembersInjector<Location> b;

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
                r0 = com.vungle.publisher.protocol.message.RequestAd.Demographic.Location_Factory.class;
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location_Factory.<clinit>():void");
            }

            public Location_Factory(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAd.Demographic.Location> r2) {
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location_Factory.<init>(dagger.MembersInjector):void");
            }

            public final com.vungle.publisher.protocol.message.RequestAd.Demographic.Location get() {
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
                r1 = new com.vungle.publisher.protocol.message.RequestAd$Demographic$Location;
                r1.<init>();
                r0 = dagger.internal.MembersInjectors.injectMembers(r0, r1);
                r0 = (com.vungle.publisher.protocol.message.RequestAd.Demographic.Location) r0;
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location_Factory.get():com.vungle.publisher.protocol.message.RequestAd$Demographic$Location");
            }

            public static dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAd.Demographic.Location> create(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAd.Demographic.Location> r1) {
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
                r0 = new com.vungle.publisher.protocol.message.RequestAd$Demographic$Location_Factory;
                r0.<init>(r1);
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.Location_Factory.create(dagger.MembersInjector):dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAd$Demographic$Location>");
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
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.b():java.lang.Object");
        }

        protected Demographic() {
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
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.<init>():void");
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
            r1 = "age";
            r2 = r3.a;
            r0.putOpt(r1, r2);
            r1 = "gender";
            r2 = r3.b;
            r0.putOpt(r1, r2);
            r1 = "location";
            r2 = r3.c;
            r2 = com.vungle.publisher.fm.a(r2);
            r0.putOpt(r1, r2);
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.Demographic.a():org.json.JSONObject");
        }
    }

    /* compiled from: vungle */
    public static class DeviceInfo extends BaseJsonObject {
        protected fv a;
        protected DisplayDimension b;
        protected Boolean c;
        protected Boolean d;
        protected String e;
        protected String f;
        protected String g;
        protected String h;
        protected a i;
        protected Float j;
        protected String k;

        /* compiled from: vungle */
        public static class DisplayDimension extends BaseJsonObject {
            protected Integer a;
            protected Integer b;

            @Singleton
            /* compiled from: vungle */
            public static class Factory extends MessageFactory<DisplayDimension> {
                @Inject
                public ek a;

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
                    r0 = new com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension;
                    r0.<init>();
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory.a():java.lang.Object");
                }

                protected final /* bridge */ /* synthetic */ java.lang.Object[] a(int r2) {
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
                    r0 = new com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension[r2];
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory.a(int):java.lang.Object[]");
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
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory.<init>():void");
                }

                protected final com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension b() {
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
                    r1 = r3.a;
                    r1 = r1.h();
                    r2 = r1.heightPixels;
                    if (r2 > 0) goto L_0x000f;
                L_0x000b:
                    r2 = r1.widthPixels;
                    if (r2 <= 0) goto L_0x0024;
                L_0x000f:
                    r0 = new com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension;
                    r0.<init>();
                    r2 = r1.heightPixels;
                    r2 = java.lang.Integer.valueOf(r2);
                    r0.a = r2;
                    r1 = r1.widthPixels;
                    r1 = java.lang.Integer.valueOf(r1);
                    r0.b = r1;
                L_0x0024:
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory.b():com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension");
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
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory_Factory.class;
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
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory_Factory.<clinit>():void");
                }

                public Factory_Factory(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory> r2) {
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
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory_Factory.<init>(dagger.MembersInjector):void");
                }

                public final com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory get() {
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
                    r1 = new com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension$Factory;
                    r1.<init>();
                    r0 = dagger.internal.MembersInjectors.injectMembers(r0, r1);
                    r0 = (com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory) r0;
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory_Factory.get():com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension$Factory");
                }

                public static dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory> create(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory> r1) {
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
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = new com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension$Factory_Factory;
                    r0.<init>(r1);
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory_Factory.create(dagger.MembersInjector):dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension$Factory>");
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.b():java.lang.Object");
            }

            @javax.inject.Inject
            DisplayDimension() {
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.<init>():void");
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
                r1 = "height";
                r2 = r3.a;
                r0.putOpt(r1, r2);
                r1 = "width";
                r2 = r3.b;
                r0.putOpt(r1, r2);
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.a():org.json.JSONObject");
            }
        }

        /* compiled from: vungle */
        public final class DisplayDimension_Factory implements dagger.internal.Factory<DisplayDimension> {
            static final /* synthetic */ boolean a;
            private final MembersInjector<DisplayDimension> b;

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
                r0 = com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension_Factory.class;
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension_Factory.<clinit>():void");
            }

            public DisplayDimension_Factory(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension> r2) {
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension_Factory.<init>(dagger.MembersInjector):void");
            }

            public final com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension get() {
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
                r1 = new com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension;
                r1.<init>();
                r0 = dagger.internal.MembersInjectors.injectMembers(r0, r1);
                r0 = (com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension) r0;
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension_Factory.get():com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension");
            }

            public static dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension> create(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension> r1) {
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
                r0 = new com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension_Factory;
                r0.<init>(r1);
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension_Factory.create(dagger.MembersInjector):dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension>");
            }
        }

        @Singleton
        /* compiled from: vungle */
        public static class Factory extends MessageFactory<DeviceInfo> {
            @Inject
            public AdConfig a;
            @Inject
            public ek b;
            @Inject
            public Factory c;
            @Inject
            public fw d;
            @Inject
            public em e;

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
                r0 = new com.vungle.publisher.protocol.message.RequestAd$DeviceInfo;
                r0.<init>();
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory.a():java.lang.Object");
            }

            protected final /* bridge */ /* synthetic */ java.lang.Object[] a(int r2) {
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
                r0 = new com.vungle.publisher.protocol.message.RequestAd.DeviceInfo[r2];
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory.a(int):java.lang.Object[]");
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory.<init>():void");
            }

            protected final com.vungle.publisher.protocol.message.RequestAd.DeviceInfo b() {
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
                r0 = new com.vungle.publisher.protocol.message.RequestAd$DeviceInfo;
                r0.<init>();
                r1 = r2.d;
                r1 = r1.a();
                r0.a = r1;
                r1 = r2.c;
                r1 = r1.b();
                r0.b = r1;
                r1 = r2.b;
                r1 = r1.o();
                r1 = java.lang.Boolean.valueOf(r1);
                r0.c = r1;
                r1 = r2.a;
                r1 = r1.isSoundEnabled();
                r1 = java.lang.Boolean.valueOf(r1);
                r0.d = r1;
                r1 = r2.b;
                r1 = r1.j();
                r0.e = r1;
                r1 = r2.b;
                r1 = r1.m();
                r0.f = r1;
                r1 = r2.d;
                r1 = r1.b();
                r0.g = r1;
                r1 = r2.b;
                r1 = r1.g();
                r0.h = r1;
                r1 = com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.a.a;
                r0.i = r1;
                r1 = r2.b;
                r1 = r1.n();
                r0.j = r1;
                r1 = r2.b;
                r1 = r1.r();
                r0.k = r1;
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory.b():com.vungle.publisher.protocol.message.RequestAd$DeviceInfo");
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
                r0 = com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory_Factory.class;
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory_Factory.<clinit>():void");
            }

            public Factory_Factory(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory> r2) {
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory_Factory.<init>(dagger.MembersInjector):void");
            }

            public final com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory get() {
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
                r1 = new com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$Factory;
                r1.<init>();
                r0 = dagger.internal.MembersInjectors.injectMembers(r0, r1);
                r0 = (com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory) r0;
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory_Factory.get():com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$Factory");
            }

            public static dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory> create(dagger.MembersInjector<com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory> r1) {
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
                r0 = new com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$Factory_Factory;
                r0.<init>(r1);
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory_Factory.create(dagger.MembersInjector):dagger.internal.Factory<com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$Factory>");
            }
        }

        public enum a {
            ;

            private a(java.lang.String r2) {
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.a.<init>(java.lang.String):void");
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
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.b():java.lang.Object");
        }

        protected DeviceInfo() {
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
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.<init>():void");
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
            r1 = super.a();
            r0 = "connection";
            r2 = r3.a;
            r1.putOpt(r0, r2);
            r0 = "dim";
            r2 = r3.b;
            r2 = com.vungle.publisher.fm.a(r2);
            r1.putOpt(r0, r2);
            r2 = "isSdCardAvailable";
            r0 = r3.c;
            if (r0 != 0) goto L_0x0059;
        L_0x001c:
            r0 = 0;
        L_0x001d:
            r1.putOpt(r2, r0);
            r0 = "soundEnabled";
            r2 = r3.d;
            r1.putOpt(r0, r2);
            r0 = "mac";
            r2 = r3.e;
            r1.putOpt(r0, r2);
            r0 = "model";
            r2 = r3.f;
            r1.putOpt(r0, r2);
            r0 = "networkOperator";
            r2 = r3.g;
            r1.putOpt(r0, r2);
            r0 = "osVersion";
            r2 = r3.h;
            r1.putOpt(r0, r2);
            r0 = "platform";
            r2 = r3.i;
            r1.putOpt(r0, r2);
            r0 = "volume";
            r2 = r3.j;
            r1.putOpt(r0, r2);
            r0 = "userAgent";
            r2 = r3.k;
            r1.putOpt(r0, r2);
            return r1;
        L_0x0059:
            r0 = r0.booleanValue();
            if (r0 == 0) goto L_0x0065;
        L_0x005f:
            r0 = 1;
        L_0x0060:
            r0 = java.lang.Integer.valueOf(r0);
            goto L_0x001d;
        L_0x0065:
            r0 = 0;
            goto L_0x0060;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.a():org.json.JSONObject");
        }
    }

    /* compiled from: vungle */
    public static abstract class Factory<Q extends RequestAd<Q>> extends MessageFactory<Q> {
        @Inject
        public Factory a;
        @Inject
        public ek b;
        @Inject
        public Factory c;
        @Inject
        public em d;

        protected Factory() {
        }

        protected Q b() {
            RequestAd requestAd = (RequestAd) a();
            requestAd.a = this.b.a();
            requestAd.b = this.b.c();
            requestAd.c = this.a.b();
            requestAd.d = this.c.b();
            requestAd.e = Boolean.valueOf(this.b.i());
            requestAd.f = this.d.b();
            return requestAd;
        }
    }

    public /* synthetic */ Object b() throws JSONException {
        return a();
    }

    public JSONObject a() throws JSONException {
        JSONObject a = super.a();
        a.putOpt("isu", this.b);
        a.putOpt("ifa", this.a);
        a.putOpt("demo", fm.a(this.c));
        a.putOpt("deviceInfo", fm.a(this.d));
        if (Boolean.FALSE.equals(this.e)) {
            a.putOpt("adTrackingEnabled", this.e);
        }
        a.putOpt("pubAppId", this.f);
        return a;
    }
}
