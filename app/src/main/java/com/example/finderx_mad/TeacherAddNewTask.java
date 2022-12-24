package com.example.finderx_mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class TeacherAddNewTask extends AppCompatActivity {

    EditText etTaskTitle, etTaskDetails, etDeadline;
    TextView tvDescription, tvSubmissionDeadline;
    Button btnUpload;

    String todayDate, currentTime;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_add_new_task);

        etTaskTitle = findViewById(R.id.etTaskTitle);
        etTaskDetails = findViewById(R.id.etTaskDetails);
        etDeadline = findViewById(R.id.etDeadline);
        tvDescription = findViewById(R.id.tvDescription);
        tvSubmissionDeadline = findViewById(R.id.tvSubmissionDeadline);
        btnUpload = findViewById(R.id.btnUpload);

        calendar = Calendar.getInstance();
        todayDate = calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(calendar.get(Calendar.HOUR))+ ":" + pad(calendar.get(Calendar.MINUTE));
        Log.d("Calendar", "Date and Time" + todayDate + " and " + currentTime);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskModel taskModel = new TaskModel(etTaskTitle.getText().toString(), etTaskDetails.getText().toString(), todayDate, currentTime);
                TaskDatabase db = new TaskDatabase(TeacherAddNewTask.this);
                db.AddTask(taskModel);

                Intent intent = new Intent(TeacherAddNewTask.this, TeacherViewTask.class);
                startActivity(intent);

                Toast.makeText(TeacherAddNewTask.this, "Task saved", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public String pad(int i){
        if(i < 0)
            return "0"+i;
        return  String.valueOf(i);
    }
}