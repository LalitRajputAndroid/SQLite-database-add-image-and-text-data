package com.example.sqldatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.sqldatabase.Adapters.CategoryAdapter;
import com.example.sqldatabase.Adapters.ProductAdapter;
import com.example.sqldatabase.Database.CategoryDatabse;
import com.example.sqldatabase.Database.ProductDatabase;
import com.example.sqldatabase.Modals.CategoryModal;
import com.example.sqldatabase.Modals.ProductModal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView categoryRV, productRV;
    CategoryDatabse databse ;
    CategoryAdapter adapter ;

    ProductDatabase productDatabase;
    ProductAdapter P_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryRV = findViewById(R.id.category_recyclerview_id);
        productRV = findViewById(R.id.product_recyclerview_id);

        categoryRV.setLayoutManager(new GridLayoutManager(this,3));
        productRV.setLayoutManager(new GridLayoutManager(this,2));

        databse = new CategoryDatabse(MainActivity.this);
        productDatabase = new ProductDatabase(this);

        ArrayList<CategoryModal> list  = new ArrayList<>();
        ArrayList<ProductModal> plist = new ArrayList<>();

        list = databse.get_categoryData();
        plist = productDatabase.getProductData();

        adapter = new CategoryAdapter(list,this);
        categoryRV.setAdapter(adapter);

        P_adapter = new ProductAdapter(plist,this);
        productRV.setAdapter(P_adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menuitem, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addcategory_id:
                startActivity(new Intent(MainActivity.this, Add_category.class));
                break;

            case R.id.additem_id:
                startActivity(new Intent(MainActivity.this, Add_product.class));
                break;
            case R.id.cancelM_id:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}