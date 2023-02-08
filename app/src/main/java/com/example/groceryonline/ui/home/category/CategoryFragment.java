package com.example.groceryonline.ui.home.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.groceryonline.R;
import com.example.groceryonline.adapters.CatExploreAdapter;
import com.example.groceryonline.models.CatExplore;
import com.example.groceryonline.models.HomeCategory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {

    RecyclerView catExplore;
    FirebaseFirestore db;

    //Category Explore
    CatExploreAdapter catExploreAdapter;
    List<CatExplore> catExploreList;

    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        db = FirebaseFirestore.getInstance();
        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        catExplore = root.findViewById(R.id.Category_Rec);
        catExplore.setVisibility(View.GONE);

        //Category explore
        //catExplore.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
       GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
       catExplore.setLayoutManager(layoutManager);
        catExploreList = new ArrayList<>();
        catExploreAdapter = new CatExploreAdapter(getActivity(),catExploreList);
        catExplore.setAdapter(catExploreAdapter);

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                CatExplore catExplore1 = document.toObject(CatExplore.class);
                                catExploreList.add(catExplore1);
                                catExploreAdapter.notifyDataSetChanged();


                                progressBar.setVisibility(View.GONE);
                                catExplore.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Toast.makeText(getActivity(), "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });




        return root;
    }

}