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
import com.example.groceryonline.models.AllCategoryItemsModel;
import com.example.groceryonline.models.GlideApp;

import java.util.List;

public class AllCategoryItemAdapter extends RecyclerView.Adapter<AllCategoryItemAdapter.ViewHolder> {
    Context context;
    List<AllCategoryItemsModel> allCategoryItemsModelList;

    public AllCategoryItemAdapter(Context context, List<AllCategoryItemsModel> allCategoryItemsModelList) {
        this.context = context;
        this.allCategoryItemsModelList = allCategoryItemsModelList;
    }
    @NonNull
    @Override
    public AllCategoryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryItemAdapter.ViewHolder holder, int position) {

        GlideApp.with(context).load(allCategoryItemsModelList.get(position).getImg_url()).into(holder.brand_item_img);
        //Glide.with(context).load(allCategoryItemsModelList.get(position).getImg_url()).into(holder.brand_item_img);
        holder.brand_item_qty.setText(allCategoryItemsModelList.get(position).getQty());
        holder.brand_item_name.setText(allCategoryItemsModelList.get(position).getName());
        holder.brand_item_price.setText(allCategoryItemsModelList.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail", allCategoryItemsModelList.get(holder.getAdapterPosition()));
                //To show similar products
                intent.putExtra("type", allCategoryItemsModelList.get(holder.getAdapterPosition()).getType());
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return allCategoryItemsModelList.size();
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
