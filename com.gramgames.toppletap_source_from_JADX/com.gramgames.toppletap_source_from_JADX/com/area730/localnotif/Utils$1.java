package com.area730.localnotif;

import android.widget.Toast;
import com.unity3d.player.UnityPlayer;

class Utils$1 implements Runnable {
    final /* synthetic */ String val$toastText;

    Utils$1(String str) {
        this.val$toastText = str;
    }

    public void run() {
        Toast.makeText(UnityPlayer.currentActivity, this.val$toastText, 0).show();
    }
}
