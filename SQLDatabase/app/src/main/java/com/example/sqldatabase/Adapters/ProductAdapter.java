package com.example.sqldatabase.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqldatabase.Modals.ProductModal;
import com.example.sqldatabase.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    ArrayList<ProductModal> list;
    Context context;

    public ProductAdapter(ArrayList<ProductModal> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_singlerow, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {


        byte[] bytes = list.get(position).getProductImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        holder.p_img.setImageBitmap(bitmap);
        holder.t1_pname.setText(list.get(position).getProductName());
        holder.t2_pprice.setText(list.get(position).getProductPrice());
        holder.t3_pdiscount.setText(list.get(position).getProductDiscount() + "%");
        holder.t4_pdiscription.setText(list.get(position).getProductDiscription());
        holder.t5_dis_price.setText("रू. " + list.get(position).getProductDis_Price());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        CircleImageView p_img;
        TextView t1_pname, t2_pprice, t3_pdiscount, t4_pdiscription, t5_dis_price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            p_img = itemView.findViewById(R.id.p_img_textviewID);
            t1_pname = itemView.findViewById(R.id.p_name_textviewID);
            t2_pprice = itemView.findViewById(R.id.p_price_textviewID);
            t3_pdiscount = itemView.findViewById(R.id.p_dicount_textviewID);
            t4_pdiscription = itemView.findViewById(R.id.p_discription_textviewID);
            t5_dis_price = itemView.findViewById(R.id.dis_price_textV_id);
            t2_pprice.setPaintFlags(t2_pprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
