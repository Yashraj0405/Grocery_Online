package com.example.groceryonline.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.groceryonline.R;
import com.example.groceryonline.adapters.AllCategoryItemAdapter;
import com.example.groceryonline.models.AllCategoryItemsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllCategoryItemActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    AllCategoryItemAdapter allCategoryItemAdapter;
    List<AllCategoryItemsModel> allCategoryItemsModelList;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        setContentView(R.layout.all_products_item);


        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);


        recyclerView = findViewById(R.id.brandspotlight_Rec);
        recyclerView.setVisibility(View.GONE);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        firestore = FirebaseFirestore.getInstance();

        allCategoryItemsModelList = new ArrayList<AllCategoryItemsModel>();
        allCategoryItemAdapter = new AllCategoryItemAdapter(this, allCategoryItemsModelList);
        recyclerView.setAdapter(allCategoryItemAdapter);

       // EventChangedListener();



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



























//        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(layoutManager);
//        allCategoryItemsModelList = new ArrayList<>();
//        allCategoryItemAdapter = new AllCategoryItemAdapter(this,allCategoryItemsModelList);
//        recyclerView.setAdapter(allCategoryItemAdapter);
//
//
//        db.collection("HomeCategory")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//
//                                AllCategoryItemsModel allCategoryItemsModel = document.toObject(AllCategoryItemsModel.class);
//                                allCategoryItemsModelList.add(allCategoryItemsModel);
//                                allCategoryItemAdapter.notifyDataSetChanged();
//                            }
//                        } else {
//                            Toast.makeText(AllCategoryItemActivity.this, "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

    }

//    private void EventChangedListener() {
//
//        firestore.collection("BrandItem")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                        if( error != null){
//                            Log.e("Firebase error ",error.getMessage());
//                            return;
//                        }
//                        for (DocumentChange dc : value.getDocumentChanges()){
//                            if(dc.getType() == DocumentChange.Type.ADDED){
//                                allCategoryItemsModelList.add(dc.getDocument().toObject(AllCategoryItemsModel.class));
//                            }
//                            allCategoryItemAdapter.notifyDataSetChanged();
//                        }
//                    }
//                });
//
//    }
}