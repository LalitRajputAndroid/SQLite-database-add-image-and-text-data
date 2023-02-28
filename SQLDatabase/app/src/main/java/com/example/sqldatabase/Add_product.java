package com.example.sqldatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sqldatabase.Database.CategoryDatabse;
import com.example.sqldatabase.Database.ProductDatabase;
import com.example.sqldatabase.Modals.ProductModal;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Add_product extends AppCompatActivity {

    TextInputEditText p_name, p_price, p_dis, p_dicription;
    CircleImageView p_img;
    Spinner spinner;
    Button addbtn;

    Uri productimgUri;
    private Bitmap bitmapIMG;
    private ByteArrayOutputStream stream;
    private byte[] bytesIMg;
    ArrayAdapter arrayAdapter;
    ArrayList list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        p_img = findViewById(R.id.product_imgid);
        p_name = findViewById(R.id.product_nameid);
        p_price = findViewById(R.id.product_priceid);
        p_dis = findViewById(R.id.product_discountid);
        p_dicription = findViewById(R.id.product_discriptionid);
        spinner = findViewById(R.id.category_spinnerid);
        addbtn = findViewById(R.id.add_productbtn_id);

        CategoryDatabse categoryDatabse = new CategoryDatabse(this);

        Cursor cursor = categoryDatabse.getcategoryname();

        while (cursor.moveToNext()) {

            list.add(cursor.getString(0));
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        spinner.setAdapter(arrayAdapter);

        ProductDatabase db = new ProductDatabase(this);

        p_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String P_Name = p_name.getText().toString();
                String P_Price = p_price.getText().toString();
                String P_Dic = p_dis.getText().toString();
                String P_Discrip = p_dicription.getText().toString();
                String Categories = spinner.getSelectedItem().toString();

                if (P_Name.isEmpty() || P_Price.isEmpty() || P_Dic.isEmpty() || P_Discrip.isEmpty()) {
                    Toast.makeText(Add_product.this, "Fill the filed", Toast.LENGTH_SHORT).show();
                } else if (bitmapIMG == null) {
                    Toast.makeText(Add_product.this, "Select Image", Toast.LENGTH_SHORT).show();
                } else {

                    long price = Long.parseLong(P_Price);
                    long discount = Long.parseLong(P_Dic);
                    long total = 100 - discount;

                    long dis_price = (total * price) / 100;

                    String DP = String.valueOf(dis_price);

                    stream = new ByteArrayOutputStream();
                    bitmapIMG.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    bytesIMg = stream.toByteArray();

                    ProductModal modal = new ProductModal(P_Name, P_Price, P_Dic, Categories, P_Discrip, DP, bytesIMg);
                    db.AddProducts(modal);
                    startActivity(new Intent(Add_product.this, MainActivity.class));
                    p_name.setText("");
                    p_price.setText("");
                    p_dis.setText("");
                    p_dicription.setText("");
                    finish();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK || resultCode == 1) {
            if (data != null) {
                if (data.getData() != null) {
                    productimgUri = data.getData();
                    p_img.setImageURI(productimgUri);
                    try {
                        bitmapIMG = MediaStore.Images.Media.getBitmap(getContentResolver(), productimgUri);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}