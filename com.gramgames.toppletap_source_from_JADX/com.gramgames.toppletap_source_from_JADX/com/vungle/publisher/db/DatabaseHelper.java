package com.vungle.publisher.db;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.vungle.log.Logger;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.bv;
import com.vungle.publisher.em;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.file.CacheManager;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String[] e;
    @Inject
    public CacheManager a;
    @Inject
    public EventBus b;
    @Inject
    public em c;
    @Inject
    public ScheduledPriorityExecutor d;

    public class 1 implements Runnable {
        final /* synthetic */ DatabaseHelper a;

        public 1(DatabaseHelper databaseHelper) {
            this.a = databaseHelper;
        }

        public final void run() {
            Logger.d(Logger.DATABASE_TAG, "initializing database vungle");
            this.a.getWritableDatabase();
            Logger.d(Logger.DATABASE_TAG, "done initializing database vungle");
            this.a.b.a(new bv());
        }
    }

    static {
        e = new String[]{"ad", "viewable", "archive_entry", "event_tracking", "ad_report", "ad_play", "ad_report_event", "ad_report_extra", "event_tracking_http_log", "logged_exceptions"};
    }

    @Inject
    public DatabaseHelper(Context context) {
        super(context, "vungle", null, 8);
    }

    public void onCreate(SQLiteDatabase database) {
        Logger.d(Logger.DATABASE_TAG, "creating database: vungle");
        Logger.d(Logger.DATABASE_TAG, "creating table: ad");
        Logger.v(Logger.DATABASE_TAG, "CREATE TABLE ad (id STRING PRIMARY KEY, advertising_app_vungle_id INTEGER, call_to_action_final_url STRING, call_to_action_url STRING, delivery_id STRING NOT NULL, status STRING NOT NULL, type STRING NOT NULL, delete_local_content_attempts INTEGER, expiration_timestamp_seconds INTEGER, parent_path STRING, prepare_retry_count INTEGER, received_timestamp_millis INTEGER, insert_timestamp_millis INTEGER NOT NULL, update_timestamp_millis INTEGER NOT NULL, failed_timestamp_millis INTEGER NOT NULL);");
        database.execSQL("CREATE TABLE ad (id STRING PRIMARY KEY, advertising_app_vungle_id INTEGER, call_to_action_final_url STRING, call_to_action_url STRING, delivery_id STRING NOT NULL, status STRING NOT NULL, type STRING NOT NULL, delete_local_content_attempts INTEGER, expiration_timestamp_seconds INTEGER, parent_path STRING, prepare_retry_count INTEGER, received_timestamp_millis INTEGER, insert_timestamp_millis INTEGER NOT NULL, update_timestamp_millis INTEGER NOT NULL, failed_timestamp_millis INTEGER NOT NULL);");
        Logger.d(Logger.DATABASE_TAG, "creating table: viewable");
        Logger.v(Logger.DATABASE_TAG, "CREATE TABLE viewable (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_id STRING NOT NULL REFERENCES ad(id) ON DELETE CASCADE ON UPDATE CASCADE, type STRING NOT NULL, url STRING NOT NULL, status STRING NOT NULL, size INTEGER, width INTEGER, height INTEGER, show_close_delay_incentivized_seconds INTEGER, show_close_delay_interstitial_seconds INTEGER, show_countdown_delay_seconds INTEGER, cta_clickable_percent REAL, enable_cta_delay_seconds INTEGER, is_cta_enabled INTEGER, is_cta_shown_on_touch INTEGER, show_cta_delay_seconds INTEGER, checksum STRING, CONSTRAINT viewable_ad_type_uk UNIQUE (ad_id, type));");
        database.execSQL("CREATE TABLE viewable (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_id STRING NOT NULL REFERENCES ad(id) ON DELETE CASCADE ON UPDATE CASCADE, type STRING NOT NULL, url STRING NOT NULL, status STRING NOT NULL, size INTEGER, width INTEGER, height INTEGER, show_close_delay_incentivized_seconds INTEGER, show_close_delay_interstitial_seconds INTEGER, show_countdown_delay_seconds INTEGER, cta_clickable_percent REAL, enable_cta_delay_seconds INTEGER, is_cta_enabled INTEGER, is_cta_shown_on_touch INTEGER, show_cta_delay_seconds INTEGER, checksum STRING, CONSTRAINT viewable_ad_type_uk UNIQUE (ad_id, type));");
        Logger.d(Logger.DATABASE_TAG, "creating table: archive_entry");
        Logger.v(Logger.DATABASE_TAG, "CREATE TABLE archive_entry (id INTEGER PRIMARY KEY AUTOINCREMENT, viewable_id INTEGER NOT NULL REFERENCES viewable(id) ON DELETE CASCADE ON UPDATE CASCADE, relative_path STRING NOT NULL, size INTEGER, CONSTRAINT archive_entry_viewable_id_path_uk UNIQUE (id, relative_path));");
        database.execSQL("CREATE TABLE archive_entry (id INTEGER PRIMARY KEY AUTOINCREMENT, viewable_id INTEGER NOT NULL REFERENCES viewable(id) ON DELETE CASCADE ON UPDATE CASCADE, relative_path STRING NOT NULL, size INTEGER, CONSTRAINT archive_entry_viewable_id_path_uk UNIQUE (id, relative_path));");
        Logger.d(Logger.DATABASE_TAG, "creating table: event_tracking");
        Logger.v(Logger.DATABASE_TAG, "CREATE TABLE event_tracking (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_id STRING NOT NULL REFERENCES ad(id) ON DELETE CASCADE ON UPDATE CASCADE, event INTEGER NOT NULL, url STRING NOT NULL);");
        database.execSQL("CREATE TABLE event_tracking (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_id STRING NOT NULL REFERENCES ad(id) ON DELETE CASCADE ON UPDATE CASCADE, event INTEGER NOT NULL, url STRING NOT NULL);");
        Logger.d(Logger.DATABASE_TAG, "creating table: ad_report");
        Logger.v(Logger.DATABASE_TAG, "CREATE TABLE ad_report (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_id STRING NOT NULL REFERENCES ad(id) ON DELETE CASCADE ON UPDATE CASCADE, incentivized_publisher_app_user_id STRING, is_incentivized INTEGER NOT NULL, placement STRING, status STRING NOT NULL, video_duration_millis INTEGER, view_end_millis INTEGER, view_start_millis INTEGER, download_end_millis INTEGER, insert_timestamp_millis INTEGER NOT NULL, update_timestamp_millis INTEGER NOT NULL);");
        database.execSQL("CREATE TABLE ad_report (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_id STRING NOT NULL REFERENCES ad(id) ON DELETE CASCADE ON UPDATE CASCADE, incentivized_publisher_app_user_id STRING, is_incentivized INTEGER NOT NULL, placement STRING, status STRING NOT NULL, video_duration_millis INTEGER, view_end_millis INTEGER, view_start_millis INTEGER, download_end_millis INTEGER, insert_timestamp_millis INTEGER NOT NULL, update_timestamp_millis INTEGER NOT NULL);");
        Logger.d(Logger.DATABASE_TAG, "creating table: ad_play");
        Logger.v(Logger.DATABASE_TAG, "CREATE TABLE ad_play (id INTEGER PRIMARY KEY AUTOINCREMENT, report_id INTEGER NOT NULL REFERENCES ad_report(id) ON DELETE CASCADE ON UPDATE CASCADE, start_millis INTEGER, watched_millis INTEGER);");
        database.execSQL("CREATE TABLE ad_play (id INTEGER PRIMARY KEY AUTOINCREMENT, report_id INTEGER NOT NULL REFERENCES ad_report(id) ON DELETE CASCADE ON UPDATE CASCADE, start_millis INTEGER, watched_millis INTEGER);");
        Logger.d(Logger.DATABASE_TAG, "creating table: ad_report_event");
        Logger.v(Logger.DATABASE_TAG, "CREATE TABLE ad_report_event (id INTEGER PRIMARY KEY AUTOINCREMENT, play_id INTEGER NOT NULL REFERENCES ad_play(id) ON DELETE CASCADE ON UPDATE CASCADE, event STRING NOT NULL, insert_timestamp_millis INTEGER NOT NULL, value STRING);");
        database.execSQL("CREATE TABLE ad_report_event (id INTEGER PRIMARY KEY AUTOINCREMENT, play_id INTEGER NOT NULL REFERENCES ad_play(id) ON DELETE CASCADE ON UPDATE CASCADE, event STRING NOT NULL, insert_timestamp_millis INTEGER NOT NULL, value STRING);");
        Logger.d(Logger.DATABASE_TAG, "creating table: ad_report_extra");
        Logger.v(Logger.DATABASE_TAG, "CREATE TABLE ad_report_extra (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_report_id INTEGER NOT NULL REFERENCES ad_report(id) ON DELETE CASCADE ON UPDATE CASCADE, name STRING, value STRING, CONSTRAINT ad_report_extra_id_name_uk UNIQUE (id, name));");
        database.execSQL("CREATE TABLE ad_report_extra (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_report_id INTEGER NOT NULL REFERENCES ad_report(id) ON DELETE CASCADE ON UPDATE CASCADE, name STRING, value STRING, CONSTRAINT ad_report_extra_id_name_uk UNIQUE (id, name));");
        Logger.d(Logger.DATABASE_TAG, "creating table: event_tracking_http_log");
        Logger.v(Logger.DATABASE_TAG, "CREATE TABLE event_tracking_http_log (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_id STRING NOT NULL, delivery_id STRING NOT NULL, event STRING NOT NULL, response_code INTEGER NOT NULL, response_timestamp_millis INTEGER, url STRING NOT NULL, insert_timestamp_millis INTEGER NOT NULL);");
        database.execSQL("CREATE TABLE event_tracking_http_log (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_id STRING NOT NULL, delivery_id STRING NOT NULL, event STRING NOT NULL, response_code INTEGER NOT NULL, response_timestamp_millis INTEGER, url STRING NOT NULL, insert_timestamp_millis INTEGER NOT NULL);");
        Logger.d(Logger.DATABASE_TAG, "creating table: logged_exceptions");
        Logger.v(Logger.DATABASE_TAG, "CREATE TABLE logged_exceptions (id INTEGER PRIMARY KEY AUTOINCREMENT, stack_trace STRING, tag STRING, log_message STRING, class STRING, type INTEGER NOT NULL, android_version STRING, sdk_version STRING NOT NULL, play_services_version STRING, insert_timestamp_millis INTEGER NOT NULL);");
        database.execSQL("CREATE TABLE logged_exceptions (id INTEGER PRIMARY KEY AUTOINCREMENT, stack_trace STRING, tag STRING, log_message STRING, class STRING, type INTEGER NOT NULL, android_version STRING, sdk_version STRING NOT NULL, play_services_version STRING, insert_timestamp_millis INTEGER NOT NULL);");
    }

    public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Logger.d(Logger.DATABASE_TAG, "downgrading databse version " + oldVersion + " --> " + newVersion);
        a(database);
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Logger.d(Logger.DATABASE_TAG, "upgrading database version " + oldVersion + " --> " + newVersion);
        a(database);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        for (String str : e) {
            Logger.d(Logger.DATABASE_TAG, "dropping table: " + str);
            String str2 = "DROP TABLE  IF EXISTS " + str2;
            Logger.v(Logger.DATABASE_TAG, str2);
            sQLiteDatabase.execSQL(str2);
        }
        CacheManager cacheManager = this.a;
        Logger.d(Logger.FILE_TAG, "deleting ad temp directory");
        CacheManager.a((String) cacheManager.a.get());
        onCreate(sQLiteDatabase);
    }

    public void onOpen(SQLiteDatabase database) {
        super.onOpen(database);
        Logger.d(Logger.DATABASE_TAG, "enabling foreign keys");
        database.execSQL("PRAGMA foreign_keys = true");
    }

    final void a(String... strArr) {
        String b = this.c.b();
        Logger.d(Logger.DATABASE_DUMP_TAG, b + " sdk version VungleDroid/3.3.5, database version 8");
        if (strArr == null || strArr.length <= 0) {
            Logger.d(Logger.DATABASE_DUMP_TAG, b + " dumping all tables");
            strArr = e;
        }
        SQLiteDatabase readableDatabase = getReadableDatabase();
        for (String str : r13) {
            Logger.i(Logger.DATABASE_DUMP_TAG, b + " dumping table " + str);
            Cursor query = readableDatabase.query(str, null, null, new String[0], null, null, null);
            StringBuilder stringBuilder = new StringBuilder();
            DatabaseUtils.dumpCursor(query, stringBuilder);
            Logger.d(Logger.DATABASE_DUMP_TAG, stringBuilder.toString());
        }
    }
}
