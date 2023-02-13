package com.example.groceryonline.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.groceryonline.ui.home.order.OrderFragment;
import com.example.groceryonline.ui.home.addToCart.MyCartFragment;
import com.example.groceryonline.ui.home.category.CategoryFragment;
import com.example.groceryonline.R;
import com.example.groceryonline.ui.home.home.HomeFragment;
import com.example.groceryonline.ui.home.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Bottom Navigation
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    CategoryFragment categoryFragment = new CategoryFragment();
    MyCartFragment myCartFragment = new MyCartFragment();

    //Drawer Navigation
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    OrderFragment orderFragment = new OrderFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Back or Up Button
        assert  getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Bottom Navigation
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, myCartFragment).commit();
                        return true;
                    case R.id.Category:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, categoryFragment).commit();
                        return true;
                }
                    return false;
            }
        });



        //Drawer navigation
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                drawerLayout.closeDrawer(GravityCompat.START);
                switch(item.getItemId()){
                    case R.id.CART:
                    {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, myCartFragment).commit();
                        break;
                    }
                    case R.id.ORDER:
                    {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,orderFragment).commit();
                        break;
                    }
                    case R.id.PROFILE:
                    {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                        break;
                    }
                }

                return false;
            }
        });

    }
    //BackPress
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
        }else {
            super.onBackPressed();
        }

    }


}