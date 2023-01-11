package com.example.finderx_mad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//TeacherOcc2001Fragment
public class TeacherOccFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    //private FirebaseAuth teacher;
    private FirebaseUser teacher;
    String TeacherID;
    RecyclerView recview;
    OccListTeacherAdapter adapter;
    ArrayList<OccListTeacher> list;
    FirebaseDatabase database;
    DatabaseReference myRef, CourseRef;
    OccListTeacher viewOccTeacher;

    public TeacherOccFragment() {
        // Required empty public constructor
    }

    public static TeacherOccFragment newInstance(String param1, String param2) {
        TeacherOccFragment fragment = new TeacherOccFragment();
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
        View view = inflater.inflate(R.layout.fragment_teacher_occ, null, false);

        // Connect to Firebase
        teacher = FirebaseAuth.getInstance().getCurrentUser();
        TeacherID = teacher.getUid();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Teachers").child(TeacherID).child("C1").child("Occ");

        //Firebase
        recview = (RecyclerView) view.findViewById(R.id.RVOccListTeacher);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);
        recview.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        adapter = new OccListTeacherAdapter(getContext().getApplicationContext(), list);
        recview.setAdapter(adapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    viewOccTeacher = dataSnapshot.getValue(OccListTeacher.class);
                    list.add(viewOccTeacher);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}