package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.db.DatabaseHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import org.json.simple.parser.Yytoken;

/* compiled from: vungle */
public abstract class cb<I> implements cy<I> {
    public Class<I> r;
    public I s;
    @Inject
    public DatabaseHelper t;

    public static abstract class a<T extends cb<I>, I> {
        @Inject
        public DatabaseHelper c;

        public abstract T a(T t, Cursor cursor);

        public abstract I[] b(int i);

        public abstract T[] c(int i);

        public abstract T c_();

        public abstract String e_();

        public final int a(I... iArr) {
            int i = 0;
            int length = iArr == null ? 0 : iArr.length;
            if (length == 0) {
                Logger.d(Logger.DATABASE_TAG, "no " + e_() + " records requested for delete");
                return 0;
            }
            int i2;
            c_();
            boolean z = iArr instanceof String[];
            String[] strArr = z ? (String[]) iArr : new String[length];
            if (!z) {
                int length2 = iArr.length;
                i2 = 0;
                while (i < length2) {
                    int i3 = i2 + 1;
                    strArr[i2] = String.valueOf(iArr[i]);
                    i++;
                    i2 = i3;
                }
            }
            i2 = this.c.getWritableDatabase().delete(e_(), "id IN (" + bs.a(length) + ")", strArr);
            if (i2 == length) {
                Logger.d(Logger.DATABASE_TAG, "deleted " + i2 + " " + e_() + " records by id in " + jj.b(iArr));
                return i2;
            }
            Logger.w(Logger.DATABASE_TAG, "deleted " + i2 + " of " + length + " requested records by id in " + jj.b(iArr));
            return i2;
        }

        public int a(List<T> list) {
            Object[] b;
            int i = 0;
            cb[] cbVarArr = list == null ? null : (cb[]) list.toArray(c(list.size()));
            if (cbVarArr != null) {
                b = b(cbVarArr.length);
                int length = cbVarArr.length;
                int i2 = 0;
                while (i2 < length) {
                    int i3 = i + 1;
                    b[i] = cbVarArr[i2].s();
                    i2++;
                    i = i3;
                }
            } else {
                b = null;
            }
            return a(b);
        }

        public List<T> d() {
            return a(null, null, null);
        }

        public List<T> d(int i) {
            return a(null, null, null, Integer.toString(i));
        }

        public final T a(I i) {
            return a((Object) i, null, null);
        }

        public final T a(I i, String str, String[] strArr) {
            cb c_ = c_();
            c_.a((Object) i);
            return a(c_, str, strArr);
        }

        final T a(T t, String str, String[] strArr) {
            if (t == null) {
                throw new IllegalArgumentException("null model");
            }
            String str2 = UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY;
            Object s = t.s();
            if (s == null) {
                throw new IllegalArgumentException("null " + str2);
            }
            StringBuilder append = new StringBuilder().append(str2).append(" = ?");
            Iterable arrayList = new ArrayList();
            arrayList.add(String.valueOf(s));
            if (str != null) {
                append.append(" AND ").append(str);
                if (strArr != null) {
                    arrayList.addAll(Arrays.asList(strArr));
                }
            }
            String stringBuilder = append.toString();
            List a = a(stringBuilder, (String[]) arrayList.toArray(new String[arrayList.size()]), null, null);
            int size = a.size();
            switch (size) {
                case Yylex.YYINITIAL /*0*/:
                    return null;
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    return (cb) a.get(0);
                default:
                    throw new SQLException(size + " " + e_() + " records found for query: " + stringBuilder + ", parameters: " + jj.a(arrayList));
            }
        }

        public final List<T> a(String str, String[] strArr, String str2) {
            return a(str, strArr, str2, null);
        }

