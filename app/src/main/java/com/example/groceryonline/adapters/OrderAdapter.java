package com.example.groceryonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryonline.R;
import com.example.groceryonline.models.MyCartModel;
import com.example.groceryonline.models.OrderModel;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> orderList;

    public OrderAdapter(Context context, List<MyCartModel> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
       Glide.with(context).load(orderList.get(position).getProductImage()).into(holder.ord_pro_img);
       holder.ord_pro_name.setText(orderList.get(position).getProductName());
       holder.ord_pro_price.setText(orderList.get(position).getProductPrice());
       holder.ord_pro_qty.setText(orderList.get(position).getProductQuantityDetails());
       holder.ord_price.setText(String.valueOf(orderList.get(position).getTotalPrice()));
       holder.ord_qty.setText(orderList.get(position).getTotalQuantity());
       holder.ord_date.setText(orderList.get(position).getCurrentDate());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ord_pro_img;
        TextView ord_pro_name,ord_pro_qty,ord_pro_price,ord_price,ord_qty,ord_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ord_pro_img = itemView.findViewById(R.id.order_product_image);
            ord_pro_name = itemView.findViewById(R.id.order_product_name);
            ord_pro_price = itemView.findViewById(R.id.order_product_price);
            ord_pro_qty = itemView.findViewById(R.id.order_product_qty);
            ord_qty = itemView.findViewById(R.id.order_quantity);
            ord_price = itemView.findViewById(R.id.order_product_TotalPrice);
            ord_date =itemView.findViewById(R.id.Date);

        }
    }
}
