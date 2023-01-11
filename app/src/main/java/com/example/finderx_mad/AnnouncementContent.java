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
import androidx.fragment.app.FragmentManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AnnouncementContent extends Fragment {

    ArrayList<AnnouncementUser> list;
//    myAnnouncementcontentAdapter myAdapter;
//    AnnouncementUser studentViewTask;
    TextView tvContentCourseCode,tvContentTaskTitle, tvContentDeadline, tvContentDetails;
//    private FragmentManager fragmentManager;
//    AnnouncementContent fragment = new AnnouncementContent();


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


    public static AnnouncementContent newInstance(String param1, String param2) {
        AnnouncementContent fragment = new AnnouncementContent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    };

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
        Bundle bundle = getArguments();
        if(bundle != null) {
            String coursecode = bundle.getString("course");
            String tasktitle = bundle.getString("title");
            String deadline = bundle.getString("deadline");
            String taskdetails = bundle.getString("details");

            tvContentCourseCode.setText(coursecode);
            tvContentTaskTitle.setText(tasktitle);
            tvContentDeadline.setText(deadline);
            tvContentDetails.setText(taskdetails);

        }

        tvContentCourseCode = view.findViewById(R.id.tvContentCourseCode);
        tvContentTaskTitle = view.findViewById(R.id.tvContentTaskTitle);
        tvContentDeadline = view.findViewById(R.id.tvContentDeadline);
        tvContentDetails = view.findViewById(R.id.tvContentDetails);

//        fragmentManager.beginTransaction().replace(R.id.DestStudentAnnouncement, fragment).commitNow();



//        Bundle bundle = getArguments();
//        if(bundle != null) {
////            tvContentCourseCode.setText(bundle.getString("course"));
////            tvContentTaskTitle.setText(bundle.getString("title"));
////            tvContentDeadline.setText(bundle.getString("deadline"));
////            tvContentDetails.setText(bundle.getString("details"));
//
//            String coursecode = bundle.getString("course");
//            tvContentCourseCode.setText(coursecode);
//            String tasktitle = bundle.getString("title");
//            tvContentTaskTitle.setText(tasktitle);
//            String deadline = bundle.getString("deadline");
//            tvContentDeadline.setText(deadline);
//            String taskdetails = bundle.getString("details");
//            tvContentDetails.setText(taskdetails);
//        }



//        database = FirebaseDatabase.getInstance("https://finderx-6cd15-default-rtdb.asia-southeast1.firebasedatabase.app");
//        myRef = database.getReference("Teacher");
//        TaskDetailsRef = myRef.child("Course Code").child("C1").child("Occ").child("Occ 1").child("Task Assigned");
//
//        tvContentCourseCode = (TextView) view.findViewById(R.id.tvContentCourseCode);
//        tvContentTaskTitle = (TextView) view.findViewById(R.id.tvContentTaskTitle);
//        tvContentDeadline = (TextView) view.findViewById(R.id.tvContentDeadline);
//        tvContentDetails = (TextView) view.findViewById(R.id.tvContentDetails);
//
//        list = new ArrayList<>();
//        myAdapter = new myAnnouncementcontentAdapter(this.getContext(), list);

//        TaskDetailsRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                tvContentCourseCode.setText(dataSnapshot.child("courseCode").getValue().toString());
//                tvContentTaskTitle.setText(dataSnapshot.child("taskTitle").getValue().toString());
//                tvContentDeadline.setText(dataSnapshot.child("taskDeadline").getValue().toString());
//                tvContentDetails.setText(dataSnapshot.child("taskDetails").getValue().toString());
//                }
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });

        // Inflate the layout for this fragment
        return view;

    }
}