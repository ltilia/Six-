package com.vungle.publisher;

import android.os.Looper;
import com.vungle.log.Logger;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.simple.parser.Yytoken;

/* compiled from: vungle */
public final class jp {
    static ExecutorService a;
    private static final Map<Class<?>, List<Class<?>>> b;
    private final Map<Class<?>, CopyOnWriteArrayList<jy>> c;
    private final Map<Object, List<Class<?>>> d;
    private final Map<Class<?>, Object> e;
    private final ThreadLocal<a> f;
    private final jr g;
    private final jo h;
    private final jn i;
    private final jx j;
    private boolean k;
    private boolean l;

    class 1 extends ThreadLocal<a> {
        final /* synthetic */ jp a;

        1(jp jpVar) {
            this.a = jpVar;
        }

        protected final /* synthetic */ Object initialValue() {
            return new a();
        }
    }

    static /* synthetic */ class 2 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[jz.values().length];
            try {
                a[jz.PostThread.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[jz.MainThread.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[jz.BackgroundThread.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[jz.Async.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    static final class a {
        List<Object> a;
        boolean b;
        boolean c;
        jy d;
        Object e;
        boolean f;

        a() {
            this.a = new ArrayList();
        }
    }

    static {
        a = Executors.newCachedThreadPool();
        b = new HashMap();
    }

    public jp() {
        this.f = new 1(this);
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = new ConcurrentHashMap();
        this.g = new jr(this, Looper.getMainLooper());
        this.h = new jo(this);
        this.i = new jn(this);
        this.j = new jx();
        this.l = true;
    }

    public final synchronized void a(Object obj, String str, boolean z) {
        for (jw jwVar : jx.a(obj.getClass(), str)) {
            CopyOnWriteArrayList copyOnWriteArrayList;
            this.k = true;
            Class cls = jwVar.c;
            CopyOnWriteArrayList copyOnWriteArrayList2 = (CopyOnWriteArrayList) this.c.get(cls);
            jy jyVar = new jy(obj, jwVar);
            if (copyOnWriteArrayList2 == null) {
                copyOnWriteArrayList2 = new CopyOnWriteArrayList();
                this.c.put(cls, copyOnWriteArrayList2);
                copyOnWriteArrayList = copyOnWriteArrayList2;
            } else {
                Iterator it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    if (((jy) it.next()).equals(jyVar)) {
                        throw new jq("Subscriber " + obj.getClass() + " already registered to event " + cls);
                    }
                }
                copyOnWriteArrayList = copyOnWriteArrayList2;
            }
            int size = copyOnWriteArrayList.size();
            int i = 0;
            while (i <= size) {
                if (i == size || jyVar.c > ((jy) copyOnWriteArrayList.get(i)).c) {
                    copyOnWriteArrayList.add(i, jyVar);
                    break;
                }
                i++;
            }
            List list = (List) this.d.get(obj);
            if (list == null) {
                list = new ArrayList();
                this.d.put(obj, list);
            }
            list.add(cls);
            if (z) {
                Object obj2;
                synchronized (this.e) {
                    obj2 = this.e.get(cls);
                }
                if (obj2 != null) {
                    a(jyVar, obj2, Looper.getMainLooper() == Looper.myLooper());
                } else {
                    continue;
                }
            }
        }
    }

    public final synchronized void a(Object obj) {
        List<Class> list = (List) this.d.get(obj);
        if (list != null) {
            for (Class cls : list) {
                List list2 = (List) this.c.get(cls);
                if (list2 != null) {
                    int size = list2.size();
                    int i = 0;
                    while (i < size) {
                        int i2;
                        jy jyVar = (jy) list2.get(i);
                        if (jyVar.a == obj) {
                            jyVar.d = false;
                            list2.remove(i);
                            i2 = i - 1;
                            i = size - 1;
                        } else {
                            i2 = i;
                            i = size;
                        }
                        size = i;
                        i = i2 + 1;
                    }
                }
            }
            this.d.remove(obj);
        } else {
            Logger.w(Logger.EVENT_TAG, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public final void b(Object obj) {
        a aVar = (a) this.f.get();
        List list = aVar.a;
        list.add(obj);
        if (!aVar.b) {
            boolean z;
            if (Looper.getMainLooper() == Looper.myLooper()) {
                z = true;
            } else {
                z = false;
            }
            aVar.c = z;
            aVar.b = true;
            if (aVar.f) {
                throw new jq("Internal error. Abort state was not reset");
            }
            while (!list.isEmpty()) {
                Object remove = list.remove(0);
                Class cls = remove.getClass();
                List a = a(cls);
                int size = a.size();
                int i = 0;
                boolean z2 = false;
                while (i < size) {
                    CopyOnWriteArrayList copyOnWriteArrayList;
                    Class cls2 = (Class) a.get(i);
                    synchronized (this) {
                        copyOnWriteArrayList = (CopyOnWriteArrayList) this.c.get(cls2);
                    }
                    if (copyOnWriteArrayList != null) {
                        if (!copyOnWriteArrayList.isEmpty()) {
                            Iterator it = copyOnWriteArrayList.iterator();
                            while (it.hasNext()) {
                                jy jyVar = (jy) it.next();
                                aVar.e = remove;
                                aVar.d = jyVar;
                                a(jyVar, remove, aVar.c);
                                z = aVar.f;
                                aVar.e = null;
                                aVar.d = null;
                                aVar.f = false;
                                if (z) {
                                    break;
                                }
                            }
                            z = true;
                            i++;
                            z2 = z;
                        }
                    }
                    z = z2;
                    i++;
                    z2 = z;
                }
                if (!z2) {
                    try {
                        Logger.d(Logger.EVENT_TAG, "No subscribers registered for event " + cls);
                        if (!(cls == js.class || cls == jv.class)) {
                            b(new js(this, remove));
                        }
                    } catch (Throwable th) {
                        aVar.b = false;
                        aVar.c = false;
                    }
                }
            }
            aVar.b = false;
            aVar.c = false;
        }
    }

    private void a(jy jyVar, Object obj, boolean z) {
        jt a;
        switch (2.a[jyVar.b.b.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                a(jyVar, obj);
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                if (z) {
                    a(jyVar, obj);
                    return;
                }
                jr jrVar = this.g;
                a = jt.a(jyVar, obj);
                synchronized (jrVar) {
                    jrVar.a.a(a);
                    if (!jrVar.b) {
                        jrVar.b = true;
                        if (!jrVar.sendMessage(jrVar.obtainMessage())) {
                            throw new jq("Could not send handler message");
                        }
                    }
                    break;
                }
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                if (z) {
                    Runnable runnable = this.h;
                    a = jt.a(jyVar, obj);
                    synchronized (runnable) {
                        runnable.a.a(a);
                        if (!runnable.b) {
                            runnable.b = true;
                            a.execute(runnable);
                        }
                        break;
                    }
                    return;
                }
                a(jyVar, obj);
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                Runnable runnable2 = this.i;
                runnable2.a.a(jt.a(jyVar, obj));
                a.execute(runnable2);
            default:
                throw new IllegalStateException("Unknown thread mode: " + jyVar.b.b);
        }
    }

    private static List<Class<?>> a(Class<?> cls) {
        List<Class<?>> list;
        synchronized (b) {
            list = (List) b.get(cls);
            if (list == null) {
                list = new ArrayList();
                for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    a((List) list, cls2.getInterfaces());
                }
                b.put(cls, list);
            }
        }
        return list;
    }

    private static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a((List) list, cls.getInterfaces());
            }
        }
    }

    final void a(jt jtVar) {
        Object obj = jtVar.a;
        jy jyVar = jtVar.b;
        jt.a(jtVar);
        if (jyVar.d) {
            a(jyVar, obj);
        }
    }

    private void a(jy jyVar, Object obj) throws Error {
        Throwable cause;
        try {
            jyVar.b.a.invoke(jyVar.a, new Object[]{obj});
        } catch (InvocationTargetException e) {
            cause = e.getCause();
            if (obj instanceof jv) {
                Logger.e(Logger.EVENT_TAG, "SubscriberExceptionEvent subscriber " + jyVar.a.getClass() + " threw an exception", cause);
                jv jvVar = (jv) obj;
                Logger.e(Logger.EVENT_TAG, "Initial event " + jvVar.c + " caused exception in " + jvVar.d, jvVar.b);
                return;
            }
            if (this.l) {
                Logger.e(Logger.EVENT_TAG, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + jyVar.a.getClass(), cause);
            }
            b(new jv(this, cause, obj, jyVar.a));
        } catch (Throwable cause2) {
            throw new IllegalStateException("Unexpected exception", cause2);
        }
    }
}
