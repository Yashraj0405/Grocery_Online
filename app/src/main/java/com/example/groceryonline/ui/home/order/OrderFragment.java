package com.example.groceryonline.ui.home.order;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.groceryonline.R;
import com.example.groceryonline.adapters.OrderAdapter;
import com.example.groceryonline.models.HomeCategory;
import com.example.groceryonline.models.MyCartModel;
import com.example.groceryonline.models.OrderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    RecyclerView order_rec;
    FirebaseFirestore db;
    FirebaseAuth auth;
    OrderAdapter orderAdapter;
    List<MyCartModel> orderModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_order, container, false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        order_rec = root.findViewById(R.id.Order_Rec);

       // GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        order_rec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        orderModelList = new ArrayList<>();
        orderAdapter = new OrderAdapter(getActivity(),orderModelList);
        order_rec.setAdapter(orderAdapter);

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("MyOrder").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        MyCartModel orderModel = documentSnapshot.toObject(MyCartModel.class);
                        orderModelList.add(orderModel);
                        orderAdapter.notifyDataSetChanged();
                    }
                }

            }
        });




        return root;
    }
}