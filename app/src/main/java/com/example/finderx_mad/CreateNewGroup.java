package com.example.finderx_mad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateNewGroup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateNewGroup extends Fragment {



    EditText ETGroupName,etTM1, etTM2, etTM3, etTM4, etTM5;


    FirebaseDatabase database;
    DatabaseReference myRef, userRef,chatRef;
    SGroupListMAD group;
    String currentUserID;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateNewGroup() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateNewGroup.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateNewGroup newInstance(String param1, String param2) {
        CreateNewGroup fragment = new CreateNewGroup();
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

        View view = inflater.inflate(R.layout.fragment_student_create_group, null, false);

        database = FirebaseDatabase.getInstance("https://finderx-6cd15-default-rtdb.asia-southeast1.firebasedatabase.app/");
        myRef = database.getReference("Student Group List MAD").child("Teams");
        mAuth = FirebaseAuth.getInstance();
        //emailRef = database.getReference("Users").child(mAuth.getUid()).child("Email");

        ETGroupName = (EditText) view.findViewById(R.id.ETGroupName);
        ETTM1 = (EditText) view.findViewById(R.id.etTM1);
        ETTM2 = (EditText) view.findViewById(R.id.etTM2);
        ETTM3 = (EditText) view.findViewById(R.id.etTM3);
        ETTM4 = (EditText) view.findViewById(R.id.etTM4);
        ETTM5 = (EditText) view.findViewById(R.id.etTM5);

        // Set ETTM1 as the gmail of the current user
        currentUserID = mAuth.getCurrentUser().getUid();
        userRef = database.getReference("Users").child(currentUserID).child("Email");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ETTM1.setText(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Check the validation of the email

//        DatabaseReference tentativeRef = database.getReference("Email");
//        tentativeRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Button BtnPublish = (Button) view.findViewById(R.id.BtnPublish);
//                BtnPublish.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (ETGroupName.getText().toString().isEmpty()) {
//                            ETGroupName.setError("Group Name is required!");
//                            return;
//                                    }
//                                if (!ETTM2.getText().toString().equals(snapshot.child("1").getValue().toString())) {
//                                    ETTM2.setError("This email does not exist");
//                                    return;
//                                } else if (ETTM2.getText().toString().isEmpty()) {
//                                    CreateNewGroup(group);
//                                }
//                                if (!ETTM3.getText().toString().equals(snapshot.child("2").getValue().toString())) {
//                                        ETTM3.setError("This email does not exist");
//                                        return;
//                                    } else if (ETTM3.getText().toString().isEmpty()) {
//                                        CreateNewGroup(group);
//                                    }
//                                    if (!ETTM4.getText().toString().equals(snapshot.child("3").getValue().toString())) {
//                                        ETTM4.setError("This email does not exist");
//                                        return;
//                                    } else if (ETTM4.getText().toString().isEmpty()) {
//                                        CreateNewGroup(group);
//                                    }
//                                    if (!ETTM5.getText().toString().equals(snapshot.child("4").getValue().toString())) {
//                                        ETTM5.setError("This email does not exist");
//                                        return;
//                                    }
//                                    //at least one member in group
//                                    else if (ETTM5.getText().toString().isEmpty()) {
//                                        CreateNewGroup(group);
//                                    }
//
//
//                                }
//                            });
//                        }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//        });

        Button BtnPublish = (Button) view.findViewById(R.id.BtnPublish);
        BtnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ETGroupName.getText().toString().isEmpty()) {
                    ETGroupName.setError("Group Name is required!");
                    return;
                }
                DatabaseReference tentativeRef = database.getReference("Email");
                tentativeRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(ETTM2.getText().toString().isEmpty()){
                            ETTM2.setError("This Field cannot be empty");
                        } else if (!ETTM2.getText().toString().equals(snapshot.child("1").getValue().toString())) {
                            ETTM2.setError("This email does not exist");
                            return;
                        }if(ETTM3.getText().toString().isEmpty()) {
                            CreateNewGroup(group);
                        }else if (!ETTM3.getText().toString().equals(snapshot.child("2").getValue().toString())) {
                            ETTM3.setError("This email does not exist");
                            return;
                        } if (ETTM4.getText().toString().isEmpty()) {
                            CreateNewGroup(group);
                        } else if (!ETTM4.getText().toString().equals(snapshot.child("3").getValue().toString())) {
                            ETTM4.setError("This email does not exist");
                            return;
                        } if (ETTM5.getText().toString().isEmpty()) {
                            CreateNewGroup(group);
                        } else if (!ETTM5.getText().toString().equals(snapshot.child("4").getValue().toString())) {
                            ETTM5.setError("This email does not exist");
                            return;
                        }

                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
            }
        });


        return view;
    }

    public void CreateNewGroup(SGroupListMAD group) {
        SGroupListDB newgroup = new SGroupListDB(
                ETGroupName.getText().toString(),
                ETTM1.getText().toString(), ETTM2.getText().toString(),
                ETTM3.getText().toString(), ETTM4.getText().toString(),
                ETTM5.getText().toString());

        myRef.child(newgroup.getTName()).setValue(newgroup).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext().getApplicationContext(), "Group is Created!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext().getApplicationContext(), "Unsuccessful to create group", Toast.LENGTH_SHORT).show();
                }
            }
        });
        chatRef = database.getReference();
        chatRef.child("Group").child(ETGroupName.getText().toString()).setValue("")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            //Toast.makeText(getContext().getApplicationContext(),groupName+ " is created", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

}