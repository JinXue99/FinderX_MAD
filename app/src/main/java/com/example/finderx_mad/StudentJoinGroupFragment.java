package com.example.finderx_mad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentJoinGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentJoinGroupFragment extends Fragment {

    RecyclerView recview;
    SGroupListAdapter2 adapter;
    ArrayList<SGroupListMAD> list;//,sortList;
    FirebaseDatabase database;
    DatabaseReference myRef;
    SGroupListMAD viewGroup;

    Button btnJoinGroup,btnDecline;
    String CurrentState = "new";
    String tm1;

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
        recview = (RecyclerView) view.findViewById(R.id.RVmembers);

//        String userEmail = getArguments().getString("etUsernameEmail");

        database = FirebaseDatabase.getInstance("https://finderx-6cd15-default-rtdb.asia-southeast1.firebasedatabase.app/");
        myRef = database.getReference("Student Group List MAD").child("Teams");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recview.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        adapter = new SGroupListAdapter2(getContext().getApplicationContext(), list);
        recview.setAdapter(adapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    viewGroup = dataSnapshot.getValue(SGroupListMAD.class);
                    list.add(viewGroup);
                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*btnJoinGroup.setOnClickListener((view1) -> {PerformAction(userEmail);});
        CheckUserExistence(userEmail);*/


        btnJoinGroup = (Button) view.findViewById(R.id.btnJoinGroup);
        btnDecline = (Button) view.findViewById(R.id.btnDecline);

        /*btnJoinGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAction(userEmail);
            }
        });*/
        return view;
    }

    //ViewRequest
    /*private void CheckUserExistence(String userEmail) {
        myRef.child("T1").child("Members").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    CurrentState = "Member";
                    btnJoinGroup.setText("View Group");
                    btnDecline.setText("Left Group");
                    btnDecline.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("Members").child("T1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    CurrentState = "Member";
                    btnJoinGroup.setText("View Group");
                    btnDecline.setText("Left Group");
                    btnDecline.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef.child(myId).child(nuserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    if(snapshot.child("status").getValue().toString().equals("pending")){
                        CurrentState = "I_sent_pending";
                        btnJoinGroup.setText("Leave Group");
                        btnDecline.setVisibility(View.GONE);
                    }
                    if(snapshot.child("status").getValue().toString().equals("decline")){
                        CurrentState = "I_sent_decline";
                        btnJoinGroup.setText("Leave Group");
                        btnDecline.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef.child(userId).child(myId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    if(snapshot.child("status").getValue().toString().equals("pending")){
                        CurrentState="he_sent_pending";
                        btnJoinGroup.setText("Accept Join Group Request");
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
            btnJoinGroup.setText("Send Join Group Request");
            btnDecline.setVisibility(View.GONE);
        }

    }
    //View Request

    //Send Request
    private void PerformAction(String userEmail) {
        if(CurrentState.equals("new")){
            HashMap hashMap = new HashMap();
            hashMap.put("status","pending");
            myRef.child("T1").child("Members").updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getActivity(), "You have sent Join Request", Toast.LENGTH_SHORT).show();
                        btnDecline.setVisibility(View.GONE);
                        CurrentState="I_sent_pending";
                        btnJoinGroup.setText("Cancel Join Request");
                    }else{
                        Toast.makeText(getActivity()," "+ task.getException().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(CurrentState.equals("I_sent_pending")||CurrentState.equals("I_sent_decline")){
            myRef.child("T1").child("Members").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getActivity(),"You have cancelled Join Request",Toast.LENGTH_SHORT).show();
                        CurrentState = "nothing";
                        btnJoinGroup.setText("Send Join Request");
                        btnDecline.setVisibility(View.GONE);
                    }else{
                        Toast.makeText(getActivity()," "+ task.getException().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(CurrentState.equals("he_sent_pending")){
            myRef.child("T1").child("Members").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        HashMap hashMap = new HashMap();
                        hashMap.put("status","member");
                        hashMap.put("TName",tm1);
                        myRef.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if(task.isSuccessful()){
                                    myRef.child("T1").child("Members").updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {
                                            Toast.makeText(getActivity(),"You accept this member",Toast.LENGTH_SHORT).show();
                                            CurrentState = "Member";
                                            btnJoinGroup.setText("View Group");
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
    }*/
}