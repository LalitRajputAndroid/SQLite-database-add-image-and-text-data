package com.example.sqldatabase.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.example.sqldatabase.Modals.CategoryModal;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class CategoryDatabse extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyCategories";
    private static final int DATABSE_VERSION = 1;

    byte[] byteImage;
    Bitmap imgbitmap;

    public CategoryDatabse(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//        sqLiteDatabase.execSQL("CREATE TABLE Category(c_id INT PRIMARY KEY AUTOINCREMENT , c_name TEXT ,c_img Blod)");
        sqLiteDatabase.execSQL("create table Category(c_id integer primary key autoincrement,CategoryName text,CategoryImage Blob)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists Category");
        onCreate(sqLiteDatabase);
    }

    public void addcategories(CategoryModal modal) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CategoryName", modal.getCategoryName());
        values.put("CategoryImage", modal.getCategoryImage());

        db.insert("Category", null, values);
    }

    @SuppressLint("Range")
    public ArrayList<CategoryModal> get_categoryData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Category", null);

        ArrayList<CategoryModal> list = new ArrayList<>();


        while (cursor.moveToNext()) {

            CategoryModal modal = new CategoryModal();
            modal.setCategoryName(cursor.getString(1));


//            byteImage = cursor.getBlob(cursor.getColumnIndex("CategoryImage"));
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            imgbitmap = BitmapFactory.decodeByteArray(byteImage,0,byteImage.length,options);

            modal.setCategoryImage(cursor.getBlob(2));

            list.add(modal);
        }
        return list;
    }

    public Cursor getcategoryname() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select CategoryName from Category", null);

        return cursor;
    }
}
