package com.example.foodwaste;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodwaste.Cart.Shopping;
import com.example.foodwaste.Community.Community;
import com.example.foodwaste.Inventory.Inventory;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    private int selectedTabPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.framelayout);



        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout , new Inventory()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                selectedTabPosition = bottomNavigationView.getMenu().findItem(item.getItemId()).getOrder();

                int id = item.getItemId();
                Fragment selectedfragment = null;

                if (id==R.id.inventory){
                    replace(new Inventory());
                } else if (id==R.id.list) {
                    replace(new Shopping());
                } else if (id==R.id.c_engage) {
                    replace(new Community());
                }
                return true;
            }
        });

        if (savedInstanceState != null) {
            selectedTabPosition = savedInstanceState.getInt("selectedTabPosition");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the selected tab position before rotation
        outState.putInt("selectedTabPosition", selectedTabPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the selected tab position after rotation
        selectedTabPosition = savedInstanceState.getInt("selectedTabPosition");

        // Update the bottom navigation view to reflect the selected tab
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(selectedTabPosition).getItemId());
    }

    public void replace(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout , fragment);
        fragmentTransaction.commit();
    }
}