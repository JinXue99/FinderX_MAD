package com.example.finderx_mad;

import android.app.Dialog;
import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

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

    //private FirebaseAuth teacher;
    private FirebaseUser teacher;
    String TeacherID;

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

        // Connect to Firebase
        teacher = FirebaseAuth.getInstance().getCurrentUser();
        TeacherID = teacher.getUid();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Teachers").child(TeacherID).child("C1").child("Occ").child("Occ A").child("Task Assigned");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        adapter = new TaskViewTeacherAdapter(getContext().getApplicationContext(), list);
        recyclerView.setAdapter(adapter);

        myRef.addValueEventListener(new ValueEventListener() {
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

        adapter.setOnItemClickListener(new TaskViewTeacherAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                teacherViewTask = list.get(position);
                //Delete the item from Firebase
                myRef.child(teacherViewTask.getTaskTitle()).removeValue();
                //Delete the item from the RecyclerView's data source
                list.remove(position);
                //Notify the adapter that the item has been removed
                adapter.notifyItemRemoved(position);
            }
        });
        adapter.setOnItemClickListenerEDIT(new TaskViewTeacherAdapter.OnItemClickListenerEDIT() {
            @Override
            public void onItemClick(int position) {
                final Dialog descDialog = new Dialog(getContext());
                //Add A title in hte custom layout
                descDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                //The user able to cancel the dialog by clicking anywhere outside the dialog
                descDialog.setCancelable(true);
                //Mention the name of the layout of your custom dialog
                descDialog.setContentView(R.layout.teacher_edit_task_dialog);


                teacherViewTask = list.get(position);
                //initializing the views of the dialog
                final TextView TVTaskTitle = descDialog.findViewById(R.id.TVTaskTitle);
                DatabaseReference dbDesc = myRef.child(teacherViewTask.getTaskTitle()).child("taskTitle");
                dbDesc.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot snapshot = task.getResult();
                        // initial description
                        TVTaskTitle.setText(snapshot.getValue().toString());
                    }
                });

                final EditText ETTaskDesc = descDialog.findViewById(R.id.ETTaskDesc);
                dbDesc = myRef.child(teacherViewTask.getTaskTitle()).child("taskDetails");
                dbDesc.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot snapshot = task.getResult();
                        // initial description
                        ETTaskDesc.setText(snapshot.getValue().toString());
                    }
                });

                final TextView tvDeadLine = descDialog.findViewById(R.id.tvDeadLine);
                dbDesc = myRef.child(teacherViewTask.getTaskTitle()).child("taskDeadline");
                dbDesc.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot snapshot = task.getResult();
                        // initial description
                        tvDeadLine.setText(snapshot.getValue().toString());
                    }
                });

                // create the calendar constraint builder
                CalendarConstraints.Builder calendarConstraintBuilder = new CalendarConstraints.Builder();
                // set the validator point forward from current day
                // this mean the all the dates before the current day are blocked
                calendarConstraintBuilder.setValidator(DateValidatorPointForward.now());

                // instantiate the Material date picker dialog builder
                MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select Submission Date")
                        //now pass the constrained calendar builder to
                        //material date picker Calendar constraints
                        .setCalendarConstraints(calendarConstraintBuilder.build())
                        .build();

                Button btnPickDeadline = descDialog.findViewById(R.id.btnPickDeadline);
                btnPickDeadline.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        materialDatePicker.show(getActivity().getSupportFragmentManager(), "Tag Picker");
                        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                            @Override
                            public void onPositiveButtonClick(Object selection) {
                               String DeadLine = materialDatePicker.getHeaderText();
                                tvDeadLine.setText(DeadLine);
                            }
                        });

                    }
                });

                Button btnEdit = descDialog.findViewById(R.id.btnEdit);
                btnEdit.setOnClickListener((v -> {
                    String taskDetails = ETTaskDesc.getText().toString();
                    String taskDeadline = tvDeadLine.getText().toString();
                    myRef.child(teacherViewTask.getTaskTitle()).child("taskDetails").setValue(taskDetails);
                    myRef.child(teacherViewTask.getTaskTitle()).child("taskDeadline").setValue(taskDeadline);

                    myRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                        @Override
                        public void onSuccess(DataSnapshot dataSnapshot) {
                            Toast.makeText(getContext().getApplicationContext(), "Edit Successfully!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext().getApplicationContext(), "Fail!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    descDialog.dismiss();
                }));
                descDialog.show();

            }
        });

        ImageView ivAddTaskButton = (ImageView) view.findViewById(R.id.ivAddTaskButton);
        ivAddTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.DestTeacherAddNewTask);

            }
        });

        return view;
    }
}