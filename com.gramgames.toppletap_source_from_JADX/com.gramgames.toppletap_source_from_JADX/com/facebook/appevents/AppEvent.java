package com.facebook.appevents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.FacebookException;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.internal.Constants;
import com.facebook.internal.Logger;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

class AppEvent implements Serializable {
    private static final long serialVersionUID = 1;
    private static final HashSet<String> validatedIdentifiers;
    private boolean isImplicit;
    private JSONObject jsonObject;
    private String name;

    static class SerializationProxyV1 implements Serializable {
        private static final long serialVersionUID = -2488473066578201069L;
        private final boolean isImplicit;
        private final String jsonString;

        private SerializationProxyV1(String jsonString, boolean isImplicit) {
            this.jsonString = jsonString;
            this.isImplicit = isImplicit;
        }

        private Object readResolve() throws JSONException {
            return new AppEvent(this.isImplicit, null);
        }
    }

    static {
        validatedIdentifiers = new HashSet();
    }

    public AppEvent(String contextName, String eventName, Double valueToSum, Bundle parameters, boolean isImplicitlyLogged, @Nullable UUID currentSessionId) {
        try {
            validateIdentifier(eventName);
            this.name = eventName;
            this.isImplicit = isImplicitlyLogged;
            this.jsonObject = new JSONObject();
            this.jsonObject.put("_eventName", eventName);
            this.jsonObject.put(Constants.LOG_TIME_APP_EVENT_KEY, System.currentTimeMillis() / 1000);
            this.jsonObject.put("_ui", contextName);
            if (currentSessionId != null) {
                this.jsonObject.put("_session_id", currentSessionId);
            }
            if (valueToSum != null) {
                this.jsonObject.put("_valueToSum", valueToSum.doubleValue());
            }
            if (this.isImplicit) {
                this.jsonObject.put("_implicitlyLogged", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            }
            if (parameters != null) {
                for (String key : parameters.keySet()) {
                    validateIdentifier(key);
                    Object value = parameters.get(key);
                    if ((value instanceof String) || (value instanceof Number)) {
                        this.jsonObject.put(key, value.toString());
                    } else {
                        throw new FacebookException(String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", new Object[]{value, key}));
                    }
                }
            }
            if (!this.isImplicit) {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Created app event '%s'", this.jsonObject.toString());
            }
        } catch (JSONException jsonException) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", jsonException.toString());
            this.jsonObject = null;
        } catch (FacebookException e) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event name or parameter:", e.toString());
            this.jsonObject = null;
        }
    }

    public String getName() {
        return this.name;
    }

    private AppEvent(String jsonString, boolean isImplicit) throws JSONException {
        this.jsonObject = new JSONObject(jsonString);
        this.isImplicit = isImplicit;
    }

    public boolean getIsImplicit() {
        return this.isImplicit;
    }

    public JSONObject getJSONObject() {
        return this.jsonObject;
    }

    private void validateIdentifier(String identifier) throws FacebookException {
        String regex = "^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$";
        if (identifier == null || identifier.length() == 0 || identifier.length() > 40) {
            if (identifier == null) {
                identifier = "<None Provided>";
            }
            throw new FacebookException(String.format(Locale.ROOT, "Identifier '%s' must be less than %d characters", new Object[]{identifier, Integer.valueOf(40)}));
        }
        synchronized (validatedIdentifiers) {
            boolean alreadyValidated = validatedIdentifiers.contains(identifier);
        }
        if (!alreadyValidated) {
            if (identifier.matches("^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$")) {
                synchronized (validatedIdentifiers) {
                    validatedIdentifiers.add(identifier);
                }
                return;
            }
            throw new FacebookException(String.format("Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen.", new Object[]{identifier}));
        }
    }

    private Object writeReplace() {
        return new SerializationProxyV1(this.isImplicit, null);
    }

    public String toString() {
        return String.format("\"%s\", implicit: %b, json: %s", new Object[]{this.jsonObject.optString("_eventName"), Boolean.valueOf(this.isImplicit), this.jsonObject.toString()});
    }
}
