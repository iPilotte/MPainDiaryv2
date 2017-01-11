package kku.mhealth.hackathon.mpaindiaryv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    private static DBConnect sInstance;

    public static synchronized DBConnect getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new DBConnect(context.getApplicationContext());
        }
        return sInstance;
    }

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

    public void addDiary(Diarylog diaryLog) {
        // Create and/or open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_DLOG_ID, diaryLog.id);
        values.put(KEY_DLOG_DATE, diaryLog.date);
        values.put(KEY_DLOG_PAINLEVEL, diaryLog.painLevel);
        values.put(KEY_DLOG_PAINPOINT, diaryLog.painPoint);
        values.put(KEY_DLOG_ACTIVITIES, diaryLog.activity);

        db.insert(TABLE_DLOG, null, values);

        db.close();
    }

    public List<String> getDiary() {
        List<String> dLog = new ArrayList<String>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query
                (TABLE_DLOG, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while(!cursor.isAfterLast()) {

            /*dLog.add(cursor.getInt(0) + " " +
                    cursor.getString(1) + " " +
                    cursor.getString(2) + " " +
                    cursor.getString(3) + " " +
                    cursor.getString(4));
            */
            dLog.add(cursor.getString(0));
            cursor.moveToNext();
        }

        db.close();

        return dLog;
    }
}
