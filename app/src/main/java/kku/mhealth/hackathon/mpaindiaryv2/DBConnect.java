package kku.mhealth.hackathon.mpaindiaryv2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TheButterfly on 11-Jan-17.
 */

public class DBConnect extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "mPainDiaryDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_DLOG = "diaryLog";

    // Post Table Columns
    private static final String KEY_DLOG_ID = "id";
    private static final String KEY_DLOG_DATE = "date";
    private static final String KEY_DLOG_PAINLEVEL = "painLevel";
    private static final String KEY_DLOG_PAINPOINT = "painPoint";
    private static final String KEY_DLOG_ACTIVITIES = "activity";

    public DBConnect(Context context) {
        //super(context, "hackathon_mpaindiary.db", null, 1);
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DLOG_TABLE = "CREATE TABLE " + TABLE_DLOG +
                "(" +
                KEY_DLOG_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                KEY_DLOG_DATE + " TEXT," +
                KEY_DLOG_PAINLEVEL + " INTEGER," +
                KEY_DLOG_PAINPOINT + " TEXT," +
                KEY_DLOG_ACTIVITIES + " TEXT" +
                ")";

        db.execSQL(CREATE_DLOG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DLOG);
            onCreate(db);
        }
    }
}
