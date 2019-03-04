package pl.pzjapp.project.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "DatabaseHelper";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "testname.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static class City implements BaseColumns {
        public static final String TABLE_NAME = "city";
        public static final String NAME = "name";
        public static final String COUNTRY = "country";
        public static final String LONGITUDE = "lon";
        public static final String LATITUDE = "lat";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + City.TABLE_NAME + " (" +
                    City._ID + " INTEGER PRIMARY KEY," +
                    City.NAME + " TEXT," +
                    City.COUNTRY + " TEXT," +
                    City.LONGITUDE + " TEXT," +
                    City.LATITUDE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + City.TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
