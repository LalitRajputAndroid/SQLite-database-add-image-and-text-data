package com.example.sqldatabase;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqldatabase.Adapters.ProductAdapter;
import com.example.sqldatabase.Database.ProductDatabase;
import com.example.sqldatabase.Modals.ProductModal;

import java.util.ArrayList;

public class ProductlistActivity extends AppCompatActivity {

    RecyclerView productlistrecyclerview;
    ProductDatabase productDatabase;
    ProductAdapter adapter;
    ArrayList<ProductModal> productModalArrayList = new ArrayList<>();
    String catname = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);

        productlistrecyclerview = findViewById(R.id.product_list_recyclerview_id);
        productDatabase = new ProductDatabase(this);

        catname = getIntent().getStringExtra("Catname");
        productlistrecyclerview.setLayoutManager(new GridLayoutManager(this, 2));

        Cursor cursor = productDatabase.getcategoryname(catname);
        while (cursor.moveToNext()) {
            ProductModal modal = new ProductModal();
            modal.setProductName(cursor.getString(1));
            modal.setProductPrice(cursor.getString(2));
            modal.setProductDiscount(cursor.getString(3));
            modal.setProductCategory(cursor.getString(4));
            modal.setProductDiscription(cursor.getString(5));
            modal.setProductDis_Price(cursor.getString(6));
            modal.setProductImage(cursor.getBlob(7));

            productModalArrayList.add(modal);
        }
        adapter = new ProductAdapter(productModalArrayList, this);
        productlistrecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}