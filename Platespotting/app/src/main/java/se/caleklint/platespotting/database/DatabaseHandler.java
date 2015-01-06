package se.caleklint.platespotting.database;

import static se.caleklint.platespotting.database.PlateOpenHelper.SPOTTED_TABLE_NAME;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHandler {
    private PlateOpenHelper databaseHelper;

    public DatabaseHandler(Context context) {
        databaseHelper = new PlateOpenHelper(context);
    }

    public int getNumberOfStoredPlates() {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * from " + SPOTTED_TABLE_NAME, null);
        cursor.moveToFirst();
        return cursor.getCount();
    }
}