        public final List<T> a(String str, String[] strArr, String str2, String str3) {
            Throwable th;
            Cursor cursor;
            try {
                String e_ = e_();
                Logger.d(Logger.DATABASE_TAG, "fetching " + (str == null ? "all " + e_ + " records" : e_ + " records by " + str + " " + jj.b(strArr)));
                Cursor query = this.c.getReadableDatabase().query(e_, null, str, strArr, null, null, str2, str3);
                try {
                    int count = query.getCount();
                    Logger.v(Logger.DATABASE_TAG, (count == 0 ? "no " : "fetched " + count + " ") + e_ + " records by " + str + " " + jj.b(strArr));
                    List<T> a = a(query);
                    if (query != null) {
                        query.close();
                    }
                    return a;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        public final boolean a(String str, String[] strArr) {
            boolean z = false;
            Cursor cursor = null;
            try {
                cursor = this.c.getWritableDatabase().rawQuery("SELECT EXISTS (SELECT 1 FROM " + e_() + " WHERE " + str + " LIMIT 1)", strArr);
                if (cursor.moveToFirst() && cursor.getInt(0) != 0) {
                    z = true;
                }
                if (cursor != null) {
                    cursor.close();
                }
                return z;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        public final int e() {
            Cursor cursor = null;
            int i = 0;
            try {
                cursor = this.c.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + e_(), null);
                if (cursor.moveToFirst()) {
                    i = cursor.getInt(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
                return i;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        public static void a(T... tArr) {
            if (tArr != null) {
                for (cb r : tArr) {
                    r.r();
                }
            }
        }

        private List<T> a(Cursor cursor) {
            List<T> arrayList = new ArrayList(cursor.getCount());
            while (cursor.moveToNext()) {
                arrayList.add(b(c_(), cursor));
            }
            return arrayList;
        }

        public final T b(T t, Cursor cursor) {
            a((cb) t, cursor);
            Logger.v(Logger.DATABASE_TAG, "fetched " + t);
            return t;
        }
    }

    public abstract ContentValues a(boolean z);

    public abstract <T extends cb<I>> a<T, I> a_();

    public abstract String b();

    public static void a(StringBuilder stringBuilder, String str, Object obj, boolean z) {
        if (!z) {
            stringBuilder.append(", ");
        }
        stringBuilder.append(str).append(": ").append(obj);
    }

    public I s() {
        return this.s;
    }

    protected final void a(I i) {
        this.s = i;
    }

    public boolean f_() {
        return true;
    }

    public I r() {
        Object s = s();
        if (!f_() || s == null) {
            Logger.d(Logger.DATABASE_TAG, "inserting " + this);
            long insertOrThrow = this.t.getWritableDatabase().insertOrThrow(b(), null, a(true));
            if (this.r == null || Integer.class.equals(this.r)) {
                this.s = Integer.valueOf((int) insertOrThrow);
            } else if (Long.class.equals(this.r)) {
                this.s = Long.valueOf(insertOrThrow);
            }
            Logger.v(Logger.DATABASE_TAG, "inserted " + this);
            return s();
        }
        throw new SQLException("attempt to insert with non-auto generated id " + x());
    }

    public final void v() {
        a_().a(this, null, null);
    }

    public final I w() {
        I s = s();
        if (s == null) {
            return r();
        }
        m();
        return s;
    }

    public int m() {
        String str = UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY;
        Object s = s();
        if (s == null) {
            throw new IllegalArgumentException("null " + str);
        }
        String b = b();
        String str2 = str + " " + s;
        int updateWithOnConflict = this.t.getWritableDatabase().updateWithOnConflict(b, a(false), "id = ?", new String[]{s.toString()}, 3);
        switch (updateWithOnConflict) {
            case Yylex.YYINITIAL /*0*/:
                Logger.d(Logger.DATABASE_TAG, "no " + b + " rows updated by " + str2);
                break;
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                Logger.d(Logger.DATABASE_TAG, "update successful " + x());
                break;
            default:
                Logger.w(Logger.DATABASE_TAG, "updated " + updateWithOnConflict + " " + b + " records for " + str2);
                break;
        }
        return updateWithOnConflict;
    }

    public int n() {
        return a_().a(s());
    }

    public final String x() {
        return o().append('}').toString();
    }

    public StringBuilder o() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{').append(y()).append(":: ");
        a(stringBuilder, UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, s(), true);
        return stringBuilder;
    }

    public String y() {
        return b();
    }

    public String toString() {
        return p().append('}').toString();
    }

    public StringBuilder p() {
        return o();
    }
}
