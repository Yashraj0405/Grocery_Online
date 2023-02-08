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
import com.example.groceryonline.activities.AllCategoryItemActivity;
import com.example.groceryonline.models.PopularModel;

import java.util.List;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder> {

    private Context context;
    // Data from Popular model is passed in list , which is stored in variable called popularModelList
    private List<PopularModel> popularModelList;

    //Constructor
    public PopularAdapters(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public PopularAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false));
    }

    // Function to bind the view in Card view(popular items) with data in popular model class
    @Override
    public void onBindViewHolder(@NonNull PopularAdapters.ViewHolder holder, int position) {

        Glide.with(context).load(popularModelList.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(popularModelList.get(position).getName());
        holder.rating.setText(popularModelList.get(position).getRating());
        holder.description.setText(popularModelList.get(position).getDescription());
        holder.discount.setText(popularModelList.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AllCategoryItemActivity.class);
                intent.putExtra("type",popularModelList.get(holder.getAdapterPosition()).getType());
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name,description,rating,discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popImg = itemView.findViewById(R.id.pop_img);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_des);
            rating=itemView.findViewById(R.id.pop_rec);
            discount=itemView.findViewById(R.id.pop_disc);


        }
    }
}
