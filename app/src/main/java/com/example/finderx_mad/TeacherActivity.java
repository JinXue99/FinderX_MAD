package com.example.finderx_mad;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finderx_mad.databinding.ActivityTeacherBinding;

public class TeacherActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityTeacherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTeacherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarTeacher.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.DestTeacherCourse, R.id.DestTeacherProfile, R.id.DestLogin)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_teacher);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_teacher);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        NavController navController = Navigation.findNavController(this,
//                R.id.nav_host_fragment_content_teacher);
//
//        // By calling onNavDestinationSelected(), you always get the right behavior
//        return NavigationUI.onNavDestinationSelected(item, navController)
//                || super.onOptionsItemSelected(item);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Bundle bundle =new Bundle();
//        switch (item.getItemId()) {
//            case R.id.DestTeacherProfile:
//            {
//                // Manually build the NavOptions that manually do
//                // what NavigationUI.onNavDestinationSelected does for you
//                NavOptions navOptions = new NavOptions.Builder()
//                        .setPopUpTo(R.id.DestTeacherCourse, false, true)
//                        .setRestoreState(true)
//                        .build();
//
//                NavController navController = Navigation.findNavController(this,
//                        R.id.nav_host_fragment_content_teacher);
//
//                navController.navigate(String.valueOf(R.id.DestTeacherCourse), navOptions);
//                return true;
//            }
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}

