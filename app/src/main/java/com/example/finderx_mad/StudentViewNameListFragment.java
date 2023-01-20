package com.example.finderx_mad;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentViewNameListFragment extends Fragment {
    private View view;
    private ListView studentNameListView;

    private List<StudentViewNameList> studentList;
    private StudentViewNameListAdapter myAdapter;

    FirebaseDatabase database;
    DatabaseReference myRef,GroupRef;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    //private FirebaseAuth teacher;
    private FirebaseUser teacher;
    String TeacherID;

    public StudentViewNameListFragment() {
        // Required empty public constructor
    }

    public static StudentViewNameListFragment newInstance(String param1, String param2) {
        StudentViewNameListFragment fragment = new StudentViewNameListFragment();
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
        view =  inflater.inflate(R.layout.fragment_student_view_name_list, null, false);

        // Connect to Firebase
        //teacher = FirebaseAuth.getInstance().getCurrentUser();
        //TeacherID = teacher.getUid();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        InitializeField();
        RetrieveAndDisplayGroups();

        return view;
    }

    //Initialize Field
    private void InitializeField() {
        studentList = new ArrayList<>();
        studentNameListView = (ListView) view.findViewById(R.id.namelist_view);

    }

    //Print the Group List
    private void RetrieveAndDisplayGroups() {
        myAdapter = new StudentViewNameListAdapter(getActivity(),R.layout.view_name_list_row,studentList);
        studentNameListView.setAdapter(myAdapter);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){

                        String name = dataSnapshot1.child("Name").getValue().toString();
                        String gmail = dataSnapshot1.child("Email").getValue().toString();
                        String majoring = dataSnapshot1.child("Majoring").getValue().toString();
                        String description = dataSnapshot1.child("Description").getValue().toString();
                        String image = dataSnapshot1.child("image").getValue().toString();
                        StudentViewNameList list =new StudentViewNameList(name,gmail,majoring,description,image);
                        studentList.add(list);

                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}