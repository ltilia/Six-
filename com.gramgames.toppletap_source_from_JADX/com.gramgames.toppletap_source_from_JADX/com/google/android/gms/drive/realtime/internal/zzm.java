package com.google.android.gms.drive.realtime.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.realtime.internal.event.ParcelableEventList;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import org.json.simple.parser.Yytoken;

public interface zzm extends IInterface {

    public static abstract class zza extends Binder implements zzm {

        private static class zza implements zzm {
            private IBinder zzoz;

            zza(IBinder iBinder) {
                this.zzoz = iBinder;
            }

            public IBinder asBinder() {
                return this.zzoz;
            }

            public void zza(int i, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzj != null ? com_google_android_gms_drive_realtime_internal_zzj.asBinder() : null);
                    this.zzoz.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(int i, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzo != null ? com_google_android_gms_drive_realtime_internal_zzo.asBinder() : null);
                    this.zzoz.transact(50, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(DriveId driveId, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    if (driveId != null) {
                        obtain.writeInt(1);
                        driveId.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzo != null ? com_google_android_gms_drive_realtime_internal_zzo.asBinder() : null);
                    this.zzoz.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(BeginCompoundOperationRequest beginCompoundOperationRequest, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    if (beginCompoundOperationRequest != null) {
                        obtain.writeInt(1);
                        beginCompoundOperationRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzo != null ? com_google_android_gms_drive_realtime_internal_zzo.asBinder() : null);
                    this.zzoz.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(EndCompoundOperationRequest endCompoundOperationRequest, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    if (endCompoundOperationRequest != null) {
                        obtain.writeInt(1);
                        endCompoundOperationRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzj != null ? com_google_android_gms_drive_realtime_internal_zzj.asBinder() : null);
                    this.zzoz.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(EndCompoundOperationRequest endCompoundOperationRequest, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    if (endCompoundOperationRequest != null) {
                        obtain.writeInt(1);
                        endCompoundOperationRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzo != null ? com_google_android_gms_drive_realtime_internal_zzo.asBinder() : null);
                    this.zzoz.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(ParcelableIndexReference parcelableIndexReference, zzn com_google_android_gms_drive_realtime_internal_zzn) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    if (parcelableIndexReference != null) {
                        obtain.writeInt(1);
                        parcelableIndexReference.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzn != null ? com_google_android_gms_drive_realtime_internal_zzn.asBinder() : null);
                    this.zzoz.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzc com_google_android_gms_drive_realtime_internal_zzc) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzc != null ? com_google_android_gms_drive_realtime_internal_zzc.asBinder() : null);
                    this.zzoz.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzd com_google_android_gms_drive_realtime_internal_zzd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzd != null ? com_google_android_gms_drive_realtime_internal_zzd.asBinder() : null);
                    this.zzoz.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zze com_google_android_gms_drive_realtime_internal_zze) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zze != null ? com_google_android_gms_drive_realtime_internal_zze.asBinder() : null);
                    this.zzoz.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzh com_google_android_gms_drive_realtime_internal_zzh) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzh != null ? com_google_android_gms_drive_realtime_internal_zzh.asBinder() : null);
                    this.zzoz.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzi com_google_android_gms_drive_realtime_internal_zzi) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzi != null ? com_google_android_gms_drive_realtime_internal_zzi.asBinder() : null);
                    this.zzoz.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzj != null ? com_google_android_gms_drive_realtime_internal_zzj.asBinder() : null);
                    this.zzoz.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzl com_google_android_gms_drive_realtime_internal_zzl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzl != null ? com_google_android_gms_drive_realtime_internal_zzl.asBinder() : null);
                    this.zzoz.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzo != null ? com_google_android_gms_drive_realtime_internal_zzo.asBinder() : null);
                    this.zzoz.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, int i, int i2, zzg com_google_android_gms_drive_realtime_internal_zzg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzg != null ? com_google_android_gms_drive_realtime_internal_zzg.asBinder() : null);
                    this.zzoz.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, int i, int i2, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzj != null ? com_google_android_gms_drive_realtime_internal_zzj.asBinder() : null);
                    this.zzoz.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, int i, DataHolder dataHolder, zzg com_google_android_gms_drive_realtime_internal_zzg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzg != null ? com_google_android_gms_drive_realtime_internal_zzg.asBinder() : null);
                    this.zzoz.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, int i, DataHolder dataHolder, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzj != null ? com_google_android_gms_drive_realtime_internal_zzj.asBinder() : null);
                    this.zzoz.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, int i, zzn com_google_android_gms_drive_realtime_internal_zzn) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzn != null ? com_google_android_gms_drive_realtime_internal_zzn.asBinder() : null);
                    this.zzoz.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, int i, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzo != null ? com_google_android_gms_drive_realtime_internal_zzo.asBinder() : null);
                    this.zzoz.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, int i, String str2, int i2, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzj != null ? com_google_android_gms_drive_realtime_internal_zzj.asBinder() : null);
                    this.zzoz.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, int i, String str2, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzj != null ? com_google_android_gms_drive_realtime_internal_zzj.asBinder() : null);
                    this.zzoz.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, DataHolder dataHolder, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzj != null ? com_google_android_gms_drive_realtime_internal_zzj.asBinder() : null);
                    this.zzoz.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, zzf com_google_android_gms_drive_realtime_internal_zzf) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzf != null ? com_google_android_gms_drive_realtime_internal_zzf.asBinder() : null);
                    this.zzoz.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzj != null ? com_google_android_gms_drive_realtime_internal_zzj.asBinder() : null);
                    this.zzoz.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, zzk com_google_android_gms_drive_realtime_internal_zzk) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzk != null ? com_google_android_gms_drive_realtime_internal_zzk.asBinder() : null);
                    this.zzoz.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, zzl com_google_android_gms_drive_realtime_internal_zzl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzl != null ? com_google_android_gms_drive_realtime_internal_zzl.asBinder() : null);
                    this.zzoz.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, zzn com_google_android_gms_drive_realtime_internal_zzn) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzn != null ? com_google_android_gms_drive_realtime_internal_zzn.asBinder() : null);
                    this.zzoz.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzo != null ? com_google_android_gms_drive_realtime_internal_zzo.asBinder() : null);
                    this.zzoz.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, String str2, DataHolder dataHolder, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzj != null ? com_google_android_gms_drive_realtime_internal_zzj.asBinder() : null);
                    this.zzoz.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, String str2, zzf com_google_android_gms_drive_realtime_internal_zzf) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzf != null ? com_google_android_gms_drive_realtime_internal_zzf.asBinder() : null);
                    this.zzoz.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, String str2, zzg com_google_android_gms_drive_realtime_internal_zzg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzg != null ? com_google_android_gms_drive_realtime_internal_zzg.asBinder() : null);
                    this.zzoz.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, String str2, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzj != null ? com_google_android_gms_drive_realtime_internal_zzj.asBinder() : null);
                    this.zzoz.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(boolean z, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzo != null ? com_google_android_gms_drive_realtime_internal_zzo.asBinder() : null);
                    this.zzoz.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(zzc com_google_android_gms_drive_realtime_internal_zzc) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzc != null ? com_google_android_gms_drive_realtime_internal_zzc.asBinder() : null);
                    this.zzoz.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzj != null ? com_google_android_gms_drive_realtime_internal_zzj.asBinder() : null);
                    this.zzoz.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(zzl com_google_android_gms_drive_realtime_internal_zzl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzl != null ? com_google_android_gms_drive_realtime_internal_zzl.asBinder() : null);
                    this.zzoz.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzo != null ? com_google_android_gms_drive_realtime_internal_zzo.asBinder() : null);
                    this.zzoz.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(String str, zzf com_google_android_gms_drive_realtime_internal_zzf) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzf != null ? com_google_android_gms_drive_realtime_internal_zzf.asBinder() : null);
                    this.zzoz.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(String str, zzl com_google_android_gms_drive_realtime_internal_zzl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzl != null ? com_google_android_gms_drive_realtime_internal_zzl.asBinder() : null);
                    this.zzoz.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(String str, zzn com_google_android_gms_drive_realtime_internal_zzn) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzn != null ? com_google_android_gms_drive_realtime_internal_zzn.asBinder() : null);
                    this.zzoz.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(String str, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzo != null ? com_google_android_gms_drive_realtime_internal_zzo.asBinder() : null);
                    this.zzoz.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(String str, String str2, zzf com_google_android_gms_drive_realtime_internal_zzf) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzf != null ? com_google_android_gms_drive_realtime_internal_zzf.asBinder() : null);
                    this.zzoz.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzc(zzc com_google_android_gms_drive_realtime_internal_zzc) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzc != null ? com_google_android_gms_drive_realtime_internal_zzc.asBinder() : null);
                    this.zzoz.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzc(zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzo != null ? com_google_android_gms_drive_realtime_internal_zzo.asBinder() : null);
                    this.zzoz.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzc(String str, zzl com_google_android_gms_drive_realtime_internal_zzl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzl != null ? com_google_android_gms_drive_realtime_internal_zzl.asBinder() : null);
                    this.zzoz.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzd(zzc com_google_android_gms_drive_realtime_internal_zzc) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzc != null ? com_google_android_gms_drive_realtime_internal_zzc.asBinder() : null);
                    this.zzoz.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zze(zzc com_google_android_gms_drive_realtime_internal_zzc) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(com_google_android_gms_drive_realtime_internal_zzc != null ? com_google_android_gms_drive_realtime_internal_zzc.asBinder() : null);
                    this.zzoz.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ParcelableEventList zzf(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.zzoz.transact(51, obtain, obtain2, 0);
                    obtain2.readException();
                    ParcelableEventList parcelableEventList = obtain2.readInt() != 0 ? (ParcelableEventList) ParcelableEventList.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return parcelableEventList;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zztT() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.zzoz.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzm zzbo(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzm)) ? new zza(iBinder) : (zzm) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            EndCompoundOperationRequest endCompoundOperationRequest = null;
            String readString;
            DataHolder zzak;
            int readInt;
            switch (code) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), com.google.android.gms.drive.realtime.internal.zzn.zza.zzbp(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(com.google.android.gms.drive.realtime.internal.zzc.zza.zzbe(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(com.google.android.gms.drive.realtime.internal.zzo.zza.zzbq(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), data.readString(), com.google.android.gms.drive.realtime.internal.zzf.zza.zzbh(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case Yytoken.TYPE_COMMA /*5*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), com.google.android.gms.drive.realtime.internal.zzl.zza.zzbn(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case Yytoken.TYPE_COLON /*6*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        zzak = DataHolder.CREATOR.zzak(data);
                    }
                    zza(readString, zzak, com.google.android.gms.drive.realtime.internal.zzj.zza.zzbl(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), com.google.android.gms.drive.realtime.internal.zzj.zza.zzbl(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zzb(data.readString(), com.google.android.gms.drive.realtime.internal.zzl.zza.zzbn(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_popupTheme /*9*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zzb(data.readString(), com.google.android.gms.drive.realtime.internal.zzn.zza.zzbp(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_titleTextAppearance /*10*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), data.readInt(), data.readString(), com.google.android.gms.drive.realtime.internal.zzj.zza.zzbl(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_subtitleTextAppearance /*11*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), data.readInt(), data.readInt(), com.google.android.gms.drive.realtime.internal.zzj.zza.zzbl(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_titleMargins /*12*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), data.readString(), com.google.android.gms.drive.realtime.internal.zzj.zza.zzbl(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_titleMarginStart /*13*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zzb(data.readString(), com.google.android.gms.drive.realtime.internal.zzf.zza.zzbh(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_titleMarginEnd /*14*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zzc(data.readString(), com.google.android.gms.drive.realtime.internal.zzl.zza.zzbn(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_titleMarginTop /*15*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    readString = data.readString();
                    readInt = data.readInt();
                    if (data.readInt() != 0) {
                        zzak = DataHolder.CREATOR.zzak(data);
                    }
                    zza(readString, readInt, zzak, com.google.android.gms.drive.realtime.internal.zzj.zza.zzbl(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_titleMarginBottom /*16*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    readString = data.readString();
                    readInt = data.readInt();
                    if (data.readInt() != 0) {
                        zzak = DataHolder.CREATOR.zzak(data);
                    }
                    zza(readString, readInt, zzak, com.google.android.gms.drive.realtime.internal.zzg.zza.zzbi(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_maxButtonHeight /*17*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), data.readInt(), data.readInt(), com.google.android.gms.drive.realtime.internal.zzg.zza.zzbi(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_collapseIcon /*18*/:
                    BeginCompoundOperationRequest beginCompoundOperationRequest;
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    if (data.readInt() != 0) {
                        beginCompoundOperationRequest = (BeginCompoundOperationRequest) BeginCompoundOperationRequest.CREATOR.createFromParcel(data);
                    }
                    zza(beginCompoundOperationRequest, com.google.android.gms.drive.realtime.internal.zzo.zza.zzbq(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_collapseContentDescription /*19*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    if (data.readInt() != 0) {
                        endCompoundOperationRequest = (EndCompoundOperationRequest) EndCompoundOperationRequest.CREATOR.createFromParcel(data);
                    }
                    zza(endCompoundOperationRequest, com.google.android.gms.drive.realtime.internal.zzo.zza.zzbq(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case UnityAdsProperties.MAX_BUFFERING_WAIT_SECONDS /*20*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), com.google.android.gms.drive.realtime.internal.zzf.zza.zzbh(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_navigationContentDescription /*21*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), data.readString(), com.google.android.gms.drive.realtime.internal.zzg.zza.zzbi(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_logoDescription /*22*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(com.google.android.gms.drive.realtime.internal.zzj.zza.zzbl(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_titleTextColor /*23*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zzb(com.google.android.gms.drive.realtime.internal.zzj.zza.zzbl(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Toolbar_subtitleTextColor /*24*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zzd(com.google.android.gms.drive.realtime.internal.zzc.zza.zzbe(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionMenuTextAppearance /*25*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zze(com.google.android.gms.drive.realtime.internal.zzc.zza.zzbe(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionMenuTextColor /*26*/:
                    ParcelableIndexReference parcelableIndexReference;
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    if (data.readInt() != 0) {
                        parcelableIndexReference = (ParcelableIndexReference) ParcelableIndexReference.CREATOR.createFromParcel(data);
                    }
                    zza(parcelableIndexReference, com.google.android.gms.drive.realtime.internal.zzn.zza.zzbp(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionModeStyle /*27*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), com.google.android.gms.drive.realtime.internal.zzk.zza.zzbm(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionModeCloseButtonStyle /*28*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), data.readInt(), com.google.android.gms.drive.realtime.internal.zzo.zza.zzbq(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionModeBackground /*29*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zzb(com.google.android.gms.drive.realtime.internal.zzl.zza.zzbn(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionModeSplitBackground /*30*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readInt(), com.google.android.gms.drive.realtime.internal.zzj.zza.zzbl(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionModeCloseDrawable /*31*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(com.google.android.gms.drive.realtime.internal.zze.zza.zzbg(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionModeCutDrawable /*32*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(com.google.android.gms.drive.realtime.internal.zzd.zza.zzbf(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionModeCopyDrawable /*33*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zzb(com.google.android.gms.drive.realtime.internal.zzc.zza.zzbe(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionModePasteDrawable /*34*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(com.google.android.gms.drive.realtime.internal.zzi.zza.zzbk(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionModeSelectAllDrawable /*35*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zzb(com.google.android.gms.drive.realtime.internal.zzo.zza.zzbq(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionModeShareDrawable /*36*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(com.google.android.gms.drive.realtime.internal.zzh.zza.zzbj(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionModeFindDrawable /*37*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), data.readInt(), data.readString(), data.readInt(), com.google.android.gms.drive.realtime.internal.zzj.zza.zzbl(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionModeWebSearchDrawable /*38*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), com.google.android.gms.drive.realtime.internal.zzo.zza.zzbq(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionModePopupWindowStyle /*39*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zzb(data.readString(), com.google.android.gms.drive.realtime.internal.zzo.zza.zzbq(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_textAppearanceLargePopupMenu /*40*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(com.google.android.gms.drive.realtime.internal.zzl.zza.zzbn(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_textAppearanceSmallPopupMenu /*41*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    if (data.readInt() != 0) {
                        endCompoundOperationRequest = (EndCompoundOperationRequest) EndCompoundOperationRequest.CREATOR.createFromParcel(data);
                    }
                    zza(endCompoundOperationRequest, com.google.android.gms.drive.realtime.internal.zzj.zza.zzbl(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_dialogTheme /*42*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zzb(data.readString(), data.readString(), com.google.android.gms.drive.realtime.internal.zzf.zza.zzbh(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_dialogPreferredPadding /*43*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    readString = data.readString();
                    String readString2 = data.readString();
                    if (data.readInt() != 0) {
                        zzak = DataHolder.CREATOR.zzak(data);
                    }
                    zza(readString, readString2, zzak, com.google.android.gms.drive.realtime.internal.zzj.zza.zzbl(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_listDividerAlertDialog /*44*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zztT();
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionDropDownStyle /*45*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zzc(com.google.android.gms.drive.realtime.internal.zzc.zza.zzbe(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_dropdownListPreferredItemHeight /*46*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readString(), data.readInt(), com.google.android.gms.drive.realtime.internal.zzn.zza.zzbp(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_spinnerDropDownItemStyle /*47*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readInt() != 0, com.google.android.gms.drive.realtime.internal.zzo.zza.zzbq(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_homeAsUpIndicator /*48*/:
                    DriveId driveId;
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    if (data.readInt() != 0) {
                        driveId = (DriveId) DriveId.CREATOR.createFromParcel(data);
                    }
                    zza(driveId, com.google.android.gms.drive.realtime.internal.zzo.zza.zzbq(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_actionButtonStyle /*49*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zzc(com.google.android.gms.drive.realtime.internal.zzo.zza.zzbq(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_buttonBarStyle /*50*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    zza(data.readInt(), com.google.android.gms.drive.realtime.internal.zzo.zza.zzbq(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.Theme_buttonBarButtonStyle /*51*/:
                    data.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    ParcelableEventList zzf = zzf(data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    if (zzf != null) {
                        reply.writeInt(1);
                        zzf.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void zza(int i, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException;

    void zza(int i, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException;

    void zza(DriveId driveId, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException;

    void zza(BeginCompoundOperationRequest beginCompoundOperationRequest, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException;

    void zza(EndCompoundOperationRequest endCompoundOperationRequest, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException;

    void zza(EndCompoundOperationRequest endCompoundOperationRequest, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException;

    void zza(ParcelableIndexReference parcelableIndexReference, zzn com_google_android_gms_drive_realtime_internal_zzn) throws RemoteException;

    void zza(zzc com_google_android_gms_drive_realtime_internal_zzc) throws RemoteException;

    void zza(zzd com_google_android_gms_drive_realtime_internal_zzd) throws RemoteException;

    void zza(zze com_google_android_gms_drive_realtime_internal_zze) throws RemoteException;

    void zza(zzh com_google_android_gms_drive_realtime_internal_zzh) throws RemoteException;

    void zza(zzi com_google_android_gms_drive_realtime_internal_zzi) throws RemoteException;

    void zza(zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException;

    void zza(zzl com_google_android_gms_drive_realtime_internal_zzl) throws RemoteException;

    void zza(zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException;

    void zza(String str, int i, int i2, zzg com_google_android_gms_drive_realtime_internal_zzg) throws RemoteException;

    void zza(String str, int i, int i2, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException;

    void zza(String str, int i, DataHolder dataHolder, zzg com_google_android_gms_drive_realtime_internal_zzg) throws RemoteException;

    void zza(String str, int i, DataHolder dataHolder, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException;

    void zza(String str, int i, zzn com_google_android_gms_drive_realtime_internal_zzn) throws RemoteException;

    void zza(String str, int i, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException;

    void zza(String str, int i, String str2, int i2, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException;

    void zza(String str, int i, String str2, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException;

    void zza(String str, DataHolder dataHolder, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException;

    void zza(String str, zzf com_google_android_gms_drive_realtime_internal_zzf) throws RemoteException;

    void zza(String str, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException;

    void zza(String str, zzk com_google_android_gms_drive_realtime_internal_zzk) throws RemoteException;

    void zza(String str, zzl com_google_android_gms_drive_realtime_internal_zzl) throws RemoteException;

    void zza(String str, zzn com_google_android_gms_drive_realtime_internal_zzn) throws RemoteException;

    void zza(String str, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException;

    void zza(String str, String str2, DataHolder dataHolder, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException;

    void zza(String str, String str2, zzf com_google_android_gms_drive_realtime_internal_zzf) throws RemoteException;

    void zza(String str, String str2, zzg com_google_android_gms_drive_realtime_internal_zzg) throws RemoteException;

    void zza(String str, String str2, zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException;

    void zza(boolean z, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException;

    void zzb(zzc com_google_android_gms_drive_realtime_internal_zzc) throws RemoteException;

    void zzb(zzj com_google_android_gms_drive_realtime_internal_zzj) throws RemoteException;

    void zzb(zzl com_google_android_gms_drive_realtime_internal_zzl) throws RemoteException;

    void zzb(zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException;

    void zzb(String str, zzf com_google_android_gms_drive_realtime_internal_zzf) throws RemoteException;

    void zzb(String str, zzl com_google_android_gms_drive_realtime_internal_zzl) throws RemoteException;

    void zzb(String str, zzn com_google_android_gms_drive_realtime_internal_zzn) throws RemoteException;

    void zzb(String str, zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException;

    void zzb(String str, String str2, zzf com_google_android_gms_drive_realtime_internal_zzf) throws RemoteException;

    void zzc(zzc com_google_android_gms_drive_realtime_internal_zzc) throws RemoteException;

    void zzc(zzo com_google_android_gms_drive_realtime_internal_zzo) throws RemoteException;

    void zzc(String str, zzl com_google_android_gms_drive_realtime_internal_zzl) throws RemoteException;

    void zzd(zzc com_google_android_gms_drive_realtime_internal_zzc) throws RemoteException;

    void zze(zzc com_google_android_gms_drive_realtime_internal_zzc) throws RemoteException;

    ParcelableEventList zzf(String str, String str2, String str3) throws RemoteException;

    void zztT() throws RemoteException;
}
