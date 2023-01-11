package com.example.finderx_mad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateNewGroup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateNewGroup extends Fragment {


    EditText ETGroupName,etTM1, etTM2, etTM3, etTM4, etTM5;
    FirebaseDatabase database;
    DatabaseReference myRef;
    SGroupListMAD group;


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

        ETGroupName = (EditText) view.findViewById(R.id.ETGroupName);
        etTM1 = (EditText) view.findViewById(R.id.etTM1);
        etTM2 = (EditText) view.findViewById(R.id.etTM2);
        etTM3 = (EditText) view.findViewById(R.id.etTM3);
        etTM4 = (EditText) view.findViewById(R.id.etTM4);
        etTM5 = (EditText) view.findViewById(R.id.etTM5);

        Button BtnPublish = (Button) view.findViewById(R.id.BtnPublish);
        BtnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ETGroupName.getText().toString().isEmpty()) {
                    ETGroupName.setError("Group Name is required!");
                    return;
                }
                if (etTM1.getText().toString().isEmpty()) {
                    etTM1.setError("At least one member is required!");
                    return;
                }
                CreateNewGroup(group);
            }
        });


        return view;
    }

    public void CreateNewGroup(SGroupListMAD group) {
        SGroupListDB newgroup = new SGroupListDB(
                ETGroupName.getText().toString(),
                etTM1.getText().toString(), etTM2.getText().toString(),
                etTM3.getText().toString(), etTM4.getText().toString(),
                etTM5.getText().toString());

        myRef.child(newgroup.getTName()).setValue(newgroup).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getContext().getApplicationContext(), "Group is Created!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext().getApplicationContext(), "Unsuccessful to create group", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}