package com.chartboost.sdk.impl;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.e.a;

public class ar extends aq {
    private av a;
    private Button b;
    private bf c;
    private a d;

    class 1 implements OnClickListener {
        final /* synthetic */ ar a;

        1(ar arVar) {
            this.a = arVar;
        }

        public void onClick(View v) {
            this.a.c();
        }
    }

    class 2 implements OnCompletionListener {
        final /* synthetic */ ar a;

        2(ar arVar) {
            this.a = arVar;
        }

        public void onCompletion(MediaPlayer arg0) {
            bg.a(false, this.a.c);
        }
    }

    public ar(av avVar, Context context) {
        super(avVar, context);
        this.a = avVar;
        this.b = new Button(context);
        this.b.setTextColor(-14571545);
        this.b.setText("Preview");
        this.b.setOnClickListener(new 1(this));
        addView(this.b, 2);
    }

    public void a(a aVar, int i) {
        super.a(aVar, i);
        this.d = aVar;
    }

    private void c() {
        CBLogging.c(this, "play the video");
        if (this.c == null) {
            this.c = new bf(getContext());
            this.a.e().addView(this.c, new LayoutParams(-1, -1));
            this.c.setVisibility(8);
        }
        this.c.a().a(new 2(this));
        bg.a(true, this.c);
        this.c.a().a();
    }
}
