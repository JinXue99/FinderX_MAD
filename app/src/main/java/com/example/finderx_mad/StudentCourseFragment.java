package com.example.finderx_mad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentCourseFragment extends Fragment {
    String Code,Name,surl;
//zero constructors

    public StudentCourseFragment(String code, String name, String surl) {
        Code = code;
        Name = name;
        this.surl = surl;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentCourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentCourseFragment newInstance(String param1, String param2) {
        StudentCourseFragment fragment = new StudentCourseFragment();
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
        View rootView=inflater.inflate(R.layout.fragment_student_course, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.addRecycleView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.addRecycleView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
//recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<StudentCourseFragment> options =
        new FirebaseRecyclerOptions.Builder<StudentCourseFragment>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Course Code"),StudentCourseFragment.class)
                .build();

        //MainAdapter mainAdapter = new MainAdapter(options);
        //recyclerView.setAdaper(mainAdapter);

    }
}