package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.device.ads.AdProperties;
import com.facebook.ads.AdError;
import com.google.android.gms.nearby.connection.AppMetadata;

public interface zzqm extends IInterface {

    public static abstract class zza extends Binder implements zzqm {

        private static class zza implements zzqm {
            private IBinder zzoz;

            zza(IBinder iBinder) {
                this.zzoz = iBinder;
            }

            public IBinder asBinder() {
                return this.zzoz;
            }

            public void onConnectionRequest(String remoteEndpointId, String remoteDeviceId, String remoteEndpointName, byte[] payload) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    obtain.writeString(remoteEndpointId);
                    obtain.writeString(remoteDeviceId);
                    obtain.writeString(remoteEndpointName);
                    obtain.writeByteArray(payload);
                    this.zzoz.transact(AdError.NO_FILL_ERROR_CODE, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onDisconnected(String remoteEndpointId) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    obtain.writeString(remoteEndpointId);
                    this.zzoz.transact(AdProperties.INTERSTITIAL, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onEndpointFound(String endpointId, String deviceId, String serviceId, String name) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    obtain.writeString(endpointId);
                    obtain.writeString(deviceId);
                    obtain.writeString(serviceId);
                    obtain.writeString(name);
                    this.zzoz.transact(AdError.LOAD_TOO_FREQUENTLY_ERROR_CODE, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onEndpointLost(String endpointId) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    obtain.writeString(endpointId);
                    this.zzoz.transact(AdProperties.CAN_EXPAND1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onMessageReceived(String remoteEndpointId, byte[] payload, boolean isReliable) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    obtain.writeString(remoteEndpointId);
                    obtain.writeByteArray(payload);
                    if (isReliable) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zzoz.transact(AdProperties.HTML, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, int i, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr);
                    this.zzoz.transact(1006, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, String str2, String str3, String str4, AppMetadata appMetadata) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (appMetadata != null) {
                        obtain.writeInt(1);
                        appMetadata.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzoz.transact(AdProperties.CAN_EXPAND2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzfy(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    obtain.writeString(str);
                    this.zzoz.transact(1005, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zziW(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    obtain.writeInt(i);
                    this.zzoz.transact(1010, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zziX(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    obtain.writeInt(i);
                    this.zzoz.transact(1011, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zziY(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    obtain.writeInt(i);
                    this.zzoz.transact(1012, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zziZ(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    obtain.writeInt(i);
                    this.zzoz.transact(1013, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzja(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    obtain.writeInt(i);
                    this.zzoz.transact(AdProperties.CAN_PLAY_VIDEO, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzm(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.zzoz.transact(1009, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        }

        public static zzqm zzdw(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzqm)) ? new zza(iBinder) : (zzqm) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case AdError.NO_FILL_ERROR_CODE /*1001*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    onConnectionRequest(data.readString(), data.readString(), data.readString(), data.createByteArray());
                    reply.writeNoException();
                    return true;
                case AdError.LOAD_TOO_FREQUENTLY_ERROR_CODE /*1002*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    onEndpointFound(data.readString(), data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case AdProperties.CAN_EXPAND1 /*1003*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    onEndpointLost(data.readString());
                    reply.writeNoException();
                    return true;
                case AdProperties.CAN_EXPAND2 /*1004*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    zza(data.readString(), data.readString(), data.readString(), data.readString(), data.readInt() != 0 ? (AppMetadata) AppMetadata.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case 1005:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    zzfy(data.readString());
                    reply.writeNoException();
                    return true;
                case 1006:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    zza(data.readString(), data.readInt(), data.createByteArray());
                    reply.writeNoException();
                    return true;
                case AdProperties.HTML /*1007*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    onMessageReceived(data.readString(), data.createByteArray(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case AdProperties.INTERSTITIAL /*1008*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    onDisconnected(data.readString());
                    reply.writeNoException();
                    return true;
                case 1009:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    zzm(data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case 1010:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    zziW(data.readInt());
                    reply.writeNoException();
                    return true;
                case 1011:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    zziX(data.readInt());
                    reply.writeNoException();
                    return true;
                case 1012:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    zziY(data.readInt());
                    reply.writeNoException();
                    return true;
                case 1013:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    zziZ(data.readInt());
                    reply.writeNoException();
                    return true;
                case AdProperties.CAN_PLAY_VIDEO /*1014*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    zzja(data.readInt());
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onConnectionRequest(String str, String str2, String str3, byte[] bArr) throws RemoteException;

    void onDisconnected(String str) throws RemoteException;

    void onEndpointFound(String str, String str2, String str3, String str4) throws RemoteException;

    void onEndpointLost(String str) throws RemoteException;

    void onMessageReceived(String str, byte[] bArr, boolean z) throws RemoteException;

    void zza(String str, int i, byte[] bArr) throws RemoteException;

    void zza(String str, String str2, String str3, String str4, AppMetadata appMetadata) throws RemoteException;

    void zzfy(String str) throws RemoteException;

    void zziW(int i) throws RemoteException;

    void zziX(int i) throws RemoteException;

    void zziY(int i) throws RemoteException;

    void zziZ(int i) throws RemoteException;

    void zzja(int i) throws RemoteException;

    void zzm(int i, String str) throws RemoteException;
}
