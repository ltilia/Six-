package com.mopub.common.event;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;

public class EventDispatcher {
    private final Iterable<EventRecorder> mEventRecorders;
    private final Callback mHandlerCallback;
    private final Looper mLooper;
    private final Handler mMessageHandler;

    class 1 implements Callback {
        1() {
        }

        public boolean handleMessage(Message msg) {
            if (msg.obj instanceof BaseEvent) {
                for (EventRecorder recorder : EventDispatcher.this.mEventRecorders) {
                    recorder.record((BaseEvent) msg.obj);
                }
            } else {
                MoPubLog.d("EventDispatcher received non-BaseEvent message type.");
            }
            return true;
        }
    }

    @VisibleForTesting
    EventDispatcher(Iterable<EventRecorder> recorders, Looper looper) {
        this.mEventRecorders = recorders;
        this.mLooper = looper;
        this.mHandlerCallback = new 1();
        this.mMessageHandler = new Handler(this.mLooper, this.mHandlerCallback);
    }

    public void dispatch(BaseEvent event) {
        Message.obtain(this.mMessageHandler, 0, event).sendToTarget();
    }

    @VisibleForTesting
    Iterable<EventRecorder> getEventRecorders() {
        return this.mEventRecorders;
    }

    @VisibleForTesting
    Callback getHandlerCallback() {
        return this.mHandlerCallback;
    }
}
