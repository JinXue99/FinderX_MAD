package com.example.finderx_mad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentGroupChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentGroupChatFragment extends Fragment {
    private View view;
    private ListView groupChatView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_group_chat = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference myRef,GroupRef;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentGroupChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentGroupChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentGroupChatFragment newInstance(String param1, String param2) {
        StudentGroupChatFragment fragment = new StudentGroupChatFragment();
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
        //Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student_group_chat,null,false);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        //GroupRef = myRef.child("Group");
        InitializeField();
        RetrieveAndDisplayGroups();

        //onItemClickListenerMethod of groupChatView item
        groupChatView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String currentGroupName = adapterView.getItemAtPosition(position).toString();

                Intent groupChatIntent = new Intent(getContext().getApplicationContext(),StudentChat.class);
                groupChatIntent.putExtra("groupName",currentGroupName);
                startActivity(groupChatIntent);
            }
        });


        //Create Group OnClickListener

//        Button tentative = (Button) view.findViewById(R.id.TentativeGroupChat);
//        tentative.setOnClickListener(new View.OnClickListener() {
//            @Override
//          public void onClick(View v) {
//               CreateGroup();
//        }
//     });

        return view;
    }

    //Initialize Field
    private void InitializeField() {
        groupChatView = (ListView) view.findViewById(R.id.group_chat_view);
        arrayAdapter = new ArrayAdapter<String>(getContext().getApplicationContext(), android.R.layout.simple_list_item_1,list_of_group_chat);
        groupChatView.setAdapter(arrayAdapter);
    }

//    // Create Group Analog
//    private void CreateGroup( ) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle("Enter Group Name");
//        final EditText groupNameField = new EditText(getContext().getApplicationContext());
//        groupNameField.setHint("e.g. FinderX");
//        builder.setView(groupNameField);
//        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String groupName = groupNameField.getText().toString();
//                if(TextUtils.isEmpty(groupName)){
//                    Toast.makeText(getContext().getApplicationContext(), "Please Enter Your GroupName", Toast.LENGTH_SHORT).show();
//                }else{
//                    CreateYourGroup(groupName);
//                }
//
//            }
//        });
//
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//        builder.show();
//    }


    //Print the Group List
    private void RetrieveAndDisplayGroups() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userID =  mAuth.getCurrentUser().getUid();
        DatabaseReference userRef = database.getReference().child("Users").child(userID).child("Email");
        DatabaseReference tm1Ref = database.getReference().child("Student Group List MAD").child("Teams");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    tm1Ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot1) {

//                            DataSnapshot snapshot3 = (DataSnapshot) snapshot1.getChildren();

                            for (DataSnapshot snapshot2 : snapshot1.getChildren()) {
                                for (DataSnapshot snapshot3 : snapshot2.getChildren()) {
                                    if (snapshot3.getValue().toString().equals(snapshot.getValue().toString())) {
                                        myRef.child("Group").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                Set<String> set = new HashSet<>();
//                                                Iterator iterator = snapshot.getChildren().iterator();
//                                                while (iterator.hasNext()) {
                                                    //set.add(((DataSnapshot) iterator.next()).getKey());

                                                    set.add(snapshot2.getKey());
//                                                }
                                                //list_of_group_chat.clear();
                                                list_of_group_chat.addAll(set);
                                                arrayAdapter.notifyDataSetChanged();

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });
                                    }
                                }

                            }
                        }
//                            if(snapshot1.child("tm1").getValue().toString().equals(snapshot.getValue().toString())||
//                                    snapshot1.child("tm2").getValue().toString().equals(snapshot.getValue().toString())||
//                                    snapshot1.child("tm3").getValue().toString().equals(snapshot.getValue().toString())||
//                                    snapshot1.child("tm4").getValue().toString().equals(snapshot.getValue().toString())||
//                                    snapshot1.child("tm5").getValue().toString().equals(snapshot.getValue().toString())){
//                                myRef.child("Group").addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                        Set<String> set = new HashSet<>();
//                                        Iterator iterator = snapshot.getChildren().iterator();
//                                        while(iterator.hasNext()){
//                                            set.add(((DataSnapshot)iterator.next()).getKey());
//                                        }
//                                        list_of_group_chat.clear();
//                                        list_of_group_chat.addAll(set);
//                                        arrayAdapter.notifyDataSetChanged();
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) { }
//                                });
//                            }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        }

//            myRef.child("Group").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    Set<String> set = new HashSet<>();
//                    Iterator iterator = snapshot.getChildren().iterator();
//                    while(iterator.hasNext()){
//                        set.add(((DataSnapshot)iterator.next()).getKey());
//                    }
//                    list_of_group_chat.clear();
//                    list_of_group_chat.addAll(set);
//                    arrayAdapter.notifyDataSetChanged();
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) { }
//            });
//        }

    }


//    //Save the Created GroupName into FirebaseDatabase
//   private void CreateYourGroup(String groupName) {
//        myRef.child("Group").child(groupName).setValue("")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(getContext().getApplicationContext(),groupName+ " is created", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
