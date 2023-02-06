package com.example.groceryonline.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.groceryonline.R;
import com.example.groceryonline.adapters.BrandItemAdapter;
import com.example.groceryonline.models.BrandItemsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    ImageView product_Image, add_Item , remove_Item;
    TextView product_Name , product_qty , product_price , quantity ;
    int totalQuantity = 0;
    Button addToCart;

    BrandItemsModel brandItemsModel = null;


    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    BrandItemAdapter brandItemAdapter;
    List<BrandItemsModel> brandItemsModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        recyclerView = findViewById(R.id.SimilarProducts_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        firestore = FirebaseFirestore.getInstance();

        brandItemsModelList = new ArrayList<BrandItemsModel>();
        brandItemAdapter = new BrandItemAdapter(this,brandItemsModelList);
        recyclerView.setAdapter(brandItemAdapter);

        product_Image = findViewById(R.id.detailed_img);
        product_Name = findViewById(R.id.detailed_name);
        product_qty = findViewById(R.id.detailed_qty_text);
        product_price = findViewById(R.id.detailed_price);

        quantity = findViewById(R.id.Quantity);
        add_Item = findViewById(R.id.add_to_cart_plus_detailed);
        add_Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity<20){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });
        remove_Item = findViewById(R.id.add_to_cart_minus_detailed);
        remove_Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity>0){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });

        addToCart = findViewById(R.id.add_to_cart_button);


        final Object object = getIntent().getSerializableExtra("detail");
        if( object instanceof BrandItemsModel){
            brandItemsModel = (BrandItemsModel) object;
        }


        if(brandItemsModel != null ){
            Glide.with(getApplicationContext()).load(brandItemsModel.getImg_url()).into(product_Image);
            product_Name.setText(brandItemsModel.getName());
            product_qty.setText(brandItemsModel.getQty());
            product_price.setText(brandItemsModel.getPrice());


        }

        //Rice
        if (type != null && type.equalsIgnoreCase("rice")){
            firestore.collection("BrandItem").whereEqualTo("type","rice").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();

                    }
                }
            });
        }

        //Naagin
        if (type != null && type.equalsIgnoreCase("naggin")){
            firestore.collection("BrandItem").whereEqualTo("type","naggin").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Plush
        if (type != null && type.equalsIgnoreCase("plush")){
            firestore.collection("BrandItem").whereEqualTo("type","plush").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Plum
        if (type != null && type.equalsIgnoreCase("plum")){
            firestore.collection("BrandItem").whereEqualTo("type","plum").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Bingo
        if (type != null && type.equalsIgnoreCase("bingo")){
            firestore.collection("BrandItem").whereEqualTo("type","bingo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Orion
        if (type != null && type.equalsIgnoreCase("orion")){
            firestore.collection("BrandItem").whereEqualTo("type","orion").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }



        //Home Category & Explorer
        //Dry Fruits
        if (type != null && type.equalsIgnoreCase("Dry Fruits")){
            firestore.collection("BrandItem").whereEqualTo("type","Dry Fruits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Baby Care
        if (type != null && type.equalsIgnoreCase("Baby Care")){
            firestore.collection("BrandItem").whereEqualTo("type","Baby Care").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Munchies
        if (type != null && type.equalsIgnoreCase("Munchies")){
            firestore.collection("BrandItem").whereEqualTo("type","Munchies").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Personal Care
        if (type != null && type.equalsIgnoreCase("Personal Care")){
            firestore.collection("BrandItem").whereEqualTo("type","Personal Care").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Atta Rice & Dal
        if (type != null && type.equalsIgnoreCase("Atta")){
            firestore.collection("BrandItem").whereEqualTo("type","Atta").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }


        //Sweet Tooth
        if (type != null && type.equalsIgnoreCase("Sweet Tooth")){
            firestore.collection("BrandItem").whereEqualTo("type","Sweet Tooth").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Dairy&Breakfast
        if (type != null && type.equalsIgnoreCase("Dairy&Breakfast")){
            firestore.collection("BrandItem").whereEqualTo("type","Dairy&Breakfast").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Biscuits
        if (type != null && type.equalsIgnoreCase("Biscuits")){
            firestore.collection("BrandItem").whereEqualTo("type","Biscuits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Tea, Coffee & Health Drinks
        if (type != null && type.equalsIgnoreCase("Tea, Coffee & Health Drinks")){
            firestore.collection("BrandItem").whereEqualTo("type","Tea, Coffee & Health Drinks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Cleaning Essentials
        if (type != null && type.equalsIgnoreCase("Cleaning Essentials")){
            firestore.collection("BrandItem").whereEqualTo("type","Cleaning Essentials").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }


        //Instant & Frozen Food
        if (type != null && type.equalsIgnoreCase("Instant & Frozen Food")){
            firestore.collection("BrandItem").whereEqualTo("type","Instant & Frozen Food").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }


        //Cold Drink & Juices
        if (type != null && type.equalsIgnoreCase("Cold Drink & Juices")){
            firestore.collection("BrandItem").whereEqualTo("type","Cold Drink & Juices").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Sauces & Spreads
        if (type != null && type.equalsIgnoreCase("Sauces & Spreads")){
            firestore.collection("BrandItem").whereEqualTo("type","Sauces & Spreads").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Vegetables
        if (type != null && type.equalsIgnoreCase("vegetable")){
            firestore.collection("BrandItem").whereEqualTo("type","vegetable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Fruits
        if (type != null && type.equalsIgnoreCase("Fruits")){
            firestore.collection("BrandItem").whereEqualTo("type","Fruits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }


        //Snacks
        if (type != null && type.equalsIgnoreCase("Snacks")){
            firestore.collection("BrandItem").whereEqualTo("type","Snacks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        BrandItemsModel brandItemsModel = documentSnapshot.toObject(BrandItemsModel.class);
                        brandItemsModelList.add(brandItemsModel);
                        brandItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }





    }
}