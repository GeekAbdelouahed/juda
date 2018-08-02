package com.academyatinfo.juda.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataTableLevel extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "table_level.db";
    private final String TABLE_NAME = "table_level";
    private static final int DATABASE_VERSION = 1;

    public DataTableLevel(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean getData(int id, int column_index) {
        String name_column = getNameColumn(column_index);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT _id ," + name_column + " from " + TABLE_NAME + " WHERE _id = " + id, null);
        c.moveToFirst();
        boolean b = c.getInt(c.getColumnIndex(name_column)) > 0;
        c.close();
        return b;
    }

    public void updateData(int id_table, int index_column) {
        String name_column = getNameColumn(index_column);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(name_column, true);
        db.update(TABLE_NAME, contentValues, id_table + " = _id", null);
    }

    private String getNameColumn(int index) {
        String name_column = null;
        switch (index) {
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
        }
        return name_column;
    }

}
