package com.chartboost.sdk.impl;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class m {
    private AtomicInteger a;
    private final Map<String, Queue<l<?>>> b;
    private final Set<l<?>> c;
    private final PriorityBlockingQueue<l<?>> d;
    private final PriorityBlockingQueue<l<?>> e;
    private final b f;
    private final f g;
    private final o h;
    private g[] i;
    private c j;
    private List<b> k;

    public interface a {
        boolean a(l<?> lVar);
    }

    class 1 implements a {
        final /* synthetic */ Object a;
        final /* synthetic */ m b;

        1(m mVar, Object obj) {
            this.b = mVar;
            this.a = obj;
        }

        public boolean a(l<?> lVar) {
            return lVar.b() == this.a;
        }
    }

    public interface b<T> {
        void a(l<T> lVar);
    }

    public m(b bVar, f fVar, int i, o oVar) {
        this.a = new AtomicInteger();
        this.b = new HashMap();
        this.c = new HashSet();
        this.d = new PriorityBlockingQueue();
        this.e = new PriorityBlockingQueue();
        this.k = new ArrayList();
        this.f = bVar;
        this.g = fVar;
        this.i = new g[i];
        this.h = oVar;
    }

    public m(b bVar, f fVar, int i) {
        this(bVar, fVar, i, new e(new Handler(Looper.getMainLooper())));
    }

    public m(b bVar, f fVar) {
        this(bVar, fVar, 4);
    }

    public void a() {
        b();
        this.j = new c(this.d, this.e, this.f, this.h);
        this.j.start();
        for (int i = 0; i < this.i.length; i++) {
            g gVar = new g(this.e, this.g, this.f, this.h);
            this.i[i] = gVar;
            gVar.start();
        }
    }

    public void b() {
        if (this.j != null) {
            this.j.a();
        }
        for (int i = 0; i < this.i.length; i++) {
            if (this.i[i] != null) {
                this.i[i].a();
            }
        }
    }

    public int c() {
        return this.a.incrementAndGet();
    }

    public b d() {
        return this.f;
    }

    public void a(a aVar) {
        synchronized (this.c) {
            for (l lVar : this.c) {
                if (aVar.a(lVar)) {
                    lVar.g();
                }
            }
        }
    }

    public void a(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        }
        a(new 1(this, obj));
    }

    public <T> l<T> a(l<T> lVar) {
        lVar.a(this);
        synchronized (this.c) {
            this.c.add(lVar);
        }
        lVar.a(c());
        lVar.a("add-to-queue");
        if (lVar.r()) {
            synchronized (this.b) {
                String e = lVar.e();
                if (this.b.containsKey(e)) {
                    Queue queue = (Queue) this.b.get(e);
                    if (queue == null) {
                        queue = new LinkedList();
                    }
                    queue.add(lVar);
                    this.b.put(e, queue);
                    if (t.b) {
                        t.a("Request for cacheKey=%s is in flight, putting on hold.", e);
                    }
                } else {
                    this.b.put(e, null);
                    this.d.add(lVar);
                }
            }
        } else {
            this.e.add(lVar);
        }
        return lVar;
    }

    <T> void b(l<T> lVar) {
        synchronized (this.c) {
            this.c.remove(lVar);
        }
        synchronized (this.k) {
            for (b a : this.k) {
                a.a(lVar);
            }
        }
        if (lVar.r()) {
            synchronized (this.b) {
                Queue queue = (Queue) this.b.remove(lVar.e());
                if (queue != null) {
                    if (t.b) {
                        t.a("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(queue.size()), r2);
                    }
                    this.d.addAll(queue);
                }
            }
        }
    }
}
