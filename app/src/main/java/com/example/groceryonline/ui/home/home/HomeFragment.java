package com.example.groceryonline.ui.home.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.groceryonline.R;
import com.example.groceryonline.activities.BrandSpotLightItemActivity;
import com.example.groceryonline.adapters.AutoImageAdapter;
import com.example.groceryonline.adapters.BrandItemAdapter;
import com.example.groceryonline.adapters.BrandSportLightAdapter;
import com.example.groceryonline.adapters.HomeAdapter;
import com.example.groceryonline.adapters.PopularAdapters;
import com.example.groceryonline.models.AutoImage;
import com.example.groceryonline.models.BrandItemsModel;
import com.example.groceryonline.models.BrandSportLight;
import com.example.groceryonline.models.HomeCategory;
import com.example.groceryonline.models.PopularModel;
import com.example.groceryonline.ui.home.profile.ProfileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ScrollView scrollView;
    ProgressBar progressBar;


    RecyclerView popularRec,homeCatRec,brandSpotRec,commonRec;
    FirebaseFirestore db;

    //Popular Items
    PopularAdapters popularAdapters;
    // Storing values of popular model in variable called popularModelList
    List<PopularModel> popularModelList;


    //Home Category
    List<HomeCategory> categoryList;
    HomeAdapter homeAdapter;

    //Brand SportLight
    List<BrandSportLight> brandSportLightList;
    BrandSportLightAdapter brandSportLightAdapter;


    //Auto Image
    private ViewPager2 viewPager2;
    private List<AutoImage> imageList;
    private AutoImageAdapter autoImageAdapter;
    private Handler sliderHandler = new Handler();


    //Profile fragment
    ImageView profileIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        profileIcon = root.findViewById(R.id.profile_circle);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Profile", Toast.LENGTH_SHORT).show();

//                Fragment fragment = new ProfileFragment();
//                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.home_container, fragment).commit();
////                fragmentTransaction.addToBackStack(null);
////                fragmentTransaction.commit();
            }
        });

        scrollView = root.findViewById(R.id.ScrollView);
        progressBar = root.findViewById(R.id.Progressbar);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        db = FirebaseFirestore.getInstance();
        // connecting recyclerview of fragment with id pop_rec
        popularRec = root.findViewById(R.id.pop_rec);
        // connecting recyclerview of fragment with id explore_rec
        homeCatRec = root.findViewById(R.id.explore_rec);
        // connecting recyclerview of fragment with id brandSpot_rec
        brandSpotRec = root.findViewById(R.id.brand_img);






        // connecting viewPager2 with the id viewPager2 in fragment home
        viewPager2 = root.findViewById(R.id.viewPager2);
        imageList = new ArrayList<>();
        // Adding all the images to the list above
        imageList.add(new AutoImage(R.drawable.auto1));
        imageList.add(new AutoImage(R.drawable.auto2));
        imageList.add(new AutoImage(R.drawable.auto3));
        imageList.add(new AutoImage(R.drawable.auto4));
        imageList.add(new AutoImage(R.drawable.auto5));
        imageList.add(new AutoImage(R.drawable.auto6));
        imageList.add(new AutoImage(R.drawable.auto7));
        autoImageAdapter = new AutoImageAdapter(imageList, viewPager2);
        viewPager2.setAdapter(autoImageAdapter);
        ///viewPager2.setOffscreenPageLimit(3);
        /// viewPager2.setClipChildren(false);
        ///viewPager2.setClipToPadding(false);

        //viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,3000);
            }
        });



        //Popular items                                                              //This can be Horizontal or Vertical
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList = new ArrayList<>();
        popularAdapters = new PopularAdapters(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapters);

        //Read data from firebase Cloud.
                                  //Same Name used in Firebase
        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapters.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



        //Home Category                                                             //This can be Horizontal or Vertical
        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        //GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        //homeCatRec.setLayoutManager(layoutManager);
        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(),categoryList);
        homeCatRec.setAdapter(homeAdapter);

        //Read data from firebase Cloud.
        //Same Name used in Firebase
        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                categoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Brand SpotLight                                                            //This can be Horizontal or Vertical
        brandSpotRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        brandSportLightList = new ArrayList<>();
        brandSportLightAdapter = new BrandSportLightAdapter(getActivity(),brandSportLightList);
        brandSpotRec.setAdapter(brandSportLightAdapter);

        //Read data from firebase Cloud.
        //Same Name used in Firebase
        db.collection("BrandSpotLight")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                BrandSportLight brandSportLight = document.toObject(BrandSportLight.class);
                                brandSportLightList.add(brandSportLight);
                                brandSportLightAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });






        return root;
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable,3000);
    }
}