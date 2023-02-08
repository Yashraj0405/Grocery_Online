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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.groceryonline.R;
import com.example.groceryonline.adapters.AllCategoryItemAdapter;
import com.example.groceryonline.models.AllCategoryItemsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    ImageView product_Image, add_Item , remove_Item;
    TextView product_Name , product_qty , product_price , quantity ;
    int totalQuantity = 0;
    int totalPrice =0;
    Button addToCart;

    ProgressBar progressBar;

    AllCategoryItemsModel allCategoryItemsModel = null;

    FirebaseAuth auth;
    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    AllCategoryItemAdapter allCategoryItemAdapter;
    List<AllCategoryItemsModel> allCategoryItemsModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.SimilarProducts_rec);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        allCategoryItemsModelList = new ArrayList<AllCategoryItemsModel>();
        allCategoryItemAdapter = new AllCategoryItemAdapter(this, allCategoryItemsModelList);
        recyclerView.setAdapter(allCategoryItemAdapter);

        product_Image = findViewById(R.id.detailed_img);
        product_Name = findViewById(R.id.detailed_name);
        product_qty = findViewById(R.id.detailed_qty_text);
        product_price = findViewById(R.id.detailed_price);

        quantity = findViewById(R.id.Quantity);

        //Add quantity button
        add_Item = findViewById(R.id.add_to_cart_plus_detailed);
        add_Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity<20){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    int price = Integer.parseInt(allCategoryItemsModel.getPrice());
                    totalPrice = totalQuantity * price;
                }
            }
        });
        //Remove quantity button
        remove_Item = findViewById(R.id.add_to_cart_minus_detailed);
        remove_Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity>0){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    int price = Integer.parseInt(allCategoryItemsModel.getPrice());
                    totalPrice = totalQuantity * price;

                }
            }
        });

        //Add to cart Button
        addToCart = findViewById(R.id.add_to_cart_button);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedToCart();
            }
        });


        final Object object = getIntent().getSerializableExtra("detail");
        if( object instanceof AllCategoryItemsModel){
            allCategoryItemsModel = (AllCategoryItemsModel) object;
        }
        if(allCategoryItemsModel != null ){
            Glide.with(getApplicationContext()).load(allCategoryItemsModel.getImg_url()).into(product_Image);
            product_Name.setText(allCategoryItemsModel.getName());
            product_qty.setText(allCategoryItemsModel.getQty());
            product_price.setText(allCategoryItemsModel.getPrice());

            int price = Integer.parseInt(allCategoryItemsModel.getPrice());
            totalPrice = totalQuantity * price;
        }



        ///Similar Products
        //Rice
        if (type != null && type.equalsIgnoreCase("rice")){
            firestore.collection("BrandItem").whereEqualTo("type","rice").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
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
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }





    }

    private void addedToCart() {
        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd|MM|yyyy");
        saveCurrentDate=currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("ProductImage", allCategoryItemsModel.getImg_url());
        cartMap.put("productName", allCategoryItemsModel.getName());
        cartMap.put("productPrice",product_price.getText().toString());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("productQuantityDetails",product_qty.getText().toString());
        cartMap.put("TotalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid()).collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}