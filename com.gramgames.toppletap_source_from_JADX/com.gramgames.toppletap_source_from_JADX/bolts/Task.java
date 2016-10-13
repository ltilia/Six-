package bolts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Task<TResult> {
    public static final ExecutorService BACKGROUND_EXECUTOR;
    private static final Executor IMMEDIATE_EXECUTOR;
    private static Task<?> TASK_CANCELLED;
    private static Task<Boolean> TASK_FALSE;
    private static Task<?> TASK_NULL;
    private static Task<Boolean> TASK_TRUE;
    public static final Executor UI_THREAD_EXECUTOR;
    private static volatile UnobservedExceptionHandler unobservedExceptionHandler;
    private boolean cancelled;
    private boolean complete;
    private List<Continuation<TResult, Void>> continuations;
    private Exception error;
    private boolean errorHasBeenObserved;
    private final Object lock;
    private TResult result;
    private UnobservedErrorNotifier unobservedErrorNotifier;

    class 10 implements Continuation<TResult, Void> {
        final /* synthetic */ Continuation val$continuation;
        final /* synthetic */ CancellationToken val$ct;
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ TaskCompletionSource val$tcs;

        10(TaskCompletionSource taskCompletionSource, Continuation continuation, Executor executor, CancellationToken cancellationToken) {
            this.val$tcs = taskCompletionSource;
            this.val$continuation = continuation;
            this.val$executor = executor;
            this.val$ct = cancellationToken;
        }

        public Void then(Task<TResult> task) {
            Task.completeImmediately(this.val$tcs, this.val$continuation, task, this.val$executor, this.val$ct);
            return null;
        }
    }

    class 11 implements Continuation<TResult, Void> {
        final /* synthetic */ Continuation val$continuation;
        final /* synthetic */ CancellationToken val$ct;
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ TaskCompletionSource val$tcs;

        11(TaskCompletionSource taskCompletionSource, Continuation continuation, Executor executor, CancellationToken cancellationToken) {
            this.val$tcs = taskCompletionSource;
            this.val$continuation = continuation;
            this.val$executor = executor;
            this.val$ct = cancellationToken;
        }

        public Void then(Task<TResult> task) {
            Task.completeAfterTask(this.val$tcs, this.val$continuation, task, this.val$executor, this.val$ct);
            return null;
        }
    }

    class 12 implements Continuation<TResult, Task<TContinuationResult>> {
        final /* synthetic */ Continuation val$continuation;
        final /* synthetic */ CancellationToken val$ct;

        12(CancellationToken cancellationToken, Continuation continuation) {
            this.val$ct = cancellationToken;
            this.val$continuation = continuation;
        }

        public Task<TContinuationResult> then(Task<TResult> task) {
            if (this.val$ct != null && this.val$ct.isCancellationRequested()) {
                return Task.cancelled();
            }
            if (task.isFaulted()) {
                return Task.forError(task.getError());
            }
            if (task.isCancelled()) {
                return Task.cancelled();
            }
            return task.continueWith(this.val$continuation);
        }
    }

    class 13 implements Continuation<TResult, Task<TContinuationResult>> {
        final /* synthetic */ Continuation val$continuation;
        final /* synthetic */ CancellationToken val$ct;

        13(CancellationToken cancellationToken, Continuation continuation) {
            this.val$ct = cancellationToken;
            this.val$continuation = continuation;
        }

        public Task<TContinuationResult> then(Task<TResult> task) {
            if (this.val$ct != null && this.val$ct.isCancellationRequested()) {
                return Task.cancelled();
            }
            if (task.isFaulted()) {
                return Task.forError(task.getError());
            }
            if (task.isCancelled()) {
                return Task.cancelled();
            }
            return task.continueWithTask(this.val$continuation);
        }
    }

    static class 14 implements Runnable {
        final /* synthetic */ Continuation val$continuation;
        final /* synthetic */ CancellationToken val$ct;
        final /* synthetic */ Task val$task;
        final /* synthetic */ TaskCompletionSource val$tcs;

        14(CancellationToken cancellationToken, TaskCompletionSource taskCompletionSource, Continuation continuation, Task task) {
            this.val$ct = cancellationToken;
            this.val$tcs = taskCompletionSource;
            this.val$continuation = continuation;
            this.val$task = task;
        }

        public void run() {
            if (this.val$ct == null || !this.val$ct.isCancellationRequested()) {
                try {
                    this.val$tcs.setResult(this.val$continuation.then(this.val$task));
                    return;
                } catch (CancellationException e) {
                    this.val$tcs.setCancelled();
                    return;
                } catch (Exception e2) {
                    this.val$tcs.setError(e2);
                    return;
                }
            }
            this.val$tcs.setCancelled();
        }
    }

    static class 15 implements Runnable {
        final /* synthetic */ Continuation val$continuation;
        final /* synthetic */ CancellationToken val$ct;
        final /* synthetic */ Task val$task;
        final /* synthetic */ TaskCompletionSource val$tcs;

        class 1 implements Continuation<TContinuationResult, Void> {
            1() {
            }

            public Void then(Task<TContinuationResult> task) {
                if (15.this.val$ct != null && 15.this.val$ct.isCancellationRequested()) {
                    15.this.val$tcs.setCancelled();
                } else if (task.isCancelled()) {
                    15.this.val$tcs.setCancelled();
                } else if (task.isFaulted()) {
                    15.this.val$tcs.setError(task.getError());
                } else {
                    15.this.val$tcs.setResult(task.getResult());
                }
                return null;
            }
        }

        15(CancellationToken cancellationToken, TaskCompletionSource taskCompletionSource, Continuation continuation, Task task) {
            this.val$ct = cancellationToken;
            this.val$tcs = taskCompletionSource;
            this.val$continuation = continuation;
            this.val$task = task;
        }

        public void run() {
            if (this.val$ct == null || !this.val$ct.isCancellationRequested()) {
                try {
                    Task<TContinuationResult> result = (Task) this.val$continuation.then(this.val$task);
                    if (result == null) {
                        this.val$tcs.setResult(null);
                        return;
                    } else {
                        result.continueWith(new 1());
                        return;
                    }
                } catch (CancellationException e) {
                    this.val$tcs.setCancelled();
                    return;
                } catch (Exception e2) {
                    this.val$tcs.setError(e2);
                    return;
                }
            }
            this.val$tcs.setCancelled();
        }
    }

    static class 1 implements Runnable {
        final /* synthetic */ TaskCompletionSource val$tcs;

        1(TaskCompletionSource taskCompletionSource) {
            this.val$tcs = taskCompletionSource;
        }

        public void run() {
            this.val$tcs.trySetResult(null);
        }
    }

    static class 2 implements Runnable {
        final /* synthetic */ ScheduledFuture val$scheduled;
        final /* synthetic */ TaskCompletionSource val$tcs;

        2(ScheduledFuture scheduledFuture, TaskCompletionSource taskCompletionSource) {
            this.val$scheduled = scheduledFuture;
            this.val$tcs = taskCompletionSource;
        }

        public void run() {
            this.val$scheduled.cancel(true);
            this.val$tcs.trySetCancelled();
        }
    }

    class 3 implements Continuation<TResult, Task<Void>> {
        3() {
        }

        public Task<Void> then(Task<TResult> task) throws Exception {
            if (task.isCancelled()) {
                return Task.cancelled();
            }
            if (task.isFaulted()) {
                return Task.forError(task.getError());
            }
            return Task.forResult(null);
        }
    }

    static class 4 implements Runnable {
        final /* synthetic */ Callable val$callable;
        final /* synthetic */ CancellationToken val$ct;
        final /* synthetic */ TaskCompletionSource val$tcs;

        4(CancellationToken cancellationToken, TaskCompletionSource taskCompletionSource, Callable callable) {
            this.val$ct = cancellationToken;
            this.val$tcs = taskCompletionSource;
            this.val$callable = callable;
        }

        public void run() {
            if (this.val$ct == null || !this.val$ct.isCancellationRequested()) {
                try {
                    this.val$tcs.setResult(this.val$callable.call());
                    return;
                } catch (CancellationException e) {
                    this.val$tcs.setCancelled();
                    return;
                } catch (Exception e2) {
                    this.val$tcs.setError(e2);
                    return;
                }
            }
            this.val$tcs.setCancelled();
        }
    }

    static class 5 implements Continuation<TResult, Void> {
        final /* synthetic */ TaskCompletionSource val$firstCompleted;
        final /* synthetic */ AtomicBoolean val$isAnyTaskComplete;

        5(AtomicBoolean atomicBoolean, TaskCompletionSource taskCompletionSource) {
            this.val$isAnyTaskComplete = atomicBoolean;
            this.val$firstCompleted = taskCompletionSource;
        }

        public Void then(Task<TResult> task) {
            if (this.val$isAnyTaskComplete.compareAndSet(false, true)) {
                this.val$firstCompleted.setResult(task);
            } else {
                task.getError();
            }
            return null;
        }
    }

    static class 6 implements Continuation<Object, Void> {
        final /* synthetic */ TaskCompletionSource val$firstCompleted;
        final /* synthetic */ AtomicBoolean val$isAnyTaskComplete;

        6(AtomicBoolean atomicBoolean, TaskCompletionSource taskCompletionSource) {
            this.val$isAnyTaskComplete = atomicBoolean;
            this.val$firstCompleted = taskCompletionSource;
        }

        public Void then(Task<Object> task) {
            if (this.val$isAnyTaskComplete.compareAndSet(false, true)) {
                this.val$firstCompleted.setResult(task);
            } else {
                task.getError();
            }
            return null;
        }
    }

    static class 7 implements Continuation<Void, List<TResult>> {
        final /* synthetic */ Collection val$tasks;

        7(Collection collection) {
            this.val$tasks = collection;
        }

        public List<TResult> then(Task<Void> task) throws Exception {
            if (this.val$tasks.size() == 0) {
                return Collections.emptyList();
            }
            List<TResult> results = new ArrayList();
            for (Task<TResult> individualTask : this.val$tasks) {
                results.add(individualTask.getResult());
            }
            return results;
        }
    }

    static class 8 implements Continuation<Object, Void> {
        final /* synthetic */ TaskCompletionSource val$allFinished;
        final /* synthetic */ ArrayList val$causes;
        final /* synthetic */ AtomicInteger val$count;
        final /* synthetic */ Object val$errorLock;
        final /* synthetic */ AtomicBoolean val$isCancelled;

        8(Object obj, ArrayList arrayList, AtomicBoolean atomicBoolean, AtomicInteger atomicInteger, TaskCompletionSource taskCompletionSource) {
            this.val$errorLock = obj;
            this.val$causes = arrayList;
            this.val$isCancelled = atomicBoolean;
            this.val$count = atomicInteger;
            this.val$allFinished = taskCompletionSource;
        }

        public Void then(Task<Object> task) {
            if (task.isFaulted()) {
                synchronized (this.val$errorLock) {
                    this.val$causes.add(task.getError());
                }
            }
            if (task.isCancelled()) {
                this.val$isCancelled.set(true);
            }
            if (this.val$count.decrementAndGet() == 0) {
                if (this.val$causes.size() != 0) {
                    if (this.val$causes.size() == 1) {
                        this.val$allFinished.setError((Exception) this.val$causes.get(0));
                    } else {
                        this.val$allFinished.setError(new AggregateException(String.format("There were %d exceptions.", new Object[]{Integer.valueOf(this.val$causes.size())}), this.val$causes));
                    }
                } else if (this.val$isCancelled.get()) {
                    this.val$allFinished.setCancelled();
                } else {
                    this.val$allFinished.setResult(null);
                }
            }
            return null;
        }
    }

    class 9 implements Continuation<Void, Task<Void>> {
        final /* synthetic */ Continuation val$continuation;
        final /* synthetic */ CancellationToken val$ct;
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Callable val$predicate;
        final /* synthetic */ Capture val$predicateContinuation;

        9(CancellationToken cancellationToken, Callable callable, Continuation continuation, Executor executor, Capture capture) {
            this.val$ct = cancellationToken;
            this.val$predicate = callable;
            this.val$continuation = continuation;
            this.val$executor = executor;
            this.val$predicateContinuation = capture;
        }

        public Task<Void> then(Task<Void> task) throws Exception {
            if (this.val$ct != null && this.val$ct.isCancellationRequested()) {
                return Task.cancelled();
            }
            if (((Boolean) this.val$predicate.call()).booleanValue()) {
                return Task.forResult(null).onSuccessTask(this.val$continuation, this.val$executor).onSuccessTask((Continuation) this.val$predicateContinuation.get(), this.val$executor);
            }
            return Task.forResult(null);
        }
    }

    public class TaskCompletionSource extends TaskCompletionSource<TResult> {
        TaskCompletionSource() {
        }
    }

    public interface UnobservedExceptionHandler {
        void unobservedException(Task<?> task, UnobservedTaskException unobservedTaskException);
    }

    static {
        BACKGROUND_EXECUTOR = BoltsExecutors.background();
        IMMEDIATE_EXECUTOR = BoltsExecutors.immediate();
        UI_THREAD_EXECUTOR = AndroidExecutors.uiThread();
        TASK_NULL = new Task(null);
        TASK_TRUE = new Task(Boolean.valueOf(true));
        TASK_FALSE = new Task(Boolean.valueOf(false));
        TASK_CANCELLED = new Task(true);
    }

    public static UnobservedExceptionHandler getUnobservedExceptionHandler() {
        return unobservedExceptionHandler;
    }

    public static void setUnobservedExceptionHandler(UnobservedExceptionHandler eh) {
        unobservedExceptionHandler = eh;
    }

    Task() {
        this.lock = new Object();
        this.continuations = new ArrayList();
    }

    private Task(TResult result) {
        this.lock = new Object();
        this.continuations = new ArrayList();
        trySetResult(result);
    }

    private Task(boolean cancelled) {
        this.lock = new Object();
        this.continuations = new ArrayList();
        if (cancelled) {
            trySetCancelled();
        } else {
            trySetResult(null);
        }
    }

    public static <TResult> TaskCompletionSource create() {
        Task<TResult> task = new Task();
        task.getClass();
        return new TaskCompletionSource();
    }

    public boolean isCompleted() {
        boolean z;
        synchronized (this.lock) {
            z = this.complete;
        }
        return z;
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.lock) {
            z = this.cancelled;
        }
        return z;
    }

    public boolean isFaulted() {
        boolean z;
        synchronized (this.lock) {
            z = getError() != null;
        }
        return z;
    }

    public TResult getResult() {
        TResult tResult;
        synchronized (this.lock) {
            tResult = this.result;
        }
        return tResult;
    }

    public Exception getError() {
        Exception exception;
        synchronized (this.lock) {
            if (this.error != null) {
                this.errorHasBeenObserved = true;
                if (this.unobservedErrorNotifier != null) {
                    this.unobservedErrorNotifier.setObserved();
                    this.unobservedErrorNotifier = null;
                }
            }
            exception = this.error;
        }
        return exception;
    }

    public void waitForCompletion() throws InterruptedException {
        synchronized (this.lock) {
            if (!isCompleted()) {
                this.lock.wait();
            }
        }
    }

    public boolean waitForCompletion(long duration, TimeUnit timeUnit) throws InterruptedException {
        boolean isCompleted;
        synchronized (this.lock) {
            if (!isCompleted()) {
                this.lock.wait(timeUnit.toMillis(duration));
            }
            isCompleted = isCompleted();
        }
        return isCompleted;
    }

    public static <TResult> Task<TResult> forResult(TResult value) {
        if (value == null) {
            return TASK_NULL;
        }
        if (value instanceof Boolean) {
            return ((Boolean) value).booleanValue() ? TASK_TRUE : TASK_FALSE;
        } else {
            TaskCompletionSource<TResult> tcs = new TaskCompletionSource();
            tcs.setResult(value);
            return tcs.getTask();
        }
    }

    public static <TResult> Task<TResult> forError(Exception error) {
        TaskCompletionSource<TResult> tcs = new TaskCompletionSource();
        tcs.setError(error);
        return tcs.getTask();
    }

    public static <TResult> Task<TResult> cancelled() {
        return TASK_CANCELLED;
    }

    public static Task<Void> delay(long delay) {
        return delay(delay, BoltsExecutors.scheduled(), null);
    }

    public static Task<Void> delay(long delay, CancellationToken cancellationToken) {
        return delay(delay, BoltsExecutors.scheduled(), cancellationToken);
    }

    static Task<Void> delay(long delay, ScheduledExecutorService executor, CancellationToken cancellationToken) {
        if (cancellationToken != null && cancellationToken.isCancellationRequested()) {
            return cancelled();
        }
        if (delay <= 0) {
            return forResult(null);
        }
        TaskCompletionSource<Void> tcs = new TaskCompletionSource();
        ScheduledFuture<?> scheduled = executor.schedule(new 1(tcs), delay, TimeUnit.MILLISECONDS);
        if (cancellationToken != null) {
            cancellationToken.register(new 2(scheduled, tcs));
        }
        return tcs.getTask();
    }

    public <TOut> Task<TOut> cast() {
        return this;
    }

    public Task<Void> makeVoid() {
        return continueWithTask(new 3());
    }

    public static <TResult> Task<TResult> callInBackground(Callable<TResult> callable) {
        return call(callable, BACKGROUND_EXECUTOR, null);
    }

    public static <TResult> Task<TResult> callInBackground(Callable<TResult> callable, CancellationToken ct) {
        return call(callable, BACKGROUND_EXECUTOR, ct);
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable, Executor executor) {
        return call(callable, executor, null);
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable, Executor executor, CancellationToken ct) {
        TaskCompletionSource<TResult> tcs = new TaskCompletionSource();
        try {
            executor.execute(new 4(ct, tcs, callable));
        } catch (Exception e) {
            tcs.setError(new ExecutorException(e));
        }
        return tcs.getTask();
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable) {
        return call(callable, IMMEDIATE_EXECUTOR, null);
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable, CancellationToken ct) {
        return call(callable, IMMEDIATE_EXECUTOR, ct);
    }

    public static <TResult> Task<Task<TResult>> whenAnyResult(Collection<? extends Task<TResult>> tasks) {
        if (tasks.size() == 0) {
            return forResult(null);
        }
        TaskCompletionSource<Task<TResult>> firstCompleted = new TaskCompletionSource();
        AtomicBoolean isAnyTaskComplete = new AtomicBoolean(false);
        for (Task<TResult> task : tasks) {
            task.continueWith(new 5(isAnyTaskComplete, firstCompleted));
        }
        return firstCompleted.getTask();
    }

    public static Task<Task<?>> whenAny(Collection<? extends Task<?>> tasks) {
        if (tasks.size() == 0) {
            return forResult(null);
        }
        TaskCompletionSource<Task<?>> firstCompleted = new TaskCompletionSource();
        AtomicBoolean isAnyTaskComplete = new AtomicBoolean(false);
        for (Task<?> task : tasks) {
            task.continueWith(new 6(isAnyTaskComplete, firstCompleted));
        }
        return firstCompleted.getTask();
    }

    public static <TResult> Task<List<TResult>> whenAllResult(Collection<? extends Task<TResult>> tasks) {
        return whenAll(tasks).onSuccess(new 7(tasks));
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> tasks) {
        if (tasks.size() == 0) {
            return forResult(null);
        }
        TaskCompletionSource<Void> allFinished = new TaskCompletionSource();
        ArrayList<Exception> causes = new ArrayList();
        Object errorLock = new Object();
        AtomicInteger count = new AtomicInteger(tasks.size());
        AtomicBoolean isCancelled = new AtomicBoolean(false);
        for (Task<Object> t : tasks) {
            t.continueWith(new 8(errorLock, causes, isCancelled, count, allFinished));
        }
        return allFinished.getTask();
    }

    public Task<Void> continueWhile(Callable<Boolean> predicate, Continuation<Void, Task<Void>> continuation) {
        return continueWhile(predicate, continuation, IMMEDIATE_EXECUTOR, null);
    }

    public Task<Void> continueWhile(Callable<Boolean> predicate, Continuation<Void, Task<Void>> continuation, CancellationToken ct) {
        return continueWhile(predicate, continuation, IMMEDIATE_EXECUTOR, ct);
    }

    public Task<Void> continueWhile(Callable<Boolean> predicate, Continuation<Void, Task<Void>> continuation, Executor executor) {
        return continueWhile(predicate, continuation, executor, null);
    }

    public Task<Void> continueWhile(Callable<Boolean> predicate, Continuation<Void, Task<Void>> continuation, Executor executor, CancellationToken ct) {
        Capture<Continuation<Void, Task<Void>>> predicateContinuation = new Capture();
        predicateContinuation.set(new 9(ct, predicate, continuation, executor, predicateContinuation));
        return makeVoid().continueWithTask((Continuation) predicateContinuation.get(), executor);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation, Executor executor) {
        return continueWith(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation, Executor executor, CancellationToken ct) {
        TaskCompletionSource<TContinuationResult> tcs = new TaskCompletionSource();
        synchronized (this.lock) {
            boolean completed = isCompleted();
            if (!completed) {
                this.continuations.add(new 10(tcs, continuation, executor, ct));
            }
        }
        if (completed) {
            completeImmediately(tcs, continuation, this, executor, ct);
        }
        return tcs.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation, CancellationToken ct) {
        return continueWith(continuation, IMMEDIATE_EXECUTOR, ct);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor) {
        return continueWithTask(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor, CancellationToken ct) {
        TaskCompletionSource<TContinuationResult> tcs = new TaskCompletionSource();
        synchronized (this.lock) {
            boolean completed = isCompleted();
            if (!completed) {
                this.continuations.add(new 11(tcs, continuation, executor, ct));
            }
        }
        if (completed) {
            completeAfterTask(tcs, continuation, this, executor, ct);
        }
        return tcs.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation, CancellationToken ct) {
        return continueWithTask(continuation, IMMEDIATE_EXECUTOR, ct);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation, Executor executor) {
        return onSuccess(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation, Executor executor, CancellationToken ct) {
        return continueWithTask(new 12(ct, continuation), executor);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation) {
        return onSuccess(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation, CancellationToken ct) {
        return onSuccess(continuation, IMMEDIATE_EXECUTOR, ct);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor) {
        return onSuccessTask(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor, CancellationToken ct) {
        return continueWithTask(new 13(ct, continuation), executor);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return onSuccessTask((Continuation) continuation, IMMEDIATE_EXECUTOR);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Continuation<TResult, Task<TContinuationResult>> continuation, CancellationToken ct) {
        return onSuccessTask(continuation, IMMEDIATE_EXECUTOR, ct);
    }

    private static <TContinuationResult, TResult> void completeImmediately(TaskCompletionSource<TContinuationResult> tcs, Continuation<TResult, TContinuationResult> continuation, Task<TResult> task, Executor executor, CancellationToken ct) {
        try {
            executor.execute(new 14(ct, tcs, continuation, task));
        } catch (Exception e) {
            tcs.setError(new ExecutorException(e));
        }
    }

    private static <TContinuationResult, TResult> void completeAfterTask(TaskCompletionSource<TContinuationResult> tcs, Continuation<TResult, Task<TContinuationResult>> continuation, Task<TResult> task, Executor executor, CancellationToken ct) {
        try {
            executor.execute(new 15(ct, tcs, continuation, task));
        } catch (Exception e) {
            tcs.setError(new ExecutorException(e));
        }
    }

    private void runContinuations() {
        synchronized (this.lock) {
            for (Continuation<TResult, ?> continuation : this.continuations) {
                try {
                    continuation.then(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.continuations = null;
        }
    }

    boolean trySetCancelled() {
        boolean z = true;
        synchronized (this.lock) {
            if (this.complete) {
                z = false;
            } else {
                this.complete = true;
                this.cancelled = true;
                this.lock.notifyAll();
                runContinuations();
            }
        }
        return z;
    }

    boolean trySetResult(TResult result) {
        boolean z = true;
        synchronized (this.lock) {
            if (this.complete) {
                z = false;
            } else {
                this.complete = true;
                this.result = result;
                this.lock.notifyAll();
                runContinuations();
            }
        }
        return z;
    }

    boolean trySetError(Exception error) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.error = error;
            this.errorHasBeenObserved = false;
            this.lock.notifyAll();
            runContinuations();
            if (!(this.errorHasBeenObserved || getUnobservedExceptionHandler() == null)) {
                this.unobservedErrorNotifier = new UnobservedErrorNotifier(this);
            }
            return true;
        }
    }
}
