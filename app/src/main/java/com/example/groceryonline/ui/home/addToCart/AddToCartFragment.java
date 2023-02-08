package com.example.groceryonline.ui.home.addToCart;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.groceryonline.R;
import com.example.groceryonline.adapters.MyCartAdapter;
import com.example.groceryonline.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddToCartFragment extends Fragment {

    ProgressBar progressBar;

    RecyclerView cart_Rec;
    FirebaseFirestore db;
    FirebaseAuth auth;

    List<MyCartModel> cartList;
    MyCartAdapter cartAdapter;

    TextView TotalAmount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_to_cart, container, false);

        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        cart_Rec = root.findViewById(R.id.cart_recyclerView);
        cart_Rec.setVisibility(View.GONE);
        cart_Rec.setLayoutManager(new LinearLayoutManager(getActivity()));

        TotalAmount = root.findViewById(R.id.Total_Amount);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,new IntentFilter("MyTotalAmount"));

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        cartList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(getActivity(),cartList);
        cart_Rec.setAdapter(cartAdapter);

        db.collection("AddToCart").document(auth.getCurrentUser().getUid()).collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        MyCartModel myCartModel = documentSnapshot.toObject(MyCartModel.class);
                        cartList.add(myCartModel);
                        cartAdapter.notifyDataSetChanged();


                        progressBar.setVisibility(View.GONE);
                        cart_Rec.setVisibility(View.VISIBLE);

                    }
                }
            }
        });




        return root;
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill = intent.getIntExtra("totalAmount",0);
            TotalAmount.setText("Total Bill : ₹"+ totalBill);
        }
    };
}