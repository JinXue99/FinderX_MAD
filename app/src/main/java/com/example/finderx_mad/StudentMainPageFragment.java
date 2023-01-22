package com.example.finderx_mad;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class StudentMainPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentMainPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentMainPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentMainPageFragment newInstance(String param1, String param2) {
        StudentMainPageFragment fragment = new StudentMainPageFragment();
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
        return inflater.inflate(R.layout.fragment_student_main_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ImageView ivAcc = view.findViewById(R.id.ivAcc);
        View.OnClickListener OCLAcc = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.DestStudentProfile);
            }
        };
        ivAcc.setOnClickListener(OCLAcc);

        CardView cvCourse = view.findViewById(R.id.cvCourse);
        View.OnClickListener OCLCourse = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.DestStudentCourse);
            }
        };
        cvCourse.setOnClickListener(OCLCourse);

        CardView cvGroupChat = view.findViewById(R.id.cvGroupChat);
        View.OnClickListener OCLGroupChat = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.DestStudentGroupChat);
            }
        };
        cvGroupChat.setOnClickListener(OCLGroupChat);

        CardView cvAnnouncement = view.findViewById(R.id.cvAnnouncement);
        View.OnClickListener OCLAnnouncement = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.DestStudentAnnouncement);
            }
        };
        cvAnnouncement.setOnClickListener(OCLAnnouncement);
    }
       // CardView cvViewRequest = view.findViewById(R.id.cvViewRequest);
//        View.OnClickListener OCLViewRequest = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.DestStudentViewRequest);
//            }
//        };
//        cvViewRequest.setOnClickListener(OCLViewRequest);
//    }


}