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

import com.bumptech.glide.Glide;
import com.example.groceryonline.R;
import com.example.groceryonline.activities.BrandSpotLightItemActivity;
import com.example.groceryonline.models.HomeCategory;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
     private Context context;
     private List<HomeCategory> categoryList;

    public HomeAdapter(Context context, List<HomeCategory> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override //onCreateViewHolder: This method inflates card layout items for Recycler View.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cat_items,parent,false));
    }

    @Override //This method sets the data to specific views of card items. It also handles methods related to clicks on items of Recycler view.
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(categoryList.get(position).getImg_url()).into(holder.homeImg);
        holder.homeCatName.setText(categoryList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BrandSpotLightItemActivity.class);
                intent.putExtra("type",categoryList.get(holder.getAdapterPosition()).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override  //This method returns the length of the RecyclerView.
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView homeImg;
        TextView homeCatName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeImg = itemView.findViewById(R.id.home_cat_img);
            homeCatName = itemView.findViewById(R.id.home_cat_name);
        }
    }
}
