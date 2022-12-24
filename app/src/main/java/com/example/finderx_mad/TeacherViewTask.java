package com.example.finderx_mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class TeacherViewTask extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    List<TaskModel> taskModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_view_task);
        ImageView ivAddTaskButton = findViewById(R.id.ivAddTaskButton);

        recyclerView = findViewById(R.id.addRecycleView);

        TaskDatabase taskDatabase = new TaskDatabase(this);
        taskModelList = taskDatabase.getTask();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this, taskModelList);
        recyclerView.setAdapter(adapter);
    }

    public void BtnAddTaskOnClicked (View v){
        Intent AddTaskIntent = new Intent(this, TeacherAddNewTask.class);
        startActivity(AddTaskIntent);
    }
}