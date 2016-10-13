package com.vungle.publisher.location;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.vungle.log.Logger;
import com.vungle.publisher.fs;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class GoogleLocationServicesDetailedLocationProvider extends BaseGoogleDetailedLocationProvider<GoogleApiClient> implements ConnectionCallbacks, OnConnectionFailedListener, fs {
    private final Context b;

    protected final /* synthetic */ boolean a(Object obj) {
        return ((GoogleApiClient) obj).isConnected();
    }

    public final /* bridge */ /* synthetic */ Location b() {
        return super.b();
    }

    protected final /* synthetic */ void b(Object obj) {
        ((GoogleApiClient) obj).connect();
    }

    protected final /* synthetic */ Location c(Object obj) {
        return LocationServices.FusedLocationApi.getLastLocation((GoogleApiClient) obj);
    }

    protected final /* synthetic */ Object c() {
        Context context = this.b;
        return new Builder(context).addApi(LocationServices.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
    }

    protected final /* synthetic */ void d(Object obj) {
        ((GoogleApiClient) obj).disconnect();
    }

    public GoogleLocationServicesDetailedLocationProvider(Context context) {
        this.b = context;
    }

    protected final String a() {
        return "Google Play Services LocationServices";
    }

    public void onConnected(Bundle bundle) {
        super.d();
    }

    public void onConnectionSuspended(int i) {
        Logger.v(Logger.LOCATION_TAG, "connection suspended for Google Play Services LocationServices " + this.a);
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
    }
}
