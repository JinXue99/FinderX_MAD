package com.example.finderx_mad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateNewGroup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateNewGroup extends Fragment {

    EditText etGroupName;
    EditText etDescription;
    Spinner SPNoMembers;
    Button btnPublish;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://finderx-6cd15-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("Student Group List DB");
    DatabaseReference groupRef = myRef.child("Teams");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_student_create_group, container, false);

        etGroupName = (EditText) view.findViewById(R.id.ETGroupName);
        etDescription = (EditText) view.findViewById(R.id.ETDescription);
        SPNoMembers = (Spinner) view.findViewById(R.id.SPNoMembers);
        btnPublish = (Button) view.findViewById(R.id.BtnPublish);


        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createGroup();
            }
        });

        return view;
    }

    public void createGroup(){

        String name = etGroupName.getText().toString();
        String description = etDescription.getText().toString();
        String noMembers = SPNoMembers.getSelectedItem().toString();

        GroupModel newGroup = new GroupModel(name, description, noMembers);
        groupRef.push().setValue(newGroup);
    }

}