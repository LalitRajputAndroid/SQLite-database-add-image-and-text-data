package com.example.sqldatabase;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RemoteController;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqldatabase.Database.CategoryDatabse;
import com.example.sqldatabase.Modals.CategoryModal;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Add_category extends AppCompatActivity {

    CircleImageView imageView;
    TextInputEditText c_item;
    Button addbtn;
    CategoryDatabse db;
    Uri selectIMG;
    Bitmap bitmapIMG;

    private ByteArrayOutputStream stream;
    private byte[] bytesimg;
    private static final int IMG_REQ_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        imageView = findViewById(R.id.addimg_cid);
        c_item = findViewById(R.id.add_itemtext_id);
        addbtn = findViewById(R.id.category_addbtn_id);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMG_REQ_CODE);
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String item = c_item.getText().toString();

                if (item.isEmpty()){
                    Toast.makeText(Add_category.this, "fill the field", Toast.LENGTH_SHORT).show();
                } else if (bitmapIMG == null) {
                    Toast.makeText(Add_category.this, "Select Image", Toast.LENGTH_SHORT).show();
                } else {

                    stream = new ByteArrayOutputStream();
                    bitmapIMG.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    bytesimg = stream.toByteArray();

                    db = new CategoryDatabse(Add_category.this);
                    CategoryModal modal = new CategoryModal(item,bytesimg);

                    db.addcategories(modal);
                    startActivity(new Intent(Add_category.this,MainActivity.class));
                    c_item.setText("");
                    finish();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (requestCode == IMG_REQ_CODE) {

                if (data != null) {

                    if (data.getData() != null) {
                        selectIMG = data.getData();
                        imageView.setImageURI(selectIMG);
                        try {
                            bitmapIMG = MediaStore.Images.Media.getBitmap(getContentResolver(),selectIMG);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}