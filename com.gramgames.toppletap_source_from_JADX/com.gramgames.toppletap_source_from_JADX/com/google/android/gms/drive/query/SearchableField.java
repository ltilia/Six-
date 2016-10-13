package com.google.android.gms.drive.query;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.internal.zznm;
import com.google.android.gms.internal.zzno;
import java.util.Date;

public class SearchableField {
    public static final SearchableMetadataField<Boolean> IS_PINNED;
    public static final SearchableOrderedMetadataField<Date> LAST_VIEWED_BY_ME;
    public static final SearchableMetadataField<String> MIME_TYPE;
    public static final SearchableOrderedMetadataField<Date> MODIFIED_DATE;
    public static final SearchableCollectionMetadataField<DriveId> PARENTS;
    public static final SearchableMetadataField<Boolean> STARRED;
    public static final SearchableMetadataField<String> TITLE;
    public static final SearchableMetadataField<Boolean> TRASHED;
    public static final SearchableOrderedMetadataField<Date> zzaub;
    public static final SearchableMetadataField<AppVisibleCustomProperties> zzauc;

    static {
        TITLE = zznm.zzatA;
        MIME_TYPE = zznm.zzatr;
        TRASHED = zznm.zzatB;
        PARENTS = zznm.zzatw;
        zzaub = zzno.zzatQ;
        STARRED = zznm.zzaty;
        MODIFIED_DATE = zzno.zzatO;
        LAST_VIEWED_BY_ME = zzno.zzatN;
        IS_PINNED = zznm.zzatj;
        zzauc = zznm.zzasW;
    }
}
