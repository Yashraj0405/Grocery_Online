package com.example.groceryonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryonline.R;
import com.example.groceryonline.activities.AllCategoryItemActivity;
import com.example.groceryonline.models.BrandSportLight;

import java.util.List;

public class BrandSportLightAdapter extends RecyclerView.Adapter<BrandSportLightAdapter.ViewHolder> {
    Context context;
    List<BrandSportLight> brandSportLightList;

    public BrandSportLightAdapter(Context context, List<BrandSportLight> brandSportLightList) {
        this.context = context;
        this.brandSportLightList = brandSportLightList;
    }

    @NonNull
    @Override
    public BrandSportLightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_sportlight,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BrandSportLightAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(brandSportLightList.get(position).getBrandSportLight_img()).into(holder.brand_img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AllCategoryItemActivity.class);
                intent.putExtra("type",brandSportLightList.get(holder.getAdapterPosition()).getType());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return brandSportLightList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView brand_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            brand_img= itemView.findViewById(R.id.Brand_spotlight_image);
        }
    }
}
