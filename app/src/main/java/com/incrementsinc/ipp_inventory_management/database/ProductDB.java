package com.incrementsinc.ipp_inventory_management.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ProductDB {
    private static final String PRO_TABLE = "product_table";
    private static final String PRO_ID = "_id";
    private static final String PRO_CLASS = "class";
    private static final String PRO_PRODUCT = "product";
    private static final String PRO_DESCRIPTION = "description";
    private static final String PRO_CROSS_REF_1 = "cross_ref_1";
    private static final String PRO_CROSS_REF_2 = "cross_ref_2";
    private static final String PRO_VENDOR = "vendor";

    private final SQLiteDatabase database;

    public ProductDB(Context context){
        SQLiteDatabaseHelper dbHelper = new SQLiteDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long createRecords(String id, String pClass, String product, String description, String crossRef1, String crossRef2, String vendor){
        ContentValues values = new ContentValues();
        values.put(PRO_ID, id);
        values.put(PRO_CLASS, pClass);
        values.put(PRO_PRODUCT, product);
        values.put(PRO_DESCRIPTION, description);
        values.put(PRO_CROSS_REF_1, crossRef1);
        values.put(PRO_CROSS_REF_2, crossRef2);
        values.put(PRO_VENDOR, vendor);
        return database.insert(PRO_TABLE, null, values);
    }

    public Cursor selectRecords() {
        String[] cols = new String[] {PRO_ID, PRO_CLASS, PRO_PRODUCT, PRO_DESCRIPTION, PRO_CROSS_REF_1, PRO_CROSS_REF_2, PRO_VENDOR};
        Cursor mCursor = database.query(true, PRO_TABLE,cols,null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }
}
