package com.google.android.gms.internal;

import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveSpace;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.zzj;
import com.google.android.gms.drive.metadata.internal.zzk;
import com.google.android.gms.drive.metadata.internal.zzm;
import com.google.android.gms.drive.metadata.internal.zzo;
import com.google.android.gms.drive.metadata.internal.zzp;
import com.google.android.gms.drive.metadata.internal.zzq;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class zznm {
    public static final MetadataField<DriveId> zzasU;
    public static final MetadataField<String> zzasV;
    public static final zza zzasW;
    public static final MetadataField<String> zzasX;
    public static final MetadataField<String> zzasY;
    public static final MetadataField<String> zzasZ;
    public static final zzg zzatA;
    public static final zzh zzatB;
    public static final MetadataField<String> zzatC;
    public static final MetadataField<String> zzatD;
    public static final MetadataField<String> zzatE;
    public static final com.google.android.gms.drive.metadata.internal.zzb zzatF;
    public static final MetadataField<String> zzatG;
    public static final MetadataField<String> zzatH;
    public static final zze zzatI;
    public static final MetadataField<String> zzatJ;
    public static final MetadataField<Boolean> zzatK;
    public static final MetadataField<Long> zzata;
    public static final MetadataField<String> zzatb;
    public static final MetadataField<Boolean> zzatc;
    public static final MetadataField<String> zzatd;
    public static final MetadataField<Boolean> zzate;
    public static final MetadataField<Boolean> zzatf;
    public static final MetadataField<Boolean> zzatg;
    public static final MetadataField<Boolean> zzath;
    public static final MetadataField<Boolean> zzati;
    public static final zzb zzatj;
    public static final MetadataField<Boolean> zzatk;
    public static final MetadataField<Boolean> zzatl;
    public static final MetadataField<Boolean> zzatm;
    public static final MetadataField<Boolean> zzatn;
    public static final MetadataField<Boolean> zzato;
    public static final MetadataField<Boolean> zzatp;
    public static final MetadataField<Boolean> zzatq;
    public static final zzc zzatr;
    public static final MetadataField<String> zzats;
    public static final com.google.android.gms.drive.metadata.zzb<String> zzatt;
    public static final zzq zzatu;
    public static final zzq zzatv;
    public static final zzm zzatw;
    public static final zzd zzatx;
    public static final zzf zzaty;
    public static final MetadataField<BitmapTeleporter> zzatz;

    static class 1 extends com.google.android.gms.drive.metadata.internal.zzb {
        1(String str, Collection collection, Collection collection2, int i) {
            super(str, collection, collection2, i);
        }

        protected /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
            return zze(dataHolder, i, i2);
        }

        protected Boolean zze(DataHolder dataHolder, int i, int i2) {
            return Boolean.valueOf(dataHolder.zzc("trashed", i, i2) == 2);
        }
    }

    static class 2 extends zzk<BitmapTeleporter> {
        2(String str, Collection collection, Collection collection2, int i) {
            super(str, collection, collection2, i);
        }

        protected /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
            return zzk(dataHolder, i, i2);
        }

        protected BitmapTeleporter zzk(DataHolder dataHolder, int i, int i2) {
            throw new IllegalStateException("Thumbnail field is write only");
        }
    }

    public static class zza extends zznn implements SearchableMetadataField<AppVisibleCustomProperties> {
        public zza(int i) {
            super(i);
        }
    }

    public static class zzb extends com.google.android.gms.drive.metadata.internal.zzb implements SearchableMetadataField<Boolean> {
        public zzb(String str, int i) {
            super(str, i);
        }
    }

    public static class zzc extends zzp implements SearchableMetadataField<String> {
        public zzc(int i) {
            super("mimeType", i);
        }
    }

    public static class zzd extends com.google.android.gms.drive.metadata.internal.zzg implements SortableMetadataField<Long> {
        public zzd(String str, int i) {
            super(str, i);
        }
    }

    public static class zze extends zzj<DriveSpace> {
        public zze(int i) {
            super("spaces", Arrays.asList(new String[]{"inDriveSpace", "isAppData", "inGooglePhotosSpace"}), Collections.emptySet(), i);
        }

        protected /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
            return zzd(dataHolder, i, i2);
        }

        protected Collection<DriveSpace> zzd(DataHolder dataHolder, int i, int i2) {
            Collection arrayList = new ArrayList();
            if (dataHolder.zze("inDriveSpace", i, i2)) {
                arrayList.add(DriveSpace.zzaoP);
            }
            if (dataHolder.zze("isAppData", i, i2)) {
                arrayList.add(DriveSpace.zzaoQ);
            }
            if (dataHolder.zze("inGooglePhotosSpace", i, i2)) {
                arrayList.add(DriveSpace.zzaoR);
            }
            return arrayList;
        }
    }

    public static class zzf extends com.google.android.gms.drive.metadata.internal.zzb implements SearchableMetadataField<Boolean> {
        public zzf(String str, int i) {
            super(str, i);
        }
    }

    public static class zzg extends zzp implements SearchableMetadataField<String>, SortableMetadataField<String> {
        public zzg(String str, int i) {
            super(str, i);
        }
    }

    public static class zzh extends com.google.android.gms.drive.metadata.internal.zzb implements SearchableMetadataField<Boolean> {
        public zzh(String str, int i) {
            super(str, i);
        }

        protected /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
            return zze(dataHolder, i, i2);
        }

        protected Boolean zze(DataHolder dataHolder, int i, int i2) {
            return Boolean.valueOf(dataHolder.zzc(getName(), i, i2) != 0);
        }
    }

    static {
        zzasU = zznp.zzatS;
        zzasV = new zzp("alternateLink", 4300000);
        zzasW = new zza(5000000);
        zzasX = new zzp(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION, 4300000);
        zzasY = new zzp("embedLink", 4300000);
        zzasZ = new zzp("fileExtension", 4300000);
        zzata = new com.google.android.gms.drive.metadata.internal.zzg("fileSize", 4300000);
        zzatb = new zzp("folderColorRgb", 7500000);
        zzatc = new com.google.android.gms.drive.metadata.internal.zzb("hasThumbnail", 4300000);
        zzatd = new zzp("indexableText", 4300000);
        zzate = new com.google.android.gms.drive.metadata.internal.zzb("isAppData", 4300000);
        zzatf = new com.google.android.gms.drive.metadata.internal.zzb("isCopyable", 4300000);
        zzatg = new com.google.android.gms.drive.metadata.internal.zzb("isEditable", 4100000);
        zzath = new 1("isExplicitlyTrashed", Collections.singleton("trashed"), Collections.emptySet(), 7000000);
        zzati = new com.google.android.gms.drive.metadata.internal.zzb("isLocalContentUpToDate", 7800000);
        zzatj = new zzb("isPinned", 4100000);
        zzatk = new com.google.android.gms.drive.metadata.internal.zzb("isOpenable", 7200000);
        zzatl = new com.google.android.gms.drive.metadata.internal.zzb("isRestricted", 4300000);
        zzatm = new com.google.android.gms.drive.metadata.internal.zzb("isShared", 4300000);
        zzatn = new com.google.android.gms.drive.metadata.internal.zzb("isGooglePhotosFolder", 7000000);
        zzato = new com.google.android.gms.drive.metadata.internal.zzb("isGooglePhotosRootFolder", 7000000);
        zzatp = new com.google.android.gms.drive.metadata.internal.zzb("isTrashable", 4400000);
        zzatq = new com.google.android.gms.drive.metadata.internal.zzb("isViewed", 4300000);
        zzatr = new zzc(4100000);
        zzats = new zzp("originalFilename", 4300000);
        zzatt = new zzo("ownerNames", 4300000);
        zzatu = new zzq("lastModifyingUser", 6000000);
        zzatv = new zzq("sharingUser", 6000000);
        zzatw = new zzm(4100000);
        zzatx = new zzd("quotaBytesUsed", 4300000);
        zzaty = new zzf("starred", 4100000);
        zzatz = new 2("thumbnail", Collections.emptySet(), Collections.emptySet(), 4400000);
        zzatA = new zzg(ShareConstants.WEB_DIALOG_PARAM_TITLE, 4100000);
        zzatB = new zzh("trashed", 4100000);
        zzatC = new zzp("webContentLink", 4300000);
        zzatD = new zzp("webViewLink", 4300000);
        zzatE = new zzp("uniqueIdentifier", 5000000);
        zzatF = new com.google.android.gms.drive.metadata.internal.zzb("writersCanShare", 6000000);
        zzatG = new zzp("role", 6000000);
        zzatH = new zzp("md5Checksum", 7000000);
        zzatI = new zze(7000000);
        zzatJ = new zzp("recencyReason", 8000000);
        zzatK = new com.google.android.gms.drive.metadata.internal.zzb("subscribed", 8000000);
    }
}
