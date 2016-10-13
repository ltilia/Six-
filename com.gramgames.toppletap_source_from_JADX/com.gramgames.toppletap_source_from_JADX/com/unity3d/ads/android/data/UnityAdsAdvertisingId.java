package com.unity3d.ads.android.data;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.mopub.common.GpsHelper;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.simple.parser.Yytoken;

@TargetApi(9)
public class UnityAdsAdvertisingId {
    private static UnityAdsAdvertisingId impl;
    private String advertisingIdentifier;
    private boolean limitedAdvertisingTracking;

    interface GoogleAdvertisingInfo extends IInterface {

        public abstract class GoogleAdvertisingInfoBinder extends Binder implements GoogleAdvertisingInfo {

            class GoogleAdvertisingInfoImplementation implements GoogleAdvertisingInfo {
                private final IBinder _binder;

                GoogleAdvertisingInfoImplementation(IBinder iBinder) {
                    this._binder = iBinder;
                }

                public IBinder asBinder() {
                    return this._binder;
                }

                public String getId() {
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        this._binder.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        String readString = obtain2.readString();
                        return readString;
                    } finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                }

                public boolean getEnabled(boolean z) {
                    boolean z2 = true;
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        obtain.writeInt(z ? 1 : 0);
                        this._binder.transact(2, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() == 0) {
                            z2 = false;
                        }
                        obtain2.recycle();
                        obtain.recycle();
                        return z2;
                    } catch (Throwable th) {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                }
            }

            public static GoogleAdvertisingInfo Create(IBinder iBinder) {
                if (iBinder == null) {
                    return null;
                }
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof GoogleAdvertisingInfo)) {
                    return new GoogleAdvertisingInfoImplementation(iBinder);
                }
                return (GoogleAdvertisingInfo) queryLocalInterface;
            }

            public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
                int i3 = 0;
                switch (i) {
                    case Yytoken.TYPE_LEFT_BRACE /*1*/:
                        parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        String id = getId();
                        parcel2.writeNoException();
                        parcel2.writeString(id);
                        return true;
                    case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                        boolean z;
                        parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        if (parcel.readInt() != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z = getEnabled(z);
                        parcel2.writeNoException();
                        if (z) {
                            i3 = 1;
                        }
                        parcel2.writeInt(i3);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
        }

        boolean getEnabled(boolean z);

        String getId();
    }

    class GoogleAdvertisingServiceConnection implements ServiceConnection {
        private final BlockingQueue _binderQueue;
        boolean _consumed;

        private GoogleAdvertisingServiceConnection() {
            this._consumed = false;
            this._binderQueue = new LinkedBlockingQueue();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this._binderQueue.put(iBinder);
            } catch (InterruptedException e) {
                UnityAdsDeviceLog.debug("Couldn't put service to binder que");
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public IBinder getBinder() {
            if (this._consumed) {
                throw new IllegalStateException();
            }
            this._consumed = true;
            return (IBinder) this._binderQueue.take();
        }
    }

    public UnityAdsAdvertisingId() {
        this.advertisingIdentifier = null;
        this.limitedAdvertisingTracking = false;
    }

    static {
        impl = null;
    }

    private static UnityAdsAdvertisingId getImpl() {
        if (impl == null) {
            impl = new UnityAdsAdvertisingId();
        }
        return impl;
    }

    public static void init(Activity activity) {
        if (!getImpl().fetchAdvertisingId(activity)) {
            getImpl().fetchAdvertisingIdFallback(activity.getApplicationContext());
        }
    }

    public static String getAdvertisingTrackingId() {
        return getImpl().advertisingIdentifier;
    }

    public static boolean getLimitedAdTracking() {
        return getImpl().limitedAdvertisingTracking;
    }

    private boolean fetchAdvertisingId(Activity activity) {
        try {
            if (Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(null, new Object[]{activity}).equals(Integer.valueOf(0))) {
                Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{activity});
                Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
                this.advertisingIdentifier = (String) cls.getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
                this.limitedAdvertisingTracking = ((Boolean) cls.getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(invoke, new Object[0])).booleanValue();
                return true;
            }
            UnityAdsDeviceLog.debug("Google Play Services not integrated, using fallback");
            return false;
        } catch (Exception e) {
            UnityAdsDeviceLog.debug("Exception while trying to access Google Play Services " + e);
            return false;
        }
    }

    private void fetchAdvertisingIdFallback(Context context) {
        ServiceConnection googleAdvertisingServiceConnection = new GoogleAdvertisingServiceConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE);
        if (context.bindService(intent, googleAdvertisingServiceConnection, 1)) {
            try {
                GoogleAdvertisingInfo Create = GoogleAdvertisingInfoBinder.Create(googleAdvertisingServiceConnection.getBinder());
                this.advertisingIdentifier = Create.getId();
                this.limitedAdvertisingTracking = Create.getEnabled(true);
            } catch (Exception e) {
                UnityAdsDeviceLog.debug("Couldn't get advertising info");
            } finally {
                context.unbindService(googleAdvertisingServiceConnection);
            }
        }
    }
}
