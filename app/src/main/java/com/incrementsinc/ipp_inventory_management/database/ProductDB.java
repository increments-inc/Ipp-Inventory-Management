package com.incrementsinc.ipp_inventory_management.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.incrementsinc.ipp_inventory_management.model.Product;

import java.util.ArrayList;

public class ProductDB {
    private static final String PRO_TABLE = "product_table";
    private static final String PRO_ID = "_id";
    private static final String PRO_CLASS = "class";
    private static final String PRO_PRODUCT = "product";
    private static final String PRO_DESCRIPTION = "description";
    private static final String PRO_CROSS_REF_1 = "cross_ref_1";
    private static final String PRO_CROSS_REF_2 = "cross_ref_2";
    private static final String PRO_VENDOR = "vendor";
    private static final String PRO_CREATED_AT = "created_at";
    private static final String PRO_UPDATED_AT = "updated_at";

    private final SQLiteDatabase mDatabase;
    private final String mToday;

    public ProductDB(Context context){
        SQLiteDatabaseHelper dbHelper = new SQLiteDatabaseHelper(context);
        mDatabase = dbHelper.getWritableDatabase();
        mToday = String.valueOf(System.currentTimeMillis());
    }

    public long createRecords(String pClass, String product, String description, String crossRef1, String crossRef2, String vendor){
        ContentValues values = new ContentValues();
        values.put(PRO_CLASS, pClass);
        values.put(PRO_PRODUCT, product);
        values.put(PRO_DESCRIPTION, description);
        values.put(PRO_CROSS_REF_1, crossRef1);
        values.put(PRO_CROSS_REF_2, crossRef2);
        values.put(PRO_VENDOR, vendor);
        values.put(PRO_CREATED_AT, mToday);
        values.put(PRO_UPDATED_AT, mToday);
        return mDatabase.insert(PRO_TABLE, null, values);
    }

    @SuppressLint("Range")
    public ArrayList<Product> selectRecords() {
        ArrayList<Product> products = new ArrayList<>();
        String[] cols = new String[]{PRO_ID, PRO_CLASS, PRO_PRODUCT, PRO_DESCRIPTION, PRO_CROSS_REF_1, PRO_CROSS_REF_2, PRO_VENDOR, PRO_CREATED_AT, PRO_UPDATED_AT};
        try (Cursor mCursor = mDatabase.query(
                true,
                PRO_TABLE,
                cols,
                null,
                null,
                null,
                null,
                null,
                null)) {
            if (mCursor.moveToFirst()) {
                do {
                    products.add(new Product(
                            mCursor.getInt(mCursor.getColumnIndex(cols[0])),
                            mCursor.getString(mCursor.getColumnIndex(cols[1])),
                            mCursor.getString(mCursor.getColumnIndex(cols[2])),
                            mCursor.getString(mCursor.getColumnIndex(cols[3])),
                            mCursor.getString(mCursor.getColumnIndex(cols[4])),
                            mCursor.getString(mCursor.getColumnIndex(cols[5])),
                            mCursor.getString(mCursor.getColumnIndex(cols[6])),
                            mCursor.getString(mCursor.getColumnIndex(cols[7])),
                            mCursor.getString(mCursor.getColumnIndex(cols[8]))
                    ));
                } while (mCursor.moveToNext());

            }
            mDatabase.close();
        }
        return products;
    }
}
