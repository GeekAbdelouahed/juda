package com.academyatinfo.juda.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataTables extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "table_mult.db";
    private final String TABLE_NAME = "table_mult";
    private static final int DATABASE_VERSION = 1;

    public DataTables(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean getData(int id, int index) {
        String name_column = getNameColumn(index);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT _id ," + name_column + " from " + TABLE_NAME + " WHERE _id = " + id, null);
        c.moveToFirst();
        boolean b = c.getInt(c.getColumnIndex(name_column)) > 0;
        c.close();
        return b;
    }

    public void updateData(int id, int index) {
        String name_column = getNameColumn(index);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(name_column, true);
        db.update(TABLE_NAME, contentValues, id + " = _id", null);
    }

    private String getNameColumn(int index) {
        String name_column = null;
        switch (index) {
            case 0:
                name_column = "_zero";
                break;
            case 1:
                name_column = "_one";
                break;
            case 2:
                name_column = "_two";
                break;
            case 3:
                name_column = "_three";
                break;
            case 4:
                name_column = "_four";
                break;
            case 5:
                name_column = "_five";
                break;
            case 6:
                name_column = "_six";
                break;
            case 7:
                name_column = "_seven";
                break;
            case 8:
                name_column = "_eight";
                break;
            case 9:
                name_column = "_nine";
                break;
        }
        return name_column;
    }
}