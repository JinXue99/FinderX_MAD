package com.example.finderx_mad;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<String> users = new ArrayList<>();
//        users.add("Student");
//        users.add("Lecturer");

//        R.layout.custom_spinner
//        android.R.layout.simple_spinner_dropdown_item,
//        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);



        Spinner spinner = findViewById(R.id.sUser);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.userlist)

        );
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinner.setAdapter(adapter);
    }
}