package com.example.finderx_mad;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.TBMainAct);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.DLMain);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavHostFragment host = (NavHostFragment)
                getSupportFragmentManager().findFragmentById(R.id.NHFMain);
        NavController navController = host.getNavController();
        AppBarConfiguration appBarConfiguration = new
                AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this,
                navController, appBarConfiguration);

        setupNavMenu(navController);
    }

    private void setupNavMenu(NavController navController) {

        NavigationView sideNav = findViewById(R.id.sideNav);

        NavigationUI.setupWithNavController(sideNav, navController, false);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            Navigation.findNavController(this,
                    R.id.NHFMain).navigate(item.getItemId());
            return true;
        } catch (Exception ex) {
            return super.onOptionsItemSelected(item);
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);
        MenuItem item = menu.findItem(id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });


        private void processsearch (String s){

        }


    }*/
}

