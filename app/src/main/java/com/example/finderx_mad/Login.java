package com.example.finderx_mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText etUsernameEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsernameEmail = findViewById(R.id.etUsernameEmail);
        etPassword = findViewById(R.id.etPassword);

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

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
    //Automatically Login to the Student Main Page
    @Override
    public void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),StudentMainPageFragment.class));
            finish();
        }
    }
}