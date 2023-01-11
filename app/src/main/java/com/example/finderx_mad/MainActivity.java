package com.example.finderx_mad;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finderx_mad.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // FirebaseUser
    private FirebaseUser currentUser;
    //Firebase Authentication
    FirebaseAuth firebaseAuth;
    //Firebase Firestore * to read the access level
    FirebaseFirestore firebaseStore;
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

        //Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();
        //Firebase Firestore
        firebaseStore = FirebaseFirestore.getInstance();
        //getUid
        currentUser = firebaseAuth.getCurrentUser();
        //When Login button is clicked
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = binding.sUser.getSelectedItem().toString();

                if (binding.etUsernameEmail.getText().toString().isEmpty()) {
                    binding.etUsernameEmail.setError("Username/Email is required!");
                    return;
                }
                if (binding.etPassword.getText().toString().isEmpty()) {
                    binding.etPassword.setError("Password is required!");
                    return;
                }

                //Data Validation & login User
                firebaseAuth.signInWithEmailAndPassword(binding.etUsernameEmail.getText().toString(), binding.etPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //Login Successfully
                        checkIsStudent(authResult.getUser().getUid());

                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(MainActivity.this, "Login Unsuccessful! Please Try Again!", Toast.LENGTH_SHORT).show();
                    }
                });

                Bundle bundle = new Bundle();
                bundle.putString("etUsernameEmail","From Activity");
                StudentJoinGroupFragment join = new StudentJoinGroupFragment();
                join.setArguments(bundle);
            }

            public void checkIsStudent(String uid) {
                DocumentReference df = firebaseStore.collection("User").document(uid);
                String user = binding.sUser.getSelectedItem().toString();
                //extract the data from the document
                df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Log.d("TAG","onSuccess: " + documentSnapshot.getData());
                        // identity the user access level
                        if(documentSnapshot.getString("isStudent")!= null && user.equals("Student")){
                            // user is Student
                            //Navigate to Student Main Page Fragment
                            Intent is = new Intent(MainActivity.this, StudentActivity.class);
                            startActivity(is);
                            Toast.makeText(MainActivity.this, "Student Login Successfully", Toast.LENGTH_SHORT).show();
                        }else if(documentSnapshot.getString("isStudent")!=null && user.equals("Lecturer")){
                            Toast.makeText(MainActivity.this, "Choose Your Correct Role", Toast.LENGTH_SHORT).show();
                        }else if(documentSnapshot.getString("isTeacher") != null && user.equals("Student")){
                            Toast.makeText(MainActivity.this, "Choose Your Correct Role", Toast.LENGTH_SHORT).show();
                        }else{
                            Intent it = new Intent(MainActivity.this, TeacherActivity.class);
                            startActivity(it);
                            Toast.makeText(MainActivity.this, "Teacher Login Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            });


    }

    @Override //To avoid memory leaks
    protected void onDestroy(){
        super.onDestroy();
        binding = null;
    }






}

