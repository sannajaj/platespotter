package se.caleklint.platespotting.database;

import static se.caleklint.platespotting.database.PlateOpenHelper.*;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;

import java.util.Date;

import se.caleklint.platespotting.R;

/**
 * Created by Susanna on 2015-01-23 in the Platespotting.
 */

/*

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
                    PICTURE_PATH + " TEXT, " +
                    PARKED + " BOOLEAN);";
 */
public class Plate {
    private int plateNumber;
    private String plate;
    private Date spottedDated;
    private String brand;
    private String model;
    private String color;
    private String notes;
    private double latitude;
    private double longitude;
    private String picturePath;
    private boolean parked;

    public Plate(Cursor cursor) {
        plateNumber = cursor.getInt(cursor.getColumnIndex(PLATE_NUMBER));
        plate = cursor.getString(cursor.getColumnIndex(FULL_PLATE));
        spottedDated = new Date(cursor.getLong(cursor.getColumnIndex(SPOTTED_DATE)));
        brand = cursor.getString(cursor.getColumnIndex(BRAND));
        model = cursor.getString(cursor.getColumnIndex(MODEL));
        color = cursor.getString(cursor.getColumnIndex(COLOR));
        notes = cursor.getString(cursor.getColumnIndex(NOTES));
        latitude = cursor.getDouble(cursor.getColumnIndex(LAT));
        longitude = cursor.getDouble(cursor.getColumnIndex(LONG));
        picturePath = cursor.getString(cursor.getColumnIndex(PICTURE_PATH));
        parked = cursor.getInt(cursor.getColumnIndex(PARKED)) == 1;
    }

    public int getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(int plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Date getSpottedDated() {
        return spottedDated;
    }

    public void setSpottedDated(Date spottedDated) {
        this.spottedDated = spottedDated;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public boolean isParked() {
        return parked;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }

    public String getParkedAsString(Context context) {
        if (parked) {
            return context.getString(R.string.parked);
        }
        return context.getString(R.string.not_parked);
    }

    public String getNumberAsString() {
        return String.format("%03d", getPlateNumber());
    }

    public String getMoreString(Context context) {
        return String.format(context.getString(R.string.more_format), getSpottedDated().toString(), getColor(), getBrand(), getModel(), getParkedAsString(context));
    }
}
