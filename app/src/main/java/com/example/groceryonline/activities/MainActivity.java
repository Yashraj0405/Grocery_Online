package com.example.groceryonline.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.groceryonline.ui.home.addToCart.AddToCartFragment;
import com.example.groceryonline.ui.home.category.CategoryFragment;
import com.example.groceryonline.R;
import com.example.groceryonline.ui.home.category.Category_2_Fragment;
import com.example.groceryonline.ui.home.home.HomeFragment;
import com.example.groceryonline.ui.home.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    CategoryFragment categoryFragment = new CategoryFragment();
    AddToCartFragment addToCartFragment = new AddToCartFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case R.id.Cart:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,addToCartFragment).commit();
                        return true;
                    case R.id.Category:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, categoryFragment).commit();
                        return true;
                }
                    return false;
            }
        });

    }
}