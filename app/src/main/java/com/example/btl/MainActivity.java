package com.example.btl;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(
                this, R.id.nav_host_fragment);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_daily_meal,
                R.id.nav_favorite,
                R.id.nav_my_cart
        ).setDrawerLayout(drawerLayout).build();

        NavigationUI.setupActionBarWithNavController(
                this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(navView, navController);

        // đóng DRAWER
        navView.setNavigationItemSelectedListener(item -> {

            boolean handled = NavigationUI.onNavDestinationSelected(item, navController);

            if (handled) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }

            return handled;
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(
                this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
