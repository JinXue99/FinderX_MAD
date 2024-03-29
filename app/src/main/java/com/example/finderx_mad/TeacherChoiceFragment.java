package com.example.finderx_mad;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeacherChoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeacherChoiceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TeacherChoiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeacherChoiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeacherChoiceFragment newInstance(String param1, String param2) {
        TeacherChoiceFragment fragment = new TeacherChoiceFragment();
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
        return inflater.inflate(R.layout.fragment_teacher_choice, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        CardView cvTaskAssigned = view.findViewById(R.id.cvTaskAssigned);
        View.OnClickListener OCLTaskAssigned = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.DestTeacherViewTask2);
            }
        };
        cvTaskAssigned.setOnClickListener(OCLTaskAssigned);

        CardView cvGroupingList = view.findViewById(R.id.cvGroupingList);
        View.OnClickListener OCLGroupingList = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.DestTeacherViewGroup);
            }
        };
        cvGroupingList.setOnClickListener(OCLGroupingList);

        CardView cvStudentNameList = view.findViewById(R.id.cvStudentNameList);
        View.OnClickListener OCLStudentNameList = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.DestTeacherViewStudentNamelist);
            }
        };
        cvStudentNameList.setOnClickListener(OCLStudentNameList);


    }
}