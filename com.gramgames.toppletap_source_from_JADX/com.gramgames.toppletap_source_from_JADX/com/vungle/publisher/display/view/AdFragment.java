package com.vungle.publisher.display.view;

import android.app.Fragment;
import com.vungle.log.Logger;

/* compiled from: vungle */
public abstract class AdFragment extends Fragment {
    public abstract void a();

    public abstract String b();

    public boolean a(int i) {
        return false;
    }

    public void a(boolean z) {
        Logger.v(Logger.AD_TAG, (z ? "gained" : "lost") + " window focus");
    }
}
