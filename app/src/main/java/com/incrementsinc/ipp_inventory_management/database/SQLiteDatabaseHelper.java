package com.incrementsinc.ipp_inventory_management.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "barcode_db";
    private static final int DATABASE_VERSION = 2;
    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table product_table( " +
            "_id integer primary key autoincrement," +
            "class text not null," +
            "product text not null," +
            "description text," +
            "cross_ref_1 text not null," +
            "cross_ref_2 text not null," +
            "vendor text," +
            "created_at text not null," +
            "updated_at text not null);";

    public SQLiteDatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    // Method is called during an upgrade of the database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w(SQLiteDatabaseHelper.class.getName(),
                "Upgrading database from version " + i + " to "
                        + i1 + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS product_table");
        onCreate(sqLiteDatabase);
    }
}
