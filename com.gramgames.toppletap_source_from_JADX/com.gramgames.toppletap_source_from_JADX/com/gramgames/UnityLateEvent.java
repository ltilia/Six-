package com.gramgames;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.facebook.internal.NativeProtocol;
import com.unity3d.player.UnityPlayer;
import gs.gram.mopub.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;

public class UnityLateEvent implements IUnityLateEvent {
    private static final String SHARED_PREF_KEY = "lateEvents";
    private static final String SHARED_PREF_NAME = "UnityLateEvent";
    private static final String TAG = "[UnityLateEvent]";
    private static IUnityLateEvent mInstance;

    private UnityLateEvent() {
    }

    public void add(String gameObject, String methodName, String params) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        if (sharedPreferences != null) {
            JSONArray events;
            Log.d(TAG, "Adding late event. GameObject: " + gameObject + " Method: " + methodName + " Params: " + params);
            String storedEvents = sharedPreferences.getString(SHARED_PREF_KEY, BuildConfig.FLAVOR);
            try {
                if (storedEvents.isEmpty()) {
                    events = new JSONArray();
                } else {
                    events = new JSONArray(storedEvents);
                }
            } catch (Exception e) {
                e.printStackTrace();
                events = new JSONArray();
            }
            try {
                JSONObject lateEvent = new JSONObject();
                lateEvent.put("gameObject", gameObject);
                lateEvent.put("methodName", methodName);
                lateEvent.put(NativeProtocol.WEB_DIALOG_PARAMS, params);
                events.put(lateEvent);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            Editor editor = sharedPreferences.edit();
            editor.putString(SHARED_PREF_KEY, events.toString());
            editor.commit();
        }
    }

    public void flush() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        if (sharedPreferences != null) {
            String storedEvents = sharedPreferences.getString(SHARED_PREF_KEY, BuildConfig.FLAVOR);
            try {
                if (storedEvents.isEmpty()) {
                    Log.d(TAG, "Late event store is empty.");
                    return;
                }
                JSONArray events = new JSONArray(storedEvents);
                if (events.length() > 0) {
                    Log.d(TAG, "Found " + events.length() + " late event. Flushing...");
                    int len = events.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject current = events.getJSONObject(i);
                        UnityPlayer.UnitySendMessage(current.getString("gameObject"), current.getString("methodName"), current.getString(NativeProtocol.WEB_DIALOG_PARAMS));
                        Log.d(TAG, "Flushing event. GameObject: " + current.getString("gameObject") + " Method: " + current.getString("methodName") + " Params: " + current.getString(NativeProtocol.WEB_DIALOG_PARAMS));
                    }
                    Editor editor = sharedPreferences.edit();
                    editor.putString(SHARED_PREF_KEY, BuildConfig.FLAVOR);
                    editor.commit();
                    return;
                }
                Log.d(TAG, "Not found any late event that stored.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private SharedPreferences getSharedPreferences() {
        if (UnityPlayer.currentActivity != null) {
            return UnityPlayer.currentActivity.getSharedPreferences(SHARED_PREF_NAME, 0);
        }
        Log.d(TAG, "UnityPlayer.currentActivity is null. Cannot get SharedPreferences.");
        return null;
    }

    public static IUnityLateEvent getInstance() {
        if (mInstance == null) {
            mInstance = new UnityLateEvent();
        }
        return mInstance;
    }
}
