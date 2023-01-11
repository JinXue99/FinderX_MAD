package com.example.finderx_mad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeacherAddNewTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeacherAddNewTaskFragment extends Fragment {
    private View view;
    FirebaseDatabase database;
    DatabaseReference myRef,TaskRef;

    TextView tvDeadLine;
    EditText etTaskTitle;
    EditText etTaskDetails;
    EditText etTaskDeadline;
    Calendar calendar;
    SimpleDateFormat sdf;
    String currentDateandTime;
    TaskModel task;
    String DL, courseCode;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TeacherAddNewTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeacherAddNewTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeacherAddNewTaskFragment newInstance(String param1, String param2) {
        TeacherAddNewTaskFragment fragment = new TeacherAddNewTaskFragment();
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
        view = inflater.inflate(R.layout.fragment_teacher_add_new_task,null,false);

        database = FirebaseDatabase.getInstance("https://finderx-6cd15-default-rtdb.asia-southeast1.firebasedatabase.app");
        myRef = database.getReference("Teacher");
        TaskRef = myRef.child("Course Code").child("C1").child("Occ").child("Occ 1").child("Task Assigned");


        tvDeadLine =(TextView)  view.findViewById(R.id.tvDeadLine);
        etTaskTitle =(EditText)  view.findViewById(R.id.etTaskTitle);
        etTaskDetails =(EditText)  view.findViewById(R.id.etTaskDetails);
        calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        sdf = new SimpleDateFormat("yyyy.MM.dd");
        currentDateandTime = sdf.format(new Date());
        courseCode = "WIA2001";

        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Submission Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        Button btnPickADate = (Button) view.findViewById(R.id.btnPickADate);
        btnPickADate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "Tag Picker");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        DL = materialDatePicker.getHeaderText();
                        tvDeadLine.setText(DL);
                    }
                });

            }
        });

        Button btnUpload = (Button) view.findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etTaskTitle.getText().toString().isEmpty()) {
                    etTaskTitle.setError("Task Title is required!");
                    return;
                }
                if (etTaskDetails.getText().toString().isEmpty()) {
                    etTaskDetails.setError("Task Description is required!");
                    return;
                }
                if (tvDeadLine.getText().toString().equals("MMM DD, YYYY")){
                    tvDeadLine.setError("Deadline is required!");
                    return;

                }
                AddNewTask(task);

            }
        });

        return view;
    }

    public void AddNewTask(TaskModel task){
        TaskModel aTask = new TaskModel(
                etTaskTitle.getText().toString(),
                etTaskDetails.getText().toString(),
                DL,
                currentDateandTime,
                courseCode
        );

        TaskRef.child(aTask.getTaskTitle()).setValue(aTask)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext().getApplicationContext(),"Task is uploaded", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext().getApplicationContext(),"Unsuccessful to upload task", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}