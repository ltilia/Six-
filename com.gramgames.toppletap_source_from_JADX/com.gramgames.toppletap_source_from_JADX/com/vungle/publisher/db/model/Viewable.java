package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import com.google.android.gms.games.Games;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.bs;
import com.vungle.publisher.cb;
import com.vungle.publisher.cg;
import com.vungle.publisher.cg.a;
import com.vungle.publisher.cg.b;
import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.simple.parser.Yytoken;

/* compiled from: vungle */
public abstract class Viewable<A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends cb<Integer> implements cg<A, V, R> {
    protected String o;
    protected a p;
    protected b q;
    protected A u;

    /* compiled from: vungle */
    public static abstract class BaseFactory<A extends Ad<A, V, R>, W extends Viewable<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends cb.a<W, Integer> {
        protected BaseFactory() {
        }

        protected W b(A a, R r) {
            if (r == null) {
                return null;
            }
            Viewable viewable = (Viewable) c_();
            viewable.u = a;
            viewable.o = r.f();
            viewable.p = a.aware;
            return viewable;
        }

        protected final W a(String str, b bVar, boolean z) throws SQLException {
            Viewable viewable = (Viewable) c_();
            viewable.o = str;
            viewable.q = bVar;
            return a(viewable, z);
        }

        private W a(W w, boolean z) throws SQLException {
            Cursor query;
            Throwable th;
            W w2 = null;
            Integer num = (Integer) w.s;
            b bVar = w.q;
            try {
                String str;
                String str2 = w.o;
                String str3;
                if (num != null) {
                    str3 = "id: " + num;
                    Logger.d(Logger.DATABASE_TAG, "fetching " + bVar + " by " + str3);
                    query = this.c.getReadableDatabase().query("viewable", null, "id = ?", new String[]{String.valueOf(num)}, null, null, null);
                    str = str3;
                } else if (str2 == null) {
                    Logger.w(Logger.DATABASE_TAG, "unable to fetch " + bVar + ": no id or ad_id");
                    str = null;
                    query = null;
                } else {
                    str3 = "ad_id " + str2;
                    Logger.d(Logger.DATABASE_TAG, "fetching " + bVar + " by " + str3);
                    query = this.c.getReadableDatabase().query("viewable", null, "ad_id = ? AND type = ?", new String[]{str2, String.valueOf(bVar)}, null, null, null);
                    str = str3;
                }
                if (query != null) {
                    try {
                        int count = query.getCount();
                        switch (count) {
                            case Yylex.YYINITIAL /*0*/:
                                Logger.v(Logger.DATABASE_TAG, "no " + bVar + " found for " + str);
                                break;
                            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                                Logger.d(Logger.DATABASE_TAG, "have " + bVar + " for " + str);
                                query.moveToFirst();
                                w2 = a((Viewable) w, query, z);
                                break;
                            default:
                                throw new SQLException(count + " " + bVar + " records for " + str);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
                if (query != null) {
                    query.close();
                }
                Logger.v(Logger.DATABASE_TAG, "fetched " + w2);
                return w2;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }

        protected W a(W w, Cursor cursor, boolean z) {
            w.s = bs.d(cursor, UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
            w.o = bs.f(cursor, "ad_id");
            w.p = (a) bs.a(cursor, Games.EXTRA_STATUS, a.class);
            w.q = (b) bs.a(cursor, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, b.class);
            return w;
        }

        protected final String e_() {
            return "viewable";
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public DatabaseHelper a;

        @Inject
        Factory() {
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

    protected abstract com.vungle.publisher.db.model.Ad.Factory<A, V, R> B();

    public final /* bridge */ /* synthetic */ Object s() {
        return (Integer) this.s;
    }

    protected Viewable() {
    }

    protected final String b() {
        return "viewable";
    }

    public final Integer C() {
        return (Integer) this.s;
    }

    public final String d() {
        return this.o;
    }

    public final A c() {
        if (this.u == null) {
            this.u = (Ad) B().a((Object) this.o);
        }
        return this.u;
    }

    public final a e() {
        return this.p;
    }

    public final void a(a aVar) {
        Logger.v(Logger.PREPARE_TAG, "setting " + this.q + " status from " + this.p + " to " + aVar + " for ad_id: " + this.o);
        this.p = aVar;
    }

    public final void b(a aVar) {
        Logger.v(Logger.PREPARE_TAG, "updating " + this.q + " status from " + this.p + " to " + aVar + " for ad_id: " + this.o);
        this.p = aVar;
        m();
    }

    public final b f() {
        return this.q;
    }

    protected ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, (Integer) this.s);
            contentValues.put("ad_id", this.o);
            contentValues.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, this.q.toString());
        }
        contentValues.put(Games.EXTRA_STATUS, this.p.toString());
        return contentValues;
    }

    protected StringBuilder p() {
        StringBuilder p = super.p();
        cb.a(p, "ad_id", this.o, false);
        cb.a(p, Games.EXTRA_STATUS, this.p, false);
        cb.a(p, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, this.q, false);
        return p;
    }

    protected final String y() {
        return String.valueOf(this.q);
    }
}
