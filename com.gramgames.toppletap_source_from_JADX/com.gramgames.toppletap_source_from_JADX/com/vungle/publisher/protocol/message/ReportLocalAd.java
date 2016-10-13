package com.vungle.publisher.protocol.message;

import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LocalAdPlay;
import com.vungle.publisher.db.model.LocalAdReport;
import com.vungle.publisher.db.model.LocalAdReportEvent;
import com.vungle.publisher.db.model.LocalVideo;
import com.vungle.publisher.fm;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class ReportLocalAd extends ReportAd<RequestLocalAd, ReportLocalAd> {
    ExtraInfo m;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.protocol.message.ReportAd.Factory<RequestLocalAd, RequestLocalAdResponse, ReportLocalAd, LocalAdReport, LocalAdPlay, LocalAdReportEvent, LocalAd, LocalVideo> {
        @Inject
        public PlayFactory b;
        @Inject
        public com.vungle.publisher.protocol.message.RequestLocalAd.Factory c;

        @Singleton
        /* compiled from: vungle */
        public static class PlayFactory extends com.vungle.publisher.protocol.message.ReportAd.Play.Factory<RequestLocalAdResponse, LocalAdReport, LocalAdPlay, LocalAdReportEvent, LocalAd, LocalVideo> {
            @Inject
            public UserActionFactory a;

            @Singleton
            /* compiled from: vungle */
            public static class UserActionFactory extends com.vungle.publisher.protocol.message.ReportAd.Play.UserAction.Factory<RequestLocalAdResponse, LocalAdReport, LocalAdPlay, LocalAdReportEvent, LocalAd, LocalVideo> {
                @javax.inject.Inject
                UserActionFactory() {
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
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory.UserActionFactory.<init>():void");
                }
            }

            /* compiled from: vungle */
            public final class UserActionFactory_Factory implements dagger.internal.Factory<UserActionFactory> {
                static final /* synthetic */ boolean a;
                private final MembersInjector<UserActionFactory> b;

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
                    r0 = com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory.UserActionFactory_Factory.class;
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
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory.UserActionFactory_Factory.<clinit>():void");
                }

                public UserActionFactory_Factory(dagger.MembersInjector<com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory.UserActionFactory> r2) {
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
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory.UserActionFactory_Factory.<init>(dagger.MembersInjector):void");
                }

                public final com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory.UserActionFactory get() {
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
                    r1 = new com.vungle.publisher.protocol.message.ReportLocalAd$Factory$PlayFactory$UserActionFactory;
                    r1.<init>();
                    r0 = dagger.internal.MembersInjectors.injectMembers(r0, r1);
                    r0 = (com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory.UserActionFactory) r0;
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory.UserActionFactory_Factory.get():com.vungle.publisher.protocol.message.ReportLocalAd$Factory$PlayFactory$UserActionFactory");
                }

                public static dagger.internal.Factory<com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory.UserActionFactory> create(dagger.MembersInjector<com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory.UserActionFactory> r1) {
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
                    r0 = new com.vungle.publisher.protocol.message.ReportLocalAd$Factory$PlayFactory$UserActionFactory_Factory;
                    r0.<init>(r1);
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory.UserActionFactory_Factory.create(dagger.MembersInjector):dagger.internal.Factory<com.vungle.publisher.protocol.message.ReportLocalAd$Factory$PlayFactory$UserActionFactory>");
                }
            }

            @javax.inject.Inject
            PlayFactory() {
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory.<init>():void");
            }
        }

        /* compiled from: vungle */
        public final class PlayFactory_Factory implements dagger.internal.Factory<PlayFactory> {
            static final /* synthetic */ boolean a;
            private final MembersInjector<PlayFactory> b;

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
                r0 = com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory_Factory.class;
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory_Factory.<clinit>():void");
            }

            public PlayFactory_Factory(dagger.MembersInjector<com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory> r2) {
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
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory_Factory.<init>(dagger.MembersInjector):void");
            }

            public final com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory get() {
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
                r1 = new com.vungle.publisher.protocol.message.ReportLocalAd$Factory$PlayFactory;
                r1.<init>();
                r0 = dagger.internal.MembersInjectors.injectMembers(r0, r1);
                r0 = (com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory) r0;
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory_Factory.get():com.vungle.publisher.protocol.message.ReportLocalAd$Factory$PlayFactory");
            }

            public static dagger.internal.Factory<com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory> create(dagger.MembersInjector<com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory> r1) {
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
                r0 = new com.vungle.publisher.protocol.message.ReportLocalAd$Factory$PlayFactory_Factory;
                r0.<init>(r1);
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory_Factory.create(dagger.MembersInjector):dagger.internal.Factory<com.vungle.publisher.protocol.message.ReportLocalAd$Factory$PlayFactory>");
            }
        }

        public final /* synthetic */ com.vungle.publisher.protocol.message.ReportAd a(com.vungle.publisher.db.model.AdReport r3) {
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
            r2 = this;
            r3 = (com.vungle.publisher.db.model.LocalAdReport) r3;
            r0 = super.a(r3);
            r0 = (com.vungle.publisher.protocol.message.ReportLocalAd) r0;
            if (r0 == 0) goto L_0x0030;
        L_0x000a:
            r1 = r3.z();
            r1 = java.lang.Integer.valueOf(r1);
            r0.a = r1;
            r1 = r3.f();
            r1 = com.vungle.publisher.protocol.message.ExtraInfo.Factory.b(r1);
            r0.m = r1;
            r1 = r3.e();
            r1 = (com.vungle.publisher.db.model.LocalAd) r1;
            r1 = r1.k();
            r1 = (com.vungle.publisher.db.model.LocalVideo) r1;
            r1 = r1.b;
            r1 = r1.b;
            r0.l = r1;
        L_0x0030:
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.a(com.vungle.publisher.db.model.AdReport):com.vungle.publisher.protocol.message.ReportAd");
        }

        protected final /* synthetic */ java.lang.Object a() {
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
            r0 = new com.vungle.publisher.protocol.message.ReportLocalAd;
            r0.<init>();
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.a():java.lang.Object");
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.protocol.message.RequestAd.Factory b() {
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
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.b():com.vungle.publisher.protocol.message.RequestAd$Factory");
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
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.protocol.message.ReportLocalAd.Factory.<init>():void");
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

    public final JSONObject a() throws JSONException {
        JSONObject a = super.a();
        if (a != null) {
            a.putOpt("extraInfo", fm.a(this.m));
        }
        return a;
    }
}
