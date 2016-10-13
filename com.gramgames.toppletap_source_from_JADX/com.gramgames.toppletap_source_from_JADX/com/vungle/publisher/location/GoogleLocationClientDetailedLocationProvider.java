package com.vungle.publisher.location;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.vungle.log.Logger;
import com.vungle.publisher.fr;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class GoogleLocationClientDetailedLocationProvider extends BaseGoogleDetailedLocationProvider<LocationClient> implements ConnectionCallbacks, OnConnectionFailedListener, fr {
    private final Context b;

    protected final /* synthetic */ boolean a(Object obj) {
        return ((LocationClient) obj).isConnected();
    }

    public final /* bridge */ /* synthetic */ Location b() {
        return super.b();
    }

    protected final /* synthetic */ void b(Object obj) {
        ((LocationClient) obj).connect();
    }

    protected final /* synthetic */ Location c(Object obj) {
        return ((LocationClient) obj).getLastLocation();
    }

    protected final /* synthetic */ Object c() {
        return new LocationClient(this.b, this, this);
    }

    protected final /* synthetic */ void d(Object obj) {
        ((LocationClient) obj).disconnect();
    }

    public GoogleLocationClientDetailedLocationProvider(Context context) {
        this.b = context;
    }

    protected final String a() {
        return "Google Play Services LocationClient";
    }

    public void onConnected(Bundle bundle) {
        super.d();
    }

    public void onDisconnected() {
        Logger.v(Logger.LOCATION_TAG, "disconnected from Google Play Services LocationClient " + this.a);
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
    }
}
