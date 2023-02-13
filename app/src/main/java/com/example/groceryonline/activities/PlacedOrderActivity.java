package com.example.groceryonline.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.groceryonline.R;
import com.example.groceryonline.models.AllCategoryItemsModel;
import com.example.groceryonline.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class PlacedOrderActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore db;
    Button backToHomeScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        backToHomeScreen = findViewById(R.id.HOME_button);
        backToHomeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlacedOrderActivity.this,MainActivity.class));
            }
        });

        List<MyCartModel> list = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");
        if(list != null && list.size() > 0){
            for( MyCartModel model : list){

                String saveCurrentDate;
                Calendar calForDate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd|MM|yyyy");
                saveCurrentDate=currentDate.format(calForDate.getTime());


                final HashMap<String,Object> cartMap = new HashMap<>();
                cartMap.put("ProductImage", model.getProductImage());
                cartMap.put("productName", model.getProductName());
                cartMap.put("productPrice",model.getProductPrice());
                cartMap.put("currentDate",saveCurrentDate);
                cartMap.put("productQuantityDetails",model.getProductQuantityDetails());
                cartMap.put("TotalQuantity",model.getTotalQuantity());
                cartMap.put("totalPrice",model.getTotalPrice());

                db.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(PlacedOrderActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }



    }
}