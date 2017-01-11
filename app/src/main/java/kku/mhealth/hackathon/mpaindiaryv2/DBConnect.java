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

    public long addOrUpdateDiary(Diarylog diaryLog) {

        SQLiteDatabase db = getWritableDatabase();
        long diaryID = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_DLOG_ID, diaryLog.id);
            values.put(KEY_DLOG_DATE, diaryLog.date);
            values.put(KEY_DLOG_PAINLEVEL, diaryLog.painLevel);
            values.put(KEY_DLOG_PAINPOINT, diaryLog.painPoint);
            values.put(KEY_DLOG_ACTIVITIES, diaryLog.activity);

            // First try to update the user in case the user already exists in the database
            // This assumes userNames are unique
            int rows = db.update(TABLE_DLOG, values, KEY_DLOG_DATE + "= ?", new String[]{diaryLog.date});

            // Check if update succeeded
            if (rows == 1) {
                // Get the primary key of the user we just updated
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        KEY_DLOG_ID, KEY_DLOG_DATE, KEY_DLOG_PAINLEVEL, KEY_DLOG_PAINPOINT, KEY_DLOG_ACTIVITIES);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(diaryLog.date)});
                try {
                    if (cursor.moveToFirst()) {
                        diaryID = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                // user with this userName did not already exist, so insert new user
                diaryID = db.insertOrThrow(TABLE_DLOG, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            //Log.d(TAG, "Error while trying to add or update user");
        } finally {
            db.endTransaction();
        }
        return diaryID;
    }

    public List<Diarylog> getDiary() {
        List<Diarylog> dLog = new ArrayList<>();

        // SELECT * FROM POSTS
        // LEFT OUTER JOIN USERS
        // ON POSTS.KEY_POST_USER_ID_FK = USERS.KEY_USER_ID
        String DIARY_SELECT_QUERY =
                String.format("SELECT * FROM %s",
                        TABLE_DLOG);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(DIARY_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Diarylog newDiary = new Diarylog();
                    newDiary.date = cursor.getString(cursor.getColumnIndex(KEY_DLOG_DATE));
                    newDiary.painLevel = cursor.getInt(cursor.getColumnIndex(KEY_DLOG_PAINLEVEL));
                    newDiary.painPoint = cursor.getString(cursor.getColumnIndex(KEY_DLOG_PAINPOINT));
                    newDiary.activity = cursor.getString(cursor.getColumnIndex(KEY_DLOG_ACTIVITIES));

                    /*Post newPost = new Post();
                    newPost.text = cursor.getString(cursor.getColumnIndex(KEY_POST_TEXT));
                    newPost.user = newUser;
                    posts.add(newPost);
                    */
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            //Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return dLog;
    }
}
