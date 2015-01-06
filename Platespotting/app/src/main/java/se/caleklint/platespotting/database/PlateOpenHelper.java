package se.caleklint.platespotting.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlateOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PlateSpotting";
    public static final String SPOTTED_TABLE_NAME = "plates";
    private static final int DATABASE_VERSION = 1;
    private static final String PLATE_NUMBER = "plate_number";
    private static final String FULL_PLATE = "full_plate";
    private static final String SPOTTED_DATE = "spotted_date";
    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private static final String COLOR = "color";
    private static final String NOTES = "notes";
    private static final String LAT = "latitude";
    private static final String LONG = "longitude";
    private static final String PICTURE_PATH = "picture_path";
    private static final String SPOTTED_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + SPOTTED_TABLE_NAME + " (" +
                    PLATE_NUMBER + " INT PRIMARY KEY ASC ON CONFLICT FAIL, " +
                    FULL_PLATE + " TEXT, " +
                    SPOTTED_DATE + " DATETIME, " +
                    BRAND + " TEXT, " +
                    MODEL + " TEXT, " +
                    COLOR + " TEXT, " +
                    NOTES + " TEXT, " +
                    LAT + " REAL, " +
                    LONG + " REAL, " +
                    PICTURE_PATH + " TEXT);";

    PlateOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SPOTTED_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
