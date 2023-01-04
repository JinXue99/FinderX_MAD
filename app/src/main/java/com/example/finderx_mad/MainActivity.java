package com.example.finderx_mad;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.example.finderx_mad.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import com.example.finderx_mad.R.array;
import com.example.finderx_mad.R.id;
import com.example.finderx_mad.R.layout;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Binding things
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ArrayList<String> users = new ArrayList<>();
        users.add("Student");
        users.add("Lecturer");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.custom_spinner,
                users

        );
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        binding.sUser.setAdapter(adapter);

        //When Login button is clicked
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = binding.sUser.getSelectedItem().toString();

                if (user.equals("Student")){
                    Intent is = new Intent(MainActivity.this, StudentActivity.class);
                    startActivity(is);
                }else if(user.equals("Lecturer")){
                    Intent it = new Intent(MainActivity.this, TeacherActivity.class);
                    startActivity(it);
                }


            }
        });
    }

    @Override //To avoid memory leaks
    protected void onDestroy(){
        super.onDestroy();
        binding = null;
    }






}

