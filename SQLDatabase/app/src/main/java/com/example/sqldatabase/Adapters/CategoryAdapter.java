package com.example.sqldatabase.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqldatabase.Modals.CategoryModal;
import com.example.sqldatabase.ProductlistActivity;
import com.example.sqldatabase.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    ArrayList<CategoryModal> categorylist;
    Context context;

    public CategoryAdapter(ArrayList<CategoryModal> categorylist, Context context) {
        this.categorylist = categorylist;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_singlerow,parent,false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        holder.category_text.setText(categorylist.get(position).getCategoryName());


        byte[] img = categorylist.get(position).getCategoryImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
        holder.category_img.setImageBitmap(bitmap);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, ProductlistActivity.class);
                i.putExtra("Catname",holder.category_text.getText().toString());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categorylist.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout c_singlerow;
        CircleImageView category_img;
        TextView category_text;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            c_singlerow = itemView.findViewById(R.id.category_singlecard_id);
            category_img = itemView.findViewById(R.id.category_img_id);
            category_text = itemView.findViewById(R.id.category_text_id);
        }
    }
}
