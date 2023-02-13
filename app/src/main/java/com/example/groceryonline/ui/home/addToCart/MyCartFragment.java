package com.example.groceryonline.ui.home.addToCart;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groceryonline.R;
import com.example.groceryonline.activities.PlacedOrderActivity;
import com.example.groceryonline.adapters.MyCartAdapter;
import com.example.groceryonline.models.HomeCategory;
import com.example.groceryonline.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    ProgressBar progressBar;

    RecyclerView cart_Rec;
    FirebaseFirestore db;
    FirebaseAuth auth;

    List<MyCartModel> cartList;
    MyCartAdapter cartAdapter;

    TextView TotalAmount;
    Button orderNOw;

    ConstraintLayout constraintLayout1 , constraintLayout2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_cart, container, false);

        constraintLayout1 = root.findViewById(R.id.Constrained_1);
        constraintLayout1.setVisibility(View.VISIBLE);
        constraintLayout2 = root.findViewById(R.id.Constrained_2);
        constraintLayout2.setVisibility(View.GONE);

        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        cart_Rec = root.findViewById(R.id.cart_recyclerView);
        cart_Rec.setVisibility(View.GONE);
        cart_Rec.setLayoutManager(new LinearLayoutManager(getActivity()));

        TotalAmount = root.findViewById(R.id.Total_Amount);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        cartList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(getActivity(), cartList);
        cart_Rec.setAdapter(cartAdapter);

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        String documentId = documentSnapshot.getId(); //1
                        MyCartModel myCartModel = documentSnapshot.toObject(MyCartModel.class); //2
                        myCartModel.setDocumentId(documentId); //1
                        cartList.add(myCartModel); //2
                        cartAdapter.notifyDataSetChanged(); //2

                        constraintLayout1.setVisibility(View.GONE);
                        constraintLayout2.setVisibility(View.VISIBLE);

                        progressBar.setVisibility(View.GONE);
                        cart_Rec.setVisibility(View.VISIBLE);

                    }

                    calculateTotalAmount(cartList);


                }

            }
        });

        orderNOw = root.findViewById(R.id.Order_button);
        orderNOw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PlacedOrderActivity.class);
                intent.putExtra("itemList", (Serializable) cartList);
                startActivity(intent);

                db.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot documentSnapshot:task.getResult()){
                                documentSnapshot.getReference().delete();

                                constraintLayout2.setVisibility(View.GONE);
                                constraintLayout1.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });

            }
        });


        return root;
    }

    private void calculateTotalAmount(List<MyCartModel> cartList) {
        double totalAmount = 0.0;
        for (MyCartModel myCartModel : cartList) {
            totalAmount += myCartModel.getTotalPrice();
            TotalAmount.setText("Total Bill : ₹" + totalAmount);
        }
    }
}