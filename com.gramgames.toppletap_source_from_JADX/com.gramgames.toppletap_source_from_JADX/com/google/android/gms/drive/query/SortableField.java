package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.internal.zznm;
import com.google.android.gms.internal.zzno;
import java.util.Date;

public class SortableField {
    public static final SortableMetadataField<Date> CREATED_DATE;
    public static final SortableMetadataField<Date> LAST_VIEWED_BY_ME;
    public static final SortableMetadataField<Date> MODIFIED_BY_ME_DATE;
    public static final SortableMetadataField<Date> MODIFIED_DATE;
    public static final SortableMetadataField<Long> QUOTA_USED;
    public static final SortableMetadataField<Date> SHARED_WITH_ME_DATE;
    public static final SortableMetadataField<String> TITLE;
    public static final SortableMetadataField<Date> zzauf;

    static {
        TITLE = zznm.zzatA;
        CREATED_DATE = zzno.zzatM;
        MODIFIED_DATE = zzno.zzatO;
        MODIFIED_BY_ME_DATE = zzno.zzatP;
        LAST_VIEWED_BY_ME = zzno.zzatN;
        SHARED_WITH_ME_DATE = zzno.zzatQ;
        QUOTA_USED = zznm.zzatx;
        zzauf = zzno.zzatR;
    }
}
