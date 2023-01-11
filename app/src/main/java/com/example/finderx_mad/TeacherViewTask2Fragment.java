package com.example.finderx_mad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
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
 * Use the {@link TeacherViewTask2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeacherViewTask2Fragment extends Fragment {

    RecyclerView recyclerView;
    TaskViewTeacherAdapter adapter;
    ArrayList<TaskModel> list;
//    List<TaskModel> taskModelList;
    FirebaseDatabase database;
    DatabaseReference myRef,TaskRef;
    TaskModel teacherViewTask;

    SearchView searchView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TeacherViewTask2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeacherViewTask2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeacherViewTask2Fragment newInstance(String param1, String param2) {
        TeacherViewTask2Fragment fragment = new TeacherViewTask2Fragment();
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
        View view = inflater.inflate(R.layout.fragment_teacher_view_task2,null,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.teacherViewTaskRecycleView);

        database = FirebaseDatabase.getInstance("https://finderx-6cd15-default-rtdb.asia-southeast1.firebasedatabase.app");
        myRef = database.getReference("Teacher");
        TaskRef = myRef.child("Course Code").child("C1").child("Occ").child("Occ 1").child("Task Assigned");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);


        list = new ArrayList<>();
        adapter = new TaskViewTeacherAdapter(getContext().getApplicationContext(), list);
        recyclerView.setAdapter(adapter);

        TaskRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    teacherViewTask = dataSnapshot.getValue(TaskModel.class);
                    list.add(teacherViewTask);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ImageView ivAddTaskButton = (ImageView) view.findViewById(R.id.ivAddTaskButton);
        ivAddTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext().getApplicationContext(),"Add Task Button is Clicked",Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.DestTeacherAddNewTask);

            }
        });


//        searchView=view.findViewById(R.id.searchTask);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filter(newText);
//                return true;
//            }
//        });


        return view;
    }

//    private void filter(String newText) {
//        ArrayList<TaskModel>filteredList = new ArrayList<>();
//        for(TaskModel item : list){
//            if(item.getTaskTitle().toLowerCase().contains(newText.toLowerCase())){
//                filteredList.add(item);
//            }
//        }
//        adapter.filterList(filteredList);
//    }
}