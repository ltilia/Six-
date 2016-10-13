package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.VunglePub;
import com.vungle.publisher.bs;
import com.vungle.publisher.cb;
import com.vungle.publisher.cb.a;
import com.vungle.publisher.ek;
import com.vungle.publisher.env.SdkConfig;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONArray;

/* compiled from: vungle */
public class LoggedException extends cb<Integer> {
    public long a;
    public String[] b;
    public int c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    @Inject
    public Factory j;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<LoggedException, Integer> {
        @Inject
        public ek a;
        @Inject
        public SdkConfig b;
        @Inject
        public Provider<LoggedException> d;

        public final /* bridge */ /* synthetic */ int a(List list) {
            return super.a(list);
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ cb[] c(int i) {
            return new LoggedException[i];
        }

        protected final /* synthetic */ cb c_() {
            return b();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        public final /* bridge */ /* synthetic */ List d(int i) {
            return super.d(i);
        }

        @Inject
        Factory() {
        }

        public final void a(String str, String str2, Throwable th) {
            a(2, str, str2, th);
        }

        public final void b(String str, String str2, Throwable th) {
            a(1, str, str2, th);
        }

        private void a(int i, String str, String str2, Throwable th) {
            Logger.e(str, str2, th);
            if (!this.b.b()) {
                return;
            }
            if (e() < 100) {
                LoggedException b = b();
                b.d = str;
                b.e = str2;
                b.f = th.getClass().toString();
                b.c = i;
                b.g = this.a.g();
                b.h = VunglePub.VERSION;
                b.i = this.a.p();
                String[] strArr = null;
                if (th != null) {
                    StackTraceElement[] stackTrace = th.getStackTrace();
                    if (stackTrace != null) {
                        String[] strArr2 = new String[stackTrace.length];
                        for (int i2 = 0; i2 < stackTrace.length; i2++) {
                            strArr2[i2] = stackTrace[i2].toString();
                        }
                        strArr = strArr2;
                    }
                }
                b.b = strArr;
                b.r();
                return;
            }
            Logger.w(str, "could not insert logged exception... too many already!");
        }

        protected final String e_() {
            return "logged_exceptions";
        }

        private static LoggedException a(LoggedException loggedException, Cursor cursor) {
            loggedException.a = bs.e(cursor, "insert_timestamp_millis").longValue();
            loggedException.c = bs.c(cursor, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
            loggedException.d = bs.f(cursor, "tag");
            loggedException.e = bs.f(cursor, "log_message");
            loggedException.f = bs.f(cursor, "class");
            loggedException.g = bs.f(cursor, "android_version");
            loggedException.h = bs.f(cursor, "sdk_version");
            loggedException.i = bs.f(cursor, "play_services_version");
            loggedException.s = bs.d(cursor, UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
            try {
                String[] strArr;
                String f = bs.f(cursor, "stack_trace");
                if (f == null) {
                    strArr = null;
                } else {
                    JSONArray jSONArray = new JSONArray(f);
                    strArr = new String[jSONArray.length()];
                    for (int i = 0; i < jSONArray.length(); i++) {
                        strArr[i] = jSONArray.getString(i);
                    }
                }
                loggedException.b = strArr;
            } catch (Throwable e) {
                Logger.e(Logger.DATABASE_TAG, "could not parse stack trace string", e);
            }
            return loggedException;
        }

        private LoggedException b() {
            return (LoggedException) this.d.get();
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

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.j;
    }

    @Inject
    LoggedException() {
    }

    protected final String b() {
        return "logged_exceptions";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, (Integer) s());
            long currentTimeMillis = System.currentTimeMillis();
            this.a = currentTimeMillis;
            contentValues.put("insert_timestamp_millis", Long.valueOf(currentTimeMillis));
        }
        contentValues.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, Integer.valueOf(this.c));
        contentValues.put("tag", this.d);
        contentValues.put("log_message", this.e);
        contentValues.put("class", this.f);
        contentValues.put("android_version", this.g);
        contentValues.put("sdk_version", this.h);
        contentValues.put("play_services_version", this.i);
        try {
            String str = "stack_trace";
            String[] strArr = this.b;
            String str2 = null;
            if (strArr != null) {
                str2 = new JSONArray(Arrays.asList(strArr)).toString();
            }
            contentValues.put(str, str2);
        } catch (Throwable e) {
            Logger.e(Logger.DATABASE_TAG, "could not parse stack trace array", e);
        }
        return contentValues;
    }
}
