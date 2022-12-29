package com.example.finderx_mad;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    //Firebase Authentication
    FirebaseAuth firebaseAuth;
    //Firebase Firestore
    FirebaseFirestore firebaseStore;
    //Edit Text
    EditText etUsernameEmail, etPassword;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Spinner spinner = view.findViewById(R.id.sUser);
        Button btnLogin;

        ArrayAdapter adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.userlist)

        );
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinner.setAdapter(adapter);

        //Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();
        //Firebase Firestore
        firebaseStore = FirebaseFirestore.getInstance();
        //Edit Text
        etUsernameEmail = view.findViewById(R.id.etUsernameEmail);
        etPassword = view.findViewById(R.id.etPassword);

        //Set and OnClickListener on the login button
        btnLogin = view.findViewById(R.id.btnLogin);
        View.OnClickListener OCLLogin = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // extract & Validate
                if (etUsernameEmail.getText().toString().isEmpty()) {
                    etUsernameEmail.setError("Username/Email is required!");
                    return;
                }
                if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("Password is required!");
                    return;
                }

                //Data Validation & login User
                firebaseAuth.signInWithEmailAndPassword(etUsernameEmail.getText().toString(), etPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //Login Successfully
                        checkIsStudent(authResult.getUser().getUid());
                        /*
                        String user = spinner.getSelectedItem().toString();
                          if (user.equals("Student")) {
                            //Navigate to Student Main Page Fragment
                            Navigation.findNavController(view).navigate(R.id.DestStudentMainPage);
                            Toast.makeText(getContext().getApplicationContext(), "Student Login Successfully", Toast.LENGTH_SHORT).show();
                       } else if (user.equals("Lecturer")) {
                            //Navigate to Teacher Course Page Fragment
                            //checkIfStudent(authResult.getUser().getUid());
                         Navigation.findNavController(view).navigate(R.id.DestTeacherCourse);
                        Toast.makeText(getContext().getApplicationContext(), "Teacher Login Successfully", Toast.LENGTH_SHORT).show();
                      }

                         */
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext().getApplicationContext(), "Login Unsuccessful! Please Try Again!", Toast.LENGTH_SHORT).show();
                    }
                });

                //  String user = spinner.getSelectedItem().toString();
                //   if(user.equals("Student")){
                //       //Navigate to Student Main Page Fragment
                //       Navigation.findNavController(view).navigate(R.id.DestStudentMainPage);
                //  }else if(user.equals("Lecturer")){
                //     //Navigate to Teacher Course Page Fragment
                //      Navigation.findNavController(view).navigate(R.id.DestTeacherCourse);
                //   }
            }

            public void checkIsStudent(String uid) {
                DocumentReference df = firebaseStore.collection("User").document(uid);
                String user = spinner.getSelectedItem().toString();
                //extract the data from the document
                df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Log.d("TAG","onSuccess: " + documentSnapshot.getData());
                        // identity the user access level
                        if(documentSnapshot.getString("isStudent")!= null && user.equals("Student")){
                                // user is Student
                                //Navigate to Student Main Page Fragment
                                Navigation.findNavController(view).navigate(R.id.DestStudentMainPage);
                                Toast.makeText(getContext().getApplicationContext(), "Student Login Successfully", Toast.LENGTH_SHORT).show();
                            }else if(documentSnapshot.getString("isStudent")!=null && user.equals("Lecturer")){
                                Toast.makeText(getContext().getApplicationContext(), "Choose Your Correct Role", Toast.LENGTH_SHORT).show();
                            }else if(documentSnapshot.getString("isTeacher") != null && user.equals("Student")){
                                Toast.makeText(getContext().getApplicationContext(), "Choose Your Correct Role", Toast.LENGTH_SHORT).show();
                            }else{
                                Navigation.findNavController(view).navigate(R.id.DestTeacherCourse);
                                Toast.makeText(getContext().getApplicationContext(), "Teacher Login Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                });
            }
        };
        btnLogin.setOnClickListener(OCLLogin);

    }

}
