package com.example.finderx_mad;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class announcementActivity extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;

    String s1[], s2[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_announcement);

        recyclerView= findViewById(R.id.AnnouncementView);

        s1 = getResources().getStringArray(R.array.lecturerName);
        s2 = getResources().getStringArray(R.array.TaskContent);
      //  s1[0] = " Dr Hafiezah";
        //s1[1] = "Dr Ang";
        //s2[0]=  " This is the Task Content";
       // s2[1]= "hello world";


        myAdapter myAdapter = new myAdapter(this,s1,s2);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
