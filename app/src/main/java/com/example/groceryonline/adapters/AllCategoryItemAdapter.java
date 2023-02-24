package com.example.groceryonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryonline.R;
import com.example.groceryonline.activities.DetailedActivity;
import com.example.groceryonline.models.AllCategoryItemsModel;
import com.example.groceryonline.models.GlideApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AllCategoryItemAdapter extends RecyclerView.Adapter<AllCategoryItemAdapter.ViewHolder> {
    Context context;
    List<AllCategoryItemsModel> allCategoryItemsModelList;


    FirebaseAuth auth;
    FirebaseFirestore firestore;
    String totalQty = "1";
    int TotalQuantity=0;
    int totalPrice =0;


    public AllCategoryItemAdapter(Context context, List<AllCategoryItemsModel> allCategoryItemsModelList) {
        this.context = context;
        this.allCategoryItemsModelList = allCategoryItemsModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
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

        holder.count.setVisibility(View.GONE);

        //Plus Button
        holder.plusButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(TotalQuantity<20){
                    TotalQuantity++;
                    holder.count.setText(String.valueOf(TotalQuantity));
                    if (TotalQuantity>=1) {
                        holder.count.setVisibility(View.VISIBLE);
                        holder.addButton.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        //Minus Button
        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TotalQuantity>0){
                    TotalQuantity--;
                    holder.count.setText(String.valueOf(TotalQuantity));
                    if(TotalQuantity==0){
                        holder.count.setVisibility(View.GONE);
                        holder.addButton.setVisibility(View.GONE);
                    }
                }
            }
        });

        ///////Add to cart Button
        holder.addButton.setVisibility(View.GONE);
        //Add To cart
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String saveCurrentDate,saveCurrentTime;
                    Calendar calForDate = Calendar.getInstance();

                    SimpleDateFormat currentDate = new SimpleDateFormat("dd|MM|yyyy");
                    saveCurrentDate=currentDate.format(calForDate.getTime());

                    SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                    saveCurrentTime = currentTime.format(calForDate.getTime());

                    int price = Integer.parseInt(allCategoryItemsModelList.get(holder.getAdapterPosition()).getPrice());
                    totalPrice = TotalQuantity * price;

                    final HashMap<String,Object> cartMap = new HashMap<>();
                    cartMap.put("ProductImage",allCategoryItemsModelList.get(holder.getAdapterPosition()).getImg_url());
                    cartMap.put("productName",allCategoryItemsModelList.get(holder.getAdapterPosition()).getName());
                    cartMap.put("productPrice",allCategoryItemsModelList.get(holder.getAdapterPosition()).getPrice());
                    cartMap.put("productQuantityDetails",allCategoryItemsModelList.get(holder.getAdapterPosition()).getQty());
                    cartMap.put("TotalQuantity",(String.valueOf(TotalQuantity)));
                    cartMap.put("totalPrice",totalPrice);
                    cartMap.put("currentDate",saveCurrentDate);
                    cartMap.put("currentTime",saveCurrentTime);



                    firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(context, "Added To Cart", Toast.LENGTH_SHORT).show();
                        }
                    });


            }
        });




    }



    @Override
    public int getItemCount() {
        return allCategoryItemsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView count;
        Button plusButton, minusButton;


        Button addButton;
        ImageView brand_item_img;
        TextView brand_item_name,brand_item_price,brand_item_qty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            brand_item_img = itemView.findViewById(R.id.Brand_item_img);
            brand_item_name = itemView.findViewById(R.id.Brand_item_Name);
            brand_item_price = itemView.findViewById(R.id.Brand_item_Price);
            brand_item_qty =itemView.findViewById(R.id.Brand_item_Qty);
            addButton = itemView.findViewById(R.id.add_button);


            count = itemView.findViewById(R.id.Count);
            plusButton = itemView.findViewById(R.id.Plus_Button);
            minusButton = itemView.findViewById(R.id.Minus_Button);

        }
    }
}
