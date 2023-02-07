package com.example.groceryonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryonline.R;
import com.example.groceryonline.models.MyCartModel;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> cartModelList;
    int TOTALPRICE = 0;

    public MyCartAdapter(Context context, List<MyCartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(cartModelList.get(position).getProductImage()).into(holder.product_img);
        holder.product_name.setText(cartModelList.get(position).getProductName());
        holder.product_price.setText(cartModelList.get(position).getProductPrice());
        holder.Product_qty.setText(cartModelList.get(position).getProductQuantityDetails());
        holder.total_qty.setText(cartModelList.get(position).getTotalQuantity());
        holder.total_price.setText(String.valueOf(cartModelList.get(position).getTotalPrice()));

        //Passing total amount to cart Fragment
        TOTALPRICE = TOTALPRICE + cartModelList.get(position).getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",TOTALPRICE);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView product_img;
        TextView product_name,product_price,Product_qty,total_qty,total_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_img = itemView.findViewById(R.id.cart_product_image);
            product_name = itemView.findViewById(R.id.cart_product_name);
            product_price = itemView.findViewById(R.id.cart_product_price);
            Product_qty = itemView.findViewById(R.id.cart_product_qty);
            total_qty = itemView.findViewById(R.id.cart_quantity);
            total_price = itemView.findViewById(R.id.cart_product_TotalPrice);
        }
    }
}
