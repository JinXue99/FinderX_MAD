package com.example.finderx_mad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class StudentGroup2007Fragment extends Fragment {

    RecyclerView recview;
    SGroupListMADAdapter adapter;
    ArrayList<SGroupListMAD> list;
    FirebaseDatabase database;
    DatabaseReference myRef;
    SGroupListMAD viewGroup;

    SearchView searchView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public StudentGroup2007Fragment() {
        // Required empty public constructor
    }


    public static StudentGroup2007Fragment newInstance(String param1, String param2) {
        StudentGroup2007Fragment fragment = new StudentGroup2007Fragment();
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

    private void filter(String newText) {
        ArrayList<SGroupListMAD>filteredList = new ArrayList<>();
        for(SGroupListMAD item : list){
            if(item.getTName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_group2007, null, false);
        recview = (RecyclerView) view.findViewById(R.id.RVGList2007);

        //Firebase
        database = FirebaseDatabase.getInstance("https://finderx-6cd15-default-rtdb.asia-southeast1.firebasedatabase.app/");
        myRef = database.getReference("Student Group List MAD").child("Teams");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recview.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        adapter = new SGroupListMADAdapter(getContext().getApplicationContext(),list);
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

        searchView=view.findViewById(R.id.search2007);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        return view;
    }
}