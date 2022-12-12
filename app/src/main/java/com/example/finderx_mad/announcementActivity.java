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

        myAdapter myAdapter = new myAdapter(this,s1,s2);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
