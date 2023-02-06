package com.example.groceryonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryonline.R;
import com.example.groceryonline.activities.DetailedActivity;
import com.example.groceryonline.models.BrandItemsModel;
import com.example.groceryonline.models.GlideApp;

import java.util.List;

public class BrandItemAdapter extends RecyclerView.Adapter<BrandItemAdapter.ViewHolder> {
    Context context;
    List<BrandItemsModel> brandItemsModelList;

    public BrandItemAdapter(Context context, List<BrandItemsModel> brandItemsModelList) {
        this.context = context;
        this.brandItemsModelList = brandItemsModelList;
    }
    @NonNull
    @Override
    public BrandItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BrandItemAdapter.ViewHolder holder, int position) {

        GlideApp.with(context).load(brandItemsModelList.get(position).getImg_url()).into(holder.brand_item_img);
        //Glide.with(context).load(brandItemsModelList.get(position).getImg_url()).into(holder.brand_item_img);
        holder.brand_item_qty.setText(brandItemsModelList.get(position).getQty());
        holder.brand_item_name.setText(brandItemsModelList.get(position).getName());
        holder.brand_item_price.setText(brandItemsModelList.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail",brandItemsModelList.get(holder.getAdapterPosition()));
                //To show similar products
                intent.putExtra("type",brandItemsModelList.get(holder.getAdapterPosition()).getType());
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return brandItemsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView brand_item_img;
        TextView brand_item_name,brand_item_price,brand_item_qty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            brand_item_img = itemView.findViewById(R.id.Brand_item_img);
            brand_item_name = itemView.findViewById(R.id.Brand_item_Name);
            brand_item_price = itemView.findViewById(R.id.Brand_item_Price);
            brand_item_qty =itemView.findViewById(R.id.Brand_item_Qty);
        }
    }
}
