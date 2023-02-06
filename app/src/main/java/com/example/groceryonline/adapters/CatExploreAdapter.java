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
import com.example.groceryonline.models.CatExplore;

import java.util.List;

public class CatExploreAdapter extends RecyclerView.Adapter<CatExploreAdapter.ViewHolder> {

    private Context context;
    private List<CatExplore> catExploreList;

    public CatExploreAdapter(Context context, List<CatExplore> catExploreList) {
        this.context = context;
        this.catExploreList = catExploreList;
    }

    @NonNull
    @Override
    public CatExploreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_explore_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CatExploreAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(catExploreList.get(position).getImg_url()).into(holder.catExp_Img);
        holder.catExp_Name.setText(catExploreList.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BrandSpotLightItemActivity.class);
                intent.putExtra("type",catExploreList.get(holder.getAdapterPosition()).getType());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return catExploreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView catExp_Img;
        TextView catExp_Name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catExp_Img = itemView.findViewById(R.id.Cat_Explore_img);
            catExp_Name =itemView.findViewById(R.id.Cat_Explore_name);
        }
    }
}
