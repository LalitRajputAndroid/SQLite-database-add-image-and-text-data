package com.example.sqldatabase.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.sqldatabase.Modals.ProductModal;

import java.util.ArrayList;

public class ProductDatabase extends SQLiteOpenHelper {
    private static final String DATABSE_NAME = "ProductDatabase";
    private static final int DATABASE_VERSION = 1;

    public ProductDatabase(@Nullable Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE Products(p_id integer primary key autoincrement ,ProductName text,ProductPrice text,ProductDiscount text,ProductCategory text,ProductDiscription text,ProductDis_Price text,ProductImage Blob)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists Products");
        onCreate(sqLiteDatabase);
    }

    public void AddProducts(ProductModal modal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("ProductName", modal.getProductName());
        values.put("ProductPrice", modal.getProductPrice());
        values.put("ProductDiscount", modal.getProductDiscount());
        values.put("ProductCategory", modal.getProductCategory());
        values.put("ProductDiscription", modal.getProductDiscription());
        values.put("ProductDis_Price", modal.getProductDis_Price());
        values.put("ProductImage", modal.getProductImage());

        db.insert("Products", null, values);
    }

    public ArrayList<ProductModal> getProductData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Products", null);

        ArrayList<ProductModal> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            ProductModal modal = new ProductModal();
            modal.setProductName(cursor.getString(1));
            modal.setProductPrice(cursor.getString(2));
            modal.setProductDiscount(cursor.getString(3));
            modal.setProductCategory(cursor.getString(4));
            modal.setProductDiscription(cursor.getString(5));
            modal.setProductDis_Price(cursor.getString(6));
            modal.setProductImage(cursor.getBlob(7));

            list.add(modal);
        }
        return list;
    }

    public Cursor getcategoryname(String c_name) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Products where ProductCategory = ?", new String[]{c_name});
        return cursor;
    }
}
