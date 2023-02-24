package com.example.groceryonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryonline.R;
import com.example.groceryonline.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> cartModelList;

    FirebaseAuth auth;
    FirebaseFirestore db;

    int TotalQuantity = 0;
    //int TotalPrice;

    public MyCartAdapter(Context context, List<MyCartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
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

        TotalQuantity = Integer.parseInt(cartModelList.get(position).getTotalQuantity());

        //Plus Button to modify Quantity
        holder.plus_button.setOnClickListener(new View.OnClickListener() {
            MyCartModel cartModel = cartModelList.get(holder.getAdapterPosition());
            //int  TotalQ = Integer.parseInt(cartModelList.get(holder.getAdapterPosition()).getTotalQuantity());
            //int Price = (holder.total_price.setText(cartModelList.get(holder.getAdapterPosition()).getTotalPrice()));
            //int Price = Integer.parseInt(cartModelList.get(holder.getAdapterPosition()).getProductPrice());
            @Override
            public void onClick(View view) {

                if (TotalQuantity<20){
                    TotalQuantity++;
                    holder.total_qty.setText(String.valueOf(TotalQuantity));
                    //TotalQuantity = TotalQ;
                    //TotalPrice =  Price* TotalQ;
                    //holder.total_price.setText(String.valueOf(TotalPrice));


                    // update the corresponding Firestore document with the new quantity
                    MyCartModel cartModel = cartModelList.get(holder.getAdapterPosition());
                    db.collection("CurrentUser")
                            .document(auth.getCurrentUser().getUid())
                            .collection("AddToCart")
                            .document(cartModel.getDocumentId())
                            .update("TotalQuantity", String.valueOf(TotalQuantity))
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //Toast.makeText(context, "ITEM UPDATED", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });







        //Minus Button to modify Quantity
        holder.minus_button.setOnClickListener(new View.OnClickListener() {
            //int TotalQ = Integer.parseInt(cartModelList.get(holder.getAdapterPosition()).getTotalQuantity());
            @Override
            public void onClick(View view) {
                if(TotalQuantity>0){
                    TotalQuantity--;
                    holder.total_qty.setText(String.valueOf(TotalQuantity));



                    // update the corresponding Firestore document with the new quantity
                    MyCartModel cartModel = cartModelList.get(holder.getAdapterPosition());
                    db.collection("CurrentUser")
                            .document(auth.getCurrentUser().getUid())
                            .collection("AddToCart")
                            .document(cartModel.getDocumentId())
                            .update("TotalQuantity", String.valueOf(TotalQuantity))
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //Toast.makeText(context, "ITEM UPDATED", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                }
            }
        });

        //Delete items from cart
        holder.delete_Item.setOnClickListener(new View.OnClickListener() {
            MyCartModel cartModel = cartModelList.get(holder.getAdapterPosition());
            @Override
            public void onClick(View view) {
                db.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("AddToCart")
                        .document(cartModelList.get(holder.getAdapterPosition()).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    cartModelList.remove(cartModelList.get(holder.getAdapterPosition()));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "ITEM REMOVED FROM CART", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button plus_button,minus_button;
        ImageView product_img ,delete_Item;
        TextView product_name,product_price,Product_qty,total_qty,total_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_img = itemView.findViewById(R.id.cart_product_image);
            product_name = itemView.findViewById(R.id.cart_product_name);
            product_price = itemView.findViewById(R.id.cart_product_price);
            Product_qty = itemView.findViewById(R.id.cart_product_qty);
            total_qty = itemView.findViewById(R.id.cart_quantity);
            total_price = itemView.findViewById(R.id.cart_product_TotalPrice);

            delete_Item = itemView.findViewById(R.id.Delet);
            plus_button = itemView.findViewById(R.id.Plus);
            minus_button = itemView.findViewById(R.id.Minus);
        }
    }
}
