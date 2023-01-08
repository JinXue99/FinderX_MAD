package com.example.finderx_mad;

import static com.example.finderx_mad.R.id.addRecycleView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentAnnouncementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  StudentAnnouncementFragment extends Fragment {

    ArrayList<AnnouncementUser> list;
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference myRef, TaskRef;
    myAnnouncementAdapter myAdapter;
    AnnouncementUser studentViewTask;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentAnnouncementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentAnnouncementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentAnnouncementFragment newInstance(String param1, String param2) {
        StudentAnnouncementFragment fragment = new StudentAnnouncementFragment();
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
        View view = inflater.inflate(R.layout.fragment_student_announcement,container,false);

        recyclerView = view.findViewById(R.id.addRecycleView);

        database = FirebaseDatabase.getInstance("https://finderx-6cd15-default-rtdb.asia-southeast1.firebasedatabase.app");
        myRef = database.getReference("Teacher");
        TaskRef = myRef.child("Course Code").child("C1").child("Occ").child("Occ 1").child("Task Assigned");


//        database = FirebaseDatabase.getInstance().getReference("Task Assigned");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        list = new ArrayList<>();
        myAdapter = new myAnnouncementAdapter(this.getContext(), list);
        recyclerView.setAdapter(myAdapter);

        TaskRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    studentViewTask = dataSnapshot.getValue(AnnouncementUser.class);
                    list.add(studentViewTask);
                }
                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        // Inflate the layout for this fragment
        return view;

    }
}