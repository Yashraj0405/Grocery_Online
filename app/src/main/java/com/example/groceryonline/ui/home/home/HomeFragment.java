package com.example.groceryonline.ui.home.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.groceryonline.R;
import com.example.groceryonline.adapters.AutoImageAdapter;
import com.example.groceryonline.adapters.AllCategoryItemAdapter;
import com.example.groceryonline.adapters.BrandSportLightAdapter;
import com.example.groceryonline.adapters.HomeAdapter;
import com.example.groceryonline.adapters.PopularAdapters;
import com.example.groceryonline.models.AllCategoryItemsModel;
import com.example.groceryonline.models.AutoImage;
import com.example.groceryonline.models.BrandSportLight;
import com.example.groceryonline.models.HomeCategory;
import com.example.groceryonline.models.PopularModel;
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


    RecyclerView popularRec,homeCatRec,brandSpotRec,vegetableRec,fruits_rec;
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

    //Search Bar & View
    EditText search_box;
    private List<AllCategoryItemsModel> allCategoryItemsList;
    private RecyclerView SearchRecyclerView;
    private AllCategoryItemAdapter AdapterAllCategoryItem;


    //Using BrandItem : Model&Adapter for vegetableRec and many more
    List<AllCategoryItemsModel> allCategoryItemsModelList;
    AllCategoryItemAdapter allCategoryItemAdapter;

    List<AllCategoryItemsModel> list;
    AllCategoryItemAdapter adapter;

    //Auto Image
    private ViewPager2 viewPager2;
    private List<AutoImage> imageList;
    private AutoImageAdapter autoImageAdapter;
    private Handler sliderHandler = new Handler();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);


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
        vegetableRec = root.findViewById(R.id.vegetable_rec);
        fruits_rec = root.findViewById(R.id.Fruits_rec);




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

        //Vegetable rec
        vegetableRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        allCategoryItemsModelList = new ArrayList<>();
        allCategoryItemAdapter = new AllCategoryItemAdapter(getActivity(), allCategoryItemsModelList);
        vegetableRec.setAdapter(allCategoryItemAdapter);



            db.collection("BrandItem").whereEqualTo("type","vegetable" ).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                        allCategoryItemsModelList.add(allCategoryItemsModel);
                        allCategoryItemAdapter.notifyDataSetChanged();
                    }
                }
            });


        //Fruit rec
        fruits_rec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        list = new ArrayList<>();
        adapter = new AllCategoryItemAdapter(getActivity(),list);
        fruits_rec.setAdapter(adapter);


        db.collection("BrandItem").whereEqualTo("type","Fruits" ).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                    AllCategoryItemsModel allCategoryItemsModel = documentSnapshot.toObject(AllCategoryItemsModel.class);
                    list.add(allCategoryItemsModel);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        /////Search Bar & View

        SearchRecyclerView = root.findViewById(R.id.search_rec);
        search_box = root.findViewById(R.id.search_box);
        allCategoryItemsList = new ArrayList<>();
        AdapterAllCategoryItem = new AllCategoryItemAdapter(getContext(),allCategoryItemsList);
        SearchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
       // SearchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SearchRecyclerView.setAdapter(AdapterAllCategoryItem);
        SearchRecyclerView.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().isEmpty()){
                    allCategoryItemsList.clear();
                    AdapterAllCategoryItem.notifyDataSetChanged();

                }else {
                    searchProduct(s.toString());
                }

            }
        });




        return root;
    }

    private void searchProduct(String type) {

        if(!type.isEmpty()){
            db.collection("BrandItem").whereEqualTo("type",type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful() && task.getResult() != null){

                                allCategoryItemsList.clear();
                                AdapterAllCategoryItem.notifyDataSetChanged();
                                for( DocumentSnapshot doc : task.getResult().getDocuments()){
                                    AllCategoryItemsModel model = doc.toObject(AllCategoryItemsModel.class);
                                    allCategoryItemsList.add(model);
                                    AdapterAllCategoryItem.notifyDataSetChanged();
                                }

                            }

                        }
                    });
        }

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

    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable,3000);
        sliderHandler.removeCallbacks(sliderRunnable);
    }
}