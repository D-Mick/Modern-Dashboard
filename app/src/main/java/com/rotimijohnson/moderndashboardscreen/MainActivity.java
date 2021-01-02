package com.rotimijohnson.moderndashboardscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.rotimijohnson.moderndashboardscreen.fragment.homeFragment;
import com.rotimijohnson.moderndashboardscreen.fragment.listener.imageClicked;
import com.rotimijohnson.moderndashboardscreen.fragment.profileActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, imageClicked {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_profile:
                Intent intent = new Intent(MainActivity.this, profileActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_home);
            default:
                return true;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void click() {
        imageView = findViewById(R.id.toolbar);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }
}
