package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.device.ads.AdProperties;
import com.facebook.ads.AdError;
import com.google.android.gms.nearby.connection.AppMetadata;

public interface zzqn extends IInterface {

    public static abstract class zza extends Binder implements zzqn {

        private static class zza implements zzqn {
            private IBinder zzoz;

            zza(IBinder iBinder) {
                this.zzoz = iBinder;
            }

            public IBinder asBinder() {
                return this.zzoz;
            }

            public String zzEk() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    this.zzoz.transact(AdProperties.MRAID1, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzF(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeLong(j);
                    this.zzoz.transact(AdProperties.CAN_PLAY_VIDEO, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzqm com_google_android_gms_internal_zzqm, int i, long j, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeStrongBinder(com_google_android_gms_internal_zzqm != null ? com_google_android_gms_internal_zzqm.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    this.zzoz.transact(1005, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzqm com_google_android_gms_internal_zzqm, String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeStrongBinder(com_google_android_gms_internal_zzqm != null ? com_google_android_gms_internal_zzqm.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    this.zzoz.transact(1009, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzqm com_google_android_gms_internal_zzqm, String str, long j, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeStrongBinder(com_google_android_gms_internal_zzqm != null ? com_google_android_gms_internal_zzqm.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    this.zzoz.transact(AdProperties.CAN_EXPAND1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzqm com_google_android_gms_internal_zzqm, String str, AppMetadata appMetadata, long j, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeStrongBinder(com_google_android_gms_internal_zzqm != null ? com_google_android_gms_internal_zzqm.asBinder() : null);
                    obtain.writeString(str);
                    if (appMetadata != null) {
                        obtain.writeInt(1);
                        appMetadata.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    this.zzoz.transact(AdError.NO_FILL_ERROR_CODE, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzqm com_google_android_gms_internal_zzqm, String str, String str2, byte[] bArr, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeStrongBinder(com_google_android_gms_internal_zzqm != null ? com_google_android_gms_internal_zzqm.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    obtain.writeLong(j);
                    this.zzoz.transact(AdProperties.HTML, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzqm com_google_android_gms_internal_zzqm, String str, byte[] bArr, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeStrongBinder(com_google_android_gms_internal_zzqm != null ? com_google_android_gms_internal_zzqm.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeLong(j);
                    this.zzoz.transact(AdProperties.INTERSTITIAL, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String[] strArr, byte[] bArr, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeStringArray(strArr);
                    obtain.writeByteArray(bArr);
                    obtain.writeLong(j);
                    this.zzoz.transact(1010, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzag(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeLong(j);
                    this.zzoz.transact(AdError.LOAD_TOO_FREQUENTLY_ERROR_CODE, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzah(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeLong(j);
                    this.zzoz.transact(1006, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzai(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeLong(j);
                    this.zzoz.transact(1013, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String zzaj(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeLong(j);
                    this.zzoz.transact(1015, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(String[] strArr, byte[] bArr, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeStringArray(strArr);
                    obtain.writeByteArray(bArr);
                    obtain.writeLong(j);
                    this.zzoz.transact(1011, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzh(String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    this.zzoz.transact(AdProperties.CAN_EXPAND2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzi(String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    this.zzoz.transact(1012, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzqn zzdx(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzqn)) ? new zza(iBinder) : (zzqn) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String zzaj;
            switch (code) {
                case AdError.NO_FILL_ERROR_CODE /*1001*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zza(com.google.android.gms.internal.zzqm.zza.zzdw(data.readStrongBinder()), data.readString(), data.readInt() != 0 ? (AppMetadata) AppMetadata.CREATOR.createFromParcel(data) : null, data.readLong(), data.readLong());
                    reply.writeNoException();
                    return true;
                case AdError.LOAD_TOO_FREQUENTLY_ERROR_CODE /*1002*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zzag(data.readLong());
                    reply.writeNoException();
                    return true;
                case AdProperties.CAN_EXPAND1 /*1003*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zza(com.google.android.gms.internal.zzqm.zza.zzdw(data.readStrongBinder()), data.readString(), data.readLong(), data.readLong());
                    reply.writeNoException();
                    return true;
                case AdProperties.CAN_EXPAND2 /*1004*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zzh(data.readString(), data.readLong());
                    reply.writeNoException();
                    return true;
                case 1005:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zza(com.google.android.gms.internal.zzqm.zza.zzdw(data.readStrongBinder()), data.readInt(), data.readLong(), data.readLong());
                    reply.writeNoException();
                    return true;
                case 1006:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zzah(data.readLong());
                    reply.writeNoException();
                    return true;
                case AdProperties.HTML /*1007*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zza(com.google.android.gms.internal.zzqm.zza.zzdw(data.readStrongBinder()), data.readString(), data.readString(), data.createByteArray(), data.readLong());
                    reply.writeNoException();
                    return true;
                case AdProperties.INTERSTITIAL /*1008*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zza(com.google.android.gms.internal.zzqm.zza.zzdw(data.readStrongBinder()), data.readString(), data.createByteArray(), data.readLong());
                    reply.writeNoException();
                    return true;
                case 1009:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zza(com.google.android.gms.internal.zzqm.zza.zzdw(data.readStrongBinder()), data.readString(), data.readLong());
                    reply.writeNoException();
                    return true;
                case 1010:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zza(data.createStringArray(), data.createByteArray(), data.readLong());
                    reply.writeNoException();
                    return true;
                case 1011:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zzb(data.createStringArray(), data.createByteArray(), data.readLong());
                    reply.writeNoException();
                    return true;
                case 1012:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zzi(data.readString(), data.readLong());
                    reply.writeNoException();
                    return true;
                case 1013:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zzai(data.readLong());
                    reply.writeNoException();
                    return true;
                case AdProperties.CAN_PLAY_VIDEO /*1014*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zzF(data.readLong());
                    reply.writeNoException();
                    return true;
                case 1015:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zzaj = zzaj(data.readLong());
                    reply.writeNoException();
                    reply.writeString(zzaj);
                    return true;
                case AdProperties.MRAID1 /*1016*/:
                    data.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    zzaj = zzEk();
                    reply.writeNoException();
                    reply.writeString(zzaj);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    String zzEk() throws RemoteException;

    void zzF(long j) throws RemoteException;

    void zza(zzqm com_google_android_gms_internal_zzqm, int i, long j, long j2) throws RemoteException;

    void zza(zzqm com_google_android_gms_internal_zzqm, String str, long j) throws RemoteException;

    void zza(zzqm com_google_android_gms_internal_zzqm, String str, long j, long j2) throws RemoteException;

    void zza(zzqm com_google_android_gms_internal_zzqm, String str, AppMetadata appMetadata, long j, long j2) throws RemoteException;

    void zza(zzqm com_google_android_gms_internal_zzqm, String str, String str2, byte[] bArr, long j) throws RemoteException;

    void zza(zzqm com_google_android_gms_internal_zzqm, String str, byte[] bArr, long j) throws RemoteException;

    void zza(String[] strArr, byte[] bArr, long j) throws RemoteException;

    void zzag(long j) throws RemoteException;

    void zzah(long j) throws RemoteException;

    void zzai(long j) throws RemoteException;

    String zzaj(long j) throws RemoteException;

    void zzb(String[] strArr, byte[] bArr, long j) throws RemoteException;

    void zzh(String str, long j) throws RemoteException;

    void zzi(String str, long j) throws RemoteException;
}
