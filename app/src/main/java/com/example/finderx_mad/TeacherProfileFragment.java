package com.example.finderx_mad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeacherProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeacherProfileFragment extends Fragment {
    //private FirebaseAuth student;
    private FirebaseUser teacher;
    private FirebaseFirestore FirebaseStore;
    private DocumentReference df;

    String TeacherID;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TeacherProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeacherProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeacherProfileFragment newInstance(String param1, String param2) {
        TeacherProfileFragment fragment = new TeacherProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_student_profile,null,false);

        TextView TVTeacherName = (TextView) view.findViewById(R.id.TVTeacherName);
        TextView TVTeacherID = (TextView) view.findViewById(R.id.TVTeacherID);
        TextView TVTeacherMajor = (TextView) view.findViewById(R.id.TVTeacherMajor);
        TextView TVTeacherPhone = (TextView) view.findViewById(R.id.TVTeacherPhone);
        TextView TVTeacherEmail =(TextView)  view.findViewById(R.id.TVTeacherEmail);
/*
        // Connect to Firebase
        teacher = FirebaseAuth.getInstance().getCurrentUser();
        TeacherID = teacher.getUid();
        this.FirebaseStore = FirebaseFirestore.getInstance();
        df = FirebaseStore.collection("User").document(TeacherID);
        df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    TVTeacherName.setText(document.getString("Name"));
                    TVTeacherID.setText(document.getString("TeacherID"));
                    TVTeacherEmail.setText(document.getString("Email"));
                    TVTeacherMajor.setText(document.getString("Majoring"));
                    TVTeacherPhone.setText(document.getString("Phone"));
                    if (document.exists()) {
                        Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });

 */
        // Inflate the layout for this fragment
        return view;
    }
}