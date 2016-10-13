package com.gramgames;

import android.os.Bundle;

public class UnityActivityProxy {
    public static void onCreate(Bundle savedInstanceStateBundle) {
        UnityLateEvent.getInstance().flush();
    }

    public static void onResume() {
        UnityLateEvent.getInstance().flush();
    }
}
