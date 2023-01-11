package com.example.finderx_mad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import java.util.Set;

public class TeacherViewStudentNamelistFragment extends Fragment {
    private View view;
    private ListView studentNameListView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_student_name = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference myRef,GroupRef;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    //private FirebaseAuth teacher;
    private FirebaseUser teacher;
    String TeacherID;

    public TeacherViewStudentNamelistFragment() {
        // Required empty public constructor
    }

    public static TeacherViewStudentNamelistFragment newInstance(String param1, String param2) {
        TeacherViewStudentNamelistFragment fragment = new TeacherViewStudentNamelistFragment();
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
        view =  inflater.inflate(R.layout.fragment_teacher_view_student_namelist, null, false);

        // Connect to Firebase
        teacher = FirebaseAuth.getInstance().getCurrentUser();
        TeacherID = teacher.getUid();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Teachers").child(TeacherID).child("C1").child("Occ").child("Occ A").child("Student List");

        InitializeField();
        RetrieveAndDisplayGroups();

        return view;
    }

    //Initialize Field
    private void InitializeField() {
        studentNameListView = (ListView) view.findViewById(R.id.student_namelist_view);
        arrayAdapter = new ArrayAdapter<String>(getContext().getApplicationContext(), android.R.layout.simple_list_item_1,list_of_student_name);
        studentNameListView.setAdapter(arrayAdapter);
    }

    //Print the Group List
    private void RetrieveAndDisplayGroups() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Set<String> set = new HashSet<>();
                Iterator iterator = snapshot.getChildren().iterator();
                while(iterator.hasNext()){
                    set.add(((DataSnapshot)iterator.next()).getKey());
                }
                list_of_student_name.clear();
                list_of_student_name.addAll(set);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}