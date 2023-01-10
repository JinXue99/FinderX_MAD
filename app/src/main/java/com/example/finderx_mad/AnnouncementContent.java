package com.example.finderx_mad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnnouncementContent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnnouncementContent extends Fragment {

    ArrayList<AnnouncementUser> list;
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference myRef, TaskDetailsRef;
//    myAnnouncementcontentAdapter myAdapter;
//    AnnouncementUser studentViewTask;
    TextView tvContentCourseCode,tvContentTaskTitle, tvContentDeadline, tvContentDetails;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AnnouncementContent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnnouncementContent.
     */
    // TODO: Rename and change types and number of parameters
    public static AnnouncementContent newInstance(String param1, String param2) {
        AnnouncementContent fragment = new AnnouncementContent();
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
        View view = inflater.inflate(R.layout.fragment_announcement_content,container,false);


        database = FirebaseDatabase.getInstance("https://finderx-6cd15-default-rtdb.asia-southeast1.firebasedatabase.app");
        myRef = database.getReference("Teacher");
        TaskDetailsRef = myRef.child("Course Code").child("C1").child("Occ").child("Occ 1").child("Task Assigned");

        tvContentCourseCode = (TextView) view.findViewById(R.id.tvContentCourseCode);
        tvContentTaskTitle = (TextView) view.findViewById(R.id.tvContentTaskTitle);
        tvContentDeadline = (TextView) view.findViewById(R.id.tvContentDeadline);
        tvContentDetails = (TextView) view.findViewById(R.id.tvContentDetails);

        list = new ArrayList<>();
//        myAdapter = new myAnnouncementcontentAdapter(this.getContext(), list);

        TaskDetailsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        tvContentCourseCode.setText(dataSnapshot.child("courseCode").getValue().toString());
                        tvContentTaskTitle.setText(dataSnapshot.child("taskTitle").getValue().toString());
                        tvContentDeadline.setText(dataSnapshot.child("taskDeadline").getValue().toString());
                        tvContentDetails.setText(dataSnapshot.child("taskDetails").getValue().toString());

                }

//                if (snapshot != null){
//                    tvContentCourseCode.setText(snapshot.child("courseCode").getValue().toString());
//                    tvContentTaskTitle.setText(snapshot.child("taskTitle").getValue().toString());
//                    tvContentDeadline.setText(snapshot.child("taskDeadline").getValue().toString());
//                    tvContentDetails.setText(snapshot.child("taskDetails").getValue().toString());
//
//                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Inflate the layout for this fragment
        return view;

    }
}