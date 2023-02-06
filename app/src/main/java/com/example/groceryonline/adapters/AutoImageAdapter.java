package com.example.groceryonline.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.groceryonline.R;
import com.example.groceryonline.models.AutoImage;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class AutoImageAdapter extends RecyclerView.Adapter<AutoImageAdapter.ViewHolder> {

    private List<AutoImage> imageList;
    private ViewPager2 viewPager2;

    public AutoImageAdapter(List<AutoImage> imageList, ViewPager2 viewPager2) {
        this.imageList = imageList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public AutoImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.auto_image_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AutoImageAdapter.ViewHolder holder, int position) {
        holder.roundedImageView.setImageResource(imageList.get(position).getAuto_img());
        if (position == imageList.size() - 2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView roundedImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.auto_image);

        }

    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            imageList.addAll(imageList);
            notifyDataSetChanged();
        }
    };
}
