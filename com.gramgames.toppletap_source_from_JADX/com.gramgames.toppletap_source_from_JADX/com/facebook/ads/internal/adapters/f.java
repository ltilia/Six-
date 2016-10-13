package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.server.AdPlacementType;
import com.facebook.ads.internal.util.t;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.simple.parser.Yytoken;

public class f {
    private static final Set<h> a;
    private static final Map<AdPlacementType, String> b;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[AdPlacementType.values().length];
            try {
                a[AdPlacementType.BANNER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[AdPlacementType.INTERSTITIAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[AdPlacementType.NATIVE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    static {
        a = new HashSet();
        b = new ConcurrentHashMap();
        for (h hVar : h.values()) {
            Class cls;
            switch (1.a[hVar.g.ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    cls = BannerAdapter.class;
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    cls = InterstitialAdapter.class;
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    cls = p.class;
                    break;
                default:
                    cls = null;
                    break;
            }
            if (cls != null) {
                Class cls2 = hVar.d;
                if (cls2 == null) {
                    try {
                        cls2 = Class.forName(hVar.e);
                    } catch (ClassNotFoundException e) {
                    }
                }
                if (cls2 != null && cls.isAssignableFrom(cls2)) {
                    a.add(hVar);
                }
            }
        }
    }

    public static AdAdapter a(g gVar, AdPlacementType adPlacementType) {
        try {
            h b = b(gVar, adPlacementType);
            if (b != null && a.contains(b)) {
                Class cls = b.d;
                if (cls == null) {
                    cls = Class.forName(b.e);
                }
                return (AdAdapter) cls.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AdAdapter a(String str, AdPlacementType adPlacementType) {
        return a(g.a(str), adPlacementType);
    }

    public static String a(AdPlacementType adPlacementType) {
        if (b.containsKey(adPlacementType)) {
            return (String) b.get(adPlacementType);
        }
        Set hashSet = new HashSet();
        for (h hVar : a) {
            if (hVar.g == adPlacementType) {
                hashSet.add(hVar.f.toString());
            }
        }
        String a = t.a(hashSet, ",");
        b.put(adPlacementType, a);
        return a;
    }

    private static h b(g gVar, AdPlacementType adPlacementType) {
        for (h hVar : a) {
            if (hVar.f == gVar && hVar.g == adPlacementType) {
                return hVar;
            }
        }
        return null;
    }
}
