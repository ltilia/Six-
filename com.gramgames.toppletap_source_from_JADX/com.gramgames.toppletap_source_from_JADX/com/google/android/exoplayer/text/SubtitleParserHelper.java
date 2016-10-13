package com.google.android.exoplayer.text;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.extractor.ts.PtsTimestampAdjuster;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;

final class SubtitleParserHelper implements Callback {
    private static final int MSG_FORMAT = 0;
    private static final int MSG_SAMPLE = 1;
    private IOException error;
    private final Handler handler;
    private final SubtitleParser parser;
    private boolean parsing;
    private PlayableSubtitle result;
    private RuntimeException runtimeError;
    private SampleHolder sampleHolder;
    private long subtitleOffsetUs;
    private boolean subtitlesAreRelative;

    public SubtitleParserHelper(Looper looper, SubtitleParser parser) {
        this.handler = new Handler(looper, this);
        this.parser = parser;
        flush();
    }

    public synchronized void flush() {
        this.sampleHolder = new SampleHolder(MSG_SAMPLE);
        this.parsing = false;
        this.result = null;
        this.error = null;
        this.runtimeError = null;
    }

    public synchronized boolean isParsing() {
        return this.parsing;
    }

    public synchronized SampleHolder getSampleHolder() {
        return this.sampleHolder;
    }

    public void setFormat(MediaFormat format) {
        this.handler.obtainMessage(MSG_FORMAT, format).sendToTarget();
    }

    public synchronized void startParseOperation() {
        boolean z = true;
        synchronized (this) {
            if (this.parsing) {
                z = false;
            }
            Assertions.checkState(z);
            this.parsing = true;
            this.result = null;
            this.error = null;
            this.runtimeError = null;
            this.handler.obtainMessage(MSG_SAMPLE, Util.getTopInt(this.sampleHolder.timeUs), Util.getBottomInt(this.sampleHolder.timeUs), this.sampleHolder).sendToTarget();
        }
    }

    public synchronized PlayableSubtitle getAndClearResult() throws IOException {
        PlayableSubtitle playableSubtitle;
        try {
            if (this.error != null) {
                throw this.error;
            } else if (this.runtimeError != null) {
                throw this.runtimeError;
            } else {
                playableSubtitle = this.result;
                this.result = null;
                this.error = null;
                this.runtimeError = null;
            }
        } catch (Throwable th) {
            this.result = null;
            this.error = null;
            this.runtimeError = null;
        }
        return playableSubtitle;
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_FORMAT /*0*/:
                handleFormat((MediaFormat) msg.obj);
                break;
            case MSG_SAMPLE /*1*/:
                handleSample(Util.getLong(msg.arg1, msg.arg2), msg.obj);
                break;
        }
        return true;
    }

    private void handleFormat(MediaFormat format) {
        this.subtitlesAreRelative = format.subsampleOffsetUs == PtsTimestampAdjuster.DO_NOT_OFFSET;
        this.subtitleOffsetUs = this.subtitlesAreRelative ? 0 : format.subsampleOffsetUs;
    }

    private void handleSample(long sampleTimeUs, SampleHolder holder) {
        Subtitle parsedSubtitle = null;
        ParserException error = null;
        RuntimeException runtimeError = null;
        try {
            parsedSubtitle = this.parser.parse(holder.data.array(), MSG_FORMAT, holder.size);
        } catch (ParserException e) {
            error = e;
        } catch (RuntimeException e2) {
            runtimeError = e2;
        }
        synchronized (this) {
            if (this.sampleHolder == holder) {
                this.result = new PlayableSubtitle(parsedSubtitle, this.subtitlesAreRelative, sampleTimeUs, this.subtitleOffsetUs);
                this.error = error;
                this.runtimeError = runtimeError;
                this.parsing = false;
            }
        }
    }
}
