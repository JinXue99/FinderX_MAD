package com.example.finderx_mad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentJoinGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentJoinGroupFragment extends Fragment {

    String username,groupname,CurrentState="new",StudentID;
    Button btnPerform,btnDecline;
    TextView UserName,GroupName;

    FirebaseDatabase database;
    DatabaseReference studentRef,requestRef,friendRef;
    FirebaseAuth mAuth;
    FirebaseUser student;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentJoinGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentJoinGroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentJoinGroupFragment newInstance(String param1, String param2) {
        StudentJoinGroupFragment fragment = new StudentJoinGroupFragment();
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
        View view = inflater.inflate(R.layout.fragment_student_join_group, null, false);

        student = FirebaseAuth.getInstance().getCurrentUser();
        StudentID = student.getUid();
        database = FirebaseDatabase.getInstance();

        // Realtime Database Reference
        studentRef = database.getReference("Users").child(StudentID);
        requestRef = FirebaseDatabase.getInstance().getReference().child("Requests");
        friendRef = FirebaseDatabase.getInstance().getReference().child("Friends");
        mAuth = FirebaseAuth.getInstance();

        UserName = (TextView) view.findViewById(R.id.username);
        GroupName = (TextView) view.findViewById(R.id.groupname);

        LoadUser();
        btnPerform.setOnClickListener((view1 -> {PerformAction(StudentID);}));
        CheckUserExistence(StudentID);
        return view;
    }

    private void LoadUser(){
        studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    username = snapshot.child("Name").getValue().toString();
                    groupname = snapshot.child("Email").getValue().toString();
                }else{
                    Toast.makeText(getActivity(), "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "" + error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //View Request
    private void CheckUserExistence(String StudentID){
        friendRef.child(student.getUid()).child(StudentID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    CurrentState = "Member";
                    btnPerform.setText("You are Member");
                    btnDecline.setText("Being Rejected");
                    btnDecline.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        requestRef.child(student.getUid()).child(StudentID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.exists()){
            if(snapshot.child("status").getValue().toString().equals("pending")){
                CurrentState = "I_sent_pending";
                btnPerform.setText("Leave Group");
                btnDecline.setVisibility(View.GONE);
            }
            if(snapshot.child("status").getValue().toString().equals("decline")){
                CurrentState = "I_sent_decline";
                btnPerform.setText("Leave Group");
                btnDecline.setVisibility(View.GONE);
            }
        }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        requestRef.child(StudentID).child(student.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    if(snapshot.child("status").getValue().toString().equals("pending")){
                        CurrentState="he_sent_pending";
                        btnPerform.setText("Accept Join Group Request");
                        btnDecline.setText(("Decline Request"));
                        btnDecline.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if(CurrentState.equals("new")){
            CurrentState = "new";
            btnPerform.setText("Send Join Group Request");
            btnDecline.setVisibility(View.GONE);
        }
    }

    //Send Request
    private void PerformAction(String StudentID) {
        if(CurrentState.equals("new")){
            HashMap hashMap = new HashMap();
            hashMap.put("status","pending");
            requestRef.child(student.getUid()).child(StudentID).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getActivity(), "You have sent Join Request", Toast.LENGTH_SHORT).show();
                        btnDecline.setVisibility(View.GONE);
                        CurrentState="I_sent_pending";
                        btnPerform.setText("Cancel Join Request");
                    }else{
                        Toast.makeText(getActivity()," "+ task.getException().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(CurrentState.equals("I_sent_pending")||CurrentState.equals("I_sent_decline")){
            requestRef.child(student.getUid()).child(StudentID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getActivity(),"You have cancelled Join Request",Toast.LENGTH_SHORT).show();
                        CurrentState = "nothing";
                        btnPerform.setText("Send Join Request");
                        btnDecline.setVisibility(View.GONE);
                    }else{
                        Toast.makeText(getActivity()," "+ task.getException().toString(),Toast.LENGTH_SHORT).show();
                    }
            };
        })
        ;if(CurrentState.equals("he_sent_pending")){
            requestRef.child(student.getUid()).child(StudentID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        HashMap hashMap = new HashMap();
                        hashMap.put("status","member");
                        hashMap.put("TName",username);
                        studentRef.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if(task.isSuccessful()){
                                    friendRef.child(student.getUid()).child(StudentID).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {
                                            Toast.makeText(getActivity(),"You accept this member",Toast.LENGTH_SHORT).show();
                                            CurrentState = "Member";
                                            btnPerform.setText("View Group");
                                            btnDecline.setText("Left Group");
                                            btnDecline.setVisibility(View.VISIBLE);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
        }
        if(CurrentState.equals("friend")){
            //
        }
    }


}}